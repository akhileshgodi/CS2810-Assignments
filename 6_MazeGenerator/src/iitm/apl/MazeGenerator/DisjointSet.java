package iitm.apl.MazeGenerator;

public class DisjointSet 
{
	private DisjointSet parent;
	int data;
	int size;
	/*Constructor equivalent to MakeSet*/
	DisjointSet(int number)
	{
		parent = this;
		this.data = number;
		this.size = 1;
	}
	
	public DisjointSet findSet()
	{
		if(parent == this)
			return parent;
		else
			parent = parent.findSet();		//Path Compression
			return parent;
	}
	
	public void Union(DisjointSet newSet)
	{
		DisjointSet representativeOld, representativeNew;
		representativeOld = this.findSet();
		representativeNew = newSet.findSet();
		if(representativeOld != representativeNew)
		{
			representativeNew.parent = representativeOld;
			representativeOld.size += representativeNew.size;
		}
	}

}
