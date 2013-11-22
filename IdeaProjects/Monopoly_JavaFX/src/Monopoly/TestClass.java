package Monopoly;

import junit.framework.TestCase;

/**
 * User: Benjamin
 * Date: 2013.10.26.
 * Time: 18:36
 */
public class TestClass extends TestCase {

	public void testPlayer(){
		HumanPlayer player = new HumanPlayer("Alfonz");
		assertTrue(!player.getIsPlayerInJail());
		assertEquals(player.getPlayerCash(),0);

		for ( int i = 0; i < 5000; ++i )
		{
			System.out.println("------------------------NEW ROLL START-----------------------------");
			player.rollTheDice();
			if ( player.getIsCurrentThrowDoubled() )
			{
				if ( player.checkIfItWasThreeDoublesInARow() )
				{
					assertTrue(player.getIsPlayerInJail());
				}
				else
				{
					assertTrue(!player.getIsPlayerInJail());
				}
			}
			System.out.println("-------------------------ROLL END------------------------------");
		}
	}

	public void testParserWithWrongLocation(){
		try{
			XMLParser parser = new XMLParser();
			parser.handleBoardElementXML("");
			assertTrue("Exception wasn't thrown", false);
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
			assertEquals("Cannot initialize document with wrong location!", npe.getMessage());
		}
	}

	public void testParserWithCorrectLocation(){
		XMLParser parser = new XMLParser();
		assertNotNull(parser.handleBoardElementXML("C:\\Users\\Benjamin\\IdeaProjects\\JavaFXCanvas\\XML\\BoardElements.xml"));
	}

}
