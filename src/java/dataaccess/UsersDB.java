package dataaccess;

import datamodels.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

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
    
}
