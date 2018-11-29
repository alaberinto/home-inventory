/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import datamodels.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

/**
 *
 * @author awarsyle
 */
public class AdminFilter implements Filter
{
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException
    {
            HttpServletRequest r = (HttpServletRequest)request;
            HttpSession session = r.getSession();
            UserService us = new UserService();
            User user = new User();
            String sessionUsername = (String) session.getAttribute("username");
            
            try
            {
                user = us.getUser(sessionUsername);
            } catch (Exception ex)
            {
                Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (user.getIsAdmin())
            {
                chain.doFilter(request, response);
            }
            else
            {
                HttpServletResponse resp = (HttpServletResponse)response;
                resp.sendRedirect("inventory");
            }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
        
    }

    
}
