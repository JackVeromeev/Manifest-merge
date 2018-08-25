package by.veromeev.sf.packagemerger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

class PackageDescriptor {
    private final String packageName;
    private final String description;
    private final String version;
    private final Map<String, Set<String>> components;

    /**
     * Parses the xml Document from file, path to which is set
     * @param pathName path to source file
     */
    PackageDescriptor(String pathName)  {
        Document document = getDocument(pathName);
        Map<String, Set<String>> components = new TreeMap<>();
        String packageName = null;
        String description = null;
        String version = null;
        for (Element element : document.getRootElement().getChildren()) {
            switch (element.getName().toUpperCase()) {
                case "TYPES" :
                    parseComponents(element, components);
                break;
                case "VERSION" :
                    version = getElementInnerText(element);
                break;
                case "FULLNAME" :
                    packageName = getElementInnerText(element);
                break;
                case "DESCRIPTION" :
                    description = getElementInnerText(element);
                break;
                default: break;
            }
        }
        this.components = components;
        this.packageName = packageName;
        this.description = description;
        this.version = version;
    }

    PackageDescriptor(String packageName,
                             String description,
                             String version,
                             Map<String, Set<String>> components) {
        this.packageName = packageName;
        this.description = description;
        this.version = version;
        this.components = components;
    }

    String getPackageName() {
        return packageName;
    }

    String getDescription() {
        return description;
    }

    String getVersion() {
        return version;
    }

    Map<String, Set<String>> getComponents() {
        return components;
    }

    // constructor helping methods

    private String getElementInnerText(Element element) {
        return element.getContent(0).getValue();
    }

    private void parseComponents(Element element, Map<String, Set<String>> components) {
        Set<String> members = new TreeSet<>();
        String memberType = null;
        for (Element child : element.getChildren()) {
            if (child.getName().toLowerCase().equals("name")) {
                memberType = getElementInnerText(child);
            } else if (child.getName().toLowerCase().equals("members")) {
                members.add(getElementInnerText(child));
            }
        }
        components.computeIfAbsent(memberType, key -> new TreeSet<>());
        components.get(memberType).addAll(members);
    }

    private Document getDocument(String pathName) {
        SAXBuilder builder = new  SAXBuilder();
        try {
            return builder.build(new File(pathName));
        } catch (JDOMException | IOException e) {
            throw XmlMergerException.fileParsingFailure(pathName, e);
        }
    }

}
