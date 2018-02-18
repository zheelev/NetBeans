/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.Collection;
import logic.Seats;


/**
 *
 * @author Admin
 */
public interface SeatDAO {
    
    public void addSeat(Seats seats)  throws SQLException;
    public void updateSeats(Integer ID_seat, boolean status)  throws SQLException;
    public void deleteSeats (Seats seats) throws SQLException;
    public Collection getAllSeats()  throws SQLException;
    public Collection getSeatsByRoute(Integer ID)  throws SQLException;
    public Collection getSeatsByID(Integer ID_seat)throws SQLException;
    public Seats getSeatByID(Integer ID_seat)throws SQLException;
    
    
}
