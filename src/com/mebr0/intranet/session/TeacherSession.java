package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.intranet.session.base.UserSession;
import com.mebr0.intranet.session.mark.Level;
import com.mebr0.study.Course;
import com.mebr0.study.Subject;
import com.mebr0.study.mark.Marks.Mode;
import com.mebr0.study.registration.Registration;
import com.mebr0.study.time.Semester;
import com.mebr0.user.base.User;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Teacher;
import com.mebr0.user.type.Faculty;

import java.util.List;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.*;

/**
 * Session class for {@link Teacher} within {@link com.mebr0.intranet.Intranet} session
 *
 * @author A.Yergali
 * @version 1.0
 */
public class TeacherSession implements UserSession {

    private final Teacher teacher;
    private Course currentCourse;

    private final Database DB = Database.getInstance();
    private final Registration REG = Registration.getInstance();

    private TeacherSession(Teacher teacher) {
        this.teacher = teacher;
    }

    public static TeacherSession getSession(Teacher teacher) {
        return new TeacherSession(teacher);
    }

    @Override
    public void greet() {
        print("Logged in as " + teacher.getFullName() + " (" + teacher.getClass().getSimpleName() + ")");
    }

    @Override
    public Status begin() {
        greet();

        root();

        return Status.OK;
    }

    @Level(1)
    private void root() {
        String[] options = { "Current courses", "Profile" };
        Runnable[] methods = { this::activeCourses, this::profile };

        split(options, methods);
    }

    @Level(2)
    private void activeCourses() {
        List<Course> courses = DB.getActiveTeacherCourses(teacher);

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

        print("Course id: " + currentCourse.getId(),
                "Main subject: " + subject.getTitle() + " (" + subject.getDescription() + ")",
                "Faculty: " + subject.getFaculty().getShortName(),
                "Number of credits: " + currentCourse.getCreditsNumber(),
                "Teacher info: " + teacher.getFullName() + " (" + teacher.getLogin() + ")",
                "Semester: " + currentCourse.getSemester().toString(),
                "Student count: " + currentCourse.getStudents().toString());
    }

    @Level(4)
    private void marks() {
        String[] options = { "Put marks", "Show marks" };
        Runnable[] methods = { this::putMarks, this::showMarks };

        split(options, methods);
    }

    @Level(5)
    private void putMarks() {
        print(DB.getCourses());

        String fullNameOrLogin = ask("Enter full name or login of student");
        Student student = DB.getUser(fullNameOrLogin, Student.class);

        float value = number("Enter number of points to put");

        Mode type = ask(Mode.class);

        if (currentCourse.addMark(student, value, type))
            print("Mark put");
        else
            error("Could not put mark to " + student);
    }

    @Level(5)
    private void showMarks() {
        for (Student student: currentCourse.getStudents()) {
            print(student + " " + currentCourse.getMarks(student.getLogin()));
        }
    }


    @Level(4)
    private void files() {
        String[] options = { "Add file", "Show files" };
        Runnable[] methods = { this::addFile, this::showFiles };

        split(options, methods);
    }

    @Level(5)
    private void addFile() {
        String name = ask("Enter title of file");
        String[] content = text("Enter content of file");

        if (currentCourse.createFile(name, content))
            print("File " + name + " created");
        else
            error("Could not create file");
    }

    @Level(5)
    private void showFiles() {
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
        if (changePassword(teacher))
            print("Password changed");
        else
            error("Could not change password");
    }
}
