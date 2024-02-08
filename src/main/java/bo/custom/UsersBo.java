package bo.custom;

import bo.SuperBo;
import dto.OrderDto;
import dto.UsersDto;

import java.sql.SQLException;

public interface UsersBo extends SuperBo {
    boolean saveUser(UsersDto dto) throws SQLException, ClassNotFoundException;
    boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException;
    boolean isUserCredentialsValid(String userEmail, String password) throws SQLException, ClassNotFoundException;

    boolean saveUser(String newUserEmail, String newUserPassword);
}
