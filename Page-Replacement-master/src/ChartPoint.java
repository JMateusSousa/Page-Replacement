public class ChartPoint {
    private int numFrames;
    private int numHits;

    public ChartPoint(int numFrames, int numHits){
        this.numFrames = numFrames;
        this.numHits = numHits;
    }

    public int getNumFrames() {
        return numFrames;
    }

    public int getNumHits() {
        return numHits;
    }
}
