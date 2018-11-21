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
        request.setAttribute("add", 1);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        UserService us = new UserService();
        String action = request.getParameter("action");
        String selected = (String) request.getAttribute("selected");

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
                String selected = request.getParameter("selected");
                try
                {
                    User toPull = us.getUser(selected);
                    request.setAttribute("pulledFirst", toPull.getFirstName());
                    request.setAttribute("pulledLast", toPull.getLastName());
                    request.setAttribute("pulledUsername", toPull.getUsername());
                    request.setAttribute("pulledPassword", toPull.getPassword());
                    request.setAttribute("pulledEmail", toPull.getEmail());
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("action", "User information pulled.");
                request.setAttribute("add", 0);
                break;
            case "delete":
                request.setAttribute("action", "User deleted");
                request.setAttribute("add", 1);
                break;
            case "reactivate":
                request.setAttribute("action", "User reactivated");
                request.setAttribute("add", 1);
                break;
            case "update":
                String firstname = request.getParameter("givenFirst");
                String lastname = request.getParameter("givenLast");
                String username = request.getParameter("givenUsername");
                String password = request.getParameter("givenPassword");
                String email = request.getParameter("givenEmail");
                request.setAttribute("action", "User edited");
                request.setAttribute("add", 1);
                break;
            case "insert":
                request.setAttribute("action", "User added.");
                request.setAttribute("add", 1);
                break;
        }
        
    }
}
