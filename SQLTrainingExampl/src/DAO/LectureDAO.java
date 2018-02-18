/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Logic.Lecture;
import java.util.Collection;

/**
 *
 * @author Admin
 */
public interface LectureDAO {
    
    
    public void Add(Lecture lecture);
    public Collection getAllLecture();
    public Collection getLectureForID(Integer ID);
    public Collection getLectureForIDUser(Integer ID);
    public void updateLecture(Integer ID_lect,String name ,String lecture);
    public void DeleteLection(Lecture lecture);
    
}
