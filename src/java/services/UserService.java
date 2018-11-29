package services;

import dataaccess.DBException;
import dataaccess.UsersDB;
import datamodels.User;
import java.util.List;

/**
 *
 * @author 587568
 */
public class UserService
{
    private final UsersDB usersDB;
    
    public UserService()
    {
        usersDB = new UsersDB();
    }
    
    public List<User> getAll() throws Exception
    {
        return usersDB.getAll();
    }

    public User getUser(String username) throws Exception 
    {
        return usersDB.get(username);
    }

    public int insert(User toAdd) throws Exception
    {
        return usersDB.insert(toAdd);
    }

    public int update(User toUpdate) throws Exception
    {
        return usersDB.update(toUpdate);
    }

    public int delete(User toDelete, String sessionUsername) throws Exception
    {        
        User user = usersDB.getUser(sessionUsername);
        
        //deleting self
        if(toDelete.getUsername().equals(sessionUsername))
        {
            return -1;
        }
        else if(toDelete.getIsAdmin() && toDelete.getActive())
        {
            return -2;
        }
        
        return usersDB.delete(toDelete);
    }
}
