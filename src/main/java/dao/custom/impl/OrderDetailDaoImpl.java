package dao.custom.impl;

import dao.custom.OrderDetailDao;
import dao.util.HibernateUtil;
import dto.OrderDetailDto;
import entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM OrderDetail ");
        List<OrderDetail> list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean saveOrderDetails(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDetailDto> getOrderDetailsByOrderId(String orderId) throws SQLException {
        return null;
    }
}
