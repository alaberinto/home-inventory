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
        Inventory inv = new Inventory();
        List<Item> itemList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>(); 
        
        try
        {
            itemList = inv.getAllItems();
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
        Inventory inv = new Inventory();
        List<Item> itemList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>(); 

        String action = request.getParameter("action");
        
        doAction(request, response, action);
        
        try
        {
            itemList = inv.getAllItems();
            categoryList = inv.getAllCategories();
        } catch (Exception ex)
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("categories", categoryList);
        request.setAttribute("items", itemList);
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException 
    {
        Inventory inv = new Inventory();
        UserService us = new UserService();
        HttpSession session = request.getSession();
        int row = 0;
        switch (action)
        {
            case "additem":
                String name = request.getParameter("itemname");
                String price = request.getParameter("itemprice");
                String category = request.getParameter("category");
                
                if(checkBlank(name, price))
                {
                    request.setAttribute("message", "Item fields are blank.");
                    return;
                }
                
                if(checkPrice(price))
                {
                    request.setAttribute("message", "Please enter a number.");
                    return;
                }
                
                Item newItem = new Item();
                Category newCategory = null;
                User owner = null;
                String sessionUsername = (String) session.getAttribute("username");
                try
                {
                    newCategory = inv.getCategory(category);
                    owner = us.getUser(sessionUsername);
                    
                    newItem.setCategory(newCategory);
                    newItem.setItemName(name);
                    newItem.setPrice(Double.parseDouble(price));
                    newItem.setOwner(owner);
                
                    row = inv.insert(newItem);
                    if(row == 1)
                        request.setAttribute("message", "Item added.");
                } catch (Exception ex)
                {
                    Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                break;
        }
    }

    private boolean checkBlank(String name, String price)
    {
        if(name.equals("") || name == null || price.equals("") || price == null)
        {
            return true;
        }
        return false;
    }

    private boolean checkPrice(String price)
    {
        try
        {
            Double.parseDouble(price);
        } catch (NumberFormatException nfe)
        {
            return true;
        }
        return false;
    }
}