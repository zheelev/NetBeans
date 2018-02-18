/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Admin
 */
public interface IntermediatestopsDAO {
    
    public Collection getAllIntermStops() throws SQLException;
    public Collection getIntermStopsOf(String nameOfStop) throws SQLException;
    
}
