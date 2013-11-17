package Monopoly;

public class HumanPlayer extends Player {

	public HumanPlayer(String name)
	{
		setPlayerName(name);
		initializePlayer();
	}

	@Override
	public void step() {
		  System.out.println("Human step called");
	}
}
