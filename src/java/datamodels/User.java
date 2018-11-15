package datamodels;

/**
 *
 * @author 587568
 */
public class User
{
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int isActive;
    private int isAdmin;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public int getIsActive() 
    {
        return isActive;
    }

    public void setIsActive(int isActive) 
    {
        this.isActive = isActive;
    }

    public int getIsAdmin() 
    {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) 
    {
        this.isAdmin = isAdmin;
    }
}
