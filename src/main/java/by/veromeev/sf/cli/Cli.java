package by.veromeev.sf.cli;

import by.veromeev.sf.packagemerger.*;

public class Cli {
    public static void main(String[] args) throws XmlMergerException {
        new PackageXmlMerger(
                "1.xml",
                "2.xml").merge(
                ContentMergeStrategy.SUBTRACT,
                VersionMergeStrategy.LOWEST, null,
                StringParameterMergeStrategy.CUSTOM, "",
                StringParameterMergeStrategy.CUSTOM, null
        ).saveResult("3.xml");

    }
}
