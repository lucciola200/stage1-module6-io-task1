package com.epam.mjc.io;

import java.io.*;
import java.util.Locale;


public class FileReader {

    public Profile getDataFromFile(File file) {
        InputStream inputStream = null;
        String fileTxt = "";
        Profile profile = new Profile();

        try {
            inputStream = new FileInputStream(file);

            for (int ch; (ch = inputStream.read()) != -1; ) {
                fileTxt += ((char) ch);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] arrfile = fileTxt.split(System.lineSeparator());
        for (int i = 0; i < arrfile.length; i++) {
            String keyVal = arrfile[i];
            String[] parts = keyVal.split(": ", 2);
            String key = parts[0].toLowerCase(Locale.ROOT);
            if (key.equals("name")) {
                profile.setName(parts[1]);
            } else if (key.equals("age")) {
                profile.setAge(Integer.parseInt(parts[1]));
            } else if (key.equals("email")) {
                profile.setEmail(parts[1]);
            } else if (key.equals("phone")) {
                profile.setPhone(Long.parseLong(parts[1]));
            }
        }
        return profile;
    }
}
