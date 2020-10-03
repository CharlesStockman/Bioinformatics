package org.charlesStockman.bioinformatics.algorithms.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.charlesStockman.bioinformatics.algorithms.util.common.NucleicAcidPolymerTestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TestNucleicAcidPolymerTestCase {

  private static String nucleicAcid = "AGCT";
  private static String result = "Passed";
  private NucleicAcidPolymerTestCase testCase;

  @BeforeAll
  public static void printStartHeader() {
    MyLogger.get().startOfTestGroup();
  }

  @AfterAll
  public static void printEndHeader() {
    MyLogger.get().endOfTestGroup();
  }

  @BeforeEach
  public void createTestCase() {
    testCase = new NucleicAcidPolymerTestCase();
    testCase.addNucleicAcidPolymer(nucleicAcid);
    testCase.addNucleicAcidPolymer(nucleicAcid);
    testCase.setResult(result);
  }

  @Test
  void testValidNumberOfNuclotides() {

    List<String> inputData = testCase.getNucleicAcidPolymer(2);
    assertTrue(inputData.size() == 2, "The getNucleicAcidPolymer should return 2 entries");
    for (String nucleicAcidPolymer : inputData) {
      assertTrue(
          nucleicAcidPolymer.compareTo(nucleicAcidPolymer) == 0,
          "The nucleic acid is return is the one that was added");
    }

    assertTrue(
        testCase.getResult().compareTo(result) == 0, "The result of the test is not correct");
  }

  @Test
  void testInvalidNumberOfNucleotides() {

    Executable testFunction = () -> testCase.getNucleicAcidPolymer(5);
    assertThrows(IllegalArgumentException.class, testFunction);
  }

  private void assertThrows(Class<IllegalArgumentException> class1, Object object) {
    // TODO Auto-generated method stub

  }
}
