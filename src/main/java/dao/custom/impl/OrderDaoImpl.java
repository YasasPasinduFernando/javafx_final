package dao.custom.impl;

import dao.custom.OrderDao;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.OrderDetailDto;
import dto.OrderDto;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Orders order = new Orders(
                dto.getOrderId(),
                dto.getDate(),
                dto.getCustId()
        );
        order.setCustomer(session.find(Customer.class,dto.getCustId()));
        session.save(order);

        List<OrderDetailDto> list = dto.getList(); //dto type

        for (OrderDetailDto detailDto:list) {
            OrderDetail orderDetail = new OrderDetail(
                    new OrderDetailsKey(detailDto.getOrderId(), detailDto.getItemCode()),
                    session.find(Item.class, detailDto.getItemCode()),
                    order,
                    detailDto.getProduct(),
                    detailDto.getFault(),
                    detailDto.getPrice()
            );
            session.save(orderDetail);
        }

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        List<OrderDto> resultList = new ArrayList<>();

        try (Session session = HibernateUtil.getSession()) {
            Query<Orders> query = session.createQuery("FROM Orders", Orders.class);
            List<Orders> orderList = query.list();

            for (Orders order : orderList) {
                OrderDto orderDto = new OrderDto(
                        order.getOrderId(),
                        order.getDate(),
                        order.getCustId(),

                        getOrderDetailsDtoList(order.getOrderDetails())
                );

                resultList.add(orderDto);
            }
        } catch (Exception e) {
            // Handle exceptions or log them
            e.printStackTrace();
        }

        return resultList;
    }
    private List<OrderDetailDto> getOrderDetailsDtoList(List<OrderDetail> orderDetails) {
        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailsKey orderDetailsKey = orderDetail.getId();

            OrderDetailDto orderDetailDto = new OrderDetailDto(
                    orderDetailsKey.getOrderId(),
                    orderDetailsKey.getItemCode(),
                    orderDetail.getProduct(),
                    orderDetail.getFault(),
                    orderDetail.getPrice()
            );

            orderDetailDtoList.add(orderDetailDto);
        }

        return orderDetailDtoList;
    }

    @Override
    public OrderDto getLastOrder() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    null
            );
        }
        return null;    }

    @Override
    public boolean deleteOrdersByItemCode(String itmCode) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // HQL query to delete orders by item code
            String hql = "DELETE FROM OrderDetail od WHERE od.item.code = :itemCode";
            int deletedCount = session.createQuery(hql)
                    .setParameter("itemCode", itmCode)
                    .executeUpdate();

            transaction.commit();
            return deletedCount > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new SQLException("Error deleting orders by item code", e);
        } finally {
            session.close();
        }    }

    @Override
    public List<String> getItemCodesForCustomerByContactNumber(String contactNumber) throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getSession()) {
            // Implement the logic to retrieve item codes based on the customer's contact number
            // You need to write the HQL query or use any other mechanism to fetch the data

            // Example: Fetching item codes from the database using HQL
            List<String> itemCodes = session.createQuery(
                            "SELECT DISTINCT o.item.code FROM OrderEntity o WHERE o.customer.contactNumber = :contactNumber", String.class)
                    .setParameter("contactNumber", contactNumber)
                    .list();

            return itemCodes;
        }

    }
}
