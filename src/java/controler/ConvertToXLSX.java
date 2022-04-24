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
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;
import ultil.Main;

/**
 *
 * @author vomin
 */
@WebServlet(name = "ConvertToXLSX", urlPatterns = {"/ConvertToXLSX"})
public class ConvertToXLSX extends HttpServlet {
      private final String ERROR_PAGE = "error.jsp";

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
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment; filename=plant.xlsx");
            response.setCharacterEncoding("UTF-8");
        try {

            String pathSave = request.getServletContext().getRealPath("/xml/plant.xlsx");
            String xmlPath = request.getServletContext().getRealPath("/xml/plant.xml");
            PlantDAO dao = new PlantDAO();
            CategoryDAO cdao = new CategoryDAO();
            List<plantDTO> plant = dao.getAllPlant(xmlPath, null, null);
            List<CategoriesDTO> categories = cdao.getAll(xmlPath);
            Workbook workbook = Main.exportXmlToExcel(pathSave, plant, categories);
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ConvertToXLSX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ConvertToXLSX.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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