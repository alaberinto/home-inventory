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
        user = checkActive(user);
        user = checkPassword(user, password);
        return user;
    }

    private User checkActive(User user)
    {
        if(user.getActive())
            return user;
        else
            return null;
    }

    private User checkPassword(User user, String password)
    {
        if(user.getPassword().equals(password))
            return user;
        else
            return null;
    }
}
