package dao.custom;

import dao.CrudDao;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends CrudDao<OrderDto> {
    OrderDto getLastOrder() throws SQLException, ClassNotFoundException;

    boolean deleteOrdersByItemCode(String itmCode)throws SQLException, ClassNotFoundException;

    List<String> getItemCodesForCustomerByContactNumber(String contactNumber) throws SQLException, ClassNotFoundException;

}
