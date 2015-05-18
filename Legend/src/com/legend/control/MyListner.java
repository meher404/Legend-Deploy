package com.legend.control;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.legend.lib.DBConnection;

/**
 * Application Lifecycle Listener implementation class MyListner
 *
 */
@WebListener
public class MyListner implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyListner() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    DBConnection db = new DBConnection();
    public void contextInitialized(ServletContextEvent arg0) {
        db.getConnection();
        ServletContext context = arg0.getServletContext();
        context.setAttribute("db", db);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	db.closeConnection();
    }
	
}
