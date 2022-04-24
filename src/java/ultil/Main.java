package ultil;

import dto.CategoriesDTO;
import dto.plantDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;


import javax.xml.validation.SchemaFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {
//    public static final String xmlFilePath = "plant.xml";

    public static void GenerateXmlFile(String xmlFilePath) {


        try {

           DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("plantList");
            document.appendChild(root);

            Element plants = document.createElement("plants");
            root.appendChild(plants);
            Element categories = document.createElement("categories");
            root.appendChild(categories);

            Element category = document.createElement("category");
            categories.appendChild(category);

            Element category1 = document.createElement("category");
            categories.appendChild(category1);

            Element category2 = document.createElement("category");
            categories.appendChild(category2);

            Attr attr0 = document.createAttribute("cateId");
            attr0.setValue("AT01");
            category.setAttributeNode(attr0);

            Element cateName = document.createElement("name");
            cateName.appendChild(document.createTextNode("Fruit tree"));
            category.appendChild(cateName);

            Attr attr1 = document.createAttribute("cateId");
            attr1.setValue("CK01");
            category1.setAttributeNode(attr1);

            Element cateName1 = document.createElement("name");
            cateName1.appendChild(document.createTextNode("Ornamental plant"));
            category1.appendChild(cateName1);

            Attr attr2 = document.createAttribute("cateId");
            attr2.setValue("RM01");
            category2.setAttributeNode(attr2);

            Element cateName2 = document.createElement("name");
            cateName2.appendChild(document.createTextNode("Vegetable"));
            category2.appendChild(cateName2);


            for (int i = 1; i <=1000; i++) {
                // plant element
                Element plant = document.createElement("plant");
                plants.appendChild(plant);

                // set an attribute to plant element
                Attr attr = document.createAttribute("id");
                attr.setValue(String.valueOf(i));
                plant.setAttributeNode(attr);

                //you can also use staff.setAttribute("id", "1") for this

                // firstname element
                if(i <= 300) {
                Element name = document.createElement("name");
                name.appendChild(document.createTextNode("Jackfruit " + String.valueOf(i)));
                plant.appendChild(name);

                Element price = document.createElement("price");
                price.appendChild(document.createTextNode("20.5"));
                plant.appendChild(price);

                Element description = document.createElement("description");
                description.appendChild(document.createTextNode("Jackfruit have sweet and fragrant is good"));
                plant.appendChild(description);

                Element createDate = document.createElement("createDate");
                createDate.appendChild(document.createTextNode("2022-06-11"));
                plant.appendChild(createDate);

                // lastname element
                Element CateID = document.createElement("cateId");
                CateID.appendChild(document.createTextNode("AT01"));
                plant.appendChild(CateID);
                } else if(i <= 600){
                    Element name = document.createElement("name");
                name.appendChild(document.createTextNode("Mini lotus " + String.valueOf(i)));
                plant.appendChild(name);

                Element price = document.createElement("price");
                price.appendChild(document.createTextNode("15.5"));
                plant.appendChild(price);

                Element description = document.createElement("description");
                description.appendChild(document.createTextNode("Mini lotus pot"));
                plant.appendChild(description);

                Element createDate = document.createElement("createDate");
                createDate.appendChild(document.createTextNode("2022-04-10"));
                plant.appendChild(createDate);

                // lastname element
                Element CateID = document.createElement("cateId");
                CateID.appendChild(document.createTextNode("CK01"));
                plant.appendChild(CateID);
                }else if(i <= 1000){
                    Element name = document.createElement("name");
                name.appendChild(document.createTextNode("Cauliflower " + String.valueOf(i)));
                plant.appendChild(name);

                Element price = document.createElement("price");
                price.appendChild(document.createTextNode("6.5"));
                plant.appendChild(price);

                Element description = document.createElement("description");
                description.appendChild(document.createTextNode("White cauliflower"));
                plant.appendChild(description);

                Element createDate = document.createElement("createDate");
                createDate.appendChild(document.createTextNode("2022-03-06"));
                plant.appendChild(createDate);

                // lastname element
                Element CateID = document.createElement("cateId");
                CateID.appendChild(document.createTextNode("RM01"));
                plant.appendChild(CateID);
                }

                // create the xml file
                //transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                // If you use
                // StreamResult result = new StreamResult(System.out);
                // the output will be pushed to the standard output ...
                // You can use that for debugging

                transformer.transform(domSource, streamResult);
                System.out.println("Done creating XML File");
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
/// validation file
//        boolean flag = true;
//        try {
//            validateXMLSchema("plant.xml","plant.xsd");
//        } catch (SAXException e) {
//            e.printStackTrace();
//            flag =false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            flag= false;
//        }
//        System.out.println("xml file is valid"+ flag);
    }



    public static void validateXMLSchema(String xmlFile, String validationFile) throws SAXException, IOException {
        SchemaFactory sc = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ((sc.newSchema(new File(validationFile))).newValidator()).validate(new StreamSource((new File(xmlFile))));

    }
    
    public static Workbook exportXmlToExcel(String pathSave, List<plantDTO> plant, List<CategoriesDTO> categories) throws FileNotFoundException, IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("plants");
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.DIAMONDS);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 13);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("No");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Category");
        headerCell.setCellStyle(headerStyle);
        
        headerCell = header.createCell(4);
        headerCell.setCellValue("Description");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("Price");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(6);
        headerCell.setCellValue("Create Date");
        headerCell.setCellStyle(headerStyle);


        //body
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(false);
        int length = plant.size();
        for (int i = 0; i < length; i++) {
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(i + 1);
            cell.setCellStyle(style);

            plantDTO plant1 = plant.get(i);
            cell = row.createCell(1);
            cell.setCellValue(plant1.getId());
            cell.setCellStyle(style);

            String category = "";
            for (CategoriesDTO category1 : categories) {
                if (category1.getId().equals(plant.get(i).getCateID())) {
                    category = category1.getName();
                }
            }

            cell = row.createCell(2);
            cell.setCellValue(plant1.getName());
            cell.setCellStyle(style);
            
            cell = row.createCell(3);
            cell.setCellValue(category);
            cell.setCellStyle(style);
            
            cell = row.createCell(4);
            cell.setCellValue(plant1.getDescription());
            cell.setCellStyle(style);
        
            cell = row.createCell(5);
            cell.setCellValue("$" + plant1.getPrice());
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue(plant1.getCreateDate());
            cell.setCellStyle(style);

        }

        return workbook;
    }
}
