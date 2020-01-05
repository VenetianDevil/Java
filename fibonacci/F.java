class Fibonacci {
    public void show( final int MAX ) {
       int lo = 1;
       int hi = 1;

       int counter = 0;
       counter++;
       System.out.println("oto counter = " + counter);

       System.out.println(lo);
       while ( hi < MAX ) {
           System.out.println(hi);
           hi = lo + hi;   
           lo = hi - lo;   
       }
   }
}

class FibonacciTester {
	private final static int MAX = 1000;
	private final static int REPETITIONS = 20;
	
	public static void main( String[] argv ) {
		Fibonacci f = new Fibonacci();
		for ( int i = 0; i < REPETITIONS; i++ )
			f.show( MAX );
		f = new Fibonacci();
		f.show( 2 * MAX );
	}
}