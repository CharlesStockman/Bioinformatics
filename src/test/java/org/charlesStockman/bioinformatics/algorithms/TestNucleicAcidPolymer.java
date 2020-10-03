package org.charlesStockman.bioinformatics.algorithms;

import static org.junit.Assert.assertTrue;

import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;
import org.charlesStockman.bioinformatics.algorithms.util.types.NucleicAcidPolymerType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestNucleicAcidPolymer {

  @BeforeAll
  public static void printStartHeader() {
    MyLogger.get().startOfTestGroup();
  }

  @AfterAll
  public static void printEndHeader() {
    MyLogger.get().endOfTestGroup();
  }

  /** Test the logs message */
  @Test
  public void testDefaultLogMessageWithoutNewLine() {

    MyLogger.get().log("Testing the Default Log Message without New Line", 1);
    NucleicAcidPolymerType polymer =
        new NucleicAcidPolymerType("ACGT", "Test for testDefaultLogMessage with no new lines");
    reusableTestDefaultLogMessage(polymer);

    MyLogger.get().log("Testing the Default Log Message with New Line", 1);
    NucleicAcidPolymerType polymer2 =
        new NucleicAcidPolymerType("ACGT", "Test for testDefaultLogMessage with no new lines");
    reusableTestDefaultLogMessage(polymer2);
  }

  private void reusableTestDefaultLogMessage(NucleicAcidPolymerType polymer) {

    String description = "Test String for testDefaultLogMessage";

    String[] lines = polymer.createLogMessage(description);

    MyLogger.get().log(lines, 2);

    assertTrue("The Default Log Message must have two lines", lines.length == 2);

    assertTrue("The Descrption must be " + description, lines[0].compareTo(description) == 0);

    assertTrue(
        "The polymer in the log message must be the same as polymer create for this test",
        polymer.getNucleicAcids().compareTo(lines[1]) == 0);

    MyLogger.get().log(polymer.getLabel() + " has passed", 2);
  }
}
