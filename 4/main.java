class main
{
    public static void main(String[] argv)
    {
        BetterPoint object = new BetterPoint();
        object.setDimensions(3);
        object.set(1, 4);
        object.set(2,2);
        double dim1 = object.get(1);
        double dim3 = object.get(3);

        System.out.println("dim1 = " + dim1 + " dim3 = " + dim3);

        object.move(1, 2);
        dim1 = object.get(1);
        System.out.println("dim1 = " + dim1);

        int l1 = object.lock("First");
        object.move(1, 2);
        dim1 = object.get(1);
        System.out.println("dim1 = " + dim1 + " l1 = " + l1);

        int l2 = object.lock(null);
        object.move(1, 2);
        dim1 = object.get(1);
        System.out.println("dim1 = " + dim1 + " l2 = " + l2);

//---------------------------------------------------------------------

        BetterPoint point = new BetterPoint();
        point.setDimensions(3);
        point.set(1, 8);
        point.set(2,9);
        double pdim1 = point.get(1);
        double pdim3 = point.get(3);

        System.out.println("\tpdim1 = " + pdim1 + " pdim3 = " + pdim3);

        int pl1 = point.lock("Third");
        point.move(1, 2);
        pdim1 = point.get(1);
        System.out.println("\tpdim1 = " + pdim1 + " pl1 = " + pl1);
        int lock = point.lockLevel();

        System.out.println("\tpoint lock level = " + lock);
        
        int pu1 = point.unlock("Third");
        point.move(1, 2);
        pdim1 = point.get(1);
        System.out.println("\tpdim1 = " + pdim1 + " u1 = " + pu1);
        
        lock = point.lockLevel();
        System.out.println("\tpoint lock level = " + lock);

//---------------------------------------------------------------

        lock=object.lockLevel();
        System.out.println("object lock level = " + lock);


        int u1 = object.unlock("Second");
        object.move(1, 2);
        dim1 = object.get(1);
        System.out.println("dim1 = " + dim1 + " u1 = " + u1);

        int u2 = object.unlock("First");
        object.move(1, 2);
        dim1 = object.get(1);
        System.out.println("dim1 = " + dim1 + " u2 = " + u2);
    }    
}