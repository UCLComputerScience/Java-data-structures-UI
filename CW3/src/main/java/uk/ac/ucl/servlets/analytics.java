package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.Patient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/analytics.html")
public class analytics extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            ModelFactory mf = new ModelFactory();
            Model model = mf.getModel();
            String oldestPatient = model.oldestPatient();
            String youngestPatient = model.youngestPatient();
            int averageAge = model.getAverageAge();
            List<Integer> patientAgeRange = model.patientAgeRange();
            request.setAttribute("averageAge", averageAge);
            request.setAttribute("oldestPatient", oldestPatient);
            request.setAttribute("youngestPatient", youngestPatient);
            request.setAttribute("patientAgeRange",patientAgeRange);

            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/analytics.jsp");
            dispatch.forward(request, response);
        }
        catch (Exception e){}

    }
}
