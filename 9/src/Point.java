
public class Point implements PointInterface 
{
	int a;
	int b;
	
	Point(int a1, int b1)
	{
		this.a = a1;
		this.b = b1;
	}
	
	@Override
	public int[] getPositions()
	{
		int[] tab = new int[2];
		tab[0] = a;
		tab[1] = b;
		
		return tab;
	}

}
