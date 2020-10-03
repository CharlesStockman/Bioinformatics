/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.charlesStockman.bioinformatics.algorithms.util.types;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;

/**
 * A base class for DNA/RNA that contains common properties/methods
 *
 * @author Charles Stockman
 */
@Data
@SuppressWarnings("serial")
public class NucleicAcidPolymerType implements Serializable {

  protected String nucleicAcids;
  protected String label;

  protected NucleicAcidInformation nucleicAcidInformation;

  /**
   * Retrieve the nucleotides for display purposes
   *
   * @return The nucletides for the polymer
   */
  public String getDisplayNucleicAcids() {
    String result = (nucleicAcids.length() != 0) ? nucleicAcids : "No Nucleotides in the polymer";
    return result;
  }

  /**
   * Creates an Object of NucleicAcidPolymer which could be DNA, RNA etc..
   *
   * @param nucleicAcidSequence -- A String of nucleobases
   * @param label -- An identifier for the instance
   */
  public NucleicAcidPolymerType(final String nucleicAcidSequence, final String label) {
    this.nucleicAcids = makeNucleicAcidPolymerValid(nucleicAcidSequence);
    this.label = (StringUtils.isBlank(label)) ? nucleicAcidInformation.getDefaultLabel() : label;
  }

  /**
   * Transform the nucleic acid sequence into a valid sequence
   *
   * <p>Remember a class can be created two ways. By using a constructor or by using json library to
   * create the class. When created from JSON it sees the class is
   */
  public static String makeNucleicAcidPolymerValid(final String nucleicAcidSequence) {
    String nucleicAcids =
        (StringUtils.isBlank(nucleicAcidSequence))
            ? new String("")
            : nucleicAcidSequence.toUpperCase();
    return nucleicAcids;
  }

  /**
   * Count the number of each distinct nucleotide in the NucleicAcid Polymer
   *
   * <p>Assumption Each Nucleotide is one character long
   *
   * @param polymer The String nucleotides
   * @return A Map where the key is a nucleotide and the value is the number of times it appears in
   *     the polymer
   */
  public static TreeMap<String, Long> counts(final NucleicAcidPolymerType polymer) {

    MyLogger.get().log(polymer.createLogMessage("The orginial nucleic acid data is "), 2);

    final Map<String, Long> result =
        Arrays.stream(polymer.getNucleicAcids().split(""))
            .filter((s) -> s.trim().length() != 0)
            .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

    MyLogger.get().log("The counts are : " + result.toString(), 2);

    return new TreeMap<>(result);
  }

  /**
   * Create a Log Message describing the polymer.
   *
   * <p>The first line will be a description and the second line will be the polymer.
   *
   * @param description Text about what the polymer represents
   * @return A log string containing the description and polymer.
   */
  public String[] createLogMessage(String description) {

    if (StringUtils.isBlank(description)) description = "No Descritpion Provided";

    description = description.replace("/\n$/m", "");
    String logMessage = description + "\n" + getDisplayNucleicAcids();

    return logMessage.split("\n");
  }

  /**
   * Validates the nucleic acid
   *
   * @param polymer -- The polymer being valid could be any object inherited from NucleicAcidPolymer
   * @return (T) The nucleic acid polymer is valid.
   */
  public Boolean validate(final NucleicAcidPolymerType polymer) {
    return this.nucleicAcidInformation.validate(polymer.getNucleicAcids());
  }
}
