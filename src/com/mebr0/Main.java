package com.mebr0;

import com.mebr0.intranet.Intranet;

public class Main {

    public static void main(String[] args) {
        Intranet system = Intranet.getInstance();

        System.out.println(system.begin());
    }
}
