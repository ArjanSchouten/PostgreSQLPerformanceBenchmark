package jordiarjan.databases.opdracht3.Simulations;

import jordiarjan.databases.opdracht3.DBManager;
import jordiarjan.databases.opdracht3.Repository.StudentRepository;

import java.util.List;

/**
 * Created by Arjan on 05/06/2015.
 */
public class Selection implements Simulation {

    private int concurrentUsers = 2;
    private int iterations = 600;
    private long time = 0;

    private List<String> students;

    public void databaseSetup() {
        StudentRepository studentRepository = new StudentRepository(new DBManager());
        students = studentRepository.getAllStudentsIdentifiers();
    }

    public void runSimulation() {
        for(int i = 0; i < concurrentUsers; i++){
            addConcurrentUser();
        }
    }

    protected void addConcurrentUser()
    {
        for(int i = 0; i < iterations; i++){

            long start = System.currentTimeMillis();

        }
    }
}
