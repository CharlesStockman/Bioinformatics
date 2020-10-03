package org.charlesStockman.bioinformatics.algorithms.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A class that allows the user the ability to read JSON Files This is a wrapper around jackson's
 * faster XML library.
 *
 * @author Charles Stockman
 */
public class JsonJacksonWrapper implements JsonWrapper {

  private ObjectMapper mapper = null;

  /** Creates an instance of JsonJacksonWrapper */
  public JsonJacksonWrapper() {
    mapper = new ObjectMapper();
  }

  @Override
  public <T> T jsonToJavaClass(String json, Class<T> myClass) throws Exception {
    T result = (T) mapper.readValue(json, myClass);
    return result;
  }
}
