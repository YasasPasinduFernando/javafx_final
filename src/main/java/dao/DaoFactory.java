package dao;

import dao.custom.impl.*;
import dao.util.DaoType;

import static com.sun.java.accessibility.util.EventID.ITEM;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory!=null? daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case CUSTOMER: return(T) new CustomerDaoImpl();
            case ITEM: return(T) new ItemDaoImpl();
            case ORDER_DETAIL: return (T) new OrderDetailDaoImpl();
            case ORDER: return(T) new OrderDaoImpl();
            case USERS: return(T) new UsersDaoImpl();

        }
        return null;
    }
}
