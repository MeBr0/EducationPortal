package com.mebr0;

import com.mebr0.user.entity.Admin;
import com.mebr0.user.entity.Student;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.type.Position;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin("Temirlan", "Safargaliyev");

        Student s = admin.student("Azamat", "Yergali", Faculty.BS, Degree.BD);

        System.out.println(admin);

        System.out.println(s);

        Student ss = admin.student("Azamat", "Yergali", Faculty.BS, Degree.BD);

        System.out.println(ss);
        System.out.println(admin.teacher("Assylbek", "Issakhov", Faculty.SMC, Position.PROFESSOR));
    }
}
