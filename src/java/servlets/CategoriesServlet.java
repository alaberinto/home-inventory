package servlets;

import datamodels.Category;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.Inventory;

/**
 *
 * @author 587568
 */
public class CategoriesServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Inventory inv = new Inventory();
        List<Category> categoryList = new ArrayList<>();
        
        try
        {
            categoryList = inv.getAllCategories();
        } catch (Exception ex)
        {
            Logger.getLogger(CategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categoryList);
        getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Inventory inv = new Inventory();
        List<Category> categoryList = new ArrayList<>();
        String action = request.getParameter("action");
        
        doAction(request, response, action);
        
        try
        {
            categoryList = inv.getAllCategories();
        } catch (Exception ex)
        {
            Logger.getLogger(CategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categoryList);
        getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response, String action)
    {
        int row = 0;
        Inventory inv = new Inventory();
        String selected = request.getParameter("selected");
        switch (action)
        {
            case "addcategory":
                String toAdd = request.getParameter("addcategory");
                
                Category newCategory = new Category();
                newCategory.setCategoryName(toAdd);

                try
                {
                    row = inv.insertCategory(newCategory);
                } catch (Exception ex)
                {
                    Logger.getLogger(CategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (row == 1)
                    request.setAttribute("message", "Category added.");
                else
                    request.setAttribute("message", "Category was not added.");
                break;
            case "pullcategory":
                request.setAttribute("editcategory", this);
                break;
            case "editcategory":
                break;
        }
    }
}