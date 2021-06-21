// 
// Decompiled by Procyon v0.5.36
// 

package com.pranav.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.PrintWriter;
import com.pranav.entities.Flight;
import com.pranav.util.HibernateUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/list-flights" })
public class ListFlights extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.print("<a href=\"dashboard.html\">Dashboard</a><br/><hr>");
        out.print("<h1>Master View</h1>");
        try {
            final SessionFactory sFactory = HibernateUtil.buildSessionFactory();
            final Session session = sFactory.openSession();
            session.beginTransaction();
            final String query = "select f from Flight f inner join Airline a on f.airline=a.id";
            final List<Flight> flights = (List<Flight>)session.createQuery(query).list();
            if (flights.size() > 0) {
                out.println("<h1>List of all Flights: </h1>");
                out.println("<style> table,th,td { border : 1px solid black ; padding :15px;} </style>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>");
                out.println("Flight ID");
                out.println("</th>");
                out.println("<th>");
                out.println("Airline");
                out.println("</th>");
                out.println("<th>");
                out.println("Number Of Seats");
                out.println("</th>");
                out.println("<th>");
                out.println("Source");
                out.println("</th>");
                out.println("<th>");
                out.println("Desintation");
                out.println("</th>");
                out.println("<th>");
                out.println("Departure Date");
                out.println("</th>");
                out.println("<th>");
                out.println("Arrival Date");
                out.println("</th>");
                out.println("<th>");
                out.println("Price");
                out.println("</th>");
                out.println("</tr>");
                for (final Flight i : flights) {
                    if (i.getNumberOfSeats() > 0) {
                        out.println("<tr>");
                        out.println("<td>");
                        out.println(i.getId());
                        out.println("</td>");
                        out.println("<td>");
                        out.println(i.getAirline().getAirline().toString());
                        out.println("</td>");
                        out.println("<td>");
                        out.println(i.getNumberOfSeats());
                        out.println("</td>");
                        out.println("<td>");
                        out.println(i.getSource());
                        out.println("</td>");
                        out.println("<td>");
                        out.println(i.getDestination());
                        out.println("</td>");
                        out.println("<td>");
                        out.println(i.getDateOfDeparture());
                        out.println("</td>");
                        out.println("<td>");
                        out.println(i.getDateOfArrival());
                        out.println("</td>");
                        out.println("<td>");
                        out.println(i.getPrice());
                        out.println("</td>");
                        out.println("</tr>");
                    }
                }
                out.println("</table>");
            }
            else {
                out.print("<a href=\"dashboard.html\">Home</a>");
                out.print("<h1>Sorry there are no flights currently!<h1>");
            }
            session.close();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}