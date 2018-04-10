package test;

import java.io.*;

import com.Run;
import com.Uniq;
import org.junit.*;

import static org.junit.Assert.*;

public class UniqTest {
    @Test
    public void run() throws Exception {
        Uniq u = new Uniq();
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        Run r = new Run();
        r.run(new BufferedReader(new StringReader("mer\nver\nmar")),
                new BufferedWriter(sw = new StringWriter()),
                true, 0, false, false);
        assertEquals("mervermar", sw.toString());
    }

    @Test
    public void run1() throws Exception {
        Uniq u = new Uniq();
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        Run r = new Run();
        r.run(new BufferedReader(new StringReader("")),
                new BufferedWriter(sw = new StringWriter()),
                true, 0, false, false);
        assertEquals("", sw.toString());
    }

    @Test
    public void falseRun() throws Exception {
        String s = null;
        try {
            Uniq u = new Uniq();
            Run r = new Run();
            r.run(null, null, true, 0, false, false);
        } catch (IllegalStateException ex) {
            s = ex.getMessage();
        }
        assertEquals("Не задан источник и приёмник данных", s);
    }

    @Test
    public void lineExit() throws Exception {
        Uniq u = new Uniq();
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        Run r = new Run();
        r.run(new BufferedReader(new StringReader("exit")),
                new BufferedWriter(sw = new StringWriter()),
                true, 0, false, false);
        assertEquals("", sw.toString());
    }

    @Test
    public void ignoringCase() throws Exception {
        Uniq u = new Uniq();
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        Run r = new Run();
        r.run(new BufferedReader(new StringReader("Raw\nraw")),
                new BufferedWriter(sw = new StringWriter()),
                true, 1, true, false);
        assertEquals("Raw", sw.toString());
    }

    @Test
    public void ignoringSymbol() throws Exception {
        Uniq u = new Uniq();
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        Run r = new Run();
        r.run(new BufferedReader(new StringReader("napo\nwepo")),
                new BufferedWriter(sw = new StringWriter()),
                true, 2, true, false);
        assertEquals("napo", sw.toString());
    }

    @Test
    public void countingTheSameRows() throws Exception {
        Uniq u = new Uniq();
        StringWriter sw = new StringWriter();
        u.setWriter(new BufferedWriter(sw));
        Run r = new Run();
        r.run(new BufferedReader(new StringReader("ra\nra")),
                new BufferedWriter(sw = new StringWriter()),
                true, 0, true, true);
        assertEquals("2ra", sw.toString());
    }
}