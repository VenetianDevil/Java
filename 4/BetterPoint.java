import java.util.Vector;

class BetterPoint extends AbstractBetterPoint
{
	private double[] pointdim;
	private int lockLvl = 0;
	private Vector<String> passLvl = new Vector<String>();

	public void setDimensions(int dimensions)
	{
        double[] tab = new double[dimensions];
		pointdim = tab;
	}
	
	public int lockLevel()
	{
		return lockLvl;
	}
	
	public int lock(String password)
	{
        if(password != null)
        {
            passLvl.add(password);
            lockLvl++;
        }
        return lockLvl;
	}

	public int unlock(String password)
	{
        if(lockLvl>0)
        {
            String last = passLvl.lastElement();
            if(password!=null && last == password)
            {
                passLvl.remove(lockLvl-1);
                lockLvl--;
            }
        }
		return lockLvl;
	}
	
	public boolean move(int dimension, double delta)
	{
	    if(lockLvl == 0 && delta != 0)
        {
            pointdim[dimension] += delta;
            return true;
        }
        return false;
	}

    public boolean set(int dimension, double value)
    {
        if(lockLvl == 0)
        {
            pointdim[dimension] = value;
            return true;
        }
        return false;

    }

    public double get(int dimension)
    {
        return pointdim[dimension];
    }
	
	public BetterPoint()
	{}
}