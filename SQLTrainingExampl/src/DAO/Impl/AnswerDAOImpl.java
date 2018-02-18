/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.AnswerDAO;
import Logic.Answer;
import Logic.Question;
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
public class AnswerDAOImpl implements AnswerDAO{

    @Override
    public void Add(Answer answer) {
        Session session = null;
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(answer);
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
    public Collection getAnswerForIDQ(Integer ID) {
        Session session = null;
         List answer = new ArrayList<Answer>();
         try {
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           Query query = session.createQuery(" from Answer where ID_question = :nameOfStop ").setInteger("nameOfStop", ID);
           answer  = (List<Answer>) query.list();
           session.getTransaction().commit();

         } finally {
           if (session != null && session.isOpen()) {
             session.close();
           }
         }
         return answer;
    }

    @Override
    public void update(Answer answer) {
        Session session = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
              int id = answer.getIdAnswer();
              Answer answerNew = (Answer) session.get(Answer.class, id);
              answerNew.setAnswer(answer.getAnswer());
              answerNew.setCorrect(answer.isCorrect());
              session.update(answerNew);
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
