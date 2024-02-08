package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.util.DaoType;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    private ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                dto.getCode(),
                dto.getName(),
                dto.getCategory(),
                dto.getStatus(),
                dto.getContact()

        ));    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(
                dto.getCode(),
                dto.getName(),
                dto.getCategory(),
                dto.getStatus(),
                dto.getContact()
        ));    }

    @Override
    public boolean deleteItem(String itmCode) throws SQLException, ClassNotFoundException {
        boolean isItemDeleted = itemDao.deleteOrdersByItemCode(itmCode);
        if (isItemDeleted) {
            return itemDao.delete(itmCode);
        }

        return false;
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        return itemDao.allItems();
    }

    @Override
    public ItemDto getItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.getItem(code);
    }
}
