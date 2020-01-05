/**
 * Interfejs generatora punktĂłw.
 */
public interface PointGeneratorInterface {
    /**
     * Metoda zwraca obiekt reprezentujÄcy pojedynczy punkt. Metoda moĹźe byÄ
     * wywoĹywana wspĂłĹbieĹźnie tj. wiele wÄtkĂłw moĹźe wywoĹywaÄ tÄ metodÄ w tym samym
     * czasie.
     *
     * @return obiekt reprezentujÄcy pojedynczy punkt w dwuwymiarowej przestrzeni.
     */
    public PointInterface getPoint();
}