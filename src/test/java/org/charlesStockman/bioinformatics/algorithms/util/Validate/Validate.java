package org.charlesStockman.bioinformatics.algorithms.util.Validate;

/**
 * Verify the data produced by the Test function is correct
 *
 * @param <R>
 */
public interface Validate<R> {

  /**
   * Compares the original answer to the expected result
   *
   * @param answerFromTest -- The value produced from the test
   * @return (T) -- The result is correct and this portion of test should be passed
   */
  public Boolean validate(R answerFromTest);
}
