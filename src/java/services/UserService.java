/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
