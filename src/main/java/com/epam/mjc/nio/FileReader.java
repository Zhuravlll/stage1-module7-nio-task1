package com.epam.mjc.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class FileReader {

    public Profile getDataFromFile(File file)  {
        String path = file.getAbsolutePath();
        try {
            return createProfile(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

        public static Profile createProfile(String path) throws IOException {
            Map<String, String> temp = new LinkedHashMap<>();
            List<String> tempList = new LinkedList<>();
            String[] tempArr = new String[2];
            try (Stream<String> fStream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
                fStream.forEach(tempList::add);
            }
            catch (FileNotFoundException e) {
                throw new IOException();
            }
            for (String e: tempList) {
                tempArr = (e.split(": "));
                temp.put(tempArr[0], tempArr[1]);
            }
             return new Profile(temp.get("Name"), Integer.parseInt(temp.get("Age")), temp.get("Email"), Long.parseLong(temp.get("Phone")));
        }
    }
