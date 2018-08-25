package by.veromeev.sf.packagemerger;

@FunctionalInterface
public interface StringMergeStrategyFunction {
    String apply(String descriptor1Value,
                 String descriptor2Value,
                 String customValue);
}
