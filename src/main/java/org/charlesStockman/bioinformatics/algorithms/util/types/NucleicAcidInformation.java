package org.charlesStockman.bioinformatics.algorithms.util.types;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.charlesStockman.bioinformatics.algorithms.util.MyLogger;

/**
 * An abstract class that can be extended to contain information about speciifc type of nucleic
 * acid.
 */
public abstract class NucleicAcidInformation {
  /*
   * A map where the key is the nucleotide and the value is the complement
   *
   * Assumption : All nucleotides will be defined here
   */
  protected Map<Character, Character> compliments;

  /*
   * A map where the key is a nculeotide and the value is the name
   *
   * Assumption : All nucleotides will be defined here
   */
  protected Map<String, String> names;

  /*
   * A matcher that matches a polymer to verify that it contians the correct type of nucliec acid ( ex. A and C are nucleic acids)
   */
  protected String includeNucleotidesMatcher;

  /* A class that creates a Map where the index is a nucleotide and the value is the complement */
  protected abstract Map<Character, Character> getComplementMap();

  /* A class that creates a Map where the index is a nucleotide and the value is the name */
  protected abstract Map<String, String> getNames();

  /** The label for the nucleic acid polymer */
  public String getDefaultLabel() {
    return "No Label";
  }

  /**
   * Gets the Complement from the nucleuic base
   *
   * @param nucleicBase The current nucleic base
   * @return The Complement of the nucleic Base
   */
  public Character getComplementedBase(Character nucleicBase) {
    Character complementBase = getComplementMap().get(nucleicBase);
    return complementBase;
  }

  /*
   * @param nucleobase The current nucleic base
   * @return The name of the nucleotide
   */
  public String getName(Character nucleobase) {
    return getNames().getOrDefault("", nucleobase + " is not defined");
  }

  /**
   * Build a pattern that will verify that only the nucleotides in the names map are found in the
   * polymer. For examlple if the polyer is DNA then the nucleotides "ACGT" should be found.
   *
   * @return A pattern to determine if a polymer has the correct types.
   */
  public Pattern buildPolymerContainsOnlyTheseNucleotides() {
    if (includeNucleotidesMatcher == null) {
      includeNucleotidesMatcher =
          getNames().keySet().stream().map(item -> item.toString()).collect(Collectors.joining(""));
    }

    return Pattern.compile("[^" + includeNucleotidesMatcher + "]+");
  }

  /**
   * A function to verify that the Nucleic Acid String is valid
   *
   * @param polymer A String containing a nucleic Acid Sequence
   * @return true The DNA Sequence is valid
   */
  public Boolean validate(String polymer) {
    Pattern validNucleotides = buildPolymerContainsOnlyTheseNucleotides();
    MyLogger.get()
        .log(
            "The Pattern to detremine if the polymer contains nucleotides other than the defined collection is "
                + validNucleotides.pattern(),
            2);

    Boolean result = validNucleotides.matcher(polymer).find();
    MyLogger.get().log("The polymer does not have unknown nucleotides " + !result, 1);

    return !result;
  }
}
