package dao.custom.impl;

import bo.BoFactory;
import bo.custom.UsersBo;
import dao.custom.UsersDao;
import dao.util.BoType;
import dao.util.HibernateUtil;
import dto.UsersDto;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UsersDaoImpl implements UsersDao {

    @Override
    public boolean save(UsersDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(UsersDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<UsersDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Users getUserById(String userId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addUser(Users user) {

        Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return false;
            }
        }

    @Override
    public boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException {
         Session session = HibernateUtil.getSession();
            Long count = (Long) session.createQuery("SELECT COUNT(*) FROM Users WHERE email = :userEmail")
                    .setParameter("userEmail", userEmail)
                    .uniqueResult();
            return count != null && count > 0;

    }

    @Override
    public List<Users> getAllUsers() {
        Session session = HibernateUtil.getSession();
            return session.createQuery("FROM Users", Users.class).list();
        }    }

