package com.barcellospedro.quickcli;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.text.MessageFormat.format;

public class FilesHelper {
    public static BufferedWriter getWriter(String fileName) throws IOException {
        Path path = Paths.get(fileName(fileName));
        return Files.newBufferedWriter(path);
    }

    public static String rootDir() {
        return System.getProperty("user.dir");
    }

    public static String rootPackage() {
        return QuickCliApplication.class.getPackage().getName();
    }

    public static String fileName(String className) {
        String pkg = rootPackage().replace(".", File.separator);
        return format("src/main/java/{0}/{1}.java", pkg, className);
    }
}
