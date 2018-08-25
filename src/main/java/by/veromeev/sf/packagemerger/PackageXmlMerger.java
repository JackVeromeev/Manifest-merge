package by.veromeev.sf.packagemerger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class PackageXmlMerger {

    private PackageDescriptor descriptor1;
    private PackageDescriptor descriptor2;
    private PackageDescriptor outDescriptor;

    public PackageXmlMerger(String xmlInName1,
                            String xmlInName2) throws XmlMergerException {
        descriptor1 = new PackageDescriptor(xmlInName1);
        descriptor2 = new PackageDescriptor(xmlInName2);
    }

    public PackageXmlMerger merge(ContentMergeStrategy contentStrategy,
                                  VersionMergeStrategy versionStrategy, String customVersion,
                                  StringParameterMergeStrategy nameStrategy, String customName,
                                  StringParameterMergeStrategy descriptionStrategy, String customDescription)
            throws XmlMergerException {
        Map<String, Set<String>> resultContent = contentStrategy.strategyApplication.apply(
                descriptor1.getComponents(),
                descriptor2.getComponents()
        );
        String resultVersion = versionStrategy.strategyApplication.apply(
                descriptor1.getVersion(),
                descriptor2.getVersion(),
                customVersion
        );
        String resultName = nameStrategy.strategyApplication.apply(
                descriptor1.getPackageName(),
                descriptor2.getPackageName(),
                customName
        );
        String resultDescription = descriptionStrategy.strategyApplication.apply(
                descriptor1.getDescription(),
                descriptor2.getDescription(),
                customDescription
        );
        outDescriptor = new PackageDescriptor(resultName, resultDescription, resultVersion, resultContent);
        return this;
    }


    public void saveResult(String outputPath) {

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(createDocument(), new FileWriter(outputPath));
        } catch (IOException e) {
            throw XmlMergerException.fileCreatingFailure(outputPath, e);
        }
    }



    private Document createDocument() {
        if (outDescriptor == null) {
            throw XmlMergerException.mergeNotCalled();
        }
        Namespace namespace = Namespace.getNamespace("http://soap.sforce.com/2006/04/metadata");
        Element root = new Element("Package", namespace);
        Document outDocument = new Document(root);
        if (outDescriptor.getPackageName() != null && !outDescriptor.getPackageName().equals("")) {
            Element nameNode = new Element("fullName", namespace);
            nameNode.setText(outDescriptor.getPackageName());
            root.addContent(nameNode);
        }
        if (outDescriptor.getDescription() != null && !outDescriptor.getDescription().equals("")) {
            Element descriptionNode = new Element("description", namespace);
            descriptionNode.setText(outDescriptor.getDescription());
            root.addContent(descriptionNode);
        }
        outDescriptor.getComponents()
                .keySet()
                .stream()
                .filter(key->!outDescriptor.getComponents().get(key).isEmpty())
                .forEach(typeName -> {
                    Element typesNode = new Element("types", namespace);
                    Element nameNode = new Element("name", namespace);
                    nameNode.setText(typeName);
                    outDescriptor.getComponents().get(typeName).forEach(member -> {
                        Element membersNode = new Element("members", namespace);
                        membersNode.setText(member);
                        typesNode.addContent(membersNode);
                    });
                    typesNode.addContent(nameNode);
                    root.addContent(typesNode);

                });
        Element versionNode = new Element("version", namespace);
        versionNode.setText(outDescriptor.getVersion());
        root.addContent(versionNode);
        return outDocument;
    }
}
