package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Run {
    public void run(BufferedReader reader, BufferedWriter writer, Boolean ignoreCase,
                    Integer skipSymbol, Boolean unique, Boolean count) throws IOException {
        if (writer == null || reader == null)
            throw new IllegalStateException("Не задан источник и приёмник данных");

        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> lines = new ArrayList<>();

        try {
            while (true) {
                String line = reader.readLine();

                if (line == null)
                    break;

                if (unique) {
                    String line2 = getLineId(line, ignoreCase, skipSymbol);

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
                    String line2 = getLineId(line, ignoreCase, skipSymbol);

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

    private String getLineId(String line, Boolean ignoreCase, Integer skipSymbol) {
        if (ignoreCase)
            line = line.toUpperCase();

        if (skipSymbol > 0)
            line = line.substring(skipSymbol);
        return line;
    }
}
