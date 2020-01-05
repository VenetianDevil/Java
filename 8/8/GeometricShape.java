import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GeometricShape implements GeometricShapeInterface
{	
	List<Point> points = new ArrayList<Point>();
	Point First = null;
	int ExpDim = 0;
	int time = 0;
	
	private class Time
	{
		Point point;
		int time;
		
		public Time(Point p, int t)
		{
			this.point = p;
			this.time = t;
		}
	}
	
	List<Time> times = new ArrayList<Time>();
	
	public int dimCount (Point point)
	{
		int i=0;
		try {
			while(true)
			{
				point.getPosition(i);
				i++;
			}
		} catch (Exception e)
		{
			return i;
		}
	}
	
	@Override
	public void add(Point point) throws WrongNumberOfDimensionsException
	{
		if(First == null)
		{
			First = point;
			ExpDim = dimCount(First);
		}
		else
		{
			int pointDim = dimCount(point);
			if(dimCount(point)!=ExpDim)
				throw new WrongNumberOfDimensionsException(ExpDim, pointDim);			
		}
		
		points.add(point);
		times.add(new Time(point, time++));
	}

	@Override
	public void remove(Point point) throws WrongArgumentException 
	{
		if(points.size()>0 && points.contains(point))
		{
			int index = points.indexOf(point);
			points.remove(point);
			times.remove(index);
		}
		else
		{
			throw new WrongArgumentException(point);			
		}
	}

	@Override
	public void addBefore(Point point, Point beforePoint) throws WrongArgumentException, WrongNumberOfDimensionsException
	{
		int pointDim = dimCount(point);
		int beforeDim  = dimCount(beforePoint);
		if(beforeDim!=ExpDim)
		{throw new WrongNumberOfDimensionsException(ExpDim, beforeDim);}
		if(pointDim != ExpDim)
		{throw new WrongNumberOfDimensionsException(ExpDim, pointDim);}
		else
		{
			int index = points.indexOf(beforePoint);
			if(index>=0)
			{
				points.add(index, point);
				times.add(index, new Time(point, time++));
			}
			else
			{
				throw new WrongArgumentException(beforePoint);			
			}			
		}
	}

	@Override
	public void addAfter(Point point, Point afterPoint) throws WrongNumberOfDimensionsException, WrongArgumentException 
	{
		int pointDim = dimCount(point);
		int afterDim  = dimCount(afterPoint);
		if(afterDim!=ExpDim)
		{throw new WrongNumberOfDimensionsException(ExpDim, afterDim);}
		if(pointDim != ExpDim)
		{throw new WrongNumberOfDimensionsException(ExpDim, pointDim);}
		else
		{
			int index = -1;
			int i= -1;
			for(Point last: points)
			{
				i++;
				if(last.equals(afterPoint))
					index = i;
				
			}
			if(index>=0)
			{
				points.add(index+1, point);
				times.add(index+1, new Time(point, time++));
			}
			else
			{
				throw new WrongArgumentException(afterPoint);
			}			
		}
	}

	@Override
	public Point removeBefore(Point beforePoint) throws NoSuchPointException, WrongNumberOfDimensionsException, WrongArgumentException 
	{
		int beforeDim  = dimCount(beforePoint);
		if(beforeDim!=ExpDim)
		{
			throw new WrongNumberOfDimensionsException(ExpDim, beforeDim);
		}
		else if(beforePoint.equals(points.get(0)))
		{
			throw new NoSuchPointException(beforePoint);
		}
		else
		{			
			int index = points.indexOf(beforePoint);
			if(index>0)
			{
				Point deleted = points.get(index-1);
				points.remove(index-1);
				times.remove(index-1);
				return deleted;
			}
			else
			{
				throw new WrongArgumentException(beforePoint);
			}
		}
	}

	@Override
	public Point removeAfter(Point afterPoint) throws NoSuchPointException, WrongNumberOfDimensionsException, WrongArgumentException 
	{
		int afterDim  = dimCount(afterPoint);
		if(afterDim!=ExpDim)
		{throw new WrongNumberOfDimensionsException(ExpDim, afterDim);}
		else if(afterPoint.equals(points.get(points.size()-1)))
		{throw new NoSuchPointException(afterPoint);}
		else
		{
			int index = -1;
			int i= -1;
			for(Point last: points)
			{
				i++;
				if(last.equals(afterPoint))
					index = i;
				
			}
			int size = points.size();
			if(index>=0 && index+1<size)
			{
				Point deleted = points.get(index+1);
				points.remove(index+1);
				times.remove(index+1);
				return deleted;
			}
			else
			{
				throw new WrongArgumentException(afterPoint);
			}
		}
	}

	@Override
	public List<Point> get() 
	{
		return points;
	}

	@Override
	public Set<Point> getSetOfPoints()
	{
		Map<Point, Integer> map = new HashMap<>();
		List<Point> res = new ArrayList<Point>(points);
		int v;
		for(Point check: res)
		{
			if(!map.containsKey(check))
			{	
				map.put(check, 1);	
			}
			else
			{
				v = map.get(check) + 1;
				map.put(check, v);
			}
		}
		Set<Point> s = map.keySet();
		return s;
	}

	@Override
	public Optional<Point> getByPosition(List<Integer> positions) throws WrongNumberOfDimensionsException 
	{
		if(ExpDim != positions.size())
		{
			throw new WrongNumberOfDimensionsException(ExpDim, positions.size());
		}
		else
		{
			Point given = new Point(ExpDim);
			for(int i=0; i<ExpDim; i++)
			{
				given.setPosition(i, positions.get(i));
			}
			
			Optional<Point> result = Optional.empty();
			Time check = null;
			for(Time p: times)
			{
				if(p.point.equals(given))
				{
					if(check == null)
					{
						check = p;
					}
					else if(check.time < p.time)
					{
						check = p;
					}
				}
			}
			if(check!=null)
			result = Optional.of(check.point);					
			return result;
		}
	}
	
	public GeometricShape() {}
}
