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
	private ArrayList<ChanceCard> chanceCardElements;
	private ArrayList<CommunityChestCard> communityChestCardElements;
	private ArrayList<PropertyCard> propertyCardElements;
	private ArrayList<Card> cardElements;

	public ArrayList<BoardElement> handleBoardElementXML(String xmlFileName)
	{
		boardElements = new ArrayList<BoardElement>();
		getBoardElementCoordinates(xmlFileName);
		return boardElements;
	}

	//generic function which return an array of a card based type
	//some pervert stuff
	public <T> T handleCardsXML(String xmlFileName, String typeName, Class<T> type)
	{
		communityChestCardElements = new ArrayList<CommunityChestCard>();
		getCardElements(xmlFileName, typeName);
		return type.cast(communityChestCardElements);
	}

	private void getCardElements(String xmlFileName, String type) {
	  //Java does not support string switches...
		if ( type == "CommunityChest" )
		{
			getCommunityCardData(xmlFileName, type);
		}
		else if ( type == "Chance" )
		{
			//getChanceCardData(xmlFileName, type);
		}
		else if ( type == "Property" )
		{
			//getPropertyData(xmlFileName,type);
		}
	}

	private void getCommunityCardData(String xmlFileName, String type)
	{
		try {
			NodeList nList = getNodeListFromFile(xmlFileName);
			addElementsToList(nList, type);
		} catch (Exception e) {
			throw new NullPointerException("Cannot initialize document with wrong location!");
		}
	}

	private void getBoardElementCoordinates(String xmlFileName)
	{
		try {
			NodeList nList = getNodeListFromFile(xmlFileName);
			addElementsToList(nList, "Board");
		} catch (Exception e) {
			throw new NullPointerException("Cannot initialize document with wrong location!");
		}
	}

	//Type gives the ROOT element of the XML like "element" or "communitychest" etc
	private NodeList getNodeListFromFile(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		try{
			File fXmlFile = new File(xmlFileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			return doc.getElementsByTagName("element");
		} catch ( Throwable throwable ) {
		    System.out.println(throwable.getMessage());
		}
		return null;
	}

	private void addElementsToList(NodeList nList, String type) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if ( type == "Board")
				{
					BoardElement element = createBoardElement(eElement);
					boardElements.add(element);
				}
				else if ( type == "CommunityChest")
				{
					CommunityChestCard element = createCommunityChestElement(eElement);
					communityChestCardElements.add(element);
				}
				else if ( type == "Chance")
				{
					ChanceCard element = createChanceElement(eElement);
				}
				else if ( type == "Property")
				{
					PropertyCard element = createPropertyElement(eElement);
				}
			}
		}
	}

	private PropertyCard createPropertyElement(Element eElement) {
//		return new PropertyCard(
//			Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())
//			,eElement.getElementsByTagName("name").item(0).getTextContent()
//			,eElement.getElementsByTagName("description").item(0).getTextContent()
//			,Card.Type.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent())
//			,Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent())
//		);
		return null;
	}

	private ChanceCard createChanceElement(Element eElement) {
		return new ChanceCard(
			Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())
			,eElement.getElementsByTagName("name").item(0).getTextContent()
			,eElement.getElementsByTagName("description").item(0).getTextContent()
			,Card.Type.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent())
			,Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent())
		);
	}

	private CommunityChestCard createCommunityChestElement(Element eElement) {
		return new CommunityChestCard(
			Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())
			,eElement.getElementsByTagName("name").item(0).getTextContent()
			,eElement.getElementsByTagName("description").item(0).getTextContent()
			,Card.Type.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent())
			,Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent())
		);
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
