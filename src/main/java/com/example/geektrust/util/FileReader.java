package com.example.geektrust.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
  private void validateFilePath(String filePath) {
    if (filePath == null || filePath.isEmpty()) {
      throw new IllegalArgumentException("File path cannot be null or empty");
    }
  }

  public List<String> readLines(String filePath) throws Exception {
    validateFilePath(filePath);

    List<String> lines = new ArrayList<>();
    FileInputStream fileInputStream = new FileInputStream(filePath);
    Scanner scanner = new Scanner(fileInputStream);
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
    return lines;
  }
}
