package jordiarjan.databases.opdracht3;

import jordiarjan.databases.opdracht3.Repository.StudentRepository;

import java.sql.SQLException;

/**
 * Created by jordi_000 on 1-6-2015.
 */
public class Program {

    public static void main(String args[] )
    {
        StudentRepository student = new StudentRepository(new DBManager());
        try
        {
            student.Create();
        }
        catch (SQLException e )
        {
            e.printStackTrace();
        }

        if (args.length <= 0)
            return;
        
        if (args[0].equals("simulationInsert"))
            insertBenchmark();
        else if (args[0].equals("simulationSelect"))
            selectBenchmark();
    }

    private static void insertBenchmark()
    {
        int concurrentUsers = 3;
        Benchmark benchmark = new Benchmark() {
            @Override
            public int runBenchmarkTarget()
            {
                return 0;
            }
        };
    }

    private static void selectBenchmark()
    {

    }



}
