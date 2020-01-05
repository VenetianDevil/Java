//import point.Point;
import java.util.Scanner;

class Main {
    public static void main(String[] argv)
    {
        Scanner in = new Scanner(System.in);
        Point firstPoint = new Point();
        Point secondPoint = new Point();
        SimpleCalculations simpleCalculations = new SimpleCalculations();

        int dimensions = 3;

        firstPoint.setNumberOfDimensions(dimensions);
        secondPoint.setNumberOfDimensions(dimensions);

        for(int i=0; i<dimensions; i++)
        {
            secondPoint.setPosition(i, 10+i*5);
            firstPoint.setPosition(i, 10+i*5);
            
        }
           secondPoint.setPosition(1, 12);

        int point = 3;
        Point[] points = simpleCalculations.equidistantPoints(firstPoint, secondPoint, point);
        double dana = 1;
        for(int j=0; j<point; j++)
        {
            for(int k=0; k<dimensions; k++)
            {
                dana = points[j].getPosition(k);
                System.out.println(dana);
            }
            System.out.println("\n");
        }

        Point Center = simpleCalculations.geometricCenter(points);
        {
            for(int k=0; k<dimensions; k++)
            {
                System.out.println(Center.getPosition(k));
            }
            System.out.println("\n");
        }
        
        Point next = simpleCalculations.next(firstPoint, secondPoint, 4);
        for(int k=0; k<dimensions; k++)
        {
            System.out.println(next.getPosition(k));
        }
        System.out.println("\n");
    }
}