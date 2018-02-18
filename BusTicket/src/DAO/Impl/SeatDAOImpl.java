/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.SeatDAO;
import busticket.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import logic.Seats;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class SeatDAOImpl implements SeatDAO{

    @Override
    public void addSeat(Seats seats) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSeats(Integer ID_seat, boolean status) throws SQLException {
         Session session = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
              LockOptions options =  new LockOptions();
              options.setLockMode(LockMode.PESSIMISTIC_WRITE);
              Seats tm = (Seats) session.get(Seats.class, ID_seat,options);
              tm.setStatus(status);
              session.update(tm);
              session.getTransaction().commit();
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
    }

    @Override
    public void deleteSeats(Seats seats) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getAllSeats() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getSeatsByRoute(Integer ID) throws SQLException {
        Session session = null;
         List emptySeat = new ArrayList<Seats>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Seats where ID_route = :ID ").setInteger("ID", ID);
           emptySeat = (List<Seats>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return emptySeat;
         }

    @Override
    public Seats getSeatByID(Integer ID_seat) throws SQLException {
            Session session = null;
            Seats seat = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              seat = (Seats) session.get(Seats.class, ID_seat);
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'findById'", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
            return seat;
            }

    @Override
    public Collection getSeatsByID(Integer ID_seat) throws SQLException {
        Session session = null;
         List seats = new ArrayList<Seats>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Seats where ID_seat = :ID ").setInteger("ID", ID_seat);
           seats = (List<Seats>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return seats;
    }
    }
    
