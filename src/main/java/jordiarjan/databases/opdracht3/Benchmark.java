package jordiarjan.databases.opdracht3;

/**
 * Created by Arjan on 03/06/2015.
 */
public class Benchmark {

    private int executedJobs = 0;

    private long totalTime = 0;

    /**
     * Run the targeted benchmark
     *
     * @return int Executed jobs
     */
    protected void runBenchmarkTarget() { }

    public void startBenchmark()
    {
        runBenchmarkTarget();
    }

    public long timePerExecutedJobs()
    {
        return getTotalTime() / executedJobs;
    }

    public synchronized void incrementExecutedJobs()
    {
        this.executedJobs++;
    }

    public synchronized long getTotalTime() {
        return totalTime;
    }

    public synchronized void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
