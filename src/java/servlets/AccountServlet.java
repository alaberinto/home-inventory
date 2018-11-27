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
        int row = 0;
        HttpSession session = request.getSession();
        UserService us = new UserService();
        User sessionUser = null;
        String action = request.getParameter("action");
        
        String first = request.getParameter("editfirst");
        String last = request.getParameter("editlast");
        String email = request.getParameter("editemail");
        String password = request.getParameter("editpass");
        
        if(action != null && action.equals("edit"))
        {
            try
            {
                User toUpdate = us.getUser((String) session.getAttribute("username"));
                
                toUpdate.setFirstName(first);
                toUpdate.setLastName(last);
                toUpdate.setEmail(email);
                toUpdate.setPassword(password);
                
                row = us.update(toUpdate);
                
                sessionUser = us.getUser((String) session.getAttribute("username"));
            } catch (Exception ex)
            {
                Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        request.setAttribute("username", sessionUser.getUsername());
        request.setAttribute("first", sessionUser.getFirstName());
        request.setAttribute("last", sessionUser.getLastName());
        request.setAttribute("email", sessionUser.getEmail());
        request.setAttribute("password", sessionUser.getPassword());
        
        if(row == 1)
            request.setAttribute("message", "User edited successfully!");
        else
            request.setAttribute("message", "Could not edit user.");
        
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }
}