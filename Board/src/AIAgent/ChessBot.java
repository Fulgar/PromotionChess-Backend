package AIAgent;

import board.Board;
import board.BoardNode;
import models.RESTCallPackage;
import pieces.Pieces;

import java.util.ArrayList;

public class ChessBot
{
	private RESTCallPackage restCallPackage;

	public ChessBot(RESTCallPackage restCallPackage)
	{
		this.restCallPackage = restCallPackage;
	}

	public RESTCallPackage getRestCallPackage()
	{
		return restCallPackage;
	}

	public void setRestCallPackage(RESTCallPackage restCallPackage)
	{
		this.restCallPackage = restCallPackage;
	}

	public Board getBestMoveBoard()
	{
		Board originBoard = new Board(this.restCallPackage.getFenString(), null);
		System.out.println("0: " + this.restCallPackage.getFenString());
		BoardNode rootNode = new BoardNode(null, null, 0, 0, originBoard, Integer.MIN_VALUE, Integer.MAX_VALUE);
		BoardNode minimaxBoardNode = minimax(rootNode);
		Board resultBoard = minimaxBoardNode.getBoard();
		return resultBoard;
	}

	private BoardNode minimax(BoardNode node)
	{
		if (node.getDepth() % 2 == 0)
		{
			int score = maxAlpha(node);
			return node.getBestBoardNode();
		}
		else
		{
			int score = minBeta(node);
			return node.getBestBoardNode();
		}
	}

	private int minBeta(BoardNode node)
	{
		int currentDepth = node.getDepth();
		Board currentBoard = node.getBoard();
		int maxDepth = this.restCallPackage.getDepth();
		boolean isAIMoving = currentDepth % 2 == 0;
		char enemyColor = this.getRestCallPackage().getAiColor() == 'b' ? 'w' : 'b';
		char movingColor = isAIMoving ? this.getRestCallPackage().getAiColor() : enemyColor;

		if (currentDepth == maxDepth)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		int movingPieceCount = 0;
		for (Pieces[] row : currentBoard.board)
		{
			for (Pieces piece : row)
			{
				if (piece == null) continue;
				if (piece.getIsWhite() != (movingColor == 'w')) continue;
				movingPieceCount++;
			}
		}

		if (movingPieceCount == 0)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		for (Pieces[] row : currentBoard.board)
		{
			for (Pieces piece : row)
			{
				if (piece == null) continue;
				int possibleMovesCount = piece.getPossibleMoves(currentBoard).size();
				if (possibleMovesCount == 0)
				{
					// Terminal node reached, add score
					// node.setHeuristic(currentBoard.getAIScore());
					// return currentBoard.getAIScore();
					continue;
				}

				if (piece.getIsWhite() == (movingColor == 'w'))
				{
					for (Board currentPossibleMoveBoard : piece.getPossibleMoves(currentBoard))
					{
						System.out.println((currentDepth + 1) + ": " + currentPossibleMoveBoard.createFenString());
						String test = currentPossibleMoveBoard.createFenString();
						BoardNode child = new BoardNode(node, null, 0, currentDepth + 1, currentPossibleMoveBoard, node.getAlpha(), node.getBeta());
						node.addChild(child);

						int score = maxAlpha(child);

						if (score <= node.getAlpha())
						{
							System.out.println("MIN PRUNED");
							return node.getBeta();
						}
						if (score < node.getBeta())
						{
							node.setBeta(score);
							node.setBestBoardNode(child);
						}
					}
				}
			}
		}

		if (node.getBestBoardNode() == null)
		{
			System.out.println("NULL NODE: " + node.getBoard().createFenString());
		}
		return node.getBeta();
	}

	private int maxAlpha(BoardNode node)
	{
		int currentDepth = node.getDepth();
		Board currentBoard = node.getBoard();
		int maxDepth = this.restCallPackage.getDepth();
		boolean isAIMoving = currentDepth % 2 == 0;
		char enemyColor = this.getRestCallPackage().getAiColor() == 'b' ? 'w' : 'b';
		char movingColor = isAIMoving ? this.getRestCallPackage().getAiColor() : enemyColor;

		if (currentDepth == maxDepth)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		int movingPieceCount = 0;
		for (Pieces[] row : currentBoard.board)
		{
			for (Pieces piece : row)
			{
				if (piece == null) continue;
				if (piece.getIsWhite() != (movingColor == 'w')) continue;
				movingPieceCount++;
			}
		}

		if (movingPieceCount == 0)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		for (Pieces[] row : currentBoard.board)
		{
			for (Pieces piece : row)
			{
				if (piece == null) continue;
				int possibleMovesCount = piece.getPossibleMoves(currentBoard).size();
				if (possibleMovesCount == 0)
				{
					// Terminal node reached, add score
					// node.setHeuristic(currentBoard.getAIScore());
					// return currentBoard.getAIScore();
					continue;
				}

				if (piece.getIsWhite() == (movingColor == 'w'))
				{
					ArrayList<Board> possibleMoves = piece.getPossibleMoves(currentBoard);
					for (Board currentPossibleMoveBoard : piece.getPossibleMoves(currentBoard))
					{
						System.out.println((currentDepth + 1) + ": " + currentPossibleMoveBoard.createFenString());
						String test = currentPossibleMoveBoard.createFenString();
						BoardNode child = new BoardNode(node, null, 0, currentDepth + 1, currentPossibleMoveBoard, node.getAlpha(), node.getBeta());
						node.addChild(child);

						int score = minBeta(child);

						if (score >= node.getBeta())
						{
							System.out.println("MAX PRUNED");
							return node.getBeta();
						}
						if (score > node.getAlpha())
						{
							node.setAlpha(score);
							node.setBestBoardNode(child);
						}
					}
				}
			}
		}

		if (node.getBestBoardNode() == null)
		{
			System.out.println("NULL NODE: " + node.getBoard().createFenString());
		}
		return node.getAlpha();
	}
}
