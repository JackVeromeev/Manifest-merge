package by.veromeev.sf.packagemerger;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.TreeSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class PackageDescriptor {
    private String packageName;
    private String description;
    private String version;
    private Map<String, Set<String>> components;

    PackageDescriptor(
        String packageName,
        String description,
        String version,
        Map<String, Set<String>> components
    ) {
        this.packageName = packageName;
        this.description = description;
        this.version = version;
        this.components = components;
    }

    PackageDescriptor() {
        this(null, null, null, new TreeMap<>());
    }

    /**
     * Parses the xml Document from file, path to which is set
     * @param pathName path to source file
     */
    PackageDescriptor(String pathName)  {
        this();
        Document document = XmlUtils.readFile(pathName);
        for (Element element : document.getRootElement().getChildren()) {
            switch (element.getName().toUpperCase()) {
                case "TYPES" :
                    this.parseComponents(element);
                break;
                case "VERSION" :
                    this.version = this.getElementInnerText(element);
                break;
                case "FULLNAME" :
                    this.packageName = this.getElementInnerText(element);
                break;
                case "DESCRIPTION" :
                    this.description = this.getElementInnerText(element);
                break;
                default: break;
            }
        }
    }

    String getPackageName() {
        return packageName;
    }

    void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    Map<String, Set<String>> getComponents() {
        return components;
    }

    private void parseComponents(Element element) {
        Set<String> members = new TreeSet<>();
        String memberType = null;
        for (Element child : element.getChildren()) {
            if (child.getName().toLowerCase().equals("name")) {
                memberType = this.getElementInnerText(child);
            } else if (child.getName().toLowerCase().equals("members")) {
                members.add(this.getElementInnerText(child));
            }
        }
        this.components.computeIfAbsent(memberType, key -> new TreeSet<>());
        this.components.get(memberType).addAll(members);
    }

    private String getElementInnerText(Element element) {
        return element.getContent(0).getValue();
    }
}
