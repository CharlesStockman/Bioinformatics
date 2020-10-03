package org.charlesStockman.bioinformatics.algorithms.util.types;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.NotImplementedException;

/**
 * A class that models a Ribonucleic Acid.
 *
 * @author charles
 */
@SuppressWarnings("serial")
public class RibonucleicAcidType extends NucleicAcidPolymerType {
  // A class that provide facts about ribonucleic acid
  protected class RNA_INFO extends NucleicAcidInformation {

    @Override
    protected final Map<Character, Character> getComplementMap() {
      throw new NotImplementedException("RNA does not needd");
    }

    @Override
    protected final Map<String, String> getNames() {
      if (names == null) {
        names = new HashMap<>();
        names.put("A", "Adenine");
        names.put("C", "Cytosine");
        names.put("G", "Guanine");
        names.put("U", "Uracil");
      }
      return Collections.unmodifiableMap(names);
    }

    @Override
    public String getDefaultLabel() {
      return "RibonucleicAcid";
    }
  }

  /**
   * Creates an instance of RibonucleicAcid
   *
   * @param nucleicAcidSequence -- A String of nucleobases
   */
  public RibonucleicAcidType(String nucleicAcidSequence) {
    super(nucleicAcidSequence, "RNA");
    this.nucleicAcidInformation = new RNA_INFO();
    this.setLabel(nucleicAcidInformation.getDefaultLabel());
  }
}
