import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args)
	{
		GeometricShape example = new GeometricShape();
		
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		Point p6 = new Point();
		Point p7 = new Point();

		
		int dimensions = p1.getNumberOfDimensions();
		
		for(int i=0; i<dimensions; i++)
		{
			p1.setPosition(i, 1*i);
			p2.setPosition(i, 2*i);
			p3.setPosition(i, 3*i);
			p6.setPosition(i, 4*i);
		}
		
		example.add(p1); //0- p1
		example.addAfter(p2, p1); //1- p2
		example.addBefore(p3, p2); //1-p3 2-p2
		example.addAfter(p3, p2); //3- p4=p3
		example.undo();
//		example.redo();
		example.add(p2); // 4- p5=p2
		example.add(p6); //5 - p6
		
		List<Point> list1 = example.get();
		System.out.println("Lista_1: ");
		for(Point l: list1)
		{
			System.out.println(l.getPosition(0) + "\t" + l.getPosition(1)+ "\t" + l.getPosition(2));
		}
		
		System.out.println("Mapa_1");
		Map<Point, Integer> map1 = example.getPointsAsMap();
		Set<Point> s = map1.keySet();
		for(Point p: s)
		{
			System.out.println(p.getPosition(1) + "\t" + map1.get(p));
		}
		
		example.remove(p2);
		example.removeAfter(p7);
		example.removeBefore(p3);
		
		List<Point> list2 = example.getUniq();
		System.out.println("Lista_2: ");
		for(Point l: list2)
		{
			System.out.println(l.getPosition(0) + "\t" + l.getPosition(1)+ "\t" + l.getPosition(2));
		}
		
		example.undo();
		example.addAfter(p1, p3);
		example.undo();
		example.redo();
//		example.undo();
		
		List<Point> list3 = example.get();
		System.out.println("Lista_3: ");
		for(Point l: list3)
		{
			System.out.println(l.getPosition(0) + "\t" + l.getPosition(1)+ "\t" + l.getPosition(2));
		}
	}
}
