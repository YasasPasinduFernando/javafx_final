package controller;

import bo.custom.CustomerBo;
import bo.custom.OrderBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import dto.tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {

    @FXML
    private JFXComboBox<String> cmbCustId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtProduct;

    @FXML
    private JFXTextArea txtFault;


    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTreeTableView<OrderTm> tblItem;

    @FXML
    private TreeTableColumn<?, ?> colCode;

    @FXML
    private TreeTableColumn<?, ?> colProduct;

    @FXML
    private TreeTableColumn<?, ?> colFault;

    @FXML
    private TreeTableColumn<?, ?> colPrice;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblOrderId;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private List<CustomerDto> customers;
    private List<ItemDto> items;
    private double total=0;
    public static String orderId;

    private CustomerBo customerBo = new CustomerBoImpl();
    private ItemDao itemDao = new ItemDaoImpl();
    private OrderBo orderBo= new OrderBoImpl();
    private ObservableList<OrderTm> tmList = FXCollections.observableArrayList();





    public void initialize(){
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colProduct.setCellValueFactory(new TreeItemPropertyValueFactory<>("product"));
        colFault.setCellValueFactory(new TreeItemPropertyValueFactory<>("fault"));
        colPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        try {
            customers = customerBo.allCustomers();
            items = itemDao.allItems();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        loadCustomerContact();
        loadItemCodes();

        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CustomerDto dto:customers) {
                if (dto.getId().equals(newValue.toString())){
                    txtContact.setText(dto.getContactNumber());
                    txtCustName.setText(dto.getName());

//                    loadItemCodesForCustomer(dto.getContactNumber());


                }
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (ItemDto dto:items) {
                if (dto.getCode().equals(newValue.toString())){
                    txtProduct.setText(dto.getName());
                }
            }
        });

        setOrderId();
    }


    private void setOrderId() {
        try {
            lblOrderId.setText(orderBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        ObservableList list = FXCollections.observableArrayList();

        for (ItemDto dto:items) {
            list.add(dto.getCode());
        }

        cmbItemCode.setItems(list);
    }

    private void loadCustomerContact() {
        ObservableList list = FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getId());
        }

        cmbCustId.setItems(list);
    }


    public void placeOrderButtonOnAction(ActionEvent event) {
        if (cmbCustId.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a customer before placing the order.").show();
            return;
        }
        List<OrderDetailDto> list = new ArrayList<>();
        for (OrderTm tm:tmList) {
            list.add(new OrderDetailDto(
                    lblOrderId.getText(),
                    tm.getCode(),
                    tm.getProduct(),
                    tm.getFault(),
                    tm.getPrice()

            ));
        }

        OrderDto dto = new OrderDto(
                lblOrderId.getText(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")),
                cmbCustId.getValue().toString(),
                list
        );


        try {
            boolean isSaved = orderBo.saveOrder(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
                setOrderId();
                orderId = lblOrderId.getText();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        }


//    private void clearFields() {
//        cmbCustId.getSelectionModel().clearSelection();
//        cmbItemCode.getSelectionModel().clearSelection();
//        txtCustName.clear();
//        txtContact.clear();
//        txtProduct.clear();
//        txtFault.clear();
//        tmList.clear();
//        lblTotal.setText("0.00");
//        tblItem.setRoot(null);
//
//    }


    public void addToCartButtonOnAction(ActionEvent event) {
        JFXButton btn = new JFXButton("Delete");



        OrderTm tm = new OrderTm(
                cmbItemCode.getValue().toString(),
                txtProduct.getText(),
                txtFault.getText(),
                Double.parseDouble(txtPrice.getText()),
                btn
                        );
        btn.setOnAction(actionEvent -> {
            tmList.remove(tm);
            total-=tm.getPrice();
            lblTotal.setText(String.format("%.2f",total));
            tblItem.refresh();
        });
        boolean isExist = false;
        for (OrderTm order:tmList) {
            if (order.getCode().equals(tm.getCode())){
//                order.setQty(order.getQty()+tm.getQty());
                order.setPrice(order.getPrice()+tm.getPrice());
                isExist = true;
                total+= tm.getPrice();
            }
        }
        if (!isExist){
            tmList.add(tm);
            total+=tm.getPrice();
        }

        lblTotal.setText(String.format("%.2f",total));

        TreeItem treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        tblItem.setRoot(treeItem);
        tblItem.setShowRoot(false);
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) tblItem.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


