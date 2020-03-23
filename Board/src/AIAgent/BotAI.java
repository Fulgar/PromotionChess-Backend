package AIAgent;

import board.Board;
import board.BoardNode;
import board.BoardTree;
import models.RESTCallPackage;
import pieces.Pieces;
import score.Score;

public class BotAI
{
	private RESTCallPackage restCallPackage;

	public BotAI(RESTCallPackage restCallPackage)
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
		// Create tree of Board moves
		Board rootBoard = new Board(this.getRestCallPackage().getFenString(), null);
		BoardNode rootNode = new BoardNode(null, null, 0, 0, rootBoard, 0, 0);
		buildTree(rootNode, 0);
		BoardTree boardTree = new BoardTree(rootNode, this.restCallPackage.getDepth());

		BoardNode bestBoardNode = minimax(rootNode, 0, 0);

		return bestBoardNode.getBoard();
	}

	private void buildTree(BoardNode parentNode, int depth)
	{
		int maxDepth = this.restCallPackage.getDepth();
		Board boardParent = parentNode.getBoard();
		boolean isAIMoving = depth % 2 == 0;
		char enemyColor = this.getRestCallPackage().getAiColor() == 'b' ? 'w' : 'b';
		char movingColor = isAIMoving ? this.getRestCallPackage().getAiColor() : enemyColor;

		if (depth == maxDepth)
		{
			// Terminal node reached, add score
			parentNode.setHeuristic(boardParent.getAIScore());
			return;
		}
		for (Pieces[] row : boardParent.board)
		{
			for (Pieces piece : row)
			{
				if (piece == null) continue;
				if (piece.getPossibleMoves(boardParent) == null)
				{
					// Terminal node reached, add score
					parentNode.setHeuristic(boardParent.getAIScore());
					return;
				}

				if (piece.getIsWhite() == (movingColor == 'w'))
				{
					for (Board currentPossibleMoveBoard : piece.getPossibleMoves(boardParent))
					{
						System.out.println(currentPossibleMoveBoard.createFenString());
						String test = currentPossibleMoveBoard.createFenString();
						BoardNode child = new BoardNode(parentNode, null, 0, depth, currentPossibleMoveBoard, 0, 0);
						parentNode.addChild(child);
						buildTree(child, depth + 1);
					}
				}
			}
		}
	}

	private BoardNode minimax(BoardNode node, int alpha, int beta)
	{
		// If node is terminal node
		if (node.getChildrenNodes() == null || node.getChildrenNodes().size() == 0 || node.getDepth() == this.restCallPackage.getDepth())
		{
			return node;
		}

		// If node depth is even, then maximize
		if (node.getDepth() % 2 == 0)
		{
			BoardNode bestHeuristicNode = null;
			int bestHeuristic = Integer.MIN_VALUE;
			for(BoardNode child : node.getChildrenNodes())
			{
				BoardNode minimaxNode = minimax(child, alpha, beta);
				int value = minimaxNode.getHeuristic();

				if (bestHeuristicNode == null)
				{
					bestHeuristicNode = minimaxNode;
				}
				else
				{
					if (Integer.max(bestHeuristic, value) == value)
					{
						bestHeuristic = Integer.max(bestHeuristic, value);
						bestHeuristicNode = minimaxNode;
					}
				}
				node.setAlpha(Integer.max(node.getAlpha(), bestHeuristic));
				if (node.getBeta() <= node.getAlpha())
				{
					break;
				}
			}
			return bestHeuristicNode;
		}
		// If node depth is odd, then minimize
		else
		{
			BoardNode bestHeuristicNode = null;
			int bestHeuristic = Integer.MAX_VALUE;
			for(BoardNode child : node.getChildrenNodes())
			{
				BoardNode minimaxNode = minimax(child, alpha, beta);
				int value = minimaxNode.getHeuristic();

				if (bestHeuristicNode == null)
				{
					bestHeuristicNode = minimaxNode;
				}
				else
				{
					if (Integer.min(bestHeuristic, value) == value)
					{
						bestHeuristic = Integer.min(bestHeuristic, value);
						bestHeuristicNode = minimaxNode;
					}
				}
				node.setBeta(Integer.min(node.getBeta(), bestHeuristic));
				if (node.getBeta() <= node.getAlpha())
				{
					break;
				}
			}
			return bestHeuristicNode;
		}

	}
}


