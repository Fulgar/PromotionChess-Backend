package board;

import AIAgent.EmilyTheAI;
import score.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class CLI {

    public static void main(String[] args) {
        int rowCoord = 2;
        int colCoord = 3;
        System.out.println("Please input Fen String");
        Board board = new Board("ppppkppp/pppppppp/8/8/8/8/PPPPPPPP/PPPPKPPP", null);
        EmilyTheAI theAI = new EmilyTheAI();
        Board tempAlpha = new Board();
        tempAlpha.setScore(new Score(Integer.MIN_VALUE/2, Integer.MIN_VALUE/2));
        Board tempBeta = new Board();
        tempBeta.setScore(new Score(Integer.MAX_VALUE/2, Integer.MAX_VALUE/2));
        Board result = theAI.minimax(board,tempAlpha,tempBeta,true,4,true);
        System.out.println("This Board's Score is: " + result.getAIScore());

        result.printBoard();
    }


}
