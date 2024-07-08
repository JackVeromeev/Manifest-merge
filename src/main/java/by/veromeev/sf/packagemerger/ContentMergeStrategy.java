package by.veromeev.sf.packagemerger;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public enum ContentMergeStrategy {
    INTERSECT(ContentMergeStrategy::intersect),
    UNIFY(ContentMergeStrategy::unify),
    SUBTRACT(ContentMergeStrategy::subtract);

    public BinaryOperator<Map<String, Set<String>>> strategyApplication;

    ContentMergeStrategy(BinaryOperator<Map<String, Set<String>>> f) {
        this.strategyApplication = f;
    }

    private static Map<String, Set<String>> unify(
        Map<String, Set<String>> content1,
        Map<String, Set<String>> content2
    ) {
        Map<String, Set<String>> resultContent = new TreeMap<>();
        copyToTarget(content1, resultContent, Set::addAll);
        copyToTarget(content2, resultContent, Set::addAll);
        return resultContent;
    }

    private static Map<String, Set<String>> intersect(
        Map<String, Set<String>> content1,
        Map<String, Set<String>> content2
    ) {
        Map<String, Set<String>> resultContent = new TreeMap<>();
        copyToTarget(content1, resultContent, Set::addAll);
        copyToTarget(content2, resultContent, Set::retainAll);
        return resultContent;
    }

    private static Map<String, Set<String>> subtract(
        Map<String, Set<String>> content1,
        Map<String, Set<String>> content2
    ) {
        Map<String, Set<String>> resultContent = new TreeMap<>();
        copyToTarget(content1, resultContent, Set::addAll);
        copyToTarget(content2, resultContent, Set::removeAll);
        return resultContent;
    }

    private static void copyToTarget(
        Map<String, Set<String>> source,
        Map<String, Set<String>> target,
        BiConsumer<Set<String>, Set<String>> mergeFunction
    ) {
        source.forEach((contentKey, contentValue)->{
            target.computeIfAbsent(contentKey, k->new TreeSet<>());
            mergeFunction.accept(target.get(contentKey), contentValue);
        });
    }
}
