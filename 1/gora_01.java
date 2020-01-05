class Silnia
{
    public int sil(int S)
    {
        int wynik=1;
        while(S>1)
        {
            wynik = wynik * S;
            S--;
        }
        return wynik;
    }
}

class Start
{
    public static int N = 3;
    public static double X_FIRST = 0.1;
    public static double X_LAST = 0.5;
    public static int STEPS = 3;

    public static void main (String[] argv)
    {
        double x = X_FIRST;
        double odstep;
        Silnia f = new Silnia();
        
        odstep=(X_LAST-x)/(STEPS+1);
        //System.out.println("odstep = " + odstep);
        
        for(int j=0; j<STEPS+2; j++)
        {
        	double wartosc = 0;
        	double szereg = 0;
        	double roznica = 0;
        	
        	//liczenie wartosci funkcji
	        wartosc = Math.sin(x)*Math.cos(x);
	        
	        // licznie z szeregu potengowego
	        for(int i=0; i<N; i++)
	        {
	            szereg = szereg + (Math.pow(-1, i)*Math.pow(2,2*i)*Math.pow(x, 2*i+1))/f.sil(2*i+1);
	        }
	        
	        //liczenie roznicy
	        roznica = wartosc - szereg;
	
	        //wypisywanie rezultatu
	        String result = String.format( "x=%7.4f sin(x)cos(x)=%8.6f aprox=%8.6f delta=%10.8f", x, wartosc, szereg, roznica);
	        System.out.println(result);
	        
	        x=x+odstep;
	    }
    }
}