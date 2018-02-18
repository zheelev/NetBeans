/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.Collection;
import logic.Tickets;

/**
 *
 * @author Admin
 */
public interface TicketsDAO {
    
    public void addTickets(Tickets tickets) throws SQLException;
    public Collection getTicketByID(Integer ID_Ticket) throws SQLException;
    public Collection getInfoByDoc(Integer document)throws SQLException;
    
}
