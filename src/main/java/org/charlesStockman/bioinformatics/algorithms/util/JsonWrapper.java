package org.charlesStockman.bioinformatics.algorithms.util;

/**
 * The purpose of this class is allow a common interface for Classes that can can convert JSON into
 * Java Classes. Also can be used to create a mock class and can allow for dependency injection.
 */
public interface JsonWrapper {
  /**
   * Converts a JSON formatted String into a Java Class
   *
   * @param json A string formatted as JSON
   * @param myClass The class the data from the JSON String will be extracted
   * @return An instance containing the content of the JSON String
   * @throws Exception Either one of the parameters could be null
   */
  public <T> T jsonToJavaClass(String json, Class<T> myClass) throws Exception;
}
