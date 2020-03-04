package board;

import AIAgent.EmilyTheAI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class CLI {

    public static void main(String[] args) {
        int rowCoord = 2;
        int colCoord = 3;
        System.out.println("Please input Fen String");
        Board board = new Board("ppppkppp/pppppppp/8/8/8/PPPPPPPP/PPPPKPPP", null);
        EmilyTheAI theAI = new EmilyTheAI();
        Board result = theAI.minimax(board,null,null,true,4,true);
        System.out.println("This Board's Score is: " + result.getAIScore());

        result.printBoard();
    }


}
