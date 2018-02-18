/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.AuthUsersDAO;
import Logic.Authusers;
import Logic.Users;
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
public class AuthUsersDAOImpl implements AuthUsersDAO{

    @Override
    public void Add(Authusers aUsers) {
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(aUsers);
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
    public Collection getAllAuthUser() {
        Session session = null;
        List aUsers = new ArrayList<Authusers>();
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          aUsers = session.createCriteria(Authusers.class).list();
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
            return aUsers;
    }

    @Override
    public void Delete(Integer ID) {
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          Authusers delU = (Authusers) session.get(Authusers.class, ID);
          session.delete(delU);
          session.getTransaction().commit();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
          if (session != null && session.isOpen()) {
            session.close();
          }
        }
    }

    @Override
    public Collection findByMAC(String MacA) {
         Session session = null;
         List aUsers = new ArrayList<Authusers>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Authusers where  MAC = :nameOfStop ").setString("nameOfStop", MacA);
           aUsers = (List<Authusers>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return aUsers;
    }    
    
}
