/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Logic.Answer;
import Logic.Question;
import java.util.Collection;

/**
 *
 * @author Admin
 */
public interface QuestionDAO {
    
    public void Add(Question question);
    public Collection getQuestionForID(Integer ID_lect);
    
    public void updateQuestion(Question question);
    
}
