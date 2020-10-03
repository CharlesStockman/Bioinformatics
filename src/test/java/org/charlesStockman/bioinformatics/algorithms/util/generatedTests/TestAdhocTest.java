package org.charlesStockman.bioinformatics.algorithms.util.generatedTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Random;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;
import org.charlesStockman.bioinformatics.algorithms.util.common.NucleicAcidPolymerTestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestAdhocTest {

  @BeforeAll
  public static void printStartHeader() {
    MyLogger.get().startOfTestGroup();
  }

  @AfterAll
  public static void printEndHeader() {
    MyLogger.get().endOfTestGroup();
  }

  /** Test -- Create a test case with only one Nucleic Acid */
  @Test
  public void testCreateDefaultTestCaseWithOneEmptyResult() {

    // Hardcoded 100 here so that I could avoid memory overflows.  The number of inputs will be from
    // 0 to 100 so the number of inputs doe not get too large
    int numberOfInputs = Math.abs((new Random().nextInt() % 100));

    LinkedHashMap<String, NucleicAcidPolymerTestCase> defaultCases =
        AdhocTest.createDefaultTestCaseWithOneEmptyResult(numberOfInputs);

    assertTrue(
        defaultCases.size() == 2,
        "There should only be two entries.  One for where inputs are an empty string and one where they are null ");
  }
}
