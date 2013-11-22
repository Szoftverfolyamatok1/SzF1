package Monopoly;

import java.util.ArrayList;

/**
 * User: Benjamin
 * Date: 2013.11.19.
 * Time: 21:50
 */
public class CardController {
	//Parse from XML
	//return specific vector of cards
	//getCommunityChestCards()
	//getPropertyCards()
	//getChanceCards()
	private ArrayList<CommunityChestCard> communityChestCards;
	private ArrayList<PropertyCard> propertyCards;
	private ArrayList<ChanceCard> chanceCards;
	private XMLParser xmlParser;

	public CardController(){
		xmlParser = new XMLParser();
		communityChestCards = new ArrayList<CommunityChestCard>();
		propertyCards = new ArrayList<PropertyCard>();
		chanceCards = new ArrayList<ChanceCard>();
	}

	public ArrayList<CommunityChestCard> processCommunityCards(){
		communityChestCards = xmlParser.handleCardsXML("C:\\Users\\Benjamin\\IdeaProjects\\Monopoly_JavaFX\\XML\\CommunityChestCards.xml"
			,"CommunityChest"
			,communityChestCards.getClass());
		System.out.println("Size: " + communityChestCards.size());
		return communityChestCards;
	}

	public ArrayList<ChanceCard> processChanceCards(){
		chanceCards = xmlParser.handleCardsXML("C:\\Users\\Benjamin\\IdeaProjects\\Monopoly_JavaFX\\XML\\ChanceCards.xml"
			,"Chance"
			,chanceCards.getClass());
		return chanceCards;
	}

	//propertycards' XML is not yet filled
	public ArrayList<PropertyCard> processPropertyCards(){
		propertyCards = xmlParser.handleCardsXML("C:\\Users\\Benjamin\\IdeaProjects\\Monopoly_JavaFX\\XML\\PropertyCards.xml"
			,"Property"
			,propertyCards.getClass());
		return propertyCards;
	}
}
