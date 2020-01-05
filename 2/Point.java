//package point;
//Klasa Punkt reprezentuje punkt w n-wymiarowej przestrzeni.
public class Point {
    private int dimensions;
    private double[] position;

    public void setNumberOfDimensions( int dimensions ) {
        this.dimensions = dimensions;
        this.position = new double[ dimensions ];
    }

    public int getNumberOfDimensions() {
        return dimensions;
    }

    public void setPosition( int dimension, double value )
    {
        position[ dimension ] = value;
    }

    public double getPosition( int dimension ) {
        return position[ dimension ];
    }
}