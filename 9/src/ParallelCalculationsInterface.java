/**
 * Interfejs systemu obliczeĹ wspĂłĹbieĹźnych z zadanÄ liczbÄ wÄtkĂłw i z
 * moĹźliwoĹciÄ wstrzymywania i pownownego uruchamiania obliczeĹ.
 */
public interface ParallelCalculationsInterface {
    /**
     * Metoda pozwala na przekazanie do systemu obiektu oferujÄcego usĹugÄ
     * generowania punktĂłw.
     *
     * @param generator generator punktĂłw
     */
    public void setPointGenerator(PointGeneratorInterface generator);

    /**
     * Metoda pozwala na wprowadzenie do systemu wymaganej liczby wÄtkĂłw. Liczba
     * wÄtkĂłw musi zostaÄ ustawiona przed uruchomieniem obliczeĹ za pomocÄ metody
     * start(). Liczba wÄtkĂłw moĹźe zostaÄ zmieniona po uruchomieniu obliczeĹ w
     * czasie, gdy obliczenia zostaĹy wstrzymane za pomocÄ suspendCalculations a
     * jeszcze nie uruchomione ponownie.
     *
     * @param threads liczba wÄtkĂłw obliczeniowych
     */
    public void setNumberOfThreads(int threads);

    /**
     * Metoda sĹuĹźÄca do rozpoczÄcia obliczeĹ. Po wprowadzeniu danych do systemu za
     * pomocÄ pary metod setPointGenerator oraz setNumberOfThreads obliczenia sÄ
     * uruchamiane metodÄ start(). Metoda start() nie moĹźe zablokowaÄ wÄtku
     * uĹźytkownika, tj. musi zakoĹczyÄ siÄ i pozwoliÄ uĹźytkownikowi na dalsze
     * uĹźywanie tego samego wÄtku. Obliczenia majÄ byÄ realizowane za pomocÄ wÄtkĂłw
     * utworzonych przez implementacjÄ interfejsu ParallelCalculationsInterface.
     */
    public void start();

    /**
     * Zlecenie wstrzymania obliczeĹ. Po zakoĹczeniu metody suspendCalculations
     * obliczenia nie mogÄ byÄ wykonywane. Czas pracy metody suspendCalculations nie
     * moĹźe byÄ znaczÄco dĹuĹźszy od czasu potrzebnego do pobrania punktu z
     * generatora.
     */
    public void suspendCalculations();

    /**
     * Zlecenie kontynuacji obliczeĹ wstrzymanych za pomocÄ suspendCalculations.
     * Metoda nie moĹźe blokowaÄ wÄtku uĹźytkownika.
     */
    public void continueCalculations();

    /**
     * Metoda zwraca poĹoĹźenie Ĺrodka geometrycznego wyliczonego na podstawie
     * wszystkich pobranych z generatora punktĂłw.
     * 
     * @return Ĺrodek geometryczny
     */
    public double[] getGeometricCenter();

    /**
     * Histogram sporzÄdzony na podstawie wszystkich pobranych z generatora punktĂłw.
     * Jest to liczba puntkĂłw, ktĂłrych wspĂłĹrzÄdne odpowiadajÄ pozycji w tablicy.
     * Pobranie np. punktu o wspĂłĹrzÄdnych [3,5] ma spowodowaÄ inkrementacjÄ
     * licznika tabeli o indeksie [3][5]. Wynikiem pracy metody jest tablica o
     * rozmiarze (PointInterface.MAX_POSITION+1)x(PointInterface.MAX_POSITION+1)
     * pozycji.
     *
     * @return histogram rozkĹadu wspĂłĹrzÄdnych punktĂłw.
     */
    public int[][] getHistogram();
}