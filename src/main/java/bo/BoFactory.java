package bo;

import bo.custom.impl.*;
import dao.util.BoType;

import static com.sun.java.accessibility.util.EventID.ITEM;
import static dao.util.DaoType.CUSTOMER;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case CUSTOMER: return (T) new CustomerBoImpl();
            case ITEM: return (T) new ItemBoImpl();
            case ORDER_DETAIL: return (T) new OrderDetailBoImpl();
            case ORDER:return (T) new OrderBoImpl();
            case USERS:return (T) new UsersBoImpl();
        }
        return null;
    }
}
