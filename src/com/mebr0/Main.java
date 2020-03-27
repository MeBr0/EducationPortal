package com.mebr0;

import com.mebr0.user.entity.Admin;
import com.mebr0.user.entity.Student;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin("qwe", "qwe");

        Student s = admin.student("qwee", "qwee", Faculty.BS, Degree.BD);

        System.out.println(admin);

        System.out.println(s);
    }
}
