package test;

import java.io.*;

import com.Uniq;
import org.junit.*;

import static org.junit.Assert.*;

public class UniqTest {
    @org.junit.Test
    public void run() throws Exception {
        Uniq u = new Uniq();
        u.setReader(new BufferedReader(new StringReader("mer\nver\nmar")));
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        u.setIgnoreCase(true);
        u.run();
        assertEquals("mervermar", sw.toString());
    }


}