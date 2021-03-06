import java.util.Vector;

class main
{
    public static void main(String[] args)
    {
        Bus a1 = new Bus(100);
        Bus a2 = new Bus(200);
        Bus a3 = new Bus(300);

        Vector<BusStop> line = new Vector<BusStop>();

        BusStop A = new BusStop("A");
        BusStop B = new BusStop("B");
        BusStop C = new BusStop("C");
        BusStop D = new BusStop("D");
        BusStop E = new BusStop("E");
        BusStop F = new BusStop("F");
        BusStop G = new BusStop("G");
        BusStop H = new BusStop("H");
        BusStop I = new BusStop("I");
        BusStop J = new BusStop("J");
        BusStop K = new BusStop("K");
        BusStop L = new BusStop("L");
        BusStop M = new BusStop("M");
        BusStop N = new BusStop("N");
        BusStop O = new BusStop("O");

        line.add(A);
        line.add(B);
        line.add(C);
        line.add(D);
        line.add(E);
        line.add(F);
        line.add(G);
        
        BusLine czerwona = new BusLine(line);

        line.clear();

        line.add(H);
        line.add(I);
        line.add(C);
        line.add(J);
        line.add(K);

        BusLine zielona = new BusLine(line);
        line.clear();

        line.add(L);
        line.add(E);
        line.add(M);
        line.add(N);
        line.add(O);

        BusLine niebieska = new BusLine(line);
        line.clear();

        PathFinder szukaj = new PathFinder();
        szukaj.addLine(czerwona, a1);
        szukaj.addLine(zieona, a2);
        szukaj.addLine(niebieska, a3);

        szukaj.find(I, N, 2);
        int numberSolutions = szukaj.getNumberOfSolutions();

        System.out.println("solutions = " + numberSolutions);

        for(int i=0; i<numberSolutions; i++)
        {
            int przystanki = szukaj.getBusStops(i);
            System.out.println("liczba przystankow w solution = " + i + " to : " + przystanki);
            System.out.println("\tkolejne przystanki to: ");
            
            for(int j=0; j<przystanki; j++)
            {
                System.out.println("\tprzystanek " + j + " to " + szukaj.getBusStop(i, j));
                System.out.println("\t\t z tego przystanku odjade: " + szukaj.getBus(i, j));
            }        
        }
    }
}