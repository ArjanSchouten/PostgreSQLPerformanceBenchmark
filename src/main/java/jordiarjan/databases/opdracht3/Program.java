package jordiarjan.databases.opdracht3;

import jordiarjan.databases.opdracht3.Repository.ClassRepository;
import jordiarjan.databases.opdracht3.Repository.StudentRepository;

import java.sql.SQLException;

/**
 * Created by jordi_000 on 1-6-2015.
 */
public class Program {

    public static void main(String args[] )
    {
        if (args.length <= 0)
            return;
        
        if (args[0].equals("simulationInsert"))
            insertBenchmark();
        else if (args[0].equals("simulationSelect"))
            selectBenchmark();
    }

    private static void insertBenchmark()
    {
        final int threads = 3;
        int runs = 600;
        Benchmark benchmark = new Benchmark() {
            @Override
            public int runBenchmarkTarget()
            {
                for( int numOfThreads = 0; numOfThreads < threads; numOfThreads++ )
                {
                    new Thread(new Runnable() {
                        public void run() {
                            for( int x = 0; x < 600; x++ ) {

                            }
                        }
                    }).start();

                }

                return 600;
            }
        };

        long averageRunTime = benchmark.runBenchmark();
        System.out.println("Average runtime per benchmark round: " + averageRunTime);
    }

    private static void selectBenchmark()
    {

    }
}
