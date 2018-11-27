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
import services.AccountService;
import services.UserService;

/**
 *
 * @author 587568
 */
public class RegistrationServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String success = request.getParameter("success");
        
        if(success != null)
        {
            request.setAttribute("message", "User registered successfully!");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        AccountService as = new AccountService();
        UserService us = new UserService();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String check = request.getParameter("confirmpassword");
        String first = request.getParameter("firstname");
        String last = request.getParameter("lastname");
        String email = request.getParameter("email");
        
        request.setAttribute("username", username);
        request.setAttribute("firstname", first);
        request.setAttribute("lastname", last);
        request.setAttribute("email", email);
        
        if(password == null || password.equals("") || check == null || check.equals(""))
        {
           request.setAttribute("message", "Passwords are blank.");
           getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
           return;
        }
        
        if(!password.equals(check))
        {
            request.setAttribute("message", "Passwords do not match.");
            
            getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
            return;
        }
        
        User user = new User(username, password, email, first, last, true, false);
        int row = as.checkBlank(user);
        
        if(row == 0)
        {
           request.setAttribute("message", "All fields must be filled.");
           getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
           return;
        }
        
        try
        {
            row = as.checkUsername(user);
        } catch (Exception ex)
        {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        if(row == 0)
        {
           request.setAttribute("message", "User already exists.");
           getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
           return;
        }
        
        try
        {            
            row = us.insert(user);
        } catch (Exception ex)
        {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(row == 1)
        {
           response.sendRedirect("login?registered");
        }
        else
        {
            request.setAttribute("message", "Oops. Can't register user.");
            getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
            return;
        }
    }
}