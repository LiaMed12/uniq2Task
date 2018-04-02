package test;

import java.io.*;

import com.Uniq;
import org.junit.*;

import static org.junit.Assert.*;

public class UniqTest {
    @Test
    public void run() throws Exception {
        Uniq u = new Uniq();
        u.setReader(new BufferedReader(new StringReader("mer\nver\nmar")));
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        u.setIgnoreCase(true);
        u.run();
        assertEquals("mervermar", sw.toString());
    }

    @Test
    public void run1() throws Exception {
        Uniq u = new Uniq();
        u.setReader(new BufferedReader(new StringReader(" ")));
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        u.setIgnoreCase(true);
        u.run();
        assertEquals(" ", sw.toString());
    }

    @Test
    public void falseRun() throws Exception {
        String s = null;
        try {
            Uniq u = new Uniq();
            u.run();
        } catch (IllegalStateException ex) {
            s = ex.getMessage();
        }
        assertEquals("Не задан источник и приёмник данных", s);
    }
    @Test
    public void lineExit() throws Exception{
        Uniq u = new Uniq();
        u.setReader(new BufferedReader(new StringReader("exit")));
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        u.setIgnoreCase(true);
        u.run();
        assertEquals("",sw.toString());
    }

    @Test
    public void ignoringCase() throws Exception{
        Uniq u = new Uniq();
        u.setReader(new BufferedReader(new StringReader("Raw\nraw")));
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        u.setIgnoreCase(true);
        u.run();
        assertEquals("Rawraw",sw.toString());
    }

    @Test
    public void ignoringSymbol() throws Exception{
        Uniq u = new Uniq();
        u.setReader(new BufferedReader(new StringReader("napo\nwepo")));
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        u.setIgnoreCase(true);
        u.setSkipSymbol(2);
        u.setUnique(true);
        u.run();
        assertEquals("napo",sw.toString());
    }



}