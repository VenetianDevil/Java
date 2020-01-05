class SimpleCalculations
{
    public Point[] equidistantPoints(Point firstPoint, Point secondPoint, int points)
    {
        if (points == 0 || firstPoint == null || secondPoint == null)
        {
            return null;
        }

        int dimensions = firstPoint.getNumberOfDimensions();
        double[] wymiary = new double[dimensions];
        double[] first = new double[dimensions];

        for (int i=0; i<dimensions; i++)
        {
            first[i] = firstPoint.getPosition(i);
            wymiary[i] = (secondPoint.getPosition(i) - firstPoint.getPosition(i))/(points+1);
        }
      
        Point[] punkty = new Point[points];
        
        for(int j=0; j<points; j++)
        {   
            punkty[j]=new Point();
            punkty[j].setNumberOfDimensions(dimensions);
        }

        for(int i=1; i<=points; i++)
        {
            for(int j=0; j<dimensions; j++)
            {
                punkty[i-1].setPosition(j, first[j]+i*wymiary[j]);
            }
        }

        return punkty;
    }

    public Point geometricCenter(Point[] points)
    {
        if(points == null)
        {
            return null;
        }

        int dimensions = points[0].getNumberOfDimensions();
        Point Center = new Point();
        Center.setNumberOfDimensions(dimensions);
        int center;
        double zmienna;
        int ilosc = points.length; // ilosc punktow w tablicy 

        for(int i=0; i<dimensions; i++)
        {
            zmienna = 0;
            for(int j=0; j<ilosc; j++)
            {
                zmienna = zmienna + points[j].getPosition(i);
            }
            Center.setPosition(i, zmienna/ilosc);
        }

        return Center;
    }

    public Point next(Point firstPoint, Point secondPoint, double distance) 
    {
        if (firstPoint == null || secondPoint == null)
        {
            return null;
        }

        int dimensions = firstPoint.getNumberOfDimensions();
        Point next = new Point();
        next.setNumberOfDimensions(dimensions);
        double f, s;

        for(int i=0; i<dimensions; i++)
        {
            f = firstPoint.getPosition(i);
            s = secondPoint.getPosition(i);
            if(f==s)
            {
                next.setPosition(i, f);
            }
            else if(f < s)
            {
                next.setPosition(i, s + distance);
            }
            else if(f > s)
            {
                next.setPosition(i, s - distance);
            }
        }
        return next;
    }
}