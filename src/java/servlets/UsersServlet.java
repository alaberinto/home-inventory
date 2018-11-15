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
import javax.servlet.http.HttpSession;
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

        try {
            usersList = us.getAll();
        } catch (Exception ex) {
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
        UserService us = new UserService();
        HttpSession session = request.getSession();
        switch (action)
        {
            case "pull":
                String username = (String) session.getAttribute("username");
                try
                {
                    User toPull = us.getUser(username);
                    request.setAttribute("pulledFirst", toPull.getFirstName());
                    request.setAttribute("pulledLast", toPull.getLastName());
                    request.setAttribute("pulledUsername", toPull.getUsername());
                    request.setAttribute("pulledPassword", toPull.getPassword());
                    request.setAttribute("pulledEmail", toPull.getEmail());
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("action", "User info pulled");
                break;
            case "delete":
                request.setAttribute("action", "User deleted");
                break;
            case "reactivate":
                request.setAttribute("action", "User reactivated");
                break;
            case "update":
                request.setAttribute("action", "User edited");
                break;
        }
        
    }
}
