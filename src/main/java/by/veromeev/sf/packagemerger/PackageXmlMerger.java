package by.veromeev.sf.packagemerger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// TODO Apply wrapper
public class PackageXmlMerger {

    private final Namespace namespace = Namespace.getNamespace("", "http://soap.sforce.com/2006/04/metadata");

    private Document document1;
    private Document document2;

    private Document outDocument;

    private MergeStrategy strategy;

    public PackageXmlMerger(String xmlInName1, String xmlInName2, MergeStrategy strategy) throws XmlMergerException {
        SAXBuilder builder = new  SAXBuilder();
        this.strategy = strategy;
        try {
            document1 = builder.build(new File(xmlInName1));
        } catch (JDOMException | IOException e) {
            throw new XmlMergerException("Unable to build first document", e);
        }
        try {
            document2 = builder.build(new File(xmlInName2));
        } catch (JDOMException | IOException e) {
            throw new XmlMergerException("Unable to build second document", e);
        }
    }

    public void merge(String fileName) throws XmlMergerException {
        Map<String, Set<String>> elements = new HashMap<>();
        String ver1 = unifyDocumentGetVersion(document1, elements);
        if (strategy == MergeStrategy.UNIFY) {
            String ver2 = unifyDocumentGetVersion(document2, elements);
        } else {
            String ver2 = intersectToDocumentGetVersion(document2, elements);
        }
        createDocument(elements, ver1);
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(outDocument, new FileWriter(fileName));
        } catch (IOException e) {
            throw new XmlMergerException("Unable to create the output file", e);
        }
    }

    private String retrieveVersion(Map<String, Set<String>> sourceElements) {
        LinkedList<String> versions = new LinkedList<>();
        documentElements.computeIfPresent("Version", (k,v)->{versions.add(v.iterator().next()); return null;});
        return versions.getFirst();
    }

    /**
     * Creates Map of Type Name => Set of names of members + "Version" => Set of 1 element with String of Version of the
     * package
     */
    private Map<String, Set<String>> parseDocument(Document sourceDocument) {
        Map<String, Set<String>> elements = new HashMap<>();
        for (Element typeNode : document.getRootElement().getChildren()) {
            if (typeNode.getName().equals("types")) {
                Set<String> members = new HashSet<>();
                String memberType = null;
                for (Element child : typeNode.getChildren()) {
                    if (child.getName().equals("name")) {
                        memberType = child.getContent(0).getValue();
                    } else if (child.getName().equals("members")) {
                        members.add(child.getContent(0).getValue());
                    }
                }
                elements.computeIfAbsent(memberType, key -> new HashSet<>());
                elements.get(memberType).addAll(members);
            } else if (typeNode.getName().equals("version")) {
                String version = typeNode.getContent(0).getValue();
                elements.put("Version", new HashSet<>(Arrays.asList(version)));
            }
        }
        return elements;
    }


    @SneakyThrows
    private void createDocument(Map<String, Set<String>> sourceElements, String version) {
        Element root = new Element("Package", namespace);
        outDocument = new Document(root);
        for (String typeName : sourceElements.keySet()) {
            if (!sourceElements.get(typeName).isEmpty()) {
                Element typesNode = new Element("types", namespace);
                Element nameNode = new Element("name", namespace);
                nameNode.setText(typeName);
                for (String member : sourceElements.get(typeName)) {
                    Element membersNode = new Element("members", namespace);
                    membersNode.setText(member);
                    typesNode.addContent(membersNode);
                }
                typesNode.addContent(nameNode);
                root.addContent(typesNode);
            }
        }
        Element versionNode = new Element("version", namespace);
        versionNode.setText(version);
        root.addContent(versionNode);
    }
}
