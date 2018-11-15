package services;

import dataaccess.DBException;
import dataaccess.UserDB;
import datamodels.User;

/**
 *
 * @author 587568
 */
public class AccountService 
{
    private final UserDB userDB;
    
    public AccountService()
    {
        userDB = new UserDB();
    }
    
    public User login(String username, String password) throws DBException
    {
        return userDB.getUser(username);
    }

}
