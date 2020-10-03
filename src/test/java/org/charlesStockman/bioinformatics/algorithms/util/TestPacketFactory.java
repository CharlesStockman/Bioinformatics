package org.charlesStockman.bioinformatics.algorithms.util;

import java.util.LinkedHashMap;
import org.charlesStockman.bioinformatics.algorithms.util.common.NucleicAcidPolymerTestCase;

/**
 * A factory to load the test data and results to use them in verifying the algorithms
 *
 * @author charles
 */
public class TestPacketFactory {

  public static TestPacketFactory testPacketFactory;

  public static TestPacketFactory get() {
    if (testPacketFactory == null) {
      testPacketFactory = new TestPacketFactory();
    }

    return testPacketFactory;
  }

  /**
   * Loads the original file format for all the files. The file is identified by an unique
   * directory/filename. If the directory/filename is the same then the original contents will be
   * overwritten with the new. The file is in the JSON format and contains two properties ( Nucleic
   * Acid and result ). It is not uncommon to see the result is not there since I get the result
   * after running the tests and verifying the output with the grader.
   *
   * @return A Map of <code>NucleicAcidPolymerTestCase</code>
   * @throws Exception -- The file could not be read or the JSON format was incorrect
   */
  public ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>> getNucleicAcidTestPacket(
      String... testCaseNames) throws Exception {

    ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>> packet = null;

    LinkedHashMap<String, NucleicAcidPolymerTestCase> testCases = new LinkedHashMap<>();

    try {
      for (String testCaseName : testCaseNames) {

        NucleicAcidPolymerTestCase testCase =
            new JsonJacksonWrapper()
                .jsonToJavaClass(
                    MyInputFile.getContentsOfFile(
                        MyInputFile.getPath(this.getClass(), testCaseName)),
                    NucleicAcidPolymerTestCase.class);

        testCases.put(testCaseName, testCase);
      }

      packet =
          new ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>>(
              ReturnPacket.SUCCESS_RETURN_CODE, ReturnPacket.SUCCESS_MESSAGE, testCases);
    } catch (Exception exception) {
      packet =
          new ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>>(
              ReturnPacket.ERROR_RETURN_CODE, exception.getMessage(), testCases, exception);
    }

    return packet;
  }
}
