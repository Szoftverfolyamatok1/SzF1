package Monopoly;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLParser{

	private ArrayList<BoardElement> boardElements;

	public ArrayList<BoardElement> handleBoardXML(String xmlFileName)
	{
		boardElements = new ArrayList<BoardElement>();
		getBoardElementCoordinatesFromXML(xmlFileName);
		return boardElements;
		//return getBoardElementCoordinatesFromXML(xmlFileName);
	}

	private void getBoardElementCoordinatesFromXML(String xmlFileName)
	{
		try {
			NodeList nList = getNodeListFromFile(xmlFileName);
			addElementsToList(nList);
		} catch (Exception e) {
		    throw new NullPointerException("Cannot initialize document with wrong location!");
		}
		//return boardElements;
	}

	private NodeList getNodeListFromFile(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		try{
			File fXmlFile = new File(xmlFileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			return doc.getElementsByTagName("element");
		} catch ( Throwable throwable ) {
		    System.out.println(throwable.getMessage());
		}
		return null;
	}

	private void addElementsToList(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				BoardElement element = createBoardElement(eElement);
				boardElements.add(element);
			}
		}
	}

	private BoardElement createBoardElement(Element eElement) {
		Integer topLeftX = Integer.parseInt(eElement.getElementsByTagName("topLeftX").item(0).getTextContent());
		Integer topLeftY = Integer.parseInt(eElement.getElementsByTagName("topLeftY").item(0).getTextContent());

		Integer bottomRightX = Integer.parseInt(eElement.getElementsByTagName("bottomRightX").item(0).getTextContent());
		Integer bottomRightY = Integer.parseInt(eElement.getElementsByTagName("bottomRightY").item(0).getTextContent());

		return new BoardElement(
			new Point(topLeftX, topLeftY)
			,new Point(bottomRightX, bottomRightY)
			,eElement.getAttribute("id")
		);
	}
}
