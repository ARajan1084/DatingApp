package datingapp.exceptions;

public class BioWordLengthException extends Exception {
    private int maxSize;

    public BioWordLengthException (int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxSize () {
        return maxSize;
    }
}
