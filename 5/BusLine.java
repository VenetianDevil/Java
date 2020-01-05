import java.util.Vector;

class BusLine implements BusLineInterface
{
    
    private int numberOfBusStops;
    private Vector<BusStop> busLine;

    public int getNumberOfBusStops()
    {
        return numberOfBusStops;
    }
    
    public BusStopInterface getBusStop(int n)
    {
        BusStopInterface busStop;
        busStop = busLine.get(n);
        return busStop;
    }

    public BusLine(Vector<BusStop> line)
    {
        busLine = new Vector<BusLine>(line);
        numberOfBusStops = busLine.size();
    }
}