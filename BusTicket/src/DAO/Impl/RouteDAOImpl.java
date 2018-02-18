/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.RouteDAO;
import busticket.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import logic.Intermediatestops;
import logic.Route;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class RouteDAOImpl implements RouteDAO{

    @Override
    public void addRoute(Route route) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRoute(Route ID_route, Route route) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRoute(Route route) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getAllRoute() throws SQLException {
        Session session = null;
        List routes = new ArrayList<Route>();
        try {
          session = HibernateUtil.getSessionFactory().openSession();
          routes = session.createCriteria(Route.class).list();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);
        } finally {
          if (session != null && session.isOpen()) {
            session.close();
          }
    }
    return routes;
        
    }

    @Override
    public Route getRouteByID(Integer ID_Route) throws SQLException {
        Session session = null;
            Route route = null;
            try {
              session = HibernateUtil.getSessionFactory().openSession();
              route = (Route) session.get(Route.class, ID_Route);
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'findById'", JOptionPane.OK_OPTION);
            } finally {
              if (session != null && session.isOpen()) {
                session.close();
              }
            }
            return route;
    }

    @Override
    public Collection getFullRoute(Intermediatestops interm) throws SQLException {
        Session session = null;
        List fullRoute = new ArrayList<Route>();
        try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Integer route_id = interm.getRoute().getIdRoute();
        Query query = session.createQuery("from Route where ID_route = :routeId").setInteger("routeId", route_id);
        fullRoute = (List<Route>) query.list();
        session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
        return fullRoute;
    } 

    @Override
    public Collection getRouteTo(String start, Intermediatestops interm) throws SQLException {
        Session session = null;
        List fullRoute = new ArrayList<Route>();
        try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Integer route_id = interm.getRoute().getIdRoute();
        Query query = session.createQuery("from Route where start = :startPoint AND ID_route = :endPoint")
                .setString("startPoint", start).setInteger("endPoint", route_id);
        fullRoute = (List<Route>) query.list();
        session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
        return fullRoute;
    }

    @Override
    public Collection getEndRoute(String start, String end) throws SQLException {
        Session session = null;
        List fullRoute = new ArrayList<Route>();
        try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Route r where start = :startPoint AND r.end = :endPoint")
                .setString("startPoint", start).setString("endPoint", end);
        fullRoute = (List<Route>) query.list();
        session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
        return fullRoute;
    }
    
}
