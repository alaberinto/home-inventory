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
import services.AccountService;
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
        return;
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response, String action)
    {
        UserService us = new UserService();
        AccountService as = new AccountService();
        HttpSession session = request.getSession();
        String sessionUsername = (String) session.getAttribute("username");
        String firstname = request.getParameter("givenFirst");
        String lastname = request.getParameter("givenLast");
        String username = request.getParameter("givenUsername");
        String password = request.getParameter("givenPassword");
        String email = request.getParameter("givenEmail");
        String selected = request.getParameter("selected");
        int row = 0;
        
        switch (action)
        {
            case "pull":                
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
                try
                {
                    User toDelete = us.getUser(selected);
                    row = us.delete(toDelete, sessionUsername);
                    
                    //deleting self
                    if(row == -1)
                    {
                        request.setAttribute("action", "Cannot delete yourself.");
                    }
                    //deleting active admin
                    else if(row == -2)
                    {
                        request.setAttribute("action", "Could not delete an active admin.");
                    }
                    else
                    {
                        request.setAttribute("action", "User deleted.");
                    }
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("action", "User could not be deleted.");
                }
                
                request.setAttribute("add", 1);
                break;
            case "update":
                try
                {
                    User toUpdate = us.getUser(username);
                    
                    toUpdate.setFirstName(firstname);
                    toUpdate.setLastName(lastname);
                    toUpdate.setEmail(email);
                    toUpdate.setPassword(password);
                    
                    row = us.update(toUpdate);
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("action", "User could not be edited.");
                }
                
                if (row == 1)
                    request.setAttribute("action", "User edited.");
                
                request.setAttribute("add", 1);
                break;
            case "insert":
                User toAdd = new User(username, password, email, firstname, lastname, true, false);
                
                try
                {
                    row = us.insert(toAdd);
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("action", "User could not be added.");
                }
                
                if (row == 1)
                    request.setAttribute("action", "User added.");

                request.setAttribute("add", 1);
                break;
            case "reactivate":  
                try
                {
                    User toReactivate = us.getUser(selected);
                    toReactivate.setActive(true);
                    
                    row = as.reactivate(toReactivate);
                    
                    if(row == 1)
                        request.setAttribute("action", "User reactivated.");
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("action", "User could not be reactivated.");
                }
                
                request.setAttribute("add", 1);
                break;
            case "deactivate":  
                try
                {
                    User toDeactivate = us.getUser(selected);
                    
                    if(toDeactivate.getUsername().equals(sessionUsername))
                    {
                        request.setAttribute("action", "Cannot deactivate yourself.");
                    }
                    else
                    {
                        toDeactivate.setActive(false);

                        row = as.deactivate(toDeactivate);
                    }
                    
                    if(row == 1)
                        request.setAttribute("action", "User deactivated.");
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("action", "User could not be deactivated.");
                }
                
                request.setAttribute("add", 1);
                break;
            case "promote":
                try
                {
                    User toPromote = us.getUser(selected);
                    
                        toPromote.setIsAdmin(true);
                        row = as.promote(toPromote);
                    
                    if(row == 1)
                        request.setAttribute("action", "User promoted to admin.");
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("add", 1);
                break;
            case "demote":
                try
                {
                    User toDemote = us.getUser(selected);

                    if(toDemote.getUsername().equals(sessionUsername))
                    {
                        request.setAttribute("action", "Cannot demote yourself.");
                    }
                    else
                    {
                        toDemote.setIsAdmin(false);

                        row = as.demote(toDemote);
                    }
                    
                    if(row == 1)
                        request.setAttribute("action", "User demoted to regular user.");
                } catch (Exception ex)
                {
                    Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("add", 1);
                break;
        } 
    }
}
