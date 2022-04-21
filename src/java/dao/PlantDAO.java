/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.plantDTO;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author pc
 */
public class PlantDAO {

    public static Document getDocument(String path_to_file) {
        Document d = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            d = db.parse(path_to_file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            d = null;
        }
        return d;
    }

    public static String getXMLContent(Document d) {
        String result = "";
        try {
            TransformerFactory tran = TransformerFactory.newInstance();
            Transformer tf = null;
            tf = tran.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult();
            DOMSource dsr = new DOMSource();
            tf.transform(dsr, sr);
            result = sw.toString();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void saveXMLContent(Document d, String path_to_file) throws TransformerException {
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer tf = tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource dsr = new DOMSource(d);
        StreamResult rs = new StreamResult(path_to_file);
        tf.transform(dsr, rs);
    }

    public List<plantDTO> getAllPlant(String fileName, String category, String filter)
            throws ParserConfigurationException,
            SAXException,
            IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));
        doc.getDocumentElement().normalize();
        NodeList list = doc.getElementsByTagName("plant");

        List<plantDTO> plant = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String idCategory = element.getElementsByTagName("cateId").item(0).getTextContent();
                if (category == null) {
                    String name = element.getElementsByTagName("name").item(0).getTextContent();

                    String id = element.getAttribute("id");
                    Float price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
                    String dateCreate = element.getElementsByTagName("createDate").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    plant.add(new plantDTO(id, name, price, description, idCategory, dateCreate));

                } else if (idCategory.equals(category)) {
                    String name = element.getElementsByTagName("name").item(0).getTextContent();

                    if (name.indexOf(filter) > 0) {
                        String id = element.getAttribute("id");
                        Float price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
                        String dateCreate = element.getElementsByTagName("createDate").item(0).getTextContent();
                        String description = element.getElementsByTagName("description").item(0).getTextContent();
                        plant.add(new plantDTO(id, name, price, description, idCategory, dateCreate));
                    }
                    if (filter == "") {
                        String id = element.getAttribute("id");
                        Float price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
                        String dateCreate = element.getElementsByTagName("createDate").item(0).getTextContent();
                        String description = element.getElementsByTagName("description").item(0).getTextContent();
                        plant.add(new plantDTO(id, name, price, description, idCategory, dateCreate));
                    }
                }
            }
        }
        return plant;
    }

    public boolean updatePlantById(String fileName, plantDTO updatePlant)
            throws ParserConfigurationException,
            SAXException,
            IOException,
            TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));
        doc.getDocumentElement().normalize();

        NodeList list = doc.getElementsByTagName("plant");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String Id = element.getAttribute("id");
                if (updatePlant.getId().equals(Id)) {
                    element.getElementsByTagName("name").item(0).setTextContent(updatePlant.getName().trim());
                    element.getElementsByTagName("price").item(0).setTextContent("" + updatePlant.getPrice());
                    element.getElementsByTagName("cateId").item(0).setTextContent(updatePlant.getCateID());
                    element.getElementsByTagName("createDate").item(0).setTextContent(updatePlant.getCreateDate());
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(doc);
                    StreamResult streamResult = new StreamResult(new File(fileName));
                    transformer.transform(domSource, streamResult);
                    return true;
                }
            }
        }

        return false;
    }
    
    public void newPlant(String fileName, plantDTO dto)
            throws ParserConfigurationException,
            SAXException,
            IOException,
            TransformerConfigurationException,
            TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File(fileName));

        doc.getDocumentElement().normalize();

        Node nodeProducts = doc.getElementsByTagName("plants").item(0);

        Element plant = doc.createElement("plant");
        
        Attr attr = doc.createAttribute("id");
        attr.setValue(dto.getId());
        plant.setAttributeNode(attr);

        Element name = doc.createElement("name");
        name.setTextContent(dto.getName());
        plant.appendChild(name);
        Element price = doc.createElement("price");
        price.setTextContent("" + dto.getPrice());
        plant.appendChild(price);
        Element des = doc.createElement("description");
        des.setTextContent(dto.getDescription());
        plant.appendChild(des);
        Element dateCreate = doc.createElement("createDate");
        dateCreate.setTextContent(dto.getCreateDate());
        plant.appendChild(dateCreate);
        Element idCate = doc.createElement("cateId");
        idCate.setTextContent(dto.getCateID());
        plant.appendChild(idCate);
       
       
        nodeProducts.appendChild(plant);

        //create the xml file 
        // transform  the DOM object to an XML file 
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(fileName));
        transformer.transform(domSource, streamResult);
    }
    
    public plantDTO RomoveById(String fileName, String id)
            throws ParserConfigurationException,
            SAXException,
            IOException,
            TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));
        doc.getDocumentElement().normalize();


        NodeList list = doc.getElementsByTagName("plant");

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String idP = element.getAttribute("id");

                if (id.equals(idP)) {
                    String idCategory = element.getElementsByTagName("cateId").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    Float price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
                    String dateCreate = element.getElementsByTagName("createDate").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();

                    Node products = doc.getElementsByTagName("plants").item(0);
                    products.removeChild(element);

                    //create the xml file 
                    // transform  the DOM object to an XML file 
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(doc);
                    StreamResult streamResult = new StreamResult(new File(fileName));
                    transformer.transform(domSource, streamResult);
                    return new plantDTO(idP, name, price, idCategory, dateCreate, description);

                }

            }
        }
        return null;
    }

}
