/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class MainControler extends HttpServlet {

    private final String INDEX_PAGE = "index.jsp";
    private final String DOWLOAD_DATA = "DownloadControler";
    private final String CHECK_DATA = "CheckDataControler";
    private final String LOAD_DATA = "LoadPlantControler";
    private final String UPDATE_DATA = "UpdatePlantControler";
    private final String CREATE_DATA = "AddNewPlantControler";
    private final String REMOVE_DATA = "RemovePlantControler";

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
        String action = request.getParameter("btnAction");
        String url = INDEX_PAGE;
        try {
            if ("Generate Data".equals(action)) {
                url = DOWLOAD_DATA;
            }
            if ("Check Data".equals(action)) {
                url = CHECK_DATA;
            }
            if ("LoadProduct".equals(action) || "search".equals(action)) {
                url = LOAD_DATA;
            }
            if ("update".equals(action)) {
                url = UPDATE_DATA;
            }if ("Create".equals(action)|| "CreatePage".equals(action)) {
                url = CREATE_DATA;
            }if("deletePlant".equals(action)){
                url = REMOVE_DATA;
            }

        } catch (Exception e) {
            e.printStackTrace();
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
