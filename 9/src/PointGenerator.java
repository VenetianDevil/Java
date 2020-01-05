public class PointGenerator implements PointGeneratorInterface
{	
	@Override
	public PointInterface getPoint()
	{
		int Max = PointInterface.MAX_POSITION + 1;
		return new Point((int) (Math.random() * Max), (int) (Math.random() * Max));
//		return new Point(3, 3);
	}
}