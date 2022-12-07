package com.kevin.java.io;

import java.io.Console;

public class TestConsole {
    public static void main(String[] args) {
        Console cons;
        char[] passwd;
        if ((cons = System.console()) != null && (passwd = cons.readPassword("[%s]", "Password:")) != null)
        {
            java.util.Arrays.fill(passwd, ' ');
            System.out.println(passwd);
        }
    }
}
