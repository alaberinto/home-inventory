package servlets;

import dataaccess.DBException;
import datamodels.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author 587568
 */
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String access = request.getParameter("access");
        
        if(access != null)
        {
            request.setAttribute("message", "Not logged in.");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        AccountService as = new AccountService();
        try
        {            
            user = as.login(username, password);
        } catch (DBException ex)
        {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("inventory");
            return;
        }
        else
        {
            request.setAttribute("message", "Invalid user credentials.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }
}