package Monopoly;

/**
 * User: Benjamin
 * Date: 2013.11.21.
 * Time: 0:54
 */
public class PlotPropertyCard extends PropertyCard {
	private Integer rentalValueNoHouses;
	private Integer rentalValueOneHouse;
	private Integer rentalValueTwoHouses;
	private Integer rentalValueThreeHouses;
	private Integer rentalValueFourHouses;
	private Integer rentalValueHotel;

	private Integer houseCost;
	private Integer hotelCost;

	public enum Colour_Type {
		BROWN
		,RED
		,LIGHT_BLUE
		,BLUE
		,YELLOW
		,PURPLE
		,GREEN
		,ORANGE
	}

	private Colour_Type colourType;

	//Wow, O.o' Ezt még átgondoljuk...
	public PlotPropertyCard(Integer id
							,String name
							,Colour_Type cType
							,Integer rentalZero
							,Integer rentalOne
							,Integer rentalTwo
							,Integer rentalThree
							,Integer rentalFour
							,Integer rentalHotel
							,Integer houseCost
							,Integer hotelCost
							,Integer mortgage)
	{
		this.setId(id);
		this.setCardName(name);
		this.colourType = cType;
		this.rentalValueNoHouses = rentalZero;
		this.rentalValueOneHouse = rentalOne;
		this.rentalValueTwoHouses = rentalTwo;
		this.rentalValueThreeHouses = rentalThree;
		this.rentalValueFourHouses = rentalFour;
		this.rentalValueHotel = rentalHotel;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
		this.setMortgageValue(mortgage);
	}

	public Integer getRentalValueNoHouses() {
		return rentalValueNoHouses;
	}

	public Integer getRentalValueOneHouse() {
		return rentalValueOneHouse;
	}

	public Integer getRentalValueTwoHouses() {
		return rentalValueTwoHouses;
	}

	public Integer getRentalValueThreeHouses() {
		return rentalValueThreeHouses;
	}

	public Integer getRentalValueFourHouses() {
		return rentalValueFourHouses;
	}

	public Integer getRentalValueHotel() {
		return rentalValueHotel;
	}

	public Integer getHouseCost() {
		return houseCost;
	}

	public Integer getHotelCost() {
		return hotelCost;
	}
}
