package dao.custom;

import dao.CrudDao;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;

    CustomerDto searchItem(String id);
    ItemDto getItem(String code)throws SQLException, ClassNotFoundException;

    boolean deleteOrdersByItemCode(String itmCode) throws SQLException, ClassNotFoundException;

}
