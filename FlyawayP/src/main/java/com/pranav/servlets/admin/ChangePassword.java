// 
//Decompiled by Procyon v0.5.36
// 

package com.pranav.servlets.admin;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import com.pranav.util.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/change-password" })
public class ChangePassword extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        final String password = request.getParameter("password");
        final String confirmPassword = request.getParameter("confirmpassword");
        if (password.equals(confirmPassword)) {
            try {
                final HttpSession httpsession = request.getSession();
                final SessionFactory sFactory = HibernateUtil.buildSessionFactory();
                final Session session = sFactory.openSession();
                session.beginTransaction();
                final String email = (String)httpsession.getAttribute("username");
                final String query = "select id from Admin a where a.email = '" + email + "'";
                final int resultFromQ1 = (int)session.createQuery(query).getSingleResult();
                final Query q3 = session.createQuery("update Admin set password=:n where id=:i");
                q3.setParameter("n", (Object)confirmPassword);
                q3.setParameter("i", (Object)resultFromQ1);
                final int status = q3.executeUpdate();
                System.out.println(status);
                session.getTransaction().commit();
                session.close();
                out.write("Password Changed Succesfully");
                out.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            out.print("Sorry, your passwords do not match");
        }
    }
}