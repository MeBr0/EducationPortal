package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.intranet.session.base.UserSession;
import com.mebr0.intranet.session.mark.Level;
import com.mebr0.study.Course;
import com.mebr0.study.Subject;
import com.mebr0.study.time.Semester;
import com.mebr0.study.time.Semester.Type;
import com.mebr0.user.entity.Manager;
import com.mebr0.user.entity.Teacher;
import com.mebr0.user.type.Faculty;

import java.util.List;

import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.ask;
import static com.mebr0.intranet.util.Scanner.index;

/**
 * Session class for {@link Manager} within {@link com.mebr0.intranet.Intranet} session
 *
 * @author A.Yergali
 * @version 1.0
 */
public class ManagerSession implements UserSession {

    private final Manager MANAGER;

    private final Database DB = Database.getInstance();

    private ManagerSession(Manager manager) {
        this.MANAGER = manager;
    }

    public static ManagerSession getSession(Manager manager) {
        return new ManagerSession(manager);
    }

    @Override
    public void greet() {
        print("Logged in as " + MANAGER.getFullName() + " (" + MANAGER.getClass().getSimpleName() + ")");
    }

    @Override
    public Status begin() {
        greet();

        root();

        return Status.OK;
    }

    @Level(1)
    private void root() {
        String[] options = { "Create subject", "Show subjects", "Create course", "Show courses" };
        Runnable[] methods = { this::createSubject, this::showSubjects, this::createCourse, this::showCourses };

        split(options, methods);
    }

    @Level(2)
    private void createSubject() {
        String title = ask("Enter title");
        String description = ask("Enter description");
        Faculty faculty = ask(Faculty.class);

        Subject subject = new Subject(title, description, faculty);
        DB.create(subject);
        print("Created " + subject);
    }

    @Level(2)
    private void showSubjects() {
        List<Subject> subjects = DB.getSubjects();

        if (subjects.isEmpty())
            print("Empty");
        else
            print(subjects);
    }

    @Level(2)
    private void createCourse() {
        String title = ask("Enter title of subject");
        Subject subject = DB.getSubject(title);

        int creditNumber = index("Enter number of credits");

        String fullNameOrLogin = ask("Enter full name or login of teacher");
        Teacher teacher = DB.getUser(fullNameOrLogin, Teacher.class);

        int year = index("Enter year of course");
        Type type = ask(Type.class);
        Semester semester = Semester.from(year, type);

        Course course = Course.from(subject, (byte) creditNumber, teacher, semester);
        DB.create(course);
        print("Created " + course);
    }

    @Level(2)
    private void showCourses() {
        List<Course> courses = DB.getCourses();

        if (courses.isEmpty())
            print("Empty");
        else
            print(courses);
    }
}
