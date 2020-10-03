package org.charlesStockman.bioinformatics.algorithms.util;

import lombok.Data;

/**
 * A package that can return the information from a function. This can include information about
 * error information such as ( Code, Message or an exception ) and the data generated from the
 * function.
 *
 * <p>The object puts all the information into class and can be returned either to a function or
 * another tier's function.
 *
 * @author Charles Stockman
 * @param <T> -- Data to be returned.
 */
@Data
public class ReturnPacket<T> {

  // An Integer describing the error
  private Integer returnCode;

  // A message about why the error happen
  private String message;

  // The Exception thrown by the error
  private Exception exception;

  // The data created by the function
  private T data;

  /** Constants */
  public static final Integer SUCCESS_RETURN_CODE = 0;

  public static final String SUCCESS_MESSAGE = "";
  public static final Integer ERROR_RETURN_CODE = 1;

  /**
   * Creates an instance of ReturnPackets without the Exception
   *
   * @param returnCode An Integer indicating error/warning/successful
   * @param message An error for the calling tier/function or a log file
   * @param data The data created by the function
   */
  public ReturnPacket(Integer returnCode, String message, T data) {
    this.returnCode = returnCode;
    this.message = message;
    this.data = data;
  }

  /**
   * Creates an instance of ReturnPackets with the Exception
   *
   * @param returnCode An Integer indicating error/warning/successful
   * @param message An error for the calling tier/function or a log file
   * @param data The data created by the function
   * @param exception An exception has been generated, returning it to the calling function.
   */
  public ReturnPacket(Integer returnCode, String message, T data, Exception exception) {
    this(returnCode, message, data);
    this.exception = exception;
  }
}
