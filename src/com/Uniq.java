package com;

import java.io.IOException;
import java.io.*;

public class Uniq {

    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean ignoreCase;
    private int skipSymbol;
    private boolean unique;
    private boolean count;

    public static void main(String[] args) throws IOException {
        Uniq uniq = new Uniq();
        switch (args[0]) {
            case "-o":
            case "-i":
            case "-s":
            case "-u":
            case "-c":
                uniq.reader = new BufferedReader(new InputStreamReader(System.in));
                break;
            default:
                uniq.reader = new BufferedReader(new FileReader(args[0]));
                break;
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    uniq.writer = new BufferedWriter(new FileWriter(args[i + 1]));
                    break;
                case "-i":
                    uniq.ignoreCase = true;
                    break;
                case "-s":
                    uniq.skipSymbol = Integer.parseInt(args[i + 1]);
                    break;
                case "-u":
                    uniq.unique = true;
                    break;
                case "-c":
                    uniq.count = true;
                    break;
            }
        }

        if (uniq.writer == null)
            uniq.writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Run r = new Run();
        r.run(uniq.reader, uniq.writer, uniq.ignoreCase,
                uniq.skipSymbol, uniq.unique, uniq.count);

    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

}
