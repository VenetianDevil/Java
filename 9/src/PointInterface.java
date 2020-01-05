public interface PointInterface {
    /**
     * NajwiÄksza, poprawna wartoĹÄ wspĂłĹrzÄdnej punktu
     */
    public static final int MAX_POSITION = 16;

    /**
     * Tablica o rozmiarze 2, zawierajÄca wspĂłĹrzÄdne punktu.
     *
     * @return poĹoĹźenie punktu w dwuwymiarowej przestrzeni
     */
    public int[] getPositions();
}