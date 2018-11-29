/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import datamodels.Category;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author 587568
 */
public class CategoriesDB
{

    public List<Category> getAllCategories() throws DBException
    {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try
        {
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categories;
        } catch (Exception ex)
        {
            Logger.getLogger(CategoriesDB.class.getName()).log(Level.SEVERE, "Cannot read categories", ex);
            throw new DBException("Error getting categories");
        } finally
        {
            em.close();
        }
    }

    public Category getCategory(String name) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            Category findCategory = em.createNamedQuery("Category.findByCategoryName", Category.class).setParameter("categoryName", name).getSingleResult();
            return findCategory;
        } catch (Exception ex)
        {
            Logger.getLogger(CategoriesDB.class.getName()).log(Level.SEVERE, "Cannot read category", ex);
            throw new DBException("Error getting category");
        } finally
        {
            em.close();
        }
    }
    
}
