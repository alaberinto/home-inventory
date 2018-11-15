package servlets;

import datamodels.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;

/**
 *
 * @author 587568
 */
public class UsersServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        UserService us = new UserService();
        List<User> usersList = null;
        
        try
        {
            usersList = us.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("users", usersList);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        UserService us = new UserService();
        String action = request.getParameter("action");
        
        doAction(request, response, action);        
        
        List<User> usersList = null;
        try
        {
            usersList = us.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("users", usersList);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response, String action)
    {
        switch (action)
        {
            case "edit":
               request.setAttribute("action", "User edited");
               break;
            case "delete":
                request.setAttribute("action", "User deleted");
                break;
            case "reactivate":
                request.setAttribute("action", "User reactivated");
                break;
        }
    }
}