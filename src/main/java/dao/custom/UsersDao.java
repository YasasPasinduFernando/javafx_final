package dao.custom;

import dao.CrudDao;
import dto.CustomerDto;
import dto.UsersDto;
import entity.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao extends CrudDao<UsersDto> {
    Users getUserById(String userId) throws SQLException, ClassNotFoundException;
    boolean addUser(Users user) throws SQLException, ClassNotFoundException;
    boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException;
    List<Users> getAllUsers();



}
