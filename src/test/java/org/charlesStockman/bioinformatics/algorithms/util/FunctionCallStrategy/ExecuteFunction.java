package org.charlesStockman.bioinformatics.algorithms.util.FunctionCallStrategy;

/**
 * Problem : Trying to make the execution of a functions(s) generic the type erasure was causing
 * issues (caused two function signatures to be the same decided to use the Strategy Pattern.
 *
 * <p>The pattern allow a behavior or function to be selected for the particular routine. By using
 * this pattern I have enclosed the function in the class so that the function signature of each
 * function will in a different namespace
 *
 * @param <T> The Type of the parameter. The initialization will be handled by the class that
 *     implements the function
 * @param <R> The Result Type of the Parameter
 */
public interface ExecuteFunction<T, R> {

  /**
   * Executes the function and Returns an instance of the Generic Type R
   *
   * @return
   */
  public R execute();
}
