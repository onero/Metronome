package dk.adamino.metronome.BLL;

import java.util.ArrayList;

public class BPMCalculator implements IBPMCalculator {


    private ArrayList<Long> mRecordedTimes;
    private static final Long MILLISECONDS_IN_A_MINUTE = 60000L;

    public BPMCalculator() {
        mRecordedTimes = new ArrayList<>();
    }

    /**
     * Check if we have enough recordings to calculate BPM
     * @return true when at least 2 records are present!
     */
    public boolean readyForCalculation() {
        return mRecordedTimes.size() >= 2;
    }
    @Override
    public void recordTime() {
        long time = System.currentTimeMillis();
        mRecordedTimes.add(time);
    }

    /**
     * Compute and return time deltas (differences between recorded times)
     * @return Array of long deltas
     */
    private ArrayList<Long> getDeltas() {
        ArrayList<Long> deltas = new ArrayList<>();

        for (int i = 0; i < mRecordedTimes.size() - 1; i++) {
            Long delta = mRecordedTimes.get(i + 1) - mRecordedTimes.get(i);
            deltas.add(delta);
        }

        return deltas;
    }

    @Override
    public int getBPM() {
        ArrayList<Long> deltas = getDeltas();
        return calculateBpm(deltas);
    }

    /**
     * Calculate the beats per minute
     * @param deltas
     * @return BPM as int
     */
    private int calculateBpm(ArrayList<Long> deltas) {
        Long sum = 0L;

        for (Long delta : deltas) {
            sum = sum + delta;
        }

        Long average = sum / deltas.size();

        return (int) (MILLISECONDS_IN_A_MINUTE / average);
    }
}
