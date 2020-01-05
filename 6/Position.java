class Position implements PositionInterface
{
	private int row;
	private int col;
	
	public Position(int c, int r)
	{
		this.row = r;
		this.col = c;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
}