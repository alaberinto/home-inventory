/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import datamodels.Item;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 587568
 */
public class ItemsDB
{
    public Item getItem(int id) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
       
        try
        {
            Item item = em.find(Item.class, id);
            return item;
        } catch (Exception ex)
        {
            Logger.getLogger(ItemsDB.class.getName()).log(Level.SEVERE, "Cannot read item", ex);
            throw new DBException("Error getting item.");
        } finally
        {
            em.close();
        }
    }
    
    public List<Item> getAllItems() throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try
        {
            List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
            return items;
        } catch (Exception ex)
        {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, "Cannot read items", ex);
            throw new DBException("Error getting items");
        } finally
        {
            em.close();
        }
    }

    public int insert(Item toAdd) throws DBException
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
            Logger.getLogger(ItemsDB.class.getName()).log(Level.SEVERE, "Cannot insert " + toAdd.toString(), ex);
            throw new DBException("Error inserting item");
        } finally
        {
            em.close();
        }
    }    
}
