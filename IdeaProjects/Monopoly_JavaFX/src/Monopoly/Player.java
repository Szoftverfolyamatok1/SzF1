package Monopoly;

import java.util.ArrayList;

public abstract class Player {
	final private int MAX_DOUBLE_THROW_NUMBER_IN_A_ROW = 3;
	final private int TURNS_TO_GET_OUT_FROM_JAIL = 3;

	private String playerName;
	private int playerCash;
	private Point playerLocation;
	private boolean isPlayerInJail;
	private boolean canPlayerThrowOneMore;
	private int doubleThrowCount;
	private Dices currentThrowResult;
	private int playerTurnsInJail;
	private int currentThrowSum;

//	private ArrayList<CardElement> playerCardList;
	private ArrayList<BoardElement> playerPropertyList;

	public abstract void step(); //I think it will need few parameters

	//needs more outwork
	//Needs to be public
	public void initializePlayer()
	{
		this.playerTurnsInJail = 0;
		this.playerCash = 0;
		this.playerLocation = new Point(0,0);
		this.playerPropertyList = new ArrayList<BoardElement>();
		this.isPlayerInJail = false;
		this.canPlayerThrowOneMore = false;

		this.doubleThrowCount = 0;
		this.currentThrowResult = new Dices();
		this.currentThrowSum = 0;
	}

	public String getPlayerName() {
		return playerName;
	}

	//Needs to be public
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	//simply roll the dice, and check if it is double
	public void rollTheDice()
	{
		currentThrowSum = currentThrowResult.rollTheDice();
		checkIfItWasDouble();
		if (!getIsPlayerInJail())
			step();
	}

		//void instead of boolean return
		//need to get, if a throw was double
		//if there were 3 doubles in a row, then the player MUST go to jail
		//if the player was in jail, and threw double in 3 turns, then able to come out
		//else has to pay 50$s to the Bank
		//if the player threw double, then able to throw one more, while has fewer double throws than 3
		private void checkIfItWasDouble()
		{
			if (currentThrowResult.isDoubled())
			{
				handleIfThrowWasDouble();
			}
			else
			{
				handleIfThrowWasNotDouble();
			}
		}

			private void handleIfThrowWasNotDouble() {
				//player is in not jail, or this is the 4. turn in jail
				if ( getIsPlayerInJail() )
				{
					checkForInJailTurns();
				}

				setCanPlayerThrowOneMore(false);
				doubleThrowCount = 0;
			}

				//check if time spent in jail is < than 3
				private void checkForInJailTurns() {
					if ( playerTurnsInJail < TURNS_TO_GET_OUT_FROM_JAIL )
					{
						++playerTurnsInJail;
					}
					else
					{
						setIsPlayerInJail(false);
						playerTurnsInJail = 0;
					}
				}

			private void handleIfThrowWasDouble() {
				if ( !getCanPlayerThrowOneMore() && !getIsPlayerInJail() )
				{
					setCanPlayerThrowOneMore(true);
				}

				if ( getIsPlayerInJail() )
				{
					setIsPlayerInJail(false);
					setCanPlayerThrowOneMore(false);
					doubleThrowCount = -1;
				}

				++doubleThrowCount;
				if ( checkIfItWasThreeDoublesInARow() )
				{
				   if ( !getIsPlayerInJail() )
				   {
					   setIsPlayerInJail(true);
					   playerTurnsInJail = 0;
				   }
				}
			}

				//check if the player threw 3 doubles in a row ( definitely "csalik" )
				//needs to be public
				public boolean checkIfItWasThreeDoublesInARow()
				{
					return this.doubleThrowCount == MAX_DOUBLE_THROW_NUMBER_IN_A_ROW;
				}

	public boolean getIsPlayerInJail() {
			return this.isPlayerInJail;
	}

	private void setIsPlayerInJail(boolean playerInJail) {
		this.isPlayerInJail = playerInJail;
		setCanPlayerThrowOneMore(false);
		//doubleThrowCount = 0;
	}

	public boolean getCanPlayerThrowOneMore() {
		return canPlayerThrowOneMore;
	}

	private void setCanPlayerThrowOneMore(boolean canPlayerThrowOneMore) {
		this.canPlayerThrowOneMore = canPlayerThrowOneMore;
	}

	public boolean getIsCurrentThrowDoubled() {
		return currentThrowResult.isDoubled();
	}

	public int getPlayerCash() {
		return playerCash;
	}

	public int getCurrentThrowSum()
	{
		return currentThrowSum;
	}
}