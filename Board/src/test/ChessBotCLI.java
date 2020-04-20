package test;

import AIAgent.ChessBot;
import board.Board;
import models.RESTCallPackage;

public class ChessBotCLI
{
	public static void main (String[] args)
	{
		System.out.println("ChessBot Test");
		// Minimal branch test fenString: 1p6/P7/8/8/8/8/8/8
		// Minimal branch test fenString for NPE Error Bug at DEPTH 4: 1p6/8/8/P7/8/8/8/8
		// Minimal branch test for "only min-pruning" bug? at DEPTH 4: r3r3/8/8/2P5/8/8/8/8
		// Default fenString: ppppkppp/pppppppp/8/8/8/8/PPPPPPPP/PPPPKPPP
		RESTCallPackage restCallPackage = new RESTCallPackage("ppppkppp/pppppppp/7P/8/8/8/PPPPPPPP/PPPPKPPP", 'b', 4, "white");
		ChessBot theAI = new ChessBot(restCallPackage);
		Board result = theAI.getBestMoveBoard();
		System.out.println("This Board's Score is: " + result.getAIScore());
		System.out.println(result.createFenString());

		result.printBoard();
	}
}
