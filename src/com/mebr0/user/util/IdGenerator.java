package com.mebr0.user.util;

import com.mebr0.user.base.Employee;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Student.Degree;

import java.time.LocalDate;

/**
 * Class that generates ids for {@link com.mebr0.user.base.Employee} and {@link com.mebr0.user.entity.Student}
 * Also increments count of following classes
 *
 * @author A.Yergali
 * @version 1.0.1
 */
public abstract class IdGenerator {

    private IdGenerator() {
        throw new AssertionError("No " + getClass().getSimpleName() + " instances for you!");
    }

    public static String generate(Degree degree) {
        LocalDate today = LocalDate.now();

        int currentYear = today.getYear();

        if (today.getMonth().getValue() <= 7)
            currentYear--;

        String yearString = String.valueOf(currentYear).substring(2);

        String degreeString;

        switch (degree) {
            case MD:
                degreeString = "MD";
                break;
            case PHD:
                degreeString = "PD";
                break;
            default:
                degreeString = "BD";
                break;
        }

        String postfix = String.valueOf(Student.count++);
        StringBuilder builder = new StringBuilder(postfix);

        while (builder.length() < 6) {
            builder.insert(0, "0");
        }

        return yearString + degreeString + builder.toString();
    }

    public static String generate() {
        String postfix = String.valueOf(Employee.count++);
        StringBuilder builder = new StringBuilder(postfix);

        while (builder.length() < 6) {
            builder.insert(0, "0");
        }

        return "EMP" + builder.toString();
    }
}
