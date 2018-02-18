/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.Collection;
import logic.Route;
import logic.Intermediatestops;
/**
 *
 * @author Admin
 */
public interface RouteDAO {
    
    public void addRoute(Route route) throws SQLException;
    public void updateRoute(Route ID_route,Route route) throws SQLException;
    public void deleteRoute(Route route) throws SQLException;
    public Collection getAllRoute() throws SQLException;
    public Route getRouteByID(Integer ID_Route) throws SQLException;
    public Collection getFullRoute(Intermediatestops interm) throws SQLException;
    public Collection getRouteTo(String start, Intermediatestops interm) throws SQLException;
    public Collection getEndRoute(String start,String end) throws SQLException;
    
}
