package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.intranet.session.base.UserSession;
import com.mebr0.intranet.session.mark.Level;
import com.mebr0.study.Course;
import com.mebr0.study.Subject;
import com.mebr0.study.registration.Registration;
import com.mebr0.study.time.Semester;
import com.mebr0.study.time.Semester.Type;
import com.mebr0.user.entity.Manager;
import com.mebr0.user.entity.Student.Degree;
import com.mebr0.user.entity.Teacher;
import com.mebr0.user.type.Faculty;

import java.util.List;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.ask;
import static com.mebr0.intranet.util.Scanner.index;

/**
 * Session class for {@link Manager} within {@link com.mebr0.intranet.Intranet} session
 *
 * @author A.Yergali
 * @version 2.0
 */
public class ManagerSession implements UserSession {

    private final Manager manager;

    private final Database DB = Database.getInstance();
    private final Registration REG = Registration.getInstance();

    private ManagerSession(Manager manager) {
        this.manager = manager;
    }

    public static ManagerSession getSession(Manager manager) {
        return new ManagerSession(manager);
    }

    @Override
    public void greet() {
        print("Logged in as " + manager.getFullName() + " (" + manager.getClass().getSimpleName() + ")");
    }

    @Override
    public Status begin() {
        greet();

        root();

        return Status.OK;
    }

    @Level(1)
    private void root() {
        String[] options = { "Subjects", "Courses", "Profile" };
        Runnable[] methods = { this::subjects, this::courses, this::profile };

        split(options, methods);
    }

    @Level(2)
    private void subjects() {
        String[] options = { "Create subject", "Show subjects" };
        Runnable[] methods = { this::createSubject, this::showSubjects };

        split(options, methods);
    }

    @Level(3)
    private void createSubject() {
        String title = ask("Enter title");
        String description = ask("Enter description");
        Faculty faculty = ask(Faculty.class);

        Subject subject = new Subject(title, description, faculty);
        DB.create(subject);
        print("Created " + subject);
    }

    @Level(3)
    private void showSubjects() {
        List<Subject> subjects = DB.getSubjects();

        print(subjects);
    }

    @Level(2)
    private void courses() {
        String[] options = { "Create course", "Show courses", "Register course" };
        Runnable[] methods = { this::createCourse, this::showCourses, this::registerCourse};

        split(options, methods);
    }

    @Level(3)
    private void createCourse() {
        String title = ask("Enter title of subject");
        Subject subject = DB.getSubject(title);

        if (subject == null) {
            error("Subject not found");
            return;
        }

        int creditNumber = index("Enter number of credits");

        String fullNameOrLogin = ask("Enter full name or login of teacher");
        Teacher teacher = DB.getUser(fullNameOrLogin, Teacher.class);

        if (teacher == null) {
            error("Teacher not found");
            return;
        }

        int year = index("Enter year of course");
        Type type = ask(Type.class);
        Semester semester = Semester.from(year, type);

        Course course = Course.from(subject, (byte) creditNumber, teacher, semester);
        DB.create(course);
        print("Created " + course);
    }

    @Level(3)
    private void showCourses() {
        List<Course> courses = DB.getCourses();

        print(courses);
    }

    @Level(3)
    private void registerCourse() {
        String title = ask("Enter title of subject");
        Subject subject = DB.getSubject(title);

        if (subject == null) {
            error("Subject not found");
            return;
        }

        int creditNumber = index("Enter number of credits");

        String fullNameOrLogin = ask("Enter full name or login of teacher");
        Teacher teacher = DB.getUser(fullNameOrLogin, Teacher.class);

        if (teacher == null) {
            error("Teacher not found");
            return;
        }

        int year = index("Enter year of course");
        Type type = ask(Type.class);
        Semester semester = Semester.from(year, type);

        Course course = DB.getCourse(subject, creditNumber, teacher, semester);

        if (course == null) {
            error("Course not found");
            return;
        }

        int yearOfStudy = index("Enter year of study of students to registration");

        Degree degree = ask(Degree.class);

        REG.register(course, (byte) yearOfStudy, degree);
        print("Registered " + course);
    }

    @Level(2)
    private void profile() {
        String[] options = { "Change password" };
        Runnable[] methods = { this::changePassword };

        split(options, methods);
    }

    @Level(3)
    private void changePassword() {
        if (changePassword(manager))
            print("Password changed");
        else
            error("Could not change password");
    }
}
