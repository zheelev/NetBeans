/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.AchievementsDAO;
import Logic.Achievements;
import Logic.Authusers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import sqltraining.HibernateUtil;

/**
 *
 * @author Admin
 */
public class AchievementsDAOImpl implements AchievementsDAO{

    @Override
    public void Add(Achievements achievments) {
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(achievments);
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
    public Collection getAchForUser(Integer ID) {
       
        Session session = null;
         List aCh = new ArrayList<Achievements>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Achievements where  ID_users = :ID ").setInteger("ID", ID);
           aCh  = (List<Authusers>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return aCh;
    } 

    @Override
    public Collection getAchForUserAndLect(Integer ID_lect, Integer ID_users) {
        Session session = null;
         List aCh = new ArrayList<Achievements>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Achievements where  ID_lecture = :lection AND ID_users = :users").setInteger("lection", ID_lect).setInteger("users", ID_users);
           aCh  = (List<Authusers>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return aCh;
    }

    @Override
    public void updateAchievements(Integer ID_ach,Integer eval,boolean status) {
        Session session = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
              LockOptions options =  new LockOptions();
              options.setLockMode(LockMode.PESSIMISTIC_WRITE);
              Achievements ac = (Achievements) session.get(Achievements.class,ID_ach);
              ac.setEval(eval);
              ac.setStatus(status);
              session.update(ac);
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
    public Collection getAllAchievm() {
        Session session = null;
        List allAchivm = new ArrayList<Achievements>();
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          allAchivm = session.createCriteria(Achievements.class).list();
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
            return allAchivm;
        
    }
}
 
