package by.veromeev.sf.packagemerger;

public enum VersionMergeStrategy {
    HIGHEST(VersionMergeStrategy::highest),
    LOWEST(VersionMergeStrategy::lowest),
    CUSTOM((v1,v2,cv)->cv),
    FIRST_PACKAGE((v1,v2,cv)->v1),
    LAST_PACKAGE((v1,v2,cv)->v2);

    StringMergeStrategyFunction strategyApplication;

    VersionMergeStrategy(StringMergeStrategyFunction strategyApplication) {
        this.strategyApplication = strategyApplication;
    }

    private static String highest(String descriptor1Value,
                           String descriptor2Value,
                           String customValue) {
        double descriptor1DoubleValue = Double.valueOf(descriptor1Value);
        double descriptor2DoubleValue = Double.valueOf(descriptor2Value);
        return descriptor1DoubleValue > descriptor2DoubleValue ? descriptor1Value : descriptor2Value;
    }

    private static String lowest(String descriptor1Value,
                           String descriptor2Value,
                           String customValue) {
        double descriptor1DoubleValue = Double.valueOf(descriptor1Value);
        double descriptor2DoubleValue = Double.valueOf(descriptor2Value);
        return descriptor1DoubleValue > descriptor2DoubleValue ? descriptor2Value : descriptor1Value;
    }
}
