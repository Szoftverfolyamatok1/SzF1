package Monopoly;

/**
 * User: Benjamin
 * Date: 2013.11.19.
 * Time: 21:48
 */
public class PropertyCard extends Card {
	//Represents the property cards
	//has name, value for place, value per houses, value for hotel, mortgage value
	//just needs getters
	//maybe more abstraction will be needed
	public enum PropertyType {
		SIMPLE
		,UTILITY
		,RAILING
	}

	private PropertyType propertyType;

	private Integer mortgageValue;

	public Integer getMortgageValue() {
		return mortgageValue;
	}

	public void setMortgageValue(Integer mortgageValue) {
		this.mortgageValue = mortgageValue;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
}
