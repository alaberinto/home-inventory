package dataaccess;

import datamodels.User;
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
    
}
