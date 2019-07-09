
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhengyangduan
 */
@WebServlet(urlPatterns = {"/newapplication"})
public class newapplication extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // get data from view layer
            String fmid = request.getParameter("username");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String homeunit = request.getParameter("homeunit");
            String rank = request.getParameter("rank");
            String proposal = request.getParameter("proposal");
            String justification = request.getParameter("justification");
            String budget = request.getParameter("budget");
            String url = "mainview2.html?user=" + fmid;
            applicationDA ada = new applicationDA(); //init DA class
            ada.addNewApplication(fmid,name,address,homeunit,proposal,justification,budget,rank);
            
            response.sendRedirect(url);
            // add new application
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
  
}
