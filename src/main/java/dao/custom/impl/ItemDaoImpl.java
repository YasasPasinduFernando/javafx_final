package dao.custom.impl;

import dao.custom.ItemDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;    }


    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Item.class,value));
        transaction.commit();
        session.close();
        return true;    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getSession()) {
            // You might implement logic to retrieve all items if needed
            return session.createQuery("FROM Item", Item.class).list();
        }    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<ItemDto> list = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        List<Item> items = session.createQuery("FROM Item", Item.class).list();
        for (Item item : items) {
            list.add(new ItemDto(
                    item.getCode(),
                    item.getName(),
                    item.getCategory(),
                    item.getStatus(),
                    item.getContact()
            ));
        }
        session.close();
        return list;    }


    @Override
    public CustomerDto searchItem(String id) {
        return null;
    }

    @Override
    public ItemDto getItem(String code) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Item item = session.get(Item.class, code);
        session.close();

        if (item != null) {
            return new ItemDto(
                    item.getCode(),
                    item.getName(),
                    item.getCategory(),
                    item.getStatus(),
                    item.getContact()
            );
        }
        return null;
    }

    @Override
    public boolean deleteOrdersByItemCode(String itmCode) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        // Assuming you have a One-to-Many relationship between Item and Order
//        List<Order> orders = session.createQuery("FROM Order WHERE itemCode = :itmCode", Order.class)
//                .setParameter("itmCode", itmCode)
//                .list();
//
//        for (Order order : orders) {
//            session.delete(order);
//        }

        transaction.commit();
        session.close();

        return true;    }
}
