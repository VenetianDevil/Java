public class WrongArgumentException extends Exception {
    public final Point problem;

    /**
     * Metoda zwraca punkt, ktĂłry stanowi powĂłd wystÄpienia wyjÄtku.
     * 
     * @return punkt bÄdÄcy powodem wystÄpienia wyjÄtku.
     */
    public Point getProblemPoint() {
        return problem;
    }

    public WrongArgumentException(Point point) {
        problem = point;
    }

}