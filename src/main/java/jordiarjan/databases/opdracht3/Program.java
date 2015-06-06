package jordiarjan.databases.opdracht3;

import jordiarjan.databases.opdracht3.Simulations.Insertion;

/**
 * Created by jordi_000 on 1-6-2015.
 */
public class Program {

    public static void main(String args[]) {
        if (args.length <= 0)
            return;

        if (args[0].equals("simulationInsert"))
            insertionBenchmark();
        else if (args[0].equals("simulationSelect"))
            selectionBenchmark();
    }

    private static void insertionBenchmark() {
        Insertion insertion = new Insertion();
        insertion.databaseSetup();
        insertion.runSimulation();
    }

    private static void selectionBenchmark() {

    }
}
