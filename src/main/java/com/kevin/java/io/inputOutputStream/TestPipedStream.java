package com.kevin.java.io.inputOutputStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class TestPipedStream {
    // Java program illustrating the working of PipedInputStream
    // write(byte[] buffer, int offset, int maxlen)
    public static void main(String[] args) throws IOException
    {
        PipedInputStream geek_input = new PipedInputStream();
        PipedOutputStream geek_output = new PipedOutputStream();

        // Use of connect() : connecting geek_input with geek_output
        geek_input.connect(geek_output);

        byte[] buffer = {'J', 'A', 'V', 'A'};

        // Use of write(byte[] buffer, int offset, int maxlen)
        geek_output.write(buffer, 0, 4);
        int a = 4;
        System.out.print("Use of write(buffer, offset, maxlen) : ");
        while(a>0)
        {
            System.out.print(" " + (char) geek_input.read());
            a--;
        }
    }
}
