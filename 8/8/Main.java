import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws WrongNumberOfDimensionsException, WrongArgumentException, NoSuchPointException
	{
		GeometricShape example = new GeometricShape();
		
		Point p1 = new Point(3);
		Point p2 = new Point(3);
		Point p3 = new Point(4);
		Point p6 = new Point(3);
		Point p7 = new Point(3);

		
		for(int i=0; i<3; i++)
		{
			p1.setPosition(i, 1*i);
			p2.setPosition(i, 2*i);
			p3.setPosition(i, 3*i);
			p6.setPosition(i, 4*i);
		}
		p3.setPosition(3, 9);
		
		example.add(p1); //0- p1
		example.add(p1); //0- p1
		example.addAfter(p2, p1); //1- p2
//		example.addBefore(p3, p2); //1-p3 2-p2
//		example.addAfter(p3, p2); //3- p4=p3
		example.add(p2); // 4- p5=p2
		example.add(p6); //5 - p6
		
		List<Point> list1 = example.get();
		System.out.println("Lista_1: ");
		for(Point l: list1)
		{
			System.out.println(l.getPosition(0) + "\t" + l.getPosition(1)+ "\t" + l.getPosition(2));
		}
		
		System.out.println("Set_1");
		Set<Point> s = example.getSetOfPoints();
		for(Point p: s)
		{
			System.out.println(p.getPosition(1));
		}
		
		example.remove(p2);
//		example.removeAfter(p6);
//		example.removeBefore(p7);
		
		List<Point> list2 = example.get();
		System.out.println("Lista_2: ");
		for(Point l: list2)
		{
			System.out.println(l.getPosition(0) + "\t" + l.getPosition(1)+ "\t" + l.getPosition(2));
		}
		
		example.addAfter(p1, p2);
		
		List<Point> list3 = example.get();
		System.out.println("Lista_3: ");
		for(Point l: list3)
		{
			System.out.println(l.getPosition(0) + "\t" + l.getPosition(1)+ "\t" + l.getPosition(2));
		}
		
		List<Integer> lista = new ArrayList<Integer>();
		for(int i=0; i<3; i++)
		{
			lista.add(i*2);
		}
		
		Optional<Point> got = example.getByPosition(lista);
		System.out.println(got.get().getPosition(1));
	}
}
