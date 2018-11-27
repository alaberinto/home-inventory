package services;

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

    public int register(User toAdd) throws Exception
    {
        return usersDB.insert(toAdd);
    }
    
    public int checkBlank(User user)
    {
        if(user.getUsername() == null || user.getUsername().equals(""))
            return 0;
        
        if(user.getFirstName() == null || user.getFirstName().equals(""))
            return 0;
        
        if(user.getLastName() == null || user.getLastName().equals(""))
            return 0;
        
        if(user.getEmail() == null || user.getEmail().equals(""))
            return 0;
        
        return 1;
    }

    public int checkUsername(User user) throws Exception
    {
        UserService us = new UserService();
        User toCheck = us.getUser(user.getUsername());
        
        if(toCheck == null)
        {
            return 1;
        }
        
        if(toCheck.getUsername().equals(user.getUsername()))
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
