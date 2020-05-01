package AIAgent;

import board.Board;
import board.BoardNode;
import models.RESTCallPackage;
import pieces.Pieces;
import pieces.ReturnGetPossibleMoves;
import score.ChessType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

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

	public Board originalBoard;

	// Method is intended to be called from REST endpoint
	// Will return best move the AI can make in the form of a Board object
	public Board getBestMoveBoard()
	{
		// Board object is obtained from fenString in restCallPackage
		Board originBoard = new Board(this.restCallPackage.getFenString(), null);
		originalBoard = originBoard;

		// Initializes root node of minimax tree
		BoardNode rootNode = new BoardNode(null, null, 0, 0, originBoard, Integer.MIN_VALUE, Integer.MAX_VALUE);

		// Returns bestBoardNode of rootNode as a BoardNode object
		BoardNode minimaxBoardNode = minimax(rootNode);
		// Returns the Board object inside of minimaxBoardNode
		Board resultBoard = minimaxBoardNode.getBoard();
		return resultBoard;
	}

	// Method will decide if rootNode is at even or odd depth, before evaluating
	private BoardNode minimax(BoardNode node)
	{
		// If node depth is even, MAXIMIZE
		if (node.getDepth() % 2 == 0)
		{
			int score = maxAlpha(node);
			if (node.getBoard().getAIScore() != node.getHeuristic())
			{
				ArrayList<BoardNode> bestList = new ArrayList<BoardNode>();
				int bestScore = Integer.MIN_VALUE;
				BoardNode bestNode = null;
				for (BoardNode child : node.getChildrenNodes())
				{
					// If child score is greater than node score
					if (node.getHeuristic() > bestScore)
					{
						bestList.clear();
						bestList.add(child);

						bestScore = child.getHeuristic();
						bestNode = child;
					}
					if (bestScore == node.getHeuristic()) {
						bestList.add(child);
					}
				}

				ArrayList<BoardNode> randomBestList = new ArrayList<BoardNode>();
				int bestStaticScore = Integer.MIN_VALUE;
				BoardNode bestStaticNode = null;
				for (BoardNode maxNode : bestList)
				{
					// If child score is greater than node score
					if (node.getBoard().getAIScore() > bestStaticScore)
					{
						randomBestList.clear();
						randomBestList.add(maxNode);

						bestStaticScore = maxNode.getHeuristic();
						bestStaticNode = maxNode;
					}
					if (bestScore == node.getBoard().getAIScore()) {
						randomBestList.add(maxNode);
					}
				}

				Random rand = new Random();
				node.setBestBoardNode(randomBestList.get(rand.nextInt(randomBestList.size())));
			}
			return node.getBestBoardNode();
		}
		// If node depth is odd, MINIMIZE
		else
		{
			int score = minBeta(node);
			return node.getBestBoardNode();
		}
	}

	// Method will return the most minimized heuristic or return early if pruned
	private int minBeta(BoardNode node)
	{
		int currentDepth = node.getDepth();
		Board currentBoard = node.getBoard();
		int maxDepth = this.restCallPackage.getDepth();
		boolean isAIMoving = currentDepth % 2 == 0; // AI only moves on even depths
		char enemyColor = this.getRestCallPackage().getAiColor() == 'b' ? 'w' : 'b';
		char movingColor = isAIMoving ? this.getRestCallPackage().getAiColor() : enemyColor;

		ArrayList<BoardNode> randomBestList = new ArrayList<BoardNode>();

		// If current depth of node has reached maximum, then terminal node has been reached
		if (currentDepth == maxDepth)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		// Counts how many pieces can be moved on the current player/AI's turn
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

		// If there are zero pieces that can be moved in current turn then return as a terminal node
		if (movingPieceCount == 0)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		// Set initial value to positive infinity
		node.setHeuristic(Integer.MAX_VALUE);

		Stack<Pieces> nonAttackingPositions = new Stack<>();
		// Search all possible moves
		for (Pieces[] row : currentBoard.board)
		{
			for (Pieces piece : row)
			{
				// If piece is an empty square, continue through loop
				if (piece == null) continue;
				ReturnGetPossibleMoves returnGetPossibleMoves = piece.getPossibleMoves(currentBoard);
				int possibleMovesCount = returnGetPossibleMoves.getPossibleMoves().size();
				// If current piece has no moves, continue through loop
				if (possibleMovesCount == 0)
				{
					// No moves for piece
					continue;
				}

				if (!returnGetPossibleMoves.isAttacking()) {
					nonAttackingPositions.push(piece);
					continue;
				}

				// If piece == movingColor
				if (piece.getIsWhite() == (movingColor == 'w'))
				{
					for (Board currentPossibleMoveBoard : returnGetPossibleMoves.getPossibleMoves())
					{
						// Creates child node of "node"
						BoardNode child = new BoardNode(node, null, 0, currentDepth + 1, currentPossibleMoveBoard, node.getAlpha(), node.getBeta());
						node.addChild(child);

						// Obtains child score
						int childScore = maxAlpha(child);

						// If child score is less than node score
						if (childScore < node.getHeuristic())
						{
							randomBestList.clear();
							randomBestList.add(child);

							node.setHeuristic(childScore);
							node.setBestBoardNode(child);
						}

						// If child score is less than or equal to alpha
						if (childScore <= node.getAlpha())
						{
							// System.out.println(currentDepth + ": MIN PRUNED");
							return node.getHeuristic();
						}

						// If child score is less than beta
						if (childScore < node.getBeta())
						{
							node.setBeta(childScore);
						}
					}
				}

			}
		}

		while (!nonAttackingPositions.isEmpty()) {
			Pieces piece = nonAttackingPositions.pop();

			// If piece is an empty square, continue through loop
			if (piece == null) continue;
			ReturnGetPossibleMoves returnGetPossibleMoves = piece.getPossibleMoves(currentBoard);
			int possibleMovesCount = returnGetPossibleMoves.getPossibleMoves().size();
			// If current piece has no moves, continue through loop
			if (possibleMovesCount == 0)
			{
				// No moves for piece
				continue;
			}

			// If piece == movingColor
			if (piece.getIsWhite() == (movingColor == 'w'))
			{
				for (Board currentPossibleMoveBoard : returnGetPossibleMoves.getPossibleMoves())
				{
					// Creates child node of "node"
					BoardNode child = new BoardNode(node, null, 0, currentDepth + 1, currentPossibleMoveBoard, node.getAlpha(), node.getBeta());
					node.addChild(child);

					// Obtains child score
					int childScore = maxAlpha(child);

					// If child score is less than node score
					if (childScore < node.getHeuristic())
					{
						randomBestList.clear();
						randomBestList.add(child);

						node.setHeuristic(childScore);
						node.setBestBoardNode(child);
					}
					if (nonAttackingPositions.size() == possibleMovesCount)
					{
						if (childScore == node.getHeuristic()) {
							randomBestList.add(child);

							Random rand = new Random();
							node.setBestBoardNode(randomBestList.get(rand.nextInt(randomBestList.size())));
						}
					}

					// If child score is less than or equal to alpha
					if (childScore <= node.getAlpha())
					{
						return node.getHeuristic();
					}

					// If child score is less than beta
					if (childScore < node.getBeta())
					{
						node.setBeta(childScore);
					}
				}
			}
		}

		// Return best heuristic
		return node.getHeuristic();
	}

	// Method will return the most maximized heuristic or return early if pruned
	private int maxAlpha(BoardNode node)
	{
		int currentDepth = node.getDepth();
		Board currentBoard = node.getBoard();
		int maxDepth = this.restCallPackage.getDepth();
		boolean isAIMoving = currentDepth % 2 == 0; // AI only moves on even depths
		char enemyColor = this.getRestCallPackage().getAiColor() == 'b' ? 'w' : 'b';
		char movingColor = isAIMoving ? this.getRestCallPackage().getAiColor() : enemyColor;

		ArrayList<BoardNode> randomBestList = new ArrayList<BoardNode>();

		// If current depth of node has reached maximum, then terminal node has been reached
		if (currentDepth == maxDepth)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		// Counts how many pieces can be moved on the current player/AI's turn
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

		// If there are zero pieces that can be moved in current turn then return as a terminal node
		if (movingPieceCount == 0)
		{
			// Terminal node reached, add score
			node.setHeuristic(currentBoard.getAIScore());
			return currentBoard.getAIScore();
		}

		// Set initial value to negative infinity
		node.setHeuristic(Integer.MIN_VALUE);

		Stack<Pieces> nonAttackingPositions = new Stack<>();

		// Search all possible moves
		for (Pieces[] row : currentBoard.board)
		{
			for (Pieces piece : row)
			{
				// If piece is an empty square, continue through loop
				if (piece == null) continue;
				ReturnGetPossibleMoves returnGetPossibleMoves = piece.getPossibleMoves(currentBoard);
				int possibleMovesCount = returnGetPossibleMoves.getPossibleMoves().size();
				// If current piece has no moves, continue through loop
				if (possibleMovesCount == 0)
				{
					// No moves for piece
					continue;
				}

				if (!returnGetPossibleMoves.isAttacking()) {
					nonAttackingPositions.push(piece);
					continue;
				}

				// If piece == movingColor
				if (piece.getIsWhite() == (movingColor == 'w'))
				{
					for (Board currentPossibleMoveBoard : returnGetPossibleMoves.getPossibleMoves())
					{
						// Creates child node of "node"
						BoardNode child = new BoardNode(node, null, 0, currentDepth + 1, currentPossibleMoveBoard, node.getAlpha(), node.getBeta());
						node.addChild(child);

						// Obtains child score
						int childScore = minBeta(child);

						// If child score is greater than node score
						if (childScore > node.getHeuristic())
						{
							node.setHeuristic(childScore);
							node.setBestBoardNode(child);
						}

						// If child score is greater than or equal to beta
						if (childScore >= node.getBeta())
						{
							// System.out.println(currentDepth + ": MAX PRUNED");
							return node.getHeuristic();
						}

						// If child score is greater than alpha
						if (childScore > node.getAlpha())
						{
							node.setAlpha(childScore);
						}
					}
				}
			}
		}

		while (!nonAttackingPositions.isEmpty()) {
			Pieces piece = nonAttackingPositions.pop();

			// If piece is an empty square, continue through loop
			if (piece == null) continue;
			ReturnGetPossibleMoves returnGetPossibleMoves = piece.getPossibleMoves(currentBoard);
			int possibleMovesCount = returnGetPossibleMoves.getPossibleMoves().size();
			// If current piece has no moves, continue through loop
			if (possibleMovesCount == 0)
			{
				// No moves for piece
				continue;
			}

			// If piece == movingColor
			if (piece.getIsWhite() == (movingColor == 'w'))
			{
				for (Board currentPossibleMoveBoard : returnGetPossibleMoves.getPossibleMoves())
				{
					// Creates child node of "node"
					BoardNode child = new BoardNode(node, null, 0, currentDepth + 1, currentPossibleMoveBoard, node.getAlpha(), node.getBeta());
					node.addChild(child);

					// Obtains child score
					int childScore = minBeta(child);

					// If child score is greater than node score
					if (childScore > node.getHeuristic())
					{
						randomBestList.clear();
						randomBestList.add(child);

						node.setHeuristic(childScore);
						node.setBestBoardNode(child);
					}
					if (nonAttackingPositions.size() == possibleMovesCount)
					{
						if (childScore == node.getHeuristic()) {
							randomBestList.add(child);
							Random rand = new Random();
							node.setBestBoardNode(randomBestList.get(rand.nextInt(randomBestList.size())));
						}
					}
					// If child score is greater than or equal to beta
					if (childScore >= node.getBeta())
					{
						// System.out.println(currentDepth + ": MAX PRUNED");
						return node.getHeuristic();
					}

					// If child score is greater than alpha
					if (childScore > node.getAlpha())
					{
						node.setAlpha(childScore);
						// System.out.println((currentDepth + ": NEW ALPHA: " + node.getAlpha()));
					}
				}
			}
		}


		// Return best heuristic
		return node.getHeuristic();
	}
}
