package org.charlesStockman.bioinformatics.algorithms;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.charlesStockman.bioinformatics.algorithms.util.FunctionCallStrategy.ConvertDnaToRnaStrategy;
import org.charlesStockman.bioinformatics.algorithms.util.FunctionCallStrategy.CountsStrategy;
import org.charlesStockman.bioinformatics.algorithms.util.FunctionCallStrategy.OperateOnDnaFunctionStrategy;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;
import org.charlesStockman.bioinformatics.algorithms.util.TestPacketFactory;
import org.charlesStockman.bioinformatics.algorithms.util.Validate.CountsValidate;
import org.charlesStockman.bioinformatics.algorithms.util.Validate.PolymerValidator;
import org.charlesStockman.bioinformatics.algorithms.util.common.NucleicAcidPolymerTestCase;
import org.charlesStockman.bioinformatics.algorithms.util.generatedTests.AdhocTest;
import org.charlesStockman.bioinformatics.algorithms.util.types.DeoxyribonucleicAcidType;
import org.charlesStockman.bioinformatics.algorithms.util.types.NucleicAcidPolymerType;
import org.charlesStockman.bioinformatics.algorithms.util.types.RibonucleicAcidType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestIntroductionBioinformatics {

  @BeforeAll
  public static void printStartHeader() {
    MyLogger.get().startOfTestGroup();
  }

  @AfterAll
  public static void printEndHeader() {
    MyLogger.get().endOfTestGroup();
  }

  /** Create the default test cases where there are one Nucleic Acid and One Reuslt */
  private final LinkedHashMap<String, NucleicAcidPolymerTestCase> oneNucleicAcidOneResult =
      AdhocTest.createDefaultTestCaseWithOneEmptyResult(1);

  /** Create the default test cases where there are one Nucleic Acid and One Reuslt */
  private final LinkedHashMap<String, NucleicAcidPolymerTestCase> oneNucleicAcidsTwoResult =
      AdhocTest.createDefaultTestCaseWithOneEmptyResult(2);

  /**
   * Count the number of nucleotides in NucleicAcidPolymer or one of its instances
   *
   * <p>Assumption : A nucleotide is one character long
   *
   * @return The number of each nucleotide sorted by it symbol
   * @throws Exception
   */
  @Test
  public void countNucleotides() throws Exception {

    MyLogger.get().startOfMethod();

    data(oneNucleicAcidOneResult, "preTest/countNucleotides", "postTest/countNucleotides")
        .forEach(
            (testName, test) -> {
              Map<String, Long> counts =
                  new CountsStrategy(
                          NucleicAcidPolymerType::counts, test.getNucleicAcidPolymer(1).get(0))
                      .execute();
              Boolean result =
                  (new CountsValidate(test.getResult())).validate(counts.values().toString());
              assertTrue("The Nucleotide Cound was calcuated correctly", result == Boolean.TRUE);
            });

    MyLogger.get().endOfMethod();
  }

  /**
   * Retrieve the set test cases that will be executed.
   *
   * @param fileNames A list of files containing the test cases
   * @return A list of test cases to tested
   * @throws Exception
   */
  private LinkedHashMap<String, NucleicAcidPolymerTestCase> data(
      LinkedHashMap<String, NucleicAcidPolymerTestCase> extraTests, String... fileNames)
      throws Exception {
    LinkedHashMap<String, NucleicAcidPolymerTestCase> results =
        TestPacketFactory.get().getNucleicAcidTestPacket(fileNames).getData();
    if (extraTests != null) results.putAll(extraTests);
    return results;
  }

  /**
   * Transcribing DNA into RNA -- Replaces the Thymine with Uracil
   *
   * @throws Exception -- There was issue with getData
   */
  @Test
  public void transcribeDna() throws Exception {

    MyLogger.get().startOfMethod();

    data(oneNucleicAcidOneResult, "preTest/transcribeDna", "postTest/transcribeDna")
        .forEach(
            (String testName, NucleicAcidPolymerTestCase test) -> {
              RibonucleicAcidType rna =
                  new ConvertDnaToRnaStrategy(
                          DeoxyribonucleicAcidType::convertToRNA,
                          new DeoxyribonucleicAcidType(test.getNucleicAcidPolymer(1).get(0)))
                      .execute();
              Boolean result =
                  (new PolymerValidator(test.getResult())).validate(rna.getNucleicAcids());
              assertTrue("The RNA was not converted correclty from DNA", result == Boolean.TRUE);
              assertTrue(
                  "The type of the result must be ",
                  rna.getLabel() == rna.getNucleicAcidInformation().getDefaultLabel());
            });

    MyLogger.get().endOfMethod();
  }

  /**
   * Take the Reverse Compliment of a DNA String
   *
   * <ol>
   *   Algorithm
   *   <li>Each Nucleotide base should be change to its opposite ( ex. A changes to T )
   *   <li>Reverse the String
   * </ol>
   */
  @Test
  public void reverseComplementDNA() throws Exception {

    MyLogger.get().startOfMethod();

    data(oneNucleicAcidOneResult, "preTest/reverseComplementDNA", "postTest/reverseComplementDNA")
        .forEach(
            (String testName, NucleicAcidPolymerTestCase test) -> {
              DeoxyribonucleicAcidType dna =
                  new OperateOnDnaFunctionStrategy(
                          DeoxyribonucleicAcidType::complement,
                          test.getNucleicAcidPolymer(1).get(0))
                      .execute();
              Boolean result =
                  (new PolymerValidator(test.getResult())).validate(dna.getNucleicAcids());
              assertTrue(
                  "The Reverse Comlplement is not correct ( either the polymer was not reversed or the opposite bases are incorrect.",
                  result);
              assertTrue(
                  "The Type of the polymer that must be returned is not correct ",
                  dna.getLabel() == dna.getNucleicAcidInformation().getDefaultLabel());
            });

    MyLogger.get().endOfMethod();
  }
}
