/**
 * Klasa reprezentujÄca okrÄt o okreĹlonym rozmiarze. Obiekty posiadajÄ pole
 * shipwreck, ktĂłre pozwala okreĹliÄ stan w jakim okrÄt siÄ znajduje.
 */
public class Ship 
{
    private boolean shipwreck;
    private int size;
    private static ShipSizeLimit limit = null;

    private static int[] counter = null;

    public static void setLimit(ShipSizeLimit limit)
    {
        Ship.limit = limit;
        Ship.counter = new int[limit.getNumberOfSizes()];
    }

    public static Ship getShip(int size)
    {
        if(Ship.limit == null || size < 1 || size > counter.length)
        {return null;}

        int lim = limit.getLimit(size);
        if(lim > Ship.counter[size-1])
        {
            Ship.counter[size-1] ++; //zwiekszenie ilosci zapamietannycvh statkow danego rozmiaru
            Ship newShip = new Ship(size); //stworzenie nowego statku
            return newShip;
        }
        else
        { return null;}
    }

    public void shipwreck() 
    {
        shipwreck = true;
        Ship.counter[size-1] --; //zmniejszenie ilosci zapamietanych statkow danego rozmiaru
        System.out.println("Zniszczylem statek o rozmiarze " + size);
    }

    public boolean isShipwreck() {
        return shipwreck;
    }

    private Ship(int size)
    {
        this.size = size;
        this.shipwreck = false;
        System.out.println("Stworzylem statek o rozmiarze " + size);
    }
}