package com;

import java.io.IOException;
import java.util.*;
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
                    uniq.writer = new BufferedWriter(new FileWriter(args[i+1]));
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

        uniq.run();
    }

    public void run() throws IOException {
        if (writer == null || reader == null)
            throw new IllegalStateException("Не задан источник и приёмник данных");

        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> lines = new ArrayList<>();

        try {
            while (true) {
                String line = reader.readLine();

                if (line == null || line.equals("exit"))
                    break;

                if (unique) {
                    String line2 = getLineId(line);

                    if (!map.containsKey(line2)) {
                        if (!count)
                            writer.write(line);

                        map.put(line2, 1);
                    } else if (count)
                        map.replace(line2, map.get(line2).intValue() + 1);
                    if (count)
                        lines.add(line);
                } else
                    writer.write(line);
            }
        } catch (EOFException ex) {
        } finally {
            if (count) {
                for (String line : lines) {
                    String line2 = getLineId(line);

                    if (map.containsKey(line2)) {
                        int cnt = map.get(line2);

                        if (cnt == 1)
                            writer.write(line);
                        else {
                            writer.write(cnt + line);
                            map.remove(line2);
                        }
                    }

                }
            }

            reader.close();
            writer.close();
        }
    }

    private String getLineId(String line) {
        if (ignoreCase)
            line = line.toUpperCase();

        if (skipSymbol > 0)
            line = line.substring(skipSymbol);
        return line;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public void setSkipSymbol(int skipSymbol) {
        this.skipSymbol = skipSymbol;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public void setCount(boolean count) {
        this.count = count;
    }
}
