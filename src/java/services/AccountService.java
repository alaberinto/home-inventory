package services;

import database.UserDB;
import datamodels.User;

/**
 *
 * @author 587568
 */
public class AccountService 
{
    public User login(String username, String password)
    {
        try
        {
            UserDB userDB = new UserDB();
            User user = userDB.getUser(username);
            
            if (user.getPassword().equals(password))
            {
                return user;
            }
        } catch (Exception e)
        {
            
        }
        return null;
    }
}
