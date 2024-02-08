package controller;

import bo.BoFactory;
import bo.custom.OrderDetailBo;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.OrderDetailDto;
import dto.tm.OrderDetailTm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class OrderDetailFormController {
    private OrderDetailBo orderDetailBo = BoFactory.getInstance().getBo(BoType.ORDER_DETAIL);

    private ObservableList<OrderDetailTm> orderDetailList = FXCollections.observableArrayList();

    public static String searchOrderId;
    private OrderDetailTm selectedOrderDetail;


    @FXML
    private JFXTreeTableView<OrderDetailTm> tblOrderDetail;

    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private TreeTableColumn<?, ?> colFault;


    @FXML
    private JFXTextField txtNewPrice;

    @FXML
    private TreeTableColumn<?, ?> colOrder;

    @FXML
    private TreeTableColumn<?, ?> colCode;

    @FXML
    private TreeTableColumn<?, ?> colProduct;

    @FXML
    private TreeTableColumn<?, ?> colPrice;

    public void initialize() {
        colOrder.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderId"));
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colProduct.setCellValueFactory(new TreeItemPropertyValueFactory<>("product"));
        colFault.setCellValueFactory(new TreeItemPropertyValueFactory<>("fault"));
        colPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("price"));

        loadOrderDetails();

        txtOrderId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                tblOrderDetail.setPredicate(new Predicate<TreeItem<OrderDetailTm>>() {
                    @Override
                    public boolean test(TreeItem<OrderDetailTm> treeItem) {
                        return treeItem.getValue().getOrderId().contains(newValue);
                    }
                });
            }
        });

        tblOrderDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedOrderDetail = newValue.getValue();
        });

    }

    private void loadOrderDetails() {
        try {

//            List<OrderDetailDto> dtoList;
//            if (searchOrderId != null && !searchOrderId.isEmpty()) {
//                dtoList = orderDetailBo.getOrderDetailsByOrderId(searchOrderId);
//            } else {
//                dtoList = orderDetailBo.allOrderDetails();
//            }
            List<OrderDetailDto> dtoList = orderDetailBo.allOrderDetails();
            orderDetailList.clear(); // Clear existing data
            orderDetailList.addAll(mapToOrderDetailsTm(dtoList));

            TreeItem<OrderDetailTm> root = new RecursiveTreeItem<>(orderDetailList, RecursiveTreeObject::getChildren);
            tblOrderDetail.setRoot(root);
            tblOrderDetail.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<OrderDetailTm> mapToOrderDetailsTm(List<OrderDetailDto> orderDetails) {
        ObservableList<OrderDetailTm> tmList = FXCollections.observableArrayList();
        for (OrderDetailDto dto : orderDetails) {
            if (dto.getOrderId() == null || dto.getItemCode() == null) {
                System.out.println("Null values found in OrderDetailDto: " + dto);
                continue;
            }
            if (dto.getOrderId() == null || dto.getItemCode() == null) {
                System.out.println("Null values found in OrderDetailDto: " + dto);
                continue;
            }
            tmList.add(new OrderDetailTm(
                    dto.getOrderId(),
                    dto.getItemCode(),
                    dto.getProduct(),
                    dto.getFault(),
                    dto.getPrice()
            ));
        }
        return tmList;
    }


    public void searchButtonOnAction(javafx.event.ActionEvent event) {
//        String searchResultsFXML = "/view/OrdersForm.fxml";
//        searchOrderId = txtOrderId.getText();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(searchResultsFXML));
//            Stage stage = new Stage();
//            stage.setScene(new Scene(loader.load()));
//
//            stage.show();
//
//            Stage currentStage = (Stage) tblOrderDetail.getScene().getWindow();
//            currentStage.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void updateButtonOnAction(javafx.event.ActionEvent event) {
        if (selectedOrderDetail != null) {
            try {
                double additionalPrice = Double.parseDouble(txtNewPrice.getText());
                selectedOrderDetail.setPrice(selectedOrderDetail.getPrice() + additionalPrice);
                refreshTableRow(selectedOrderDetail);
            } catch (NumberFormatException e) {
                // Handle the case where the input for additional price is not a valid number
                e.printStackTrace();
            }
        } else {
        }
    }

    private void refreshTableRow(OrderDetailTm updatedOrderDetail) {
        int index = orderDetailList.indexOf(updatedOrderDetail);
        if (index != -1) {
            orderDetailList.set(index, updatedOrderDetail);
            tblOrderDetail.refresh();
        }
    }

//    private void refreshTable() throws SQLException, ClassNotFoundException {
//        orderDetailList.clear();
//        orderDetailList.addAll(mapToOrderDetailsTm(orderDetailBo.allOrderDetails()));
//        tblOrderDetail.refresh();
//    }




    public void backButtonOnAction(javafx.event.ActionEvent event) {
        Stage stage = (Stage) tblOrderDetail.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"))));
            stage.setTitle("Orders Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


