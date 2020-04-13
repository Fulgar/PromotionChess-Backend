package test;

import AIAgent.ChessBot;
import board.Board;
import models.RESTCallPackage;

public class ChessBotCLI
{
	public static void main (String[] args)
	{
		System.out.println("Please input Fen String");
		// Minimal branch test fenString: 1p6/P7/8/8/8/8/8/8
		// Minimal branch test fenString for NPE Error Bug at DEPTH 4: 1p6/8/8/P7/8/8/8/8
		// Default fenString: ppppkppp/pppppppp/8/8/8/8/PPPPPPPP/PPPPKPPP
		RESTCallPackage restCallPackage = new RESTCallPackage("1p6/8/8/P7/8/8/8/8", 'b', 4, "white");
		Board board = new Board(restCallPackage.getFenString(), null);
		ChessBot theAI = new ChessBot(restCallPackage);
		Board result = theAI.getBestMoveBoard();
		System.out.println("This Board's Score is: " + result.getAIScore());
		System.out.println(result.createFenString());

		result.printBoard();
	}
}
