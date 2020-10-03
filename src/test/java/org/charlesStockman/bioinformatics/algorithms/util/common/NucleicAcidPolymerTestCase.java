package org.charlesStockman.bioinformatics.algorithms.util.common;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.charlesStockman.bioinformatics.algorithms.util.types.NucleicAcidPolymerType;

/** Models a data files that contain nucleic Acid Polymer as the input and a result */
@Data
public class NucleicAcidPolymerTestCase extends BaseTestCase {

  List<String> nucleicAcidPolymers;

  /**
   * Retrieves the first n elements of the
   *
   * @param inputCasesCount -- The number of input polymers
   * @throws IllegalAccessException The calling routine ask for more input test case then in the
   *     file
   */
  public List<String> getNucleicAcidPolymer(int inputCasesCount) throws IllegalArgumentException {

    if (inputCasesCount > nucleicAcidPolymers.size()) {
      throw new IllegalArgumentException(
          String.format(
              "The number of Input Cases asked for was %d, but the number of cases in the file was %d",
              inputCasesCount, nucleicAcidPolymers.size()));
    }

    return nucleicAcidPolymers.subList(0, inputCasesCount);
  }

  /**
   * Add a input polymer to the testcase.
   *
   * @param nucleicAcidPolymer -- The nucleicAcidPolymer to the test case.
   */
  public void addNucleicAcidPolymer(String nucleicAcidPolymer) {
    if (nucleicAcidPolymers == null) {
      nucleicAcidPolymers = new ArrayList<>();
    }

    nucleicAcidPolymers.add(NucleicAcidPolymerType.makeNucleicAcidPolymerValid(nucleicAcidPolymer));
  }
}
