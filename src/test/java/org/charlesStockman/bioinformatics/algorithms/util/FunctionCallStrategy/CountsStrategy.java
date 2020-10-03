package org.charlesStockman.bioinformatics.algorithms.util.FunctionCallStrategy;

import java.util.Map;
import java.util.function.Function;
import org.charlesStockman.bioinformatics.algorithms.util.types.NucleicAcidPolymerType;

/**
 * A function that stores a Function with the parameter : NucleicAcidPolymerType and when execute
 * returns a Map.
 *
 * @author Charles Stockman
 */
public class CountsStrategy implements ExecuteFunction<NucleicAcidPolymerType, Map<String, Long>> {

  private Function<NucleicAcidPolymerType, Map<String, Long>> function;
  private String polymer;

  /**
   * Creates an instance of CountStrategy
   *
   * @param function The function that will executed
   * @param polymer the parameter that will be appied to the function
   */
  public CountsStrategy(
      Function<NucleicAcidPolymerType, Map<String, Long>> function, String polymer) {
    this.function = function;
    this.polymer = polymer;
  }

  @Override
  public Map<String, Long> execute() {
    NucleicAcidPolymerType testPolymer =
        new NucleicAcidPolymerType(polymer, "Polymer generated for test");
    return function.apply(testPolymer);
  }
}
