/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.QuestionDAO;
import Logic.Answer;
import Logic.Lecture;
import Logic.Question;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import sqltraining.HibernateUtil;

/**
 *
 * @author Admin
 */
public class QuestionDAOImpl implements QuestionDAO{
    

    @Override
    public void Add(Question question) {
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(question);
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
    public Collection getQuestionForID(Integer ID_lect) {
        Session session = null;
         List lection = new ArrayList<Question>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Question where  ID_lecture = :nameOfStop ").setInteger("nameOfStop", ID_lect);
           lection  = (List<Question>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return lection;
    }

    @Override
    public void updateQuestion(Question question) {
        Session session = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
              int id = question.getIdQuestions();
              Question quest = (Question) session.get(Question.class, id);
              quest.setQuestions(question.getQuestions());
              session.update(quest);
              session.getTransaction().commit();
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error update", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
    }
    
}
