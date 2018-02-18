/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Logic.Achievements;
import java.util.Collection;

/**
 *
 * @author Admin
 */
public interface AchievementsDAO {
    
    public void Add(Achievements achievments);
    public Collection getAllAchievm();
    public Collection getAchForUser(Integer ID);
    public Collection getAchForUserAndLect(Integer ID_lect, Integer ID_users);
    public void updateAchievements(Integer ID_ach, Integer eval, boolean status);
    
}
