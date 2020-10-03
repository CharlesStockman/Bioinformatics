package org.charlesStockman.bioinformatics.algorithms.util.generatedTests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Data;
import org.charlesStockman.bioinformatics.algorithms.util.common.NucleicAcidPolymerTestCase;

/**
 * Creates tests without putting them into the JSON file. The main tests created will be test case
 * where the polymer is an empty string or null
 */
public class AdhocTest {

  /** Default */
  @Data
  private static class DefaultInputPolymer {

    private static List<DefaultInputPolymer> inputPolymers;

    private final String inputPolymerValue;
    private final String testCaseLabel;

    /**
     * Creates an instance of DefaultInputPolymer
     *
     * @param inputPolymerValue The value of the polymer(s) that will be used as input
     * @param testCaseLabel A name to describe the test case
     */
    public DefaultInputPolymer(String inputPolymerValue, String testCaseLabel) {
      this.inputPolymerValue = inputPolymerValue;
      this.testCaseLabel = testCaseLabel;
    }

    /** Creates an instance of Default Polymer */
    public static List<DefaultInputPolymer> getInstance() {

      if (inputPolymers == null) {

        inputPolymers = new ArrayList<DefaultInputPolymer>();
        inputPolymers.add(new DefaultInputPolymer("", "Polymers are Empty Strings"));
        inputPolymers.add(new DefaultInputPolymer(null, "Polymners are null Strings"));

        inputPolymers = Collections.unmodifiableList(inputPolymers);
      }

      return inputPolymers;
    }
  }

  /**
   * Returns the instance of the class that contains the specific information @Return : The
   * information for the
   */
  public static List<DefaultInputPolymer> getDefaultInputPolymers() {

    return DefaultInputPolymer.getInstance();
  }

  /**
   * Create two polymer test cases : one test where input polymer(s) are null and the other test
   * where the input polymer is empty.
   *
   * @param numberOfPolymers The number of polymers that will be used to generate the result
   * @return A <code>Map</code> of test cases that have an empty string or null string
   */
  public static LinkedHashMap<String, NucleicAcidPolymerTestCase>
      createDefaultTestCaseWithOneEmptyResult(int numberOfPolymers) {

    LinkedHashMap<String, NucleicAcidPolymerTestCase> adhocTest = new LinkedHashMap<>();

    for (DefaultInputPolymer defaultInputPolymer : DefaultInputPolymer.getInstance()) {
      NucleicAcidPolymerTestCase testCaseDefaultPolymer = new NucleicAcidPolymerTestCase();

      for (int index2 = 0; index2 < numberOfPolymers; index2++)
        testCaseDefaultPolymer.addNucleicAcidPolymer(defaultInputPolymer.getInputPolymerValue());

      testCaseDefaultPolymer.setResult("");

      adhocTest.put(defaultInputPolymer.getTestCaseLabel(), testCaseDefaultPolymer);
    }

    return adhocTest;
  }
}
