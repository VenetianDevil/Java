import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelCalculations implements ParallelCalculationsInterface
{
	PointGeneratorInterface generate;
	int threadsNumber;
	Vector<PointInterface> points = new Vector<PointInterface>();
	int a=0;
	int b=0;
	int pointsCount =0;
//	AtomicInteger pointsCount = new AtomicInteger();
	double[] geom = new double[2];
	int[][] hist = new int[PointInterface.MAX_POSITION+1][PointInterface.MAX_POSITION+1];
//	AtomicInteger[][] hist = new AtomicInteger[PointInterface.MAX_POSITION+1][PointInterface.MAX_POSITION+1];
	
	void Calculate(PointInterface point)
	{
//		pointsCount.addAndGet(1);
		synchronized(this)
		{
			pointsCount++;
			a += point.getPositions()[0];
			b += point.getPositions()[1];
			geom[0] = 1.0 * a/pointsCount;
			geom[1] = 1.0 * b/pointsCount;
			hist[point.getPositions()[0]][point.getPositions()[1]]++;//.incrementAndGet();//.addAndGet(1);		
		}
	}
	
	class Create extends Thread
	{
		private boolean suspended = false;
		
		Create() {}
		
		public void run()
		{
			while(!Thread.currentThread().isInterrupted())
			{
				try
				{
					while(!suspended)
					{
						PointInterface point = generate.getPoint();
//						points.add(point);
//						System.out.println(point.getPositions()[0] + "\t" + point.getPositions()[1]);
						Calculate(point);
						synchronized(this)
						{
							if(suspended==true)
								wait();
						}
					}
				}catch(InterruptedException e){}
			}
		}
		
		synchronized void suspendT()
		{
			suspended = true;
//			System.out.println("thread: " + this.getName() + " suspended");
		}
		
		synchronized void resumeT()
		{
			suspended = false;
			notify();
//			System.out.println("thread: " + this.getName() + " resumed");
		}
	}
	
	List<Create> watki = new ArrayList<Create>(); 
	
	@Override
	public void setPointGenerator(PointGeneratorInterface generator)
	{
		generate = generator;

	}

	@Override
	public void setNumberOfThreads(int threads)
	{
		int needed = 0;
		if(threadsNumber<threads)
		{
			needed = threads-threadsNumber;
			for(int i=0; i<needed; i++)
			{
				watki.add(new Create());
				
			}
		}
		else if(threadsNumber>threads)
		{
			needed = threadsNumber-threads;
			for(int i=0; i<needed; i++)
			{	
				watki.remove(threadsNumber-1-i);
			}
		}
		
		threadsNumber = threads;
	}

	@Override
	public void start()
	{
		for(int i=0; i<threadsNumber; i++)
		{
			watki.get(i).start();
		}
//		System.out.println("Start");
	}

	@Override
	public void suspendCalculations()
	{
		for(int i=0; i<threadsNumber; i++)
		{
			watki.get(i).suspendT();
		}
	}

	@Override
	public void continueCalculations()
	{
		for(int i=0; i<threadsNumber; i++)
		{
			watki.get(i).resumeT();
		}
	}

	@Override
	public double[] getGeometricCenter()
	{
		return geom;
	}

	@Override
	public int[][] getHistogram()
	{
//		int[][] histInt = new int[PointInterface.MAX_POSITION+1][PointInterface.MAX_POSITION+1];
//		
//		for(int i=0; i<PointInterface.MAX_POSITION+1; i++)
//		{
//			for(int j=0; j<PointInterface.MAX_POSITION+1; j++)
////			System.out.println(p.getPositions()[0] + "\t" + p.getPositions()[1]);
//			{
//				if(hist[i][j]!=null)
//					histInt[i][j] = hist[i][j].get();				
//			}
////			System.out.println(hist[p.getPositions()[0]][p.getPositions()[1]]);
//		}
		
		return hist;
	}

}
