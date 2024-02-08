package bo.custom.impl;

import bo.custom.OrderBo;
import dao.DaoFactory;
import dao.custom.OrderDao;
import dao.util.DaoType;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.save(dto);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        try {
            OrderDto dto = orderDao.getLastOrder();
            if (dto!=null){
                String id = dto.getOrderId();
                int num = Integer.parseInt(id.split("[D]")[1]);
                num++;
                return String.format("D%03d",num);
            }else{
                return "D001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }    }

    @Override
    public List<String> getItemCodesForCustomerByContactNumber(String contactNumber) throws SQLException, ClassNotFoundException {
        List<String> itemCodes = orderDao.getItemCodesForCustomerByContactNumber(contactNumber);

        return itemCodes;    }

    @Override
    public boolean deleteOrdersByItemCode(String itmCode) throws SQLException, ClassNotFoundException {
        return orderDao.deleteOrdersByItemCode(itmCode);
    }

    @Override
    public List<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException {
        return orderDao.getAll();
    }
}
