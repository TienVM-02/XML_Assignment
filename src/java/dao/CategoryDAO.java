/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CategoriesDTO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pc
 */
public class CategoryDAO {
     public static final String xmlFilePath = "C:\\Users\\pc\\Downloads\\Assisgment_XML\\web\\xml\\plant.xml";
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
       
        CategoryDAO daoCate = new CategoryDAO();
      
        List<CategoriesDTO> categories = daoCate.getAll(xmlFilePath);
        System.out.println(categories + "heheh");
    }
    
 public List<CategoriesDTO> getAll(String fileName)
            throws ParserConfigurationException,
            SAXException,
            IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));
        doc.getDocumentElement().normalize();
        NodeList list = doc.getElementsByTagName("category");
        List<CategoriesDTO> categories = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String id = element.getAttribute("cateId");
                String name = element.getElementsByTagName("name").item(0).getTextContent();

                CategoriesDTO category = new CategoriesDTO(name, id);
                categories.add(category);
            }
        }
        return categories;
    }
    
   
}
