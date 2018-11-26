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
    private final UsersDB usersDB;
    
    public AccountService()
    {
        usersDB = new UsersDB();
    }
    
    public User login(String username, String password) throws Exception
    {
        User user = usersDB.getUser(username);
        if(!user.getPassword().equals(password))
            return null;
        else
            return user;
    }
    
    public User get(String username) throws Exception
    {
        return usersDB.getUser(username);
    }
    
    public int reactivate(User toReactivate) throws Exception
    {
        return usersDB.update(toReactivate);
    }

    public int deactivate(User toDeactivate) throws Exception
    {
        return usersDB.update(toDeactivate);
    }

    public int promote(User toPromote) throws Exception
    {
        return usersDB.update(toPromote);
    }

    public int demote(User toDemote) throws Exception
    {
        return usersDB.update(toDemote);
    }
}
