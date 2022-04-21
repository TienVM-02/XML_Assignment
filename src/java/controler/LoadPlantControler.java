/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import dao.CategoryDAO;
import dao.PlantDAO;
import dto.CategoriesDTO;
import dto.plantDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author pc
 */
@WebServlet(name = "LoadPlantControler", urlPatterns = {"/LoadPlantControler"})
public class LoadPlantControler extends HttpServlet {
    private final String LOAD_PLANT = "loadPlant.jsp";
    private final String ERROR = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParserConfigurationException, SAXException {
        response.setContentType("text/html;charset=UTF-8");
         String url = ERROR;
        String xmlPath = request.getServletContext().getRealPath("/xml/plant.xml");
        String idCategory = request.getParameter("idCategory");
        String filter = request.getParameter("searchValue");
        request.setAttribute("filter", filter);
        
        
         request.setAttribute("idCategory", idCategory);
            CategoryDAO daoCate = new CategoryDAO();
            List<CategoriesDTO> categories = daoCate.getAll(xmlPath);
            request.setAttribute("categories", categories);
            PlantDAO dao = new PlantDAO();
            List<plantDTO> plant = dao.getAllPlant(xmlPath, idCategory, filter);
            request.setAttribute("plant", plant);
            url = LOAD_PLANT;
            request.getRequestDispatcher(url).forward(request, response);
            
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LoadPlantControler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LoadPlantControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LoadPlantControler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LoadPlantControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
