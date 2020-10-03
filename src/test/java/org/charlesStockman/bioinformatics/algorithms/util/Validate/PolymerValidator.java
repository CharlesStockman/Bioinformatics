package org.charlesStockman.bioinformatics.algorithms.util.Validate;

/** Verifies that a polymer is the correct result. */
public class PolymerValidator implements Validate<String> {

  String polymer;

  /**
   * Create an instance of PolymerValidator and provdie allows the developer to provide the ansers
   *
   * @param answer The correct answer
   */
  public PolymerValidator(String answer) {
    this.polymer = answer;
  }

  @Override
  public Boolean validate(String answer) {
    return answer.compareTo(polymer) == 0;
  }
}
