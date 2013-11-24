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
	private ArrayList<Card> cardElements;

	public ArrayList<BoardElement> handleBoardElementXML(String xmlFileName)
	{
		boardElements = new ArrayList<BoardElement>();
		getBoardElementCoordinates(xmlFileName);
		System.out.println("Loaded BoardElement, Counter: " + boardElements.size());
		return boardElements;
	}

	//generic function which returns an array of a card based type
	//some pervert stuff
	public <T> T handleCardsXML(String xmlFileName, String typeName, Class<T> type)
	{
		cardElements = new ArrayList<Card>();
		getCardData(xmlFileName, typeName);
		System.out.println("Loaded " + typeName + ", Counter: " + cardElements.size());
		return type.cast(cardElements);
	}

	private void getCardData(String xmlFileName, String type)
	{
		try {
			NodeList nList = getNodeListFromFile(xmlFileName);
			addCardElementsToList(nList, type);
		} catch (Exception e) {
//			throw new NullPointerException("Cannot initialize document with wrong location!");
		}
	}

	private void getBoardElementCoordinates(String xmlFileName)
	{
		try {
			NodeList nList = getNodeListFromFile(xmlFileName);
			addBoardElementsToList(nList);
		} catch (Exception e) {
//			throw new NullPointerException("Cannot initialize document with wrong location!");
		}
	}

	//gets the node list from the file ( nList )
	private NodeList getNodeListFromFile(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		try{
			//sample XMLParser code, nothing to change
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

	//hmm...1
	private void addBoardElementsToList(NodeList nList)
	{
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
					BoardElement element = createBoardElement(eElement);
					boardElements.add(element);
			}
		}
	}

	//hmm...2
	private void addCardElementsToList(NodeList nList, String type) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				if ( type == "CommunityChest" ) {
					cardElements.add(createCommunityChestElement(eElement));
				}
				else if ( type == "Chance" ) {
					cardElements.add(createChanceElement(eElement));
				}
				else if ( type == "Property" ) {
					cardElements.add(createPropertyElement(eElement));
				}
				//endif
			}
		}
	}

	//get the node's elements and create a Property card
	private PropertyCard createPropertyElement(Element eElement) {
		if ( PropertyCard.PropertyType.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent()) == PropertyCard.PropertyType.UTILITY )
		{
		   return new UtilityPropertyCard(
			   Integer.parseInt(eElement.getAttribute("id"))
			   ,eElement.getElementsByTagName("name").item(0).getTextContent()
			   ,Integer.parseInt(eElement.getElementsByTagName("rentalValue1").item(0).getTextContent())
			   ,Integer.parseInt(eElement.getElementsByTagName("rentalValue2").item(0).getTextContent())
		   );
		}
		else if (PropertyCard.PropertyType.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent()) == PropertyCard.PropertyType.RAILING )
		{
		   return new RailingPropertyCard(
			   Integer.parseInt(eElement.getAttribute("id"))
			   ,eElement.getElementsByTagName("name").item(0).getTextContent()
			   ,Integer.parseInt(eElement.getElementsByTagName("rentalValue1").item(0).getTextContent())
			   ,Integer.parseInt(eElement.getElementsByTagName("rentalValue2").item(0).getTextContent())
			   ,Integer.parseInt(eElement.getElementsByTagName("rentalValue3").item(0).getTextContent())
			   ,Integer.parseInt(eElement.getElementsByTagName("rentalValue4").item(0).getTextContent())
			   ,Integer.parseInt(eElement.getElementsByTagName("mortgageValue").item(0).getTextContent())
		   );
		}
		else if ( PropertyCard.PropertyType.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent()) == PropertyCard.PropertyType.SIMPLE )
		{
			return new PlotPropertyCard(
				Integer.parseInt(eElement.getAttribute("id"))
				,eElement.getElementsByTagName("name").item(0).getTextContent()
				,PlotPropertyCard.Colour_Type.valueOf(eElement.getElementsByTagName("colour").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("rentalValue0").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("rentalValue1").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("rentalValue2").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("rentalValue3").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("rentalValue4").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("rentalValueHotel").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("mortgageValue").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("houseCost").item(0).getTextContent())
				,Integer.parseInt(eElement.getElementsByTagName("hotelCost").item(0).getTextContent())
			);
		}
		return null;
	}

	//get the node's elements and create a Chance card
	private ChanceCard createChanceElement(Element eElement) {
		return new ChanceCard(
			Integer.parseInt(eElement.getAttribute("id"))
			,eElement.getElementsByTagName("name").item(0).getTextContent()
			,eElement.getElementsByTagName("description").item(0).getTextContent()
			,Card.Type.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent())
			,eElement.getElementsByTagName("amount").item(0).getTextContent()
		);
	}

	//get the node's elements and create a Community card
	private CommunityChestCard createCommunityChestElement(Element eElement) {
		return new CommunityChestCard(
			Integer.parseInt(eElement.getAttribute("id"))
			,eElement.getElementsByTagName("name").item(0).getTextContent()
			,eElement.getElementsByTagName("description").item(0).getTextContent()
			,Card.Type.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent())
			,eElement.getElementsByTagName("amount").item(0).getTextContent()
		);
	}

	//get the node's elements and create a Board element
	private BoardElement createBoardElement(Element eElement) {
		return new BoardElement(
			eElement.getAttribute("id")
			,new Point(Integer.parseInt(eElement.getElementsByTagName("topLeftX").item(0).getTextContent())
					 ,Integer.parseInt(eElement.getElementsByTagName("topLeftY").item(0).getTextContent()))
			,new Point(Integer.parseInt(eElement.getElementsByTagName("bottomRightX").item(0).getTextContent())
					 ,Integer.parseInt(eElement.getElementsByTagName("bottomRightY").item(0).getTextContent()))
		);
	}
}
