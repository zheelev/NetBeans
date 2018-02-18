/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.ReviewDAO;
import busticket.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import logic.Review;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ReviewDAOImpl implements ReviewDAO{

    @Override
    public void addReview(Review review) throws SQLException {
         Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(review);
      session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error ", JOptionPane.OK_OPTION);
        } finally {
        if (session != null && session.isOpen()) {

            session.close();
        }
        }
    }

    @Override
    public Collection getAllReview() throws SQLException {
         Session session = null;
         List review = new ArrayList<Review>();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                review = session.createCriteria(Review.class).list();
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);
            } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return review;
    }

    @Override
    public Collection getReviewFor(Integer rID) throws SQLException {
        Session session = null;
         List review = new ArrayList<Review>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Review where  ID_route = :idR ").setInteger("idR", rID);
           review = (List<Review>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return review;
         }

    @Override
    public Collection getInfoByDoc(String document, Integer idRoute) throws SQLException {
        Session session = null;
         List review = new ArrayList<Review>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Review where documentUsers = :docum AND ID_route = :idR").setString("docum", document).setInteger("idR", idRoute);
           review = (List<Review>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return review;
    }
    }
    
