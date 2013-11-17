package Monopoly;

import java.util.ArrayList;

/**
 * User: Benjamin
 * Date: 2013.11.11.
 * Time: 13:31
 */
public class Bank {

	private int bankMoney;
	private ArrayList<BoardElement> bankProperties;

	public Bank(){
		//initializes the bank's money, cards and properties
	}

	public int giveMoneyToPlayer(Player player, int amount) {
		//gives money to the given player
		//else gives 0
		return 0;
	}

	public void givePropertyToPlayer(Player player, String propertyName){
		//gives a property to the given player
		//at first the bank owns all of the properties
		//if a player steps on a property, he/she can buy that property from the Bank
		//1.) the player gives the given money to the bank
		//2.) the bank gives the player the property ( its added to the player's property list )
		//getMoneyFromPlayer(player, getPriceOfProperty());
	}

	private int getPriceOfProperty(String propertyName) {
		return 0;
	}

	public void getMoneyFromPlayer(Player player, int amount){
		//gets money from the given player
	}

	private BoardElement getBoardElementFromName(String propertyName)
	{
		for( BoardElement element : bankProperties){
			if ( element.getElementName() == propertyName ){
				return element;
			}
		}
		return null;
	}

}
