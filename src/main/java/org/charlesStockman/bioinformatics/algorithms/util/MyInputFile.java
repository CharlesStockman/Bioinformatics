package org.charlesStockman.bioinformatics.algorithms.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A package to handle the loading of text resources from a file.
 *
 * @author Charles Stockman
 */
public class MyInputFile {

  public static final String ENVIRONMENTAL_VARIABLE_FILENAME = "bioInfFile";

  /**
   * Find the File
   *
   * @param callingClass The class that will provide the classLoad to located the text file
   * @param resourceName The text file that will be read
   * @return The Object that contains information about the file on the filesystem
   * @throws URISyntaxException Does not conform to the RFC2396
   */
  public static Path getPath(Class<?> callingClass, String resourceName) throws URISyntaxException {
    return Paths.get(callingClass.getResource(resourceName).toURI());
  }

  /**
   * From the provided file read all the line and combine them into one giant line.
   *
   * @return The content from the file as a string
   * @throws IOException The file contents is corrupted
   */
  public static String getContentsOfFile(Path path) throws IOException {
    String allLines = Files.readAllLines(path).stream().reduce(String::concat).get();
    return allLines;
  }
}
