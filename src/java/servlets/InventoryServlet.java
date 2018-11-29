package servlets;

import datamodels.Category;
import datamodels.Item;
import datamodels.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.Inventory;
import services.UserService;

/**
 *
 * @author 587568
 */
public class InventoryServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String sessionUsername = (String) session.getAttribute("username");
        UserService us = new UserService();
        Inventory inv = new Inventory();
        List<Item> itemList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>(); 
        
        try
        {
            User user = us.getUser(sessionUsername);
            itemList = user.getItemList();
            categoryList = inv.getAllCategories();
        } catch (Exception ex)
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("categories", categoryList);
        request.setAttribute("items", itemList);
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String sessionUsername = (String) session.getAttribute("username");
        UserService us = new UserService();
        List<Item> itemList = new ArrayList<>();

        try
        {
            User user = us.getUser(sessionUsername);
            itemList = user.getItemList();
        } catch (Exception ex)
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }
}