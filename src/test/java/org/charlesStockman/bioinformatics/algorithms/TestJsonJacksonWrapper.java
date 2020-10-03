package org.charlesStockman.bioinformatics.algorithms;

import org.charlesStockman.bioinformatics.algorithms.util.JsonJacksonWrapper;
import org.charlesStockman.bioinformatics.algorithms.util.JsonWrapper;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;
import org.charlesStockman.bioinformatics.algorithms.util.common.NucleicAcidPolymerTestCase;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestJsonJacksonWrapper {

  @BeforeAll
  public static void printStartHeader() {
    MyLogger.get().startOfTestGroup();
  }

  @AfterAll
  public static void printEndHeader() {
    MyLogger.get().endOfTestGroup();
  }

  /** Handle the case where the JSON contains both a nucleicAcidPolymer and a result. */
  @Test
  public void jsonConvertToClassWithResultProperty() {
    try {

      MyLogger.get().startOfMethod();

      String json =
          "{\"result\":\"20 12 17 21\",\"nucleicAcidPolymers\": "
              + "[ \"AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC\"]}";

      printInitialJson(json);

      NucleicAcidPolymerTestCase testCase = new NucleicAcidPolymerTestCase();

      JsonWrapper jsonReader = new JsonJacksonWrapper();
      testCase = jsonReader.jsonToJavaClass(json, testCase.getClass());

      Assert.assertEquals(
          testCase.getNucleicAcidPolymer(1).get(0),
          "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC");
      Assert.assertEquals(testCase.getResult(), "20 12 17 21");
      Assert.assertTrue(testCase.hasResult());

      printCreatedTestCase(testCase);

    } catch (Exception exception) {
      MyLogger.get().logException(exception);
    }

    MyLogger.get().endOfMethod();
  }

  /** Handle the case where the JSON contains both a nucleicAcidPolymer and a result. */
  @Test
  public void jsonConvertToClassWithoutResultProperty() {

    try {

      MyLogger.get().startOfMethod();

      String json =
          "{\"nucleicAcidPolymers\":"
              + "[\"AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC\"] }";
      printInitialJson(json);

      NucleicAcidPolymerTestCase testCase = new NucleicAcidPolymerTestCase();

      JsonWrapper jsonReader = new JsonJacksonWrapper();
      testCase = jsonReader.jsonToJavaClass(json, testCase.getClass());

      Assert.assertEquals(
          testCase.getNucleicAcidPolymer(1).get(0),
          "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC");
      Assert.assertFalse(testCase.hasResult());

      printInitialJson(json);

    } catch (Exception exception) {
      MyLogger.get().logException(exception);
    }

    MyLogger.get().endOfMethod();
  }

  /**
   * Print the inital JSON that will be used as input
   *
   * @param json The json string that will be used for the test.
   */
  private void printInitialJson(String json) {
    MyLogger.get().log("Creating a JSON without a result and nucleic Acid parameter", 2);
    MyLogger.get().log(json, 3);
  }

  /** Print the contents fo the test case */
  private void printCreatedTestCase(NucleicAcidPolymerTestCase testCase) {
    MyLogger.get().log("The Conversion from JSON to a Java Class created", 2);
    MyLogger.get().log("The Conversion to a java class created", 3);
  }
}
