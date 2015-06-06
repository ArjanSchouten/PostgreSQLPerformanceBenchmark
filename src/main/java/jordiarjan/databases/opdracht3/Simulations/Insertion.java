package jordiarjan.databases.opdracht3.Simulations;

import jordiarjan.databases.opdracht3.DBManager;
import jordiarjan.databases.opdracht3.Repository.ClassRepository;
import jordiarjan.databases.opdracht3.Repository.StudentRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Arjan on 03/06/2015.
 */
public class Insertion implements Simulation {

    private int concurrentUsers = 3;
    private int runs = 600;
    private int insertedClassesAmount = 1;
    private long totalTime = 0;

    public void databaseSetup() {
        DBManager dbManager = new DBManager();
        try {
            Statement statement = dbManager.getConnection().createStatement();
            statement.execute("INSERT INTO teacher (teacherscode, firstname, lastname, insertion, age, sex, street, postalcode, city, phonenumber) " +
                    "SELECT 'teacher', 'test', 'test', '', 19, 'man', 'test', '2157', 'Rotterdam', '02545751256' WHERE NOT EXISTS (SELECT 1 FROM teacher WHERE teacherscode='teacher')");
            (new ClassRepository(dbManager  )).insertFakeClass();
            dbManager.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void runSimulation() {
        for(int i = 0; i < concurrentUsers; i++){
            addConcurrentUser();
        }
    }

    protected void addConcurrentUser()
    {
        new Thread(new Runnable() {
            public void run() {
                DBManager dbManager = new DBManager();
                StudentRepository studentRepository = new StudentRepository(dbManager);
                ClassRepository classRepository = new ClassRepository(dbManager);
                runUserInsert(studentRepository, classRepository, dbManager);
                System.out.println("Average insertion time " + averageRuntime());
            }
        }).start();
    }

    protected void runUserInsert(StudentRepository studentRepository, ClassRepository classRepository, DBManager dbManager)
    {
        for(int i = 0; i < runs; i++){
            insertIntoDatabase(studentRepository, classRepository, dbManager);
        }
    }

    protected void insertIntoDatabase(StudentRepository studentRepository, final ClassRepository classRepository, DBManager dbManager)
    {
        long start = System.currentTimeMillis();
        boolean classInserted = false;
        try {
            String studentNumber = studentRepository.insertFakeStudent();
            if(isChanceOf((double)1/30)) {
                classRepository.insertFakeClass();
                classInserted = true;
            }
            classRepository.addStudentToClass((int)Math.ceil(Math.random() * insertedClassesAmount), studentNumber);
            if(isChanceOf((double)1/30)) {
                String course = classRepository.insertFakeModule();
                List<Integer> classIds = classRepository.getAllClassIds();
                for(int classId : classIds)
                    if(isChanceOf((double)1/6.66667))
                        classRepository.insertFakeRoster(classId, course);
            }
            dbManager.getConnection().commit();
            if(classInserted)
                insertedClassesAmount++;
            Thread.sleep(50);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.totalTime += System.currentTimeMillis() - start;
    }

    public long averageRuntime()
    {
        return totalTime / this.totalRunnedIterations();
    }

    private boolean isChanceOf(double chance)
    {
        return (new Random()).nextInt(100) <= (int)(chance * 100);
    }

    private int totalRunnedIterations() { return runs * concurrentUsers; }
}
