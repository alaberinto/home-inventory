package dataaccess;

import datamodels.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 587568
 */
public class UsersDB 
{

    public User getUser(String username) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            User user = em.find(User.class, username);
            return user;
        } catch (Exception ex)
        {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            throw new DBException("Error getting user.");
        } finally
        {
            em.close();
        }
    }

    public List<User> getAll() throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try
        {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } catch (Exception ex)
        {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new DBException("Error getting users");
        } finally
        {
            em.close();
        }
    }

    public User get(String username) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            User user = em.find(User.class, username);
            return user;
        } catch (Exception ex)
        {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            throw new DBException("Error getting user.");
        } finally
        {
            em.close();
        }
    }

    public int insert(User toAdd) throws DBException
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
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, "Cannot insert " + toAdd.toString(), ex);
            throw new DBException("Error inserting user");
        } finally
        {
            em.close();
        }
    }

    public int update(User toUpdate) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.merge(toUpdate);
            trans.commit();
            return 1;
        } catch (Exception ex)
        {
            trans.rollback();
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, "Cannot update " + toUpdate.toString(), ex);
            throw new DBException("Error updating user");
        } finally
        {
            em.close();
        }
    }

    public int delete(User toDelete) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.remove(em.merge(toDelete));
            trans.commit();
            return 1;
        } catch (Exception ex)
        {
            trans.rollback();
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, "Cannot delete " + toDelete.toString(), ex);
            throw new DBException("Error deleting user");
        } finally
        {
            em.close();
        }
    }
}
