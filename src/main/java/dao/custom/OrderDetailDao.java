package dao.custom;

import dao.CrudDao;
import dto.OrderDetailDto;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao extends CrudDao<OrderDetail> {
    boolean saveOrderDetails(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException;
    List<OrderDetailDto> getOrderDetailsByOrderId(String orderId) throws SQLException;

}
