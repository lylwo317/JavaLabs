package com.kevin.java.io.inputOutputStream;
import java.io.*;


public class TestPushbackStream {
    // Java code illustrating available(), close()
    // and markSupported() methods

    public static void main(String[] arg) throws Exception
    {
        PrintWriter pw = new PrintWriter(System.out, true);
        String str = "Geeks a computer science portal ";
        byte[] b = str.getBytes();
        ByteArrayInputStream bout = new ByteArrayInputStream(b);
        PushbackInputStream push = new PushbackInputStream(bout,100);

        int c;
        while((c=push.read())!=-1)
        {
            pw.print((char)c);
        }
        pw.println();

        // unread method
        //类似stack，入栈到push中的buf数组里面。当再次read的时候，会从栈顶（最后一次unread开始）读取
        push.unread(b);//"Geeks a computer science portal "
        push.unread(b, 0, 8);//"Geeks a "
        //"Geeks a Geeks a computer science portal "

        while((c=push.read())!=-1)
        {
            pw.print((char)c);//打印字符，这里会先读取buf。然后再读取bout中的数据
        }
        pw.println();
        pw.close();
    }
}
