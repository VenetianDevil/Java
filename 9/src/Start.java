

public class Start {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		PointGeneratorInterface pointGeneratorInterface = new PointGenerator();
		ParallelCalculations parallelCalculationsInterface = new ParallelCalculations();
		parallelCalculationsInterface.setPointGenerator(pointGeneratorInterface);
		parallelCalculationsInterface.setNumberOfThreads(3);
		parallelCalculationsInterface.start();
		Thread.sleep(2);
		parallelCalculationsInterface.suspendCalculations();
		parallelCalculationsInterface.setNumberOfThreads(5);
		Thread.sleep(1);
		parallelCalculationsInterface.continueCalculations();
		Thread.sleep(2);
		parallelCalculationsInterface.suspendCalculations();
		int[][] histogram = parallelCalculationsInterface.getHistogram();
		double[] center = parallelCalculationsInterface.getGeometricCenter();
		for(int i=0; i<PointInterface.MAX_POSITION+1; i++)
		{
			for(int j=0; j<PointInterface.MAX_POSITION+1; j++)
			System.out.print(histogram[i][j] + "\t");
			
			System.out.println();
		}
 		System.out.println(center[0] + " " + center[1]);
 		System.out.println(parallelCalculationsInterface.pointsCount);//.get());
	}
}
