package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import dao.UserDao;

public class WelcomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDao userDao = new UserDao();
		User userD = new User();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userNm = request.getParameter("userName");
		String userPsw = request.getParameter("password");
		String userRol = request.getParameter("rol");
		
		userD.setUserName(userNm);
        userD.setPassword(userPsw);
        userD.setRol(userRol);
        
        User usr = userDao.loginUser(userD);
        //System.out.println(usr);
        
        if( usr.getUserName() == null && usr.getPassword() == null)
        {
        	response.sendRedirect("index.jsp");
            //out.println("<font color=red>Either user name or password is wrong.</font>");
        }
        else
        {
        	if( usr.getRol().equals("user") )
        	{
        		HttpSession session = request.getSession(true); //creates a new session.  
        		if(session.isNew() )
        		{
        			session.invalidate();  //this clears the session
        			session = request.getSession(true); // creates a new session 
        		}  
                session.setAttribute("userName", usr.getUserName() );
        		response.sendRedirect("indexMatch-user.jsp");
        	}
        	else if( usr.getRol().equals("admin") )
        	{
        		HttpSession session = request.getSession(true); //creates a new session.  
        		if(session.isNew() )
        		{
        			session.invalidate();  //this clears the session
        			session = request.getSession(true); // creates a new session 
        		}  
                session.setAttribute("userName", usr.getUserName() );  
        		response.sendRedirect("indexMatch-admin.jsp");
        	}
        		
        }
		out.close();
	}

}
