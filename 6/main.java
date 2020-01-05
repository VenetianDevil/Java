class Main
{

	public static void main(String[] args)
    {
		int[][] tab = new int[8][7];

		
    	tab[2][0] = 1;
    	tab[3][0] = 2;
    	tab[4][0] = 2;
    	tab[5][0] = 5;
    	
    	tab[2][1] = 1;
    	tab[5][1] = 1;
    	
    	tab[0][2] = 1;
    	tab[2][2] = 1;
    	tab[3][2] = 1;
    	tab[5][2] = 1;
    	
    	tab[0][3] = 1;
    	tab[1][3] = 1;
    	tab[2][3] = 1;
    	tab[4][3] = 1;
    	tab[5][3] = 1;
    	tab[6][3] = 1;
    	
    	tab[2][4] = 1;
    	tab[3][4] = 1;
    	tab[4][4] = 3;
    	tab[6][4] = 2;
    	
    	tab[0][5] = 1;
    	tab[1][5] = 1;
    	tab[2][5] = 1;
    	tab[4][5] = 1;
    	tab[5][5] = 1;
    	tab[6][5] = 1;    	
    	tab[7][4] = 1;
    	
    	tab[7][6] = 1;
    	
    	PathFinderEnum mapkaR = PathFinderEnum.RIGHT_HAND_TRAFFIC;
    	mapkaR.setMap(tab);
    	
    	PositionInterface begin = new Position(7, 4);
    	PositionInterface end = new Position(0, 2);
    	PositionInterface[] route =  mapkaR.getFastestRoute(begin ,end);
    	
    	System.out.println("Wynik: ");
    	
    	for(int m=0; m<route.length; m++)
    	{
    		System.out.println(route[m].getCol() + "\t" + route[m].getRow());
    	}
    }
}