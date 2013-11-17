package Monopoly;

public class AIPlayer extends Player {

	public AIPlayer(String name)
	{
		setPlayerName(name);
		initializePlayer();
	}

	@Override
	public void step() {
		  System.out.println("AI step called");
	}
}
