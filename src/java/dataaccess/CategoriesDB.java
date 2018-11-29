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
import javax.persistence.EntityTransaction;

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
            Category findCategory = em.find(Category.class, name);
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

    
    public Category getCategory(int id) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            Category findCategory = em.find(Category.class, id);
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
    
    public int insert(Category toAdd) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.persist(toAdd);
            trans.commit();
            return 1;
        } catch (Exception ex)
        {
            trans.rollback();
            Logger.getLogger(CategoriesDB.class.getName()).log(Level.SEVERE, "Cannot insert " + toAdd.toString(), ex);
            throw new DBException("Error inserting item");
        } finally
        {
            em.close();
        }
    }

    public int update(Category toEdit) throws DBException
    {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.merge(toEdit);
            trans.commit();
            return 1;
        } catch (Exception ex)
        {
            trans.rollback();
            Logger.getLogger(CategoriesDB.class.getName()).log(Level.SEVERE, "Cannot update " + toEdit.toString(), ex);
            throw new DBException("Error updating item");
        } finally
        {
            em.close();
        }
    }
    
}
