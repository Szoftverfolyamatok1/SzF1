package Monopoly;

import java.util.ArrayList;
import java.lang.*;

public class BoardElement {

	private int topLeftX;
	private int topLeftY;
	private int bottomRightX;
	private int bottomRightY;
	private String elementName;

	private ArrayList<BoardElement> boardCoordinateList;

	public BoardElement()
	{
		boardCoordinateList = new ArrayList<BoardElement>(new XMLParser().handleBoardXML("C:\\Users\\Benjamin\\IdeaProjects\\Monopoly_JavaFX\\XML\\BoardElements.xml"));
	}

	public BoardElement(Point topLeft, Point bottomRight, String elementName)
	{
		this.topLeftX = topLeft.getX();
		this.topLeftY = topLeft.getY();

		this.bottomRightX = bottomRight.getX();
		this.bottomRightY = bottomRight.getY();

		this.elementName = elementName;
	}

	public ArrayList<BoardElement> getBoardElementList()
	{
		return this.boardCoordinateList;
	}

	public Point getTopLeftCoordinates()
	{
		return new Point(this.topLeftX, this.topLeftY);
	}

	public Point getBottomRightCoordinate()
	{
		return new Point(this.bottomRightX, this.bottomRightY);
	}

	public String getElementName() {
		return this.elementName;
	}
}