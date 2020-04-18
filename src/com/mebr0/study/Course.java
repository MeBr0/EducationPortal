package com.mebr0.study;

import com.mebr0.study.file.FileManager;
import com.mebr0.study.mark.Marks;
import com.mebr0.study.mark.Marks.Mode;
import com.mebr0.study.time.Semester;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Teacher;

import java.io.Serializable;
import java.util.*;

import static com.mebr0.intranet.util.Printer.print;

/**
 * Course class
 *
 * @author A.Yergali
 * @version 1.1
 */
public class Course implements Serializable {

    // Todo: add schedule
    private final String id;
    private Subject subject;
    private byte creditsNumber;
    private Teacher teacher;
    private final List<Student> students;
    private final Semester semester;
    private final Map<String, Marks> marks;

    {
        id = UUID.randomUUID().toString();
        students = new ArrayList<>();
        marks = new HashMap<>();

        // Todo: log it
        FileManager.createDirectory(id);
    }

    private Course(Subject subject, byte number, Teacher teacher, Semester semester) {
        this.subject = Objects.requireNonNull(subject, "Subject cannot be null");
        this.creditsNumber = number;
        this.teacher = Objects.requireNonNull(teacher, "Teacher cannot be null");
        this.semester = Objects.requireNonNull(semester, "Semester cannot be null");
    }

    public static Course from(Subject subject, byte number, Teacher teacher) {
        Course course = new Course(subject, number, teacher, Semester.getCurrent());

        teacher.addCourse(course);

        return course;
    }

    public static Course from(Subject subject, byte number, Teacher teacher, Semester semester) {
        Course course = new Course(subject, number, teacher, semester);

        teacher.addCourse(course);

        return course;
    }

    public boolean isActive() {
        return getSemester().equals(Semester.getCurrent());
    }

    public String getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public byte getCreditsNumber() {
        return creditsNumber;
    }

    public void setCreditsNumber(byte number) {
        this.creditsNumber = number;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    // Todo: not gonna work because not overridden equals(Object o)
    public boolean addStudent(Student student) {
        boolean alreadyExistsCheck = students.contains(student);

        students.add(student);
        marks.put(student.getLogin(), new Marks());

        student.addCourse(this);

        return !alreadyExistsCheck;
    }

    // Todo: not gonna work because not overridden equals(Object o)
    public void removeStudent(Student student) {
        students.remove(student);
        marks.remove(student.getLogin());
        student.removeCourse(this);
    }

    public Semester getSemester() {
        return semester;
    }

    public Marks getMarks(String student) {
        return marks.get(student);
    }

    public boolean addMark(Student student, float value, Mode mode) {
        Marks marks = this.marks.get(student.getLogin());

        if (marks == null)
            return false;

        return marks.updateMark(value, mode);
    }

    public List<String> getFiles() {
        return FileManager.getFiles(id);
    }

    public String readFile(String file) {
        return FileManager.readFromFile(id, file);
    }

    public boolean createFile(String name, String... content) {
        return FileManager.createFileAndWrite(id, name, content);
    }

    public boolean writeToFile(String name, String... content) {
        return FileManager.writeToFile(id, name, content);
    }

    public boolean removeFile(String name) {
        return FileManager.removeFile(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Course))
            return false;

        Course course = (Course) o;
        return creditsNumber == course.creditsNumber &&
                id.equals(course.id) &&
                subject.equals(course.subject) &&
                teacher.equals(course.teacher) &&
                students.equals(course.students) &&
                semester.equals(course.semester) &&
                marks.equals(course.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, creditsNumber, teacher, students, semester, marks);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [subject: " + subject.getTitle() + ", teacher: " +
                teacher.getFullName() + ", credits: " + creditsNumber + ", students: " + students + "]";
    }
}
