package board;

import java.util.ArrayList;

public class BoardNode
{
	private BoardNode parentNode;
	private ArrayList<BoardNode> childrenNodes;
	private int heuristic;
	private int depth;
	private Board board;
	private int alpha;
	private int beta;
	private BoardNode bestBoardNode;

	public BoardNode(BoardNode parentNode, ArrayList<BoardNode> childrenNodes, int heuristic, int depth, Board board, int alpha, int beta)
	{
		this.parentNode = parentNode;
		this.childrenNodes = childrenNodes;
		this.heuristic = heuristic;
		this.depth = depth;
		this.board = board;
		this.alpha = alpha;
		this.beta = beta;
		this.bestBoardNode = null;
	}

	public BoardNode getParentNode()
	{
		return parentNode;
	}

	public void setParentNode(BoardNode parentNode)
	{
		this.parentNode = parentNode;
	}

	public ArrayList<BoardNode> getChildrenNodes()
	{
		return childrenNodes;
	}

	public void setChildrenNodes(ArrayList<BoardNode> childrenNodes)
	{
		this.childrenNodes = childrenNodes;
	}

	public void addChild(BoardNode child)
	{
		if (this.childrenNodes == null)
		{
			ArrayList<BoardNode> newArray = new ArrayList<BoardNode>();
			newArray.add(child);
			setChildrenNodes(newArray);
		}
		else
		{
			this.childrenNodes.add(child);
		}
	}

	public int getHeuristic()
	{
		return heuristic;
	}

	public void setHeuristic(int heuristic)
	{
		this.heuristic = heuristic;
	}

	public int getDepth()
	{
		return depth;
	}

	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	public Board getBoard()
	{
		return board;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}

	public int getAlpha()
	{
		return alpha;
	}

	public void setAlpha(int alpha)
	{
		this.alpha = alpha;
	}

	public int getBeta()
	{
		return beta;
	}

	public void setBeta(int beta)
	{
		this.beta = beta;
	}

	public BoardNode getBestBoardNode()
	{
		return bestBoardNode;
	}

	public void setBestBoardNode(BoardNode bestBoardNode)
	{
		this.bestBoardNode = bestBoardNode;
	}

	@Override
	public String toString()
	{
		return "BoardNode{" +
				" heuristic=" + heuristic +
				", depth=" + depth +
				", board=" + board +
				", alpha=" + alpha +
				", beta=" + beta +
				'}';
	}
}
