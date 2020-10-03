package org.charlesStockman.bioinformatics.algorithms.util.Validate;

/**
 * A class that mutates the answer into an acceptable value so that it can be verified ( counts are
 * correct)
 */
public class CountsValidate implements Validate<String> {

  public String answer;

  /**
   * Creates an instance of CountValidate
   *
   * @param answer The answer that the test will expect.
   */
  public CountsValidate(String answer) {
    this.answer = answer;
  }

  @Override
  public Boolean validate(String answerFromTest) {
    answerFromTest = answerFromTest.replace("[", "").replace("]", "").replace(",", "");
    boolean result = answerFromTest.compareTo(answer) == 0;

    return (Boolean) result;
  }
}
