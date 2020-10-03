package org.charlesStockman.bioinformatics.algorithms.util.types;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;

/**
 * Models a Deoxyribonucleic Acid
 *
 * @author charles
 */
@SuppressWarnings("serial")
public class DeoxyribonucleicAcidType extends NucleicAcidPolymerType {

  // A class that provides the information about Deoxyribonucleic Acid
  private class DNAInformation extends NucleicAcidInformation {

    @Override
    protected final Map<Character, Character> getComplementMap() {
      if (compliments == null) {
        compliments = new HashMap<>();
        compliments.put('A', 'T');
        compliments.put('C', 'G');
        compliments.put('G', 'C');
        compliments.put('T', 'A');
      }
      return Collections.unmodifiableMap(compliments);
    }

    @Override
    protected final Map<String, String> getNames() {
      if (names == null) {
        names = new HashMap<>();
        names.put("A", "Adenine");
        names.put("C", "Cytosine");
        names.put("G", "Guanine");
        names.put("T", "Thymine");
      }
      return Collections.unmodifiableMap(names);
    }

    @Override
    public String getDefaultLabel() {
      return "DNA";
    }
  }

  /**
   * Creates an Object of Deoxyribonucleic Acid
   *
   * @param nucleicAcidSequence -- A String of nucleobases
   */
  public DeoxyribonucleicAcidType(String nucleicAcidSequence) {
    super(nucleicAcidSequence, "DNA");
    nucleicAcidInformation = new DNAInformation();
    this.label = getLabel();
  }

  /**
   * Convert DNA to RNA by replace T nucleotide with U nucleotide
   *
   * @param dna -- The DNA sequence that will converted to RNA
   * @return The RNA derived from the DNA String
   */
  public static RibonucleicAcidType convertToRNA(DeoxyribonucleicAcidType dna) {

    MyLogger.get().log(dna.createLogMessage("The original nucleic acid data is "), 2);

    String result = dna.getNucleicAcids().replace("T", "U");
    RibonucleicAcidType rna = new RibonucleicAcidType(result);

    MyLogger.get().log(rna.createLogMessage("The Conversion to RNA produces"), 2);

    return rna;
  }

  /**
   * Convert a 3' to a 5' or a 5' to 3' in other words complement a nucleic acid sequence
   *
   * <ol>
   *   <li>Reverse the String
   *   <li>For each nucleic base replace with the complement
   * </ol>
   *
   * @param dna A dexoyribonucleic acid.
   * @return A Deoxyribonucleic Acid
   */
  public static DeoxyribonucleicAcidType complement(DeoxyribonucleicAcidType dna) {

    MyLogger.get().log(dna.createLogMessage("The original nucleic data is " + dna.getLabel()), 2);

    // Alogirthm
    // Line 1. Create a stream of character from the dna ( Java will convert the
    // character Stream to a stream of integers)
    // Line 2. Convert the stream of integers into character and retrieve the
    // complement nucleic base
    // Line 3. Append the base to a String.
    String complement =
        StringUtils.reverse(dna.getNucleicAcids())
            .chars()
            .mapToObj(
                (int element) ->
                    dna.getNucleicAcidInformation().getComplementedBase((char) element))
            .map(Object::toString)
            .collect(Collectors.joining(""));

    DeoxyribonucleicAcidType complementDna = new DeoxyribonucleicAcidType(complement.toString());

    MyLogger.get().log(complementDna.createLogMessage("The Complement of the nucleic acid"), 2);

    return complementDna;
  }
}
