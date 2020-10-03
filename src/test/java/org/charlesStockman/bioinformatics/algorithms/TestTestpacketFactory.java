package org.charlesStockman.bioinformatics.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedHashMap;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;
import org.charlesStockman.bioinformatics.algorithms.util.ReturnPacket;
import org.charlesStockman.bioinformatics.algorithms.util.TestPacketFactory;
import org.charlesStockman.bioinformatics.algorithms.util.common.NucleicAcidPolymerTestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestTestpacketFactory {

  // ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>> packet;

  @BeforeAll
  public static void printStartHeader() {
    MyLogger.get().startOfTestGroup();
  }

  @AfterAll
  public static void printEndHeader() {
    MyLogger.get().endOfTestGroup();
  }

  /**
   * Load the data using the fasta format.
   *
   * <p>The fasta format is a file format that will multiple groups ( A label followed by a nucleic
   * acid String
   */
  @Test
  public void TestgetFastaPacket() {

    MyLogger.get().startOfMethod();

    MyLogger.get().endOfMethod();
  }

  /**
   * Load the data using the original file format
   *
   * <p>The original file format is a json file that contains a nucleic acid sequence which is an
   * input and a result using the JSON format.
   */
  @Test
  public void testGetNucleicAcidTestPacket() {

    MyLogger.get().startOfMethod();

    try {

      ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>> packet;

      packet =
          TestPacketFactory.get()
              .getNucleicAcidTestPacket("preTest/countNucleotides", "postTest/countNucleotides");

      verifyPacket(packet);
      verifyOriginal(packet);
      verifyOriginalData(packet);

    } catch (Exception exception) {
      MyLogger.get().log("Exception in TestgetNucleicAcideTestPacket()", 0);
      MyLogger.get().logException(exception);
    } finally {
      MyLogger.get().endOfMethod();
    }
  }

  /** Verify that the packet returned from a routine that is acceptable */
  private void verifyPacket(ReturnPacket<?> packet) {
    assertNotNull(packet);
    assertTrue(packet.getReturnCode() == 0);
    assertNull(packet.getException());
  }

  /** Verify the keys */
  private void verifyOriginal(
      ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>> packet) {
    assertNotNull(packet.getData());
    assertTrue("The Map should contain two eleements", packet.getData().size() == 2);
    assertTrue(
        "There should be a key pretest/countNucleotides",
        packet.getData().keySet().contains("preTest/countNucleotides"));
    assertTrue(
        "There should be a key postTest/countNucletides",
        packet.getData().keySet().contains("postTest/countNucleotides"));
  }

  /** Verify the Data */
  private void verifyOriginalData(
      ReturnPacket<LinkedHashMap<String, NucleicAcidPolymerTestCase>> packet) {
    assertEquals(
        "preTest does not contain the correct input",
        packet.getData().get("preTest/countNucleotides").getNucleicAcidPolymer(1).get(0),
        "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC");

    assertEquals(
        "preTest does not contain the correct reuslt",
        packet.getData().get("preTest/countNucleotides").getResult(),
        "20 12 17 21");

    assertEquals(
        "postTest does not contain the correct input",
        packet.getData().get("postTest/countNucleotides").getNucleicAcidPolymer(1).get(0),
        "GCGTAGATAGATCCCTGGTAGCACGGATGTACATCCATGGCATACTGAGTACATTAGATATGATCTGCAAGGGTCGTGTAGATGTACTTTCGATCTTTGAAAAATAGCCGAGGCCCCGTAGTTGGATTATAGATTATGACGCTCTGGTGACCTAATTAGACACATAATCAGTTACTTTCTAGATTTGTCACCCGCAGTTGCTCCTCGATATCTGCCCTTAGAGCTGGCGATGGCTTCGCACGGCGGCCACTTTTATAGATCCTTTGTCCTATTGTTAATGCAATGGGTGCCAACAAATGTCCCAGCACATAACCGCGTTCATGGGTTCCAAACGGCACCGCACGGTCGTCTTTCGCATAAGGGCCGACAGCATTCACATGGGCCCATTCTATCATAAACAACATATGTAAGCCTCTTCGAGTTAGGTGCATGAAAACCACTAGCGTCCAAACACACCGAACAAATCCAACGTTGGCAGGCATATCTTAGCGCTATATAATCCCTTACGTGTAATCCCGAACTTATAGAACAGAACGACAACTTCTTACAACTGCCTGAGTAAGGCCGTCCGATTCTATATTCGATATACGGGGTTTCTAAACTAAGAAAAGACTGGGGGCTTCAGACCTTACTTGGTGTGATTCAGCCGAACGGGTCTGATACAGTCGACACTCAGCTTCCACGCTTTTAGCACAGGTCATACCTTTACAAACCAGGTTTTGTAACAGACTAGCTGTCATGCAGAACTCTCGGAACACCGTTGTCACACGCAAGCATCCCTTAGGCCACAAGTAGAAGCTTTAGCTAGGTCCCGTAGACTGTCGACAAGGGTACCGAATTCTCGTAACCCCCTGGACTTTACTCCTCATTGGGGGTTTCGCGACCGATCTTAGCTTCGGCTGTCATCTTTATAGGAACGCAGACAGGATTTTGACGCGATGCCAGTTTATTTAGTTACCGCTTT");

    assertEquals(
        "preTest does not contain the correct reuslt",
        packet.getData().get("postTest/countNucleotides").getResult(),
        "252 242 210 260");
  }
}
