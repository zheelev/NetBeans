/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.UsersDAO;
import Logic.Authusers;
import Logic.Users;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import sqltraining.HibernateUtil;

/**
 *
 * @author Admin
 */
public class UsersDAOImpl implements UsersDAO{

    @Override
    public void Add(Users users) throws SQLException {   
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(users);
      session.getTransaction().commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Error add", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {

        session.close();
      }
     }
    }

    @Override
    public Collection getUser(String username, String password) throws SQLException {
        Session session = null;
        List UInfo = new ArrayList<Users>();
        try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
         Query query = session.createQuery("from Users u where username = :un AND u.password = :pw")
                .setString("un", username).setString("pw", password);
        UInfo = (List<Users>) query.list();
        session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
        return UInfo;
    }

    @Override
    public Users findByID(Integer ID_users) throws SQLException {
        Session session = null;
            Users user = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              user = (Users) session.get(Users.class, ID_users);
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'findById'", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
            return user;
    }

    @Override
    public Collection getAuthUserInfo(Authusers user) {
       Session session = null;
    List aUsers = new ArrayList<Authusers>();
    try {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      session.beginTransaction();
      int ID_users = user.getUsers().getIdUsers();
      Query query = session.createQuery("from Users where ID_users = :ID_users ").setInteger("ID_users", ID_users);
      aUsers = (List<Authusers>) query.list();
      session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return aUsers;
    }

    @Override
    public Collection findUserByUserName(String username) throws SQLException {
        Session session = null;
        List UInfo = new ArrayList<Users>();
        try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
         Query query = session.createQuery("from Users u where username = :un")
                .setString("un", username);
        UInfo = (List<Users>) query.list();
        session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
        return UInfo;
    }

    @Override
    public Collection getAllUser() throws SQLException {
         Session session = null;
        List allUser = new ArrayList<Users>();
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          allUser = session.createCriteria(Users.class).list();
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
            return allUser;
    }

    @Override
    public void UpdateUser(Users users) {
       Session session = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
              int id = users.getIdUsers();
              Users usersNew = (Users) session.get(Users.class, id);
              usersNew.setFirstname(users.getFirstname());
              usersNew.setLastname(users.getLastname());
              usersNew.setUsername(users.getUsername());
              usersNew.setPassword(users.getPassword());
              usersNew.setTypeUsers(users.getTypeUsers());
              session.update(usersNew);
              session.getTransaction().commit();
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error update", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
    }

    @Override
    public void deleteUser(Users users) {
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          int ID = users.getIdUsers();
          Users  deleteUser = (Users) session.get(Users.class,ID);
          session.delete(deleteUser);
          session.getTransaction().commit();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e.getMessage(), "Error add", JOptionPane.OK_OPTION);
        } finally {
          if (session != null && session.isOpen()) {
            session.close();
          }
         }
    }
    
}
