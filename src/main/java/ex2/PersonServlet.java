package ex2;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 26, 2017
 */
@WebServlet(urlPatterns={"/PersonServlet"})
public class PersonServlet extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    Gson gson = new Gson();
    private static int index = 0;
   private static List<Person> persons = new ArrayList(){
       {
           add(new Person(1, "Hans", "Pedersen", 57));
           add(new Person(2, "Gerda", "von Höve", 89));
           add(new Person(3, "Frederik", "Brahms", 4));
           add(new Person(4, "Hanne", "Jash", 46));
           add(new Person(5, "Henriette", "Pust", 32));
           add(new Person(6, "Aishe", "Hassimi", 51));
           add(new Person(7, "Kasper", "Enoksen", 33));
       }
   };
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           String person = gson.toJson(persons.get(index));
           if(++index >= persons.size()) index = 0;
            out.print(person);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

           request.getReader().lines().forEach((x)->{
               Person p = gson.fromJson(x, Person.class);
               p.setId(persons.size()+1);
               persons.add(p);
               out.println(p.getFirstname()+" "+p.getLastname()+" was added to the list");
           });
           //out.println(firstname);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
