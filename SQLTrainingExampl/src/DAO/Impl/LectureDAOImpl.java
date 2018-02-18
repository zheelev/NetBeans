/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.LectureDAO;
import Logic.Lecture;
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
public class LectureDAOImpl implements LectureDAO {

    @Override
    public void Add(Lecture lecture) {
         Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(lecture);
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
    public Collection getAllLecture() {
        Session session = null;
        List allLect = new ArrayList<Lecture>();
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          allLect = session.createCriteria(Lecture.class).list();
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
            return allLect;
       
    }

    @Override
    public Collection getLectureForID(Integer ID) {
        Session session = null;
         List lection = new ArrayList<Lecture>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Lecture where  ID_lecture = :ID ").setInteger("ID", ID);
           lection  = (List<Lecture>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return lection;
    }

    @Override
    public void updateLecture(Integer ID_lect,String name ,String lecture) {
        Session session = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
              Lecture  lect = (Lecture) session.get(Lecture.class,ID_lect);
              lect.setName(name);
              lect.setLecture(lecture);
              session.update(lect);
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
    public void DeleteLection(Lecture lecture) {
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          int ID = lecture.getIdLecture();
          Lecture  lect = (Lecture) session.get(Lecture.class,ID);
          session.delete(lect);
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
    public Collection getLectureForIDUser(Integer ID) {
         Session session = null;
         List lection = new ArrayList<Lecture>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Lecture where  ID_users = :ID ").setInteger("ID", ID);
           lection  = (List<Lecture>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return lection;
    }
    
}
