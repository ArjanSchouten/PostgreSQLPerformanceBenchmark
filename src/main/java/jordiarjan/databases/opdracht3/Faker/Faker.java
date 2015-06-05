package jordiarjan.databases.opdracht3.Faker;

import java.util.Random;

/**
 * Created by Arjan on 05/06/2015.
 */
public class Faker extends com.github.javafaker.Faker {

    public String studentNumber()
    {
        Random randomStudentNumber = new Random();

        long range = 9999999 - 1000000 + 1;
        long fraction = (long)(range * randomStudentNumber.nextDouble());
        int randomNumber =  (int)(fraction + 1000000);

        return Integer.toString(randomNumber);
    }

    public int age()
    {
        int maxAge = 100;
        return (int)(Math.random() * maxAge);
    }

    public String gender()
    {
        String[] gender = { "man", "vrouw", "onbekend", "onbepaald" };
        int index = (int)Math.round(Math.random() * 4);
        return gender[index % gender.length];
    }
}
