package org.charlesStockman.bioinformatics.algorithms.util.FunctionCallStrategy;

import java.util.function.Function;
import org.charlesStockman.bioinformatics.algorithms.util.types.DeoxyribonucleicAcidType;

/** Operates on Deoxyribonucleic Acid and return a Dexoyribonucleic acid */
public class OperateOnDnaFunctionStrategy
    implements ExecuteFunction<DeoxyribonucleicAcidType, DeoxyribonucleicAcidType> {

  Function<DeoxyribonucleicAcidType, DeoxyribonucleicAcidType> function;
  DeoxyribonucleicAcidType dna;

  /**
   * Create an instance of of OperateOnDnaFucntionStrategy
   *
   * @param function The function that will be executed.
   * @param dnaString The input of the function which is a Deoxyribo nucleic acid.
   */
  public OperateOnDnaFunctionStrategy(
      Function<DeoxyribonucleicAcidType, DeoxyribonucleicAcidType> function, String dnaString) {
    this.function = function;
    this.dna = new DeoxyribonucleicAcidType(dnaString);
  }

  @Override
  public DeoxyribonucleicAcidType execute() {
    return function.apply(dna);
  }
}
