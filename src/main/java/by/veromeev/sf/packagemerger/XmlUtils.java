package by.veromeev.sf.packagemerger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlUtils {
    private XmlUtils() {}

    /**
     * parse document from the file
     * @param filePath
     * @return
     */
    public static Document readFile(String filePath) {
        SAXBuilder builder = new SAXBuilder();
        try {
            return builder.build(new File(filePath));
        } catch (JDOMException | IOException e) {
            throw XmlMergerException.fileParsingFailure(filePath, e);
        }
    }

    public static void saveFile(String filePath, Document sourceDocument) {
        XMLOutputter xmlOutput = new XMLOutputter();
        Format xmlFormat = Format.getPrettyFormat();
        xmlFormat.setIndent("\t");
        xmlOutput.setFormat(xmlFormat);
        try {
            xmlOutput.output(sourceDocument, new FileWriter(filePath));
        } catch (IOException e) {
            throw XmlMergerException.fileCreatingFailure(filePath, e);
        }
    }
}
