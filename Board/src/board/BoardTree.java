package board;

public class BoardTree
{
	private BoardNode rootNode;
	private int maxDepth;

	public BoardTree(BoardNode rootNode, int maxDepth)
	{
		this.rootNode = rootNode;
		this.maxDepth = maxDepth;
	}

	public BoardNode getRootNode()
	{
		return rootNode;
	}

	public void setRootNode(BoardNode rootNode)
	{
		this.rootNode = rootNode;
	}

	public int getMaxDepth()
	{
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth)
	{
		this.maxDepth = maxDepth;
	}
}
