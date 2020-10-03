package org.charlesStockman.bioinformatics.algorithms.util.common;

import org.apache.commons.lang3.StringUtils;

/**
 * A base class for test cases. The base class allows the answer to always be a string, but this
 * class can be inherited by classes where each class has test data could be a Fasta Object. The
 * test could would accept Fasta Object, but the answer will always be a String (At least for this
 * set of problems).
 *
 * <p>Example The test data could be fasta format. The result will alway be string that it can be
 * compared agains
 *
 * @author Charles Stockman
 */
public class BaseTestCase {

  String result;

  /**
   * The Test Case has a result.
   *
   * @return (t) does the test case have a result
   */
  public Boolean hasResult() {

    return (StringUtils.isBlank(this.result)) ? false : true;
  }

  /** @return the result */
  public String getResult() {
    return result;
  }

  /** @param result the result to set */
  public void setResult(String result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "TestCaseBase [result=" + result + "]";
  }
}
