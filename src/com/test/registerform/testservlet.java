package com.test.registerform;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;





@WebServlet("/testservlet")
public class testservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		org.hibernate.Session session = null;
	    org.hibernate.Transaction transaction = null;
	    try {
	      session = Hbutil.getSessionFactory().openSession();
	      transaction=session.getTransaction();
	        	  if(!transaction.isActive()) 
	      transaction.begin();
	      Customer customer = new Customer();
	      customer.setId(Integer.parseInt(request.getParameter("id")));
	      customer.setFirstName(request.getParameter("fname"));
	      customer.setLastName(request.getParameter("lname"));
	      customer.setEmail(request.getParameter("email"));
	      session.save(customer);	
	
	      transaction.commit();
	    } catch (Exception e) {
	      if (transaction != null) {
	        transaction.rollback();
	      }
	      e.printStackTrace();
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	    }

	    Hbutil.shutdown();
	  }
	
		

}

