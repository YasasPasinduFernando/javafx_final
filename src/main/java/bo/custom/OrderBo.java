package bo.custom;

import bo.SuperBo;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    boolean saveOrder(OrderDto dto)throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;
    List<String> getItemCodesForCustomerByContactNumber(String contactNumber) throws SQLException, ClassNotFoundException;

    boolean deleteOrdersByItemCode(String itmCode) throws SQLException, ClassNotFoundException;
    List<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException;

}
