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
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author pc
 */
@WebServlet(name = "AddNewPlantControler", urlPatterns = {"/AddNewPlantControler"})
public class AddNewPlantControler extends HttpServlet {

    private final String LOAD_DATA = "loadPlant.jsp";
    private final String ERROR_PAGE = "error.jsp";
    private final String NEW_PLANT_PAGE = "newplant.jsp";

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

        String xmlPath = request.getServletContext().getRealPath("/xml/plant.xml");
        String url = ERROR_PAGE;
        String action = request.getParameter("btnAction");
        try {
            if ("CreatePage".equals(action)) {
                List<CategoriesDTO> categories = new CategoryDAO().getAll(xmlPath);
                request.setAttribute("categories", categories);
                url = NEW_PLANT_PAGE;
            } else {
                String id = request.getParameter("id");

                String name = request.getParameter("name");
                String dateCreate = request.getParameter("dateCreate");
                String idCategory = request.getParameter("idCategory");
                String des = request.getParameter("description");
                Float price = Float.parseFloat(request.getParameter("price"));
                plantDTO dto = new plantDTO(id, name, price, des, idCategory, dateCreate);
                PlantDAO dao = new PlantDAO();
                try {
                    dao.newPlant(xmlPath, dto);
                } catch (TransformerException ex) {
                    Logger.getLogger(AddNewPlantControler.class.getName()).log(Level.SEVERE, null, ex);
                }
//                url = LOAD_DATA;
                request.setAttribute("Success", "Update success");
            }
        } catch (Exception e) {
            System.out.println("error nè nhe");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

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
            Logger.getLogger(AddNewPlantControler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AddNewPlantControler.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddNewPlantControler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AddNewPlantControler.class.getName()).log(Level.SEVERE, null, ex);
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
