package by.veromeev.sf.packagemerger;

@FunctionalInterface
public interface StringMergeStrategyFunction {
    String apply(String source1, String source2, String optional);
}
