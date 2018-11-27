package servlets;

import datamodels.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

/**
 *
 * @author 587568
 */
public class AccountServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
        {
            HttpSession session = request.getSession();
            UserService us = new UserService();
            User sessionUser = us.getUser((String) session.getAttribute("username"));
            
            request.setAttribute("username", sessionUser.getUsername());
            request.setAttribute("first", sessionUser.getFirstName());
            request.setAttribute("last", sessionUser.getLastName());
            request.setAttribute("email", sessionUser.getEmail());
            request.setAttribute("password", sessionUser.getPassword());
            
            getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
        } catch (Exception ex)
        {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }
}