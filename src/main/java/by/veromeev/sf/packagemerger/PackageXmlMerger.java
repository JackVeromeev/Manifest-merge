package by.veromeev.sf.packagemerger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class PackageXmlMerger {

    private List<PackageDescriptor> descriptors = new ArrayList<>();

    private PackageDescriptor outDescriptor;

    private ContentMergeStrategy contentStrategy;

    private VersionMergeStrategy versionStrategy;
    private String customVersion;

    private StringParameterMergeStrategy nameStrategy;
    private String customName;

    private StringParameterMergeStrategy descriptionStrategy;
    private String customDescription;


    public PackageXmlMerger(ContentMergeStrategy contentStrategy) {
        this.contentStrategy = contentStrategy;
    }

    public PackageXmlMerger withSource(String manifestFileName) {
        this.descriptors.add(new PackageDescriptor(manifestFileName));
        return this;
    }

    public PackageXmlMerger withVersionStrategy(VersionMergeStrategy versionStrategy, String customVersion) {
        this.versionStrategy = versionStrategy;
        this.customVersion = customVersion;
        return this;
    }
    public PackageXmlMerger withNameStrategy(StringParameterMergeStrategy nameStrategy, String customName) {
        this.nameStrategy = nameStrategy;
        this.customName = customName;
        return this;
    }

    public PackageXmlMerger withDescriptionStrategy(StringParameterMergeStrategy descriptionStrategy, String customDescription) {
        this.descriptionStrategy = descriptionStrategy;
        this.customDescription = customDescription;
        return this;
    }

    public PackageXmlMerger merge() {

        this.outDescriptor = this.descriptors.get(0);
        this.descriptors.remove(0);
        this.descriptors.forEach(source -> {
            this.mergeDescriptor(source);
        });
        return this;
    }

    private void mergeDescriptor(PackageDescriptor descriptor) {
        Map<String, Set<String>> resultContent = contentStrategy.strategyApplication.apply(
            this.outDescriptor.getComponents(),
            descriptor.getComponents()
        );
        String resultVersion = versionStrategy.strategyApplication.apply(
            this.outDescriptor.getVersion(),
            descriptor.getVersion(),
            customVersion
        );
        String resultName = nameStrategy.strategyApplication.apply(
            this.outDescriptor.getPackageName(),
            descriptor.getPackageName(),
            customName
        );
        String resultDescription = descriptionStrategy.strategyApplication.apply(
            this.outDescriptor.getDescription(),
            descriptor.getDescription(),
            customDescription
        );
        this.outDescriptor = new PackageDescriptor(resultName, resultDescription, resultVersion, resultContent);
    }


    public void saveResult(String outputPath) {
        XmlUtils.saveFile(outputPath, this.createDocument());
    }

    private Document createDocument() {
        if (outDescriptor == null) {
            throw XmlMergerException.mergeNotCalled();
        }
        Namespace namespace = Namespace.getNamespace("http://soap.sforce.com/2006/04/metadata");
        Element root = new Element("Package", namespace);
        Document outDocument = new Document(root);
        if (this.outDescriptor.getPackageName() != null && !this.outDescriptor.getPackageName().equals("")) {
            Element nameNode = new Element("fullName", namespace);
            nameNode.setText(this.outDescriptor.getPackageName());
            root.addContent(nameNode);
        }
        if (this.outDescriptor.getDescription() != null && !this.outDescriptor.getDescription().equals("")) {
            Element descriptionNode = new Element("description", namespace);
            descriptionNode.setText(this.outDescriptor.getDescription());
            root.addContent(descriptionNode);
        }
        this.outDescriptor.getComponents()
                .keySet()
                .stream()
                .filter(key -> !this.outDescriptor.getComponents().get(key).isEmpty())
                .forEach(typeName -> {
                    Element typesNode = new Element("types", namespace);
                    Element nameNode = new Element("name", namespace);
                    nameNode.setText(typeName);
                    this.outDescriptor.getComponents().get(typeName).forEach(member -> {
                        Element membersNode = new Element("members", namespace);
                        membersNode.setText(member);
                        typesNode.addContent(membersNode);
                    });
                    typesNode.addContent(nameNode);
                    root.addContent(typesNode);

                });
        Element versionNode = new Element("version", namespace);
        versionNode.setText(this.outDescriptor.getVersion());
        root.addContent(versionNode);
        return outDocument;
    }
}
