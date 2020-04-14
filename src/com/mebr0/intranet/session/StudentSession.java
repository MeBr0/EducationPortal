package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.intranet.session.base.Session;
import com.mebr0.intranet.session.base.UserSession;
import com.mebr0.intranet.session.mark.Level;
import com.mebr0.study.Course;
import com.mebr0.study.Subject;
import com.mebr0.study.mark.Marks;
import com.mebr0.study.registration.Registration;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Teacher;

import java.util.List;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.print;

/**
 * Session class for {@link Student} within {@link com.mebr0.intranet.Intranet} session
 *
 * @author A.Yergali
 * @version 2.0
 */
public class StudentSession implements UserSession {

    private final Student student;
    private Course currentCourse;

    private final Database DB = Database.getInstance();
    private final Registration REG = Registration.getInstance();

    private StudentSession(Student student) {
        this.student = student;
    }

    public static StudentSession getSession(Student student) {
        return new StudentSession(student);
    }

    @Override
    public void greet() {
        print("Logged in as " + student.getFullName() + " (" + student.getClass().getSimpleName() + ")");
    }

    @Override
    public Session.Status begin() {
        greet();

        root();

        return Session.Status.OK;
    }

    @Level(1)
    private void root() {
        String[] options = { "Registration", "Current courses", "Profile" };
        Runnable[] methods = { this::registration, this::activeCourses, this::profile };

        split(options, methods);
    }

    @Level(2)
    private void registration() {
        List<Course> courses = REG.get(student);

        split(courses, (option -> {
            courses.get(option - 1).addStudent(student);
            print("Registered");
        }));
    }

    @Level(2)
    private void activeCourses() {
        List<Course> courses = student.getActiveCourses();

        split(courses, (option -> {
            currentCourse = courses.get(option - 1);
            course();
        }));
    }

    @Level(3)
    private void course() {
        String[] options = { "Main info", "Marks", "Files" };
        Runnable[] methods = { this::courseInfo, this::marks, this::files };

        split(options, methods);
    }

    @Level(4)
    private void courseInfo() {
        Subject subject = currentCourse.getSubject();
        Teacher teacher = currentCourse.getTeacher();

        print("Course id: " + currentCourse.getId(),
                "Main subject: " + subject.getTitle() + " (" + subject.getDescription() + ")",
                "Faculty: " + subject.getFaculty().getShortName(),
                "Number of credits: " + currentCourse.getCreditsNumber(),
                "Teacher info: " + teacher.getFullName() + " (" + teacher.getLogin() + ")",
                "Semester: " + currentCourse.getSemester().toString(),
                "Student count: " + currentCourse.getStudents().size());
    }

    @Level(4)
    private void marks() {
        Marks marks = currentCourse.getMarks(student);

        print("Attestation 1: " + marks.getAttestation1(),
                "Attestation 2: " + marks.getAttestation2(),
                "Final: " + marks.getFinal());
    }

    @Level(4)
    private void files() {
        List<String> files = currentCourse.getFiles();

        split(files, (option -> {
            print(currentCourse.readFile(files.get(option - 1)));
        }));
    }

    @Level(2)
    private void profile() {
        String[] options = { "Change password" };
        Runnable[] methods = { this::changePassword };

        split(options, methods);
    }

    @Level(3)
    private void changePassword() {
        if (changePassword(student))
            print("Password changed");
        else
            error("Could not change password");
    }
}
