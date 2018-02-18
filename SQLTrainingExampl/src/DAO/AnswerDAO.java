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
public interface AnswerDAO {
    
    public void Add(Answer answer);
    public Collection getAnswerForIDQ(Integer ID);
    public void update(Answer answer);
    
}
