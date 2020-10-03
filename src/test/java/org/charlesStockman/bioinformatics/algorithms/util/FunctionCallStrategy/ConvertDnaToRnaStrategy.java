package org.charlesStockman.bioinformatics.algorithms.util.FunctionCallStrategy;

import java.util.function.Function;
import org.charlesStockman.bioinformatics.algorithms.util.types.DeoxyribonucleicAcidType;
import org.charlesStockman.bioinformatics.algorithms.util.types.RibonucleicAcidType;

/** A function that Starts with a Deoxyribonucleic Acid and produces a Ribonucleic Acid */
public class ConvertDnaToRnaStrategy
    implements ExecuteFunction<DeoxyribonucleicAcidType, RibonucleicAcidType> {

  private Function<DeoxyribonucleicAcidType, RibonucleicAcidType> function;
  private DeoxyribonucleicAcidType dna;

  /**
   * Creates an instance of ConvertDnaToRna
   *
   * @param function The function that will be
   * @param dna The first parameter which is a Deoxy Ribonucleic Acid
   */
  public ConvertDnaToRnaStrategy(
      Function<DeoxyribonucleicAcidType, RibonucleicAcidType> function,
      DeoxyribonucleicAcidType dna) {
    this.function = function;
    this.dna = dna;
  }

  @Override
  public RibonucleicAcidType execute() {
    return function.apply(dna);
  }
}
