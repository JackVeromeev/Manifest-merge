package by.veromeev.sf.cli;

import by.veromeev.sf.packagemerger.*;

import java.util.Set;

public class Cli {

    public static void main(String[] args) throws XmlMergerException {
        try {
            Set<String> argSet = Set.of(args);
            if (argSet.contains("-h") || argSet.contains("--help") || args[0].equalsIgnoreCase("help")) {
                throw XmlMergerException.helpNeeded();
            }

            if (args.length < 4 ) {
                throw XmlMergerException.notEnoughParameters();
            }

            ContentMergeStrategy strategy = null;
            try {
                strategy = ContentMergeStrategy.valueOf(args[0]);
            } catch (Exception e) {
                XmlMergerException.wrongStrategy(args[0], e);
            }
            if (strategy == null) {
                XmlMergerException.wrongStrategy(args[0], null);
            }

            PackageXmlMerger merger = new PackageXmlMerger(strategy);
            String targetFile = null;

            for (int i = 1; i < args.length; i++) {
                if (!args[i].startsWith("-")) {
                    if (targetFile == null) {
                        targetFile = args[i];
                    } else {
                        merger.withSource(args[i]);
                    }
                }
            }

            merger
                .withVersionStrategy(VersionMergeStrategy.HIGHEST, null)
                .withNameStrategy(StringParameterMergeStrategy.CUSTOM, "")
                .withDescriptionStrategy(StringParameterMergeStrategy.CUSTOM, "");

            merger.merge().saveResult(targetFile);

        } catch (XmlMergerException e) {
            System.out.println(e.getMessage());
            help();
        }


    }

    private static void help() {
        System.out.println("Usage:");
        System.out.println("$ java -jar manifest-merge.jar STRATEGY target source1 source2 [optional sources]");
        System.out.println("");
        System.out.println("Strategies:");
        System.out.println("- UNIFY - combine 2 manifests in one, add one to another");
        System.out.println("  allows as many sources as entered, at least 2");
        System.out.println("- INTERSECT - leave only elements remaining in all mentioned sources");
        System.out.println("  allows as many sources as entered, at least 2");
        System.out.println("- SUBTRACT - remove elements of source 2 from source 1");
        System.out.println("  allows exactly 2 sources");
        System.out.println("- HELP - print help");
        System.out.println("");
        System.out.println("Additional flags:");
        System.out.println("-h, --help - print help and enable verbose mode");
    }
}
