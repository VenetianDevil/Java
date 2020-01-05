class main
{
    public static void main(String[] argv)
    {
        int[] shipSizes = new int[3];
        shipSizes[0] = 3;
        shipSizes[1] = 2;
        shipSizes[2] = 4;
        
        Ship statek_1 = Ship.getShip(2);
        
        ShipSizeLimit limit = new ShipSizeLimit(shipSizes);
        Ship.setLimit(limit);

        /*int size = 2;
        int lim = limit.getLimit(size);
        System.out.println(lim);*/

        // Ship statek_1 = Ship.getShip(2);

        Ship statek_2 = Ship.getShip(3);

        Ship statek_3 = Ship.getShip(2);
        Ship statek_4 = Ship.getShip(1);
        statek_4.shipwreck();
        Ship statek_5 = Ship.getShip(1);
        Ship statek_6 = Ship.getShip(1);
        Ship statek_7 = Ship.getShip(1);
        Ship statek_9 = Ship.getShip(1);
        Ship statek_8 = Ship.getShip(4);
    }
}