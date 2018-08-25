package by.veromeev.sf.packagemerger;

public enum StringParameterMergeStrategy {
    CUSTOM((v1,v2,cv)->cv),
    FIRST_PACKAGE((v1,v2,cv)->v1),
    LAST_PACKAGE((v1,v2,cv)->v2);

    StringMergeStrategyFunction strategyApplication;

    StringParameterMergeStrategy(StringMergeStrategyFunction strategyApplication) {
        this.strategyApplication = strategyApplication;
    }
}
