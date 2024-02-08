package bo.custom.impl;

import bo.custom.OrderDetailBo;
import dao.DaoFactory;
import dao.custom.OrderDetailDao;
import dao.util.DaoType;
import dto.OrderDetailDto;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBoImpl implements OrderDetailBo {
    private OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);


    @Override
    public List<OrderDetailDto> getOrderDetailsByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        List<OrderDetail> entityList = orderDetailDao.getAll();
        List<OrderDetailDto> list = new ArrayList<>();
        for (OrderDetail orderDetail:entityList) {
            list.add(new OrderDetailDto(
                    orderDetail.getId().getOrderId(),
                    orderDetail.getId().getItemCode(),
                    orderDetail.getProduct(),
                    orderDetail.getFault(),
                    orderDetail.getPrice()
            ));
        }
        return list;
}

    @Override
    public List<OrderDetailDto> allOrderDetails() throws SQLException, ClassNotFoundException {
        List<OrderDetail> entityList = orderDetailDao.getAll();
        List<OrderDetailDto> list = new ArrayList<>();
        for (OrderDetail orderDetail : entityList) {
            list.add(new OrderDetailDto(
                    orderDetail.getId().getOrderId(),
                    orderDetail.getId().getItemCode(),
                    orderDetail.getProduct(),
                    orderDetail.getFault(),
                    orderDetail.getPrice()
            ));
        }
        return list;
    }
}
