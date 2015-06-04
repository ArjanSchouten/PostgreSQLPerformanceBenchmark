package jordiarjan.databases.opdracht3;

/**
 * Created by Arjan on 03/06/2015.
 */
public class Benchmark {

    /**
     * Run the targeted benchmark
     *
     * @return int Executed jobs
     */
    public int runBenchmarkTarget() { return 0; }

    public long runBenchmark()
    {
        long start = System.currentTimeMillis();
        int executedJobs = runBenchmarkTarget();
        return timePerExecutedJobs(start, executedJobs);
    }

    protected long timePerExecutedJobs(long start, int executedJobs)
    {
        return (System.currentTimeMillis() - start) / executedJobs;
    }
}
