package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (BufferedReader br = Files.newBufferedReader(file.toPath())) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] property = line.split(":");
                String field = property[0].trim();
                String value = property[1].trim();

                switch (field) {
                    case "Name":
                        profile.setName(value);
                        break; 
                    case "Age":
                        profile.setAge(Integer.valueOf(value));
                        break;
                    case "Email":
                        profile.setEmail(value);
                        break;
                    case "Phone":
                        profile.setPhone(Long.valueOf(value));
                        break;
                    default: // skip if no field is being found
                }
            }
        } catch (IOException e) {
            throw new ProfileFileException("Error while reading file: " + file.getName(), e);
        }
        return profile;
    }
}
