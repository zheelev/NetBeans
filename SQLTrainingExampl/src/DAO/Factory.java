/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Impl.*;



/**
 *
 * @author Admin
 */
public class Factory {
    
    private static UsersDAO userDAO = null;
    private static AchievementsDAO achDAO = null;
    private static LectureDAO lecDAO = null;
    private static AuthUsersDAO aUsDAO = null;
    private static QuestionDAO questDAO = null;
    private static AnswerDAO answDAO = null;
    private static Factory instance = null;
    
    
    public static synchronized Factory getInstance(){
    if (instance == null){
      instance = new Factory();
        }
        return instance;
    }
    public UsersDAO getUserDAO(){
    if (userDAO == null){
      userDAO = new UsersDAOImpl();
        }
        return userDAO;
    }
    public AuthUsersDAO getAuthUserDAO(){
    if (aUsDAO == null){
      aUsDAO = new AuthUsersDAOImpl();
        }
        return aUsDAO;
    }
    public LectureDAO getLectureDAO(){
    if (lecDAO == null){
      lecDAO = new LectureDAOImpl();
        }
        return lecDAO;
    }
    
    public AchievementsDAO getAchievementsDAO(){
    if (achDAO == null){
      achDAO = new AchievementsDAOImpl();
        }
        return achDAO;
    }
    public QuestionDAO getQuestionDAO(){
    if (questDAO == null){
      questDAO = new QuestionDAOImpl();
        }
        return questDAO;
    }
    
    public AnswerDAO getAnswerDAO(){
    if (answDAO == null){
      answDAO = new AnswerDAOImpl();
        }
        return answDAO;
    }
    
}
