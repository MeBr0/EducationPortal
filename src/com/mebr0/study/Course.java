package com.mebr0.study;

import com.mebr0.study.file.FileManager;
import com.mebr0.study.mark.Marks;
import com.mebr0.study.mark.Marks.Mode;
import com.mebr0.study.time.Semester;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Teacher;

import java.util.*;

public class Course {

    // Todo: add schedule
    private final String ID;
    private Subject subject;
    private byte creditsNumber;
    private Teacher teacher;
    private final List<Student> STUDENTS;
    private final Semester SEMESTER;
    private final Map<String, Marks> MARKS;

    {
        ID = UUID.randomUUID().toString();
        STUDENTS = new ArrayList<>();
        MARKS = new HashMap<>();

        // Todo: log it
        FileManager.createDirectory(ID);
    }

    private Course(Subject subject, byte number, Teacher teacher, Semester semester) {
        this.subject = Objects.requireNonNull(subject, "Subject cannot be null");
        this.creditsNumber = number;
        this.teacher = Objects.requireNonNull(teacher, "Teacher cannot be null");
        this.SEMESTER = Objects.requireNonNull(semester, "Semester cannot be null");
    }

    public static Course from(Subject subject, byte number, Teacher teacher) {
        return new Course(subject, number, teacher, Semester.getCurrent());
    }

    public static Course from(Subject subject, byte number, Teacher teacher, Semester semester) {
        return new Course(subject, number, teacher, semester);
    }

    public String getId() {
        return ID;
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
        return STUDENTS;
    }

    // Todo: not gonna work because not overridden equals(Object o)
    public boolean addStudent(Student student) {
        boolean alreadyExistsCheck = STUDENTS.contains(student);

        if (!alreadyExistsCheck) {
            STUDENTS.add(student);
            MARKS.put(student.getLogin(), new Marks());
        }

        return !alreadyExistsCheck;
    }

    // Todo: not gonna work because not overridden equals(Object o)
    public void removeStudent(Student student) {
        STUDENTS.remove(student);
        MARKS.remove(student.getLogin());
    }

    public Semester getSemester() {
        return SEMESTER;
    }

    public Marks getMarks(Student student) {
        return MARKS.get(student.getLogin());
    }

    public boolean addMark(Student student, float value, Mode mode) {
        Marks marks = MARKS.get(student.getLogin());

        if (marks == null)
            return false;

        return marks.updateMark(value, mode);
    }

    public boolean createFile(String name, String... content) {
        return FileManager.createFileAndWrite(ID, name, content);
    }

    public boolean writeToFile(String name, String... content) {
        return FileManager.writeToFile(ID, name, content);
    }

    public boolean removeFile(String name) {
        return FileManager.removeFile(ID, name);
    }
}
