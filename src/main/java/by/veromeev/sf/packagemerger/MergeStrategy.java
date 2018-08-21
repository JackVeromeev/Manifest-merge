package by.veromeev.sf.packagemerger;

import org.jdom2.Document;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum MergeStrategy {
    INTERSECT(MergeStrategy::fu),
    UNIFY(MergeStrategy::fu);

    BiFunction<Document, Map<String, Set<String>>, String> strategyApplication;

    // TODO apply wrapper
    MergeStrategy(Function<String, String, String> f) {
        this.f = f;
    }

    static void fu(){}

    static String unifyDocumentGetVersion(Document document, Map<String, Set<String>> elements) {
        Map<String, Set<String>> documentElements = parseDocument(document);
        String version = retrieveVersion(documentElements);
        documentElements.forEach((documentKey,documentValue)->{
            elements.computeIfAbsent(documentKey, k->new HashSet<>());
            elements.get(documentKey).addAll(documentValue);
        });
        return version;
    }

    static String intersectToDocumentGetVersion(Document document, Map<String, Set<String>> elements) {
        Map<String, Set<String>> documentElements = parseDocument(document);
        String version = retrieveVersion(documentElements);
        documentElements.forEach((documentKey,documentValue)->{
            elements.computeIfAbsent(documentKey, k->new HashSet<>());
            elements.get(documentKey).retainAll(documentValue);
        });
        return version;
    }
}
