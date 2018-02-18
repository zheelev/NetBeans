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
    
    private static RouteDAO routeDAO = null;
    private static SeatDAO seatDAO = null;
    private static TicketsDAO ticketDAO = null;
    private static ReviewDAO reviewDAO = null;
    private static IntermediatestopsDAO interDAO = null;
    private static Factory instance = null;
    
    public static synchronized Factory getInstance(){
    if (instance == null){
      instance = new Factory();
    }
    return instance;
  }
    public RouteDAO getRouteDAO(){
    if (routeDAO == null){
      routeDAO = new RouteDAOImpl();
    }
    return routeDAO;
  }
    public SeatDAO getSeatDAO(){
    if (seatDAO == null){
      seatDAO = new SeatDAOImpl();
    }
    return seatDAO;
  }
    public TicketsDAO getTicketDAO(){
    if (ticketDAO == null){
      ticketDAO = new TicketsDAOImpl();
    }
    return ticketDAO;
  }
    public ReviewDAO getReviewDAO(){
    if (reviewDAO == null){
      reviewDAO = new ReviewDAOImpl();
    }
    return reviewDAO;
  }
    public IntermediatestopsDAO getInterDAO(){
    if (interDAO == null){
      interDAO = new IntermediatestopsDAOImpl();
    }
    return interDAO;
  }
}
