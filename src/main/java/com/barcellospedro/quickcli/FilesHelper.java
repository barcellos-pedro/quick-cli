package com.barcellospedro.quickcli;

import static java.text.MessageFormat.format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesHelper {
    public static String ROOT_PACKAGE = QuickCliApplication.class.getPackage().getName();

    public static BufferedWriter getWriter(String fileName) throws IOException {
        Path path = Paths.get(fileName(fileName));
        return Files.newBufferedWriter(path);
    }

    public static String rootDir() {
        return System.getProperty("user.dir");
    }

    public static String fileName(String className) {
        String pkg = ROOT_PACKAGE.replace(".", File.separator);
        return format("src/main/java/{0}/{1}.java", pkg, className);
    }
}
