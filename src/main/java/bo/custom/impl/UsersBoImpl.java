package bo.custom.impl;

import bo.custom.UsersBo;
import dao.custom.UsersDao;
import dao.custom.impl.UsersDaoImpl;
import dao.util.HibernateUtil;
import dto.UsersDto;
import entity.Users;
import org.hibernate.Session;

import java.sql.SQLException;

public class UsersBoImpl implements UsersBo {
    private UsersDao usersDao;

    public UsersBoImpl() {
        // Initialize the UserDAO instance, you may use dependency injection or other methods for this
        this.usersDao = new UsersDaoImpl();
    }


    @Override
    public boolean saveUser(UsersDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException {
        return usersDao.doesUserExist(userEmail);
    }

    @Override
    public boolean isUserCredentialsValid(String userEmail, String password) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
            Users user = (Users) session.createQuery("FROM Users WHERE email = :userEmail")
                    .setParameter("userEmail", userEmail)
                    .uniqueResult();

            return user != null && user.getPassword().equals(password);
        }

    public boolean saveUser(String newUserEmail, String newUserPassword) {
        try {
            // Check if the user already exists in the database
            if (usersDao.doesUserExist(newUserEmail)) {
                return false; // User already exists
            }

            // Create a User entity and set values
            Users users = new Users();
            users.setEmail(newUserEmail);
            users.setPassword(newUserPassword);

            // Save the user to the database
            usersDao.addUser(users);

            return true;
        } catch (Exception e) {
            // Handle exceptions (logging, throwing, etc.)
            e.printStackTrace();
            throw new RuntimeException("Error saving user", e);
        }    }
}
