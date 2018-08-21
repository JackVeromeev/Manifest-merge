package by.veromeev.sf.cli;

import by.veromeev.sf.packagemerger.MergeStrategy;
import by.veromeev.sf.packagemerger.PackageXmlMerger;
import by.veromeev.sf.packagemerger.XmlMergerException;

public class Cli {
    // TODO apply version choose strategy
    public static void main(String[] args) throws XmlMergerException {
        new PackageXmlMerger(
                "D:\\OneDrive - VRP Consulting, Inc\\projects\\global_radio\\changesets\\package descriptors\\package-cs1.xml",
                "D:\\OneDrive - VRP Consulting, Inc\\projects\\global_radio\\changesets\\package descriptors\\package-cs2.xml",
                MergeStrategy.UNIFY
        ).merge(
                "D:\\OneDrive - VRP Consulting, Inc\\projects\\global_radio\\changesets\\package descriptors\\package-cs-full.xml"
        );

    }
}
