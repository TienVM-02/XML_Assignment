/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.catalina.connector.InputBuffer;
import org.xml.sax.SAXException;
import ultil.Main;

/**
 *
 * @author pc
 */
@WebServlet(name = "CheckDataControler", urlPatterns = {"/CheckDataControler"})
@MultipartConfig
public class CheckDataControler extends HttpServlet {
    private final String INDEX_PAGE = "index.html";
    private final String ERROR_PAGE = "error.html";
    private final String CHECK_FILE_FAIL_PAGE = "failed.jsp";
    private final String CHECK_FILE_SUCCESS_PAGE = "thanhcong.jsp";

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
            throws ServletException, IOException, SAXException {
        response.setContentType("text/html;charset=UTF-8");
         String url = CHECK_FILE_FAIL_PAGE;
        Part filePart = request.getPart("data");
        
            String xmlPath = request.getServletContext().getRealPath("/xml/plant.xml");
            String xsdPath = request.getServletContext().getRealPath("/xml/plant.xsd");
            this.writeFile(request, filePart);
         boolean flag = true;
        try{
           
            Main.validateXMLSchema(xmlPath,xsdPath);
        }catch(Exception e){
            flag= false;
//            url = CHECK_FILE_FAIL_PAGE;
        }
//        url = CHECK_FILE_SUCCESS_PAGE;
        if(flag){
            url = CHECK_FILE_SUCCESS_PAGE;
        } else {
            url = CHECK_FILE_FAIL_PAGE;
        }
        
        System.out.println("xml file is valid"+ flag);
        
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
        } catch (SAXException ex) {
            Logger.getLogger(CheckDataControler.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SAXException ex) {
            Logger.getLogger(CheckDataControler.class.getName()).log(Level.SEVERE, null, ex);
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

    private void writeFile(HttpServletRequest request, Part filePart) throws IOException {
        
        InputStream fileContent = filePart.getInputStream();
        String path = getServletContext().getRealPath("/xml/plant.xml");
        FileOutputStream fos = new FileOutputStream(path, false);
        try {
            int read;
            byte[] bytes = new byte[InputBuffer.DEFAULT_BUFFER_SIZE];
            while ((read = fileContent.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
        //To change body of generated methods, choose Tools | Templates.
    }

}
