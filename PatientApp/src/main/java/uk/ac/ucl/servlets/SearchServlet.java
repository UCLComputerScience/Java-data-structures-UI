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
import java.text.ParseException;
import java.util.List;

// The servlet invoked to perform a search.
// The url http://localhost:8080/runsearch.html is mapped to calling doPost on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/runSearch.html")
public class SearchServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Use the model to do the search and put the results into the request object sent to the
    // Java Server Page used to display the results.
    try {
      Model model = ModelFactory.getModel();
      String searchType = request.getParameter("type");
      String nameType = "searchName";
      String ageType = "searchAgeRange";
      String searchGender = request.getParameter("searchGender");
      String searchCity = request.getParameter("searchCity");
      String searchState = request.getParameter("searchState");
      String searchRace = request.getParameter("searchRace");
      if(searchType.equals(nameType)){
        List<Patient> searchResultPatient = model.searchFor(request.getParameter("searchString"));
        List<Patient> searchCityList = model.searchCity(searchCity,searchResultPatient);
        List<Patient> searchGenderList = model.searchGender(searchGender,searchCityList);
        List<Patient> searchStateList = model.searchState(searchState,searchGenderList);
        List<Patient> searchRaceList = model.searchRace(searchRace,searchStateList);
        List<String> searchResult = model.processData(searchRaceList);
        request.setAttribute("result", searchResult);
      }
      else if(searchType.equals(ageType)) {
        List<Patient> searchResultPatient = model.searchAge(request.getParameter("searchString"));
        List<Patient> searchGenderList = model.searchGender(searchGender,searchResultPatient);
        List<Patient> searchCityList = model.searchCity(searchCity,searchGenderList);
        List<Patient> searchRaceList = model.searchRace(searchRace,searchCityList);
        List<Patient> searchStateList = model.searchState(searchState,searchRaceList);
        List<String> searchResult = model.processData(searchStateList);
        request.setAttribute("result", searchResult);
      }

      // Invoke the JSP page.
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
      dispatch.forward(request, response);
    }
    catch (Exception e){
    }
  }
}
