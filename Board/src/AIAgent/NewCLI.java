package AIAgent;

import board.Board;
import models.RESTCallPackage;

public class NewCLI
{
	public static void main (String[] args)
	{
		System.out.println("Please input Fen String");
		RESTCallPackage restCallPackage = new RESTCallPackage("ppppkppp/pppppppp/P7/8/8/8/PPPPPPPP/PPPPKPPP", 'b', 1, "white");
		Board board = new Board(restCallPackage.getFenString(), null);
		BotAI theAI = new BotAI(restCallPackage);
		Board result = theAI.getBestMoveBoard();
		System.out.println("This Board's Score is: " + result.getAIScore());
		System.out.println(result.createFenString());

		result.printBoard();
	}
}
