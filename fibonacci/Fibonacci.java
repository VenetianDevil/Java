class Fibonacci {
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;

       System.out.println( "Oto kilka pierwszych wyrazow ciagu Fibonacciego" );

       System.out.println(lo);
       
       while ( hi < 10000 ) {
           System.out.println(hi);
           hi = lo + hi; 
           lo = hi - lo; 
       }
   }
}