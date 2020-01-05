import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class GeometricShape implements GeometricShapeInterface
{
	class Act
	{
		int operation;
		int position;
		Point pkt;
		
		Act(int o, int p, Point pt)
		{
			operation = o;
			position = p;
			pkt = pt;
		}
	}
		
	List<Point> points = new ArrayList<Point>();
	Vector<Act> done = new Vector<Act>();
	Act todo = null;
	
	@Override
	public void add(Point point)
	{
		points.add(point);
		done.add(new Act(1, points.indexOf(point), point));
		todo = null;
	}

	@Override
	public boolean remove(Point point)
	{
		if(points.size()>0 && points.contains(point))
		{
			done.add(new Act(2, points.indexOf(point), point));
			todo = null;
			points.remove(point);			
			return true;
		}
		return false;
	}

	@Override
	public boolean addBefore(Point point, Point beforePoint)
	{
		int index = points.indexOf(beforePoint);
		if(index>=0)
		{
			points.add(index, point);
			done.add(new Act(3, index, point));
			todo = null;
			return true;			
		}
		return false;
	}

	@Override
	public boolean addAfter(Point point, Point afterPoint)
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
			done.add(new Act(4, index+1, point));
			todo = null;
			return true;
		}
		return false;
	}

	@Override
	public Point removeBefore(Point beforePoint)
	{
		int index = points.indexOf(beforePoint);
		if(index>0)
		{
			Point deleted = points.get(index-1);
			points.remove(index-1);
			done.add(new Act(5, index-1, deleted));
			todo = null;
			return deleted;
		}
		return null;
	}

	@Override
	public Point removeAfter(Point afterPoint)
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
			done.add(new Act(6, index+1, deleted));
			todo = null;
			return deleted;
		}
		return null;
	}

	@Override
	public boolean undo()
	{
		if(done.size()>0)
		{
			Act last = done.lastElement();
			if(last.operation==1 || last.operation==3 || last.operation==4)
			{
				todo = last;
				done.remove(last);
				points.remove(last.position);
				return true;
			}
			else if(last.operation==2 || last.operation==5 || last.operation==6)
			{
				todo = last;
				done.remove(last);
				points.add(last.position, last.pkt);
				return true;
			}			
		}
		return false;
	}

	@Override
	public boolean redo()
	{
		if(todo!=null)
		{
			if(todo.operation==2 || todo.operation==5 || todo.operation==6)
			{
				done.add(todo);
				points.remove(todo.position);
				todo = null;
				return true;
			}
			else if(todo.operation==1 || todo.operation==3 || todo.operation==4)
			{
				done.add(todo);
				points.add(todo.position, todo.pkt);
				todo = null;
				return true;
			}			
		}
		return false;
	}

	@Override
	public List<Point> get()
	{
		return points;
	}

	@Override
	public List<Point> getUniq()
	{
		Point newCheck = new Point();
		Point here;
		List<Point> result = new ArrayList<Point>();
		
		Iterator<Point> check = points.iterator();
		while(check.hasNext())
		{
			here = check.next();
			if(!newCheck.equals(here))
			{
				newCheck = here;
				result.add(newCheck);
			}
		}
		return result;
	}

	@Override
	public Map<Point, Integer> getPointsAsMap()
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
		return map;
	}
}
