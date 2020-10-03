package org.charlesStockman.bioinformatics.algorithms;

import java.io.IOException;
import java.net.URISyntaxException;
import org.charlesStockman.bioinformatics.algorithms.util.MyInputFile;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestMyInputFile {

  @BeforeAll
  public static void printStartHeader() {
    MyLogger.get().startOfTestGroup();
  }

  @AfterAll
  public static void printEndHeader() {
    MyLogger.get().endOfTestGroup();
  }

  /**
   * Test the ability to retrieve the data using getResource
   *
   * @throws URISyntaxException
   */
  @Test
  public void getTextContent() throws URISyntaxException {

    try {
      String content = MyInputFile.getContentsOfFile(MyInputFile.getPath(getClass(), "test.txt"));
      Assertions.assertEquals(content, new String("hello worldhello world"));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Test loadDataFromAllDataFiles where only the pretest is defined. */
  @Test
  public void testLoadDataFromAllDataFilesWithPretestOnly() {}
}
