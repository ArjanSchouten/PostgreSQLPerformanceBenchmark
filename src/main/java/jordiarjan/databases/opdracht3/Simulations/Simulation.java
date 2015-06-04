package jordiarjan.databases.opdracht3.Simulations;

import jordiarjan.databases.opdracht3.DBManager;

/**
 * Created by Arjan on 03/06/2015.
 */
public interface Simulation {

    void setupDatabase(DBManager dbManager);

    void runSimulation(DBManager dbManager);
}
