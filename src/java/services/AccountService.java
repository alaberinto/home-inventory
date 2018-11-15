package services;

import dataaccess.DBException;
import dataaccess.UsersDB;
import datamodels.User;

/**
 *
 * @author 587568
 */
public class AccountService 
{
    private final UsersDB userDB;
    
    public AccountService()
    {
        userDB = new UsersDB();
    }
    
    public User login(String username, String password) throws DBException
    {
        User user = userDB.getUser(username);
        if(!user.getActive())
            return null;
        else if(!user.getPassword().equals(password))
            return null;
        else
            return user;
    }
    
    public User get(String username) throws DBException
    {
        return userDB.getUser(username);
    }
}
