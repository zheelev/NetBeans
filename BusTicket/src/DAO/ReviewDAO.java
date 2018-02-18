/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.Collection;
import logic.Review;

/**
 *
 * @author Admin
 */
public interface ReviewDAO {
    
    public void addReview(Review review) throws SQLException;
    public Collection getAllReview() throws SQLException;
    public Collection getReviewFor(Integer rID) throws SQLException;
    public Collection getInfoByDoc(String document, Integer idRoute)throws SQLException;
    
}
