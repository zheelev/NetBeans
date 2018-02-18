/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.IntermediatestopsDAO;
import busticket.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import logic.Intermediatestops;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class IntermediatestopsDAOImpl implements IntermediatestopsDAO{

    @Override
    public Collection getAllIntermStops() throws SQLException {
        Session session = null;
        List busses = new ArrayList<Intermediatestops>();
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          busses = session.createCriteria(Intermediatestops.class).list();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);
        } finally {
          if (session != null && session.isOpen()) {
            session.close();
          }
    }
    return busses;
        
    }

    @Override
    public Collection getIntermStopsOf(String nameOfStop) throws SQLException {
         Session session = null;
         List end = new ArrayList<Intermediatestops>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Intermediatestops where  nameOfStop = :nameOfStop ").setString("nameOfStop", nameOfStop);
           end = (List<Intermediatestops>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return end;
         }
    
}
