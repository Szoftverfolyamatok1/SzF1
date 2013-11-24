package Monopoly;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BoardController {
	@FXML public Pane paneLayoutLeft;
	@FXML public Pane paneLayoutRight;

	public ImageView diceImageView;

	private BoardElement boardElement;
	private String elementName;
	private Label mouseCoordinateLabel;
	private DropShadow shadow;
	public Stage stage;
	private Label diceLabel;
	private Label doubledDiceThrowLabel;
	private Bank bank;

	private ArrayList<Player> playerList;
	private int playerCounter;

	public void initialize() {
		playerCounter = 0;
		playerList = new ArrayList<Player>();

		initializeBank();
		initializePlayers(4);
		initializeVariables();

		paneLayoutLeft.addEventHandler(MouseEvent.MOUSE_MOVED,
			new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent) {
					mouseCoordinateLabel.setText(Double.toString(mouseEvent.getX()) + " " + Double.toString(mouseEvent.getY()));

					for (BoardElement coord : boardElement.getBoardElementList())
					{
						getBoardElementProperties(mouseEvent, coord);
					}
				}
			});

		initializePlayerLabels();
		initializeDiceOnBoard();
	}

	private void initializeBank() {
		bank = new Bank();
		CardController cc = new CardController();
		cc.processCommunityCards();
		cc.processChanceCards();
		cc.processPropertyCards();
	}

	private void initializePlayers(int playerNumber)
	{
		//set current player's name
		HumanPlayer humanPlayer = new HumanPlayer("HumanPlayer");
		playerList.add(humanPlayer);
		for ( int i = 0; i < playerNumber-1; ++i )
		{
			AIPlayer aiPlayer = new AIPlayer("Computer Player " + (i+1));
			playerList.add(aiPlayer);
		}
	}

	private void nextPlayer(){
		enableDiceOnBoard();
		diceLabel.setVisible(false);
		doubledDiceThrowLabel.setVisible(false);
		++playerCounter;
	}

	private void disableDiceOnBoard() {
		//better idea?
		diceImageView.setVisible(false);
	}

	private void enableDiceOnBoard() {
		diceImageView.setVisible(true);
	}

	private void initializeDiceOnBoard() {

		diceImageView = new ImageView();
		diceImageView.relocate(120,120);
		diceImageView.setImage(new Image(getClass().getResourceAsStream("dice.png"), 100, 100, false, false));
		paneLayoutLeft.getChildren().add(diceImageView);

		diceImageView.addEventHandler(MouseEvent.MOUSE_ENTERED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					diceImageView.setEffect(shadow);

				}
			});
		diceImageView.addEventHandler(MouseEvent.MOUSE_EXITED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					diceImageView.setEffect(null);
				}
			});
		diceImageView.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {

					System.out.println("Dice clicked");

					playerList.get(getPlayerCounter_CheckForOrderValidity()).rollTheDice();
					diceLabel.setText(playerList.get(getPlayerCounter_CheckForOrderValidity()).getPlayerName() + ", you have rolled: " + playerList.get(playerCounter).getCurrentThrowSum());
					diceLabel.relocate(250,120);
					diceLabel.setVisible(true);
					doubledDiceThrowLabel.setVisible(false);

					if ( playerList.get(playerCounter).getIsCurrentThrowDoubled() )
					{
						doubledDiceThrowLabel.setVisible(true);
						doubledDiceThrowLabel.relocate(250,140);
						doubledDiceThrowLabel.setText("Wow, it was a double throw!");
					}
					else
						disableDiceOnBoard();
				}
			});
	}

	private void initializeVariables() {
		diceLabel = new Label();
		doubledDiceThrowLabel = new Label();
		paneLayoutLeft.getChildren().add(diceLabel);
		paneLayoutLeft.getChildren().add(doubledDiceThrowLabel);
		elementName = new String();
		boardElement = new BoardElement();
		shadow = new DropShadow();
		mouseCoordinateLabel = new Label();
			mouseCoordinateLabel.relocate(0,-20);
			mouseCoordinateLabel.setFont(new Font("Arial", 20));
			paneLayoutRight.getChildren().add(mouseCoordinateLabel);
		stage  = new Stage();
	}

	private void initializePlayerLabels() {
		for ( Integer i = 0; i < 4; ++i )
		{
			Label playerLabel = new Label("Label " + i.toString());
			playerLabel.relocate(0, i * 30);

			Text text = new Text("Text " + i.toString());
			text.relocate(50,i*30);

			paneLayoutRight.getChildren().addAll(playerLabel, text);
		}

		Button nextTurnButton = new Button("Next Turn");
		nextTurnButton.relocate(0,200);
		nextTurnButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if ( !diceImageView.isVisible())
						nextPlayer();
				}
			});
		paneLayoutRight.getChildren().add(nextTurnButton);
	}

	private void getBoardElementProperties(MouseEvent mouseEvent, final BoardElement coord) {
		if (isMouseCoordInRectangle(mouseEvent, coord))
		{
			elementName = coord.getElementName();
			paneLayoutLeft.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (isMouseCoordInRectangle(mouseEvent, coord))
						{
							showNewWindow();
						}
					}
				});
		}
	}

	private void showNewWindow() {
		try{
			FXMLLoader loader = new FXMLLoader();
			Parent p = loader.load(getClass().getResource("card.fxml"));
			stage.setTitle(elementName);
			Scene scene = new Scene(p, 250, 400);
			stage.setScene(scene);
			stage.show();
		} catch (Throwable ex) {
			//Location is required exception: class.getResource() will return null
			//if it does not find the resource.
			System.out.println(ex.getMessage());
		}
	}

	private boolean isMouseCoordInRectangle(MouseEvent mouseEvent, BoardElement coord) {
		return (mouseEvent.getX() >= coord.getTopLeftCoordinates().getX()
			&& mouseEvent.getY() >= coord.getTopLeftCoordinates().getY() )
			&& ( mouseEvent.getX() <= coord.getBottomRightCoordinate().getX()
			&& mouseEvent.getY() <= coord.getBottomRightCoordinate().getY());
	}

	//If we are going to overflow from playerList, set the counter to 0
	private int getPlayerCounter_CheckForOrderValidity() {
		if ( playerList.size() <= playerCounter)
			playerCounter = 0;
		return playerCounter;
	}
}
