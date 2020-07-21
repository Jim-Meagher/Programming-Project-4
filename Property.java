package edu.umgc.cmis242;

/**
 * <h3>Programming Project 4</h3>
 * <p>The fourth programming project involves writing a program to manage a real estate database</p>
 * @author      Jimmy Maher
 * @version     %I%, %G%
 * @since       1.0
 */

public class Property implements StateChangeable
{
    /* Declare Class Fields */
    protected String propertyAddress;
    protected int numberOfBedrooms;
    protected int squareFootage;
    protected int price;
    protected String statusOfProperty;

    /**
     * This Class constructor initialized the characteristics of the property
     * @param propertyAddress   the address of the property
     * @param numberOfBedrooms  the number of bedrooms the property has
     * @param squareFootage     the square footage of the property
     * @param price             the selling price of the property
     */
    public Property(String propertyAddress, int numberOfBedrooms, int squareFootage, int price)
    {
        this.propertyAddress = propertyAddress;

        this.numberOfBedrooms = numberOfBedrooms;

        this.squareFootage = squareFootage;

        this.price = price;

        statusOfProperty = String.valueOf(Status.FOR_SALE);

    }// end of Property constructor

    /**
     * Default class constructor
     */
    public Property( ) { }


    /**
     * <p>Allows the status of the property to be changed</p>
     * @param n  status of the property
     */
    @Override
    public void changeState(Status n)
    {
        if(n == Status.FOR_SALE)
        {
            statusOfProperty = "For_Sale";
        }

        else if(n == Status.UNDER_CONTRACT)
        {
            statusOfProperty = "Under_Contract";
        }

        else if(n==Status.SOLD)
        {
            statusOfProperty = "Sold";
        }
    }// end of changeState method

    /**
     *<p>Display property information</p>
     * @return  a string containing the property address, the number of bedrooms, the square footage,
     *          the price and the current status
     */
    @Override
    public String toString( )
    {
        return "Property{" +
                "propertyAddress='" + propertyAddress + '\'' +
                ", numberOfBedrooms=" + numberOfBedrooms +
                ", squareFootage=" + squareFootage +
                ", price=" + price +
                ", statusOfProperty=" + statusOfProperty +
                '}';
    }// end of toString method

}//end of Property class
