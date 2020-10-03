/*
 * A class that creates a logger that can be used to display information for the algorithms
 */
package org.charlesStockman.bioinformatics.algorithms.util;

import java.util.function.Function;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * A class to displays information that the user decides that is important. This information can do
 * the following
 *
 * <ol>
 *   <li>Allow the developer to understand what the algoriithm is doing
 *   <li>Some performance problems might be solved ( ex. if a function run more times than expected
 *       it should be fixed )
 *   <li>Easier to integrate new functions (even from different tiers into the same function)
 * </ol>
 *
 * @author Charles Stockman
 */
public class MyLogger {

  // The reference that will be used to accessed the logger.
  private static MyLogger myLogger;

  // The actual logger that will never be accessed by the directly any caller
  private Logger logger = null;

  // The index into the StackTrace Array of the function that
  // called <code>get</code> from the Function and
  // <code>getStartMethod</code> or getEndMethod() and
  // Logger.getCallingMethod and get()
  private static final int INDEX_OF_FUNCTION_CALLS_LOGGER = 3;

  // The index into the StackTrace for getting the filename
  // from getstartOfTestGroup() the printStartHeader()
  private static final int INDEX_OF_FILENAME = 2;

  /**
   * Constructs and/or returns a instance of MyLogger for other classes to use. For every message, I
   * want to use the same logger so I made the class a <code>Singleton</code>
   *
   * @return The only instance of MyLogger which the developer can use to log messages.
   */
  public static MyLogger get() {
    if (myLogger == null) {
      myLogger = new MyLogger();
    }

    return myLogger;
  }

  /**
   * Create a logger and configure it to the developers specifications. Instead of having the
   * configuration in a text file I have put the configuration into the code since it usually easier
   * it remove the need to have another file
   *
   * <p>When logs are created, I want to keep them out production logs unless needed. For this
   * situation, I always set the logging level very low for debugging messages
   *
   * @return A Logger that can be used to display information
   */
  private Logger getLogger() {
    if (logger == null) {
      logger = Logger.getLogger("Global Logger");
      logger.setUseParentHandlers(Boolean.FALSE);
      logger.setLevel(Level.FINER);

      // For now a quick fix, but we need to figure out why the logger is being set to null
      // for each iteration
      if (logger.getHandlers().length < 1) {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new MyFormatter());
        logger.addHandler(handler);
      }
    }

    return logger;
  }

  /**
   * Get the actual method to call the logger
   *
   * <p>The idea is to get Stack Trace at the time of execution. The Stack Trace is an array where
   * the 0 index contains information about the current function executing. The 1st index contains
   * the information about the parent function and so on.
   *
   * <p>There will away be two functions that are called getCallingMethod and getLogger.
   *
   * @return The name of the method that called a routine in the logger class
   */
  public String getCallingMethod() {
    String callingFunction =
        Thread.currentThread().getStackTrace()[INDEX_OF_FUNCTION_CALLS_LOGGER].getMethodName();
    return callingFunction;
  }

  /** Displays a message for the start of a routine */
  public void startOfMethod() {
    log("Starting to log " + getCallingMethod(), 1);
  }

  /** Displays a message for the end of a routine */
  public void endOfMethod() {
    log("Ending logging of " + getCallingMethod(), 1);
  }

  /** Displays a message starting a group of tests since each function will have multiple tests. */
  public void startOfTestGroup() {
    getLogger()
        .info(
            "Starting Test Group from file "
                + getCallingMethod()
                + " --> "
                + Thread.currentThread().getStackTrace()[INDEX_OF_FILENAME].getFileName());
  }

  /** Displays a message ending a group of tests since each function will have multiple test */
  public void endOfTestGroup() {
    getLogger().info("Ending Test Group");
  }

  /**
   * logs information
   *
   * @param message What to print to the logger
   * @param levelOfIndetation How many tabs should be added to the start of the string.
   */
  public void log(String message, int levelOfIdentation) {
    levelOfIdentation = (levelOfIdentation < 0) ? Math.abs(levelOfIdentation) : levelOfIdentation;
    getLogger().info("\t".repeat(levelOfIdentation) + message);
  }

  /**
   * Print out an array of line where each line get an extra level of indentation for each line
   *
   * <p>Accepts an array of String and a starting level of indentation
   *
   * @param line The lines that will be logged to the output device
   * @param startingLevelOfIndetation How many tabs should the first string have
   * @return
   */
  public void log(String[] lines, int startingLevelOfIndentation) {
    for (int index = 0; index < lines.length; index++) {
      log(lines[index], startingLevelOfIndentation);
      startingLevelOfIndentation++;
    }
  }

  /**
   * Print a the Contents of the Array where each values is on a different line
   *
   * @param <T> The element of the array
   */
  public <T> void printArrayContent(T[] arrayToBePrinted) {
    for (T element : arrayToBePrinted) {
      getLogger().info("\t" + element);
    }
  }

  /**
   * Get the logger and create when the user needs to log an exception ( exception )
   *
   * @param exception The current exception
   */
  public void logException(Exception exception) {

    // A lambda function to print the message contained within the exception
    Function<Exception, String> getMessage =
        (x) -> (!StringUtils.isBlank(x.getMessage())) ? "No Message" : x.getMessage();

    getLogger().info("****** Exception ******");

    getLogger().info("Exception type " + exception.getClass().getCanonicalName());
    getLogger().info("Message: " + getMessage.apply(exception));

    getLogger().info("*** Stack Trace");
    StackTraceElement[] iterator = exception.getStackTrace();
    for (int index = 0; index < iterator.length; index++) {
      getLogger().info("\t" + iterator[index]);
    }

    if (exception.getCause() != null) {
      getLogger().info("Dispalying data on the original problem that caused the exception");
      getLogger()
          .info("\tThe exception that caused the execption was " + exception.getCause().getClass());
      getLogger()
          .info(
              "\tThe excecption that caused the exception produced the following message: "
                  + exception.getCause());
    }
    getLogger().info("*******End Exception ****************");
  }

  public class MyFormatter extends Formatter {
    /** A class that will provide the formatting for the Logger Message */
    public String format(LogRecord record) {
      StringBuilder builder = new StringBuilder(1000);
      builder.append(record.getMessage());
      builder.append("\n");
      return builder.toString();
    }
  }
}
