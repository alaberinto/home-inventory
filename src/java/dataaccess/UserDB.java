package dataaccess;

import datamodels.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 587568
 */
public class UserDB 
{

    public User getUser(String username) throws DBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try
        {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            User user = null;
            while(rs.next())
            {
                user = new User();
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setIsActive(rs.getByte("Active"));
                user.setIsAdmin(rs.getByte("IsAdmin"));
            }
            pool.freeConnection(connection);
            return user;
        } catch (SQLException e)
        {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read users", e);
            throw new DBException("Error getting notes");
        } finally
        {
            try
            {
                rs.close();
                ps.close();
            } catch (SQLException ex)
            {
            }
            pool.freeConnection(connection);
        }
    }
    
}
