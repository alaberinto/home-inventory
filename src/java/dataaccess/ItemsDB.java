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

/**
 *
 * @author 587568
 */
public class ItemsDB {

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
    
}
