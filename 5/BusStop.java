class BusStop implements BusStopInterface
{
    private String stopName;

    public String getName()
    {
        return stopName;
    }

    private BusStop()
    {    }

    public BusStop(String n)
    {
        BusStop.stopName = n;
    }
}