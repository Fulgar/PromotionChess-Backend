package pieces;

import board.Board;

import java.util.ArrayList;

public class ReturnGetPossibleMoves
{
	private boolean isAttacking = false;
	private ArrayList<Board> possibleMoves;

	public boolean isAttacking()
	{
		return isAttacking;
	}

	public void setAttacking(boolean attacking)
	{
		isAttacking = attacking;
	}

	public ArrayList<Board> getPossibleMoves()
	{
		return possibleMoves;
	}

	public void setPossibleMoves(ArrayList<Board> possibleMoves)
	{
		this.possibleMoves = possibleMoves;
	}
}
