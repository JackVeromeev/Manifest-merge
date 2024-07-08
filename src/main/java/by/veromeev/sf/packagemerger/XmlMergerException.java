package by.veromeev.sf.packagemerger;

public class XmlMergerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static XmlMergerException fileParsingFailure(String filepath, Exception cause) {
        String message = "Error: Unable to build the document \"" + filepath + "\"";
        return new XmlMergerException(message, cause);
    }

    public static XmlMergerException mergeNotCalled() {
        return new XmlMergerException("Error: Merge function is not called");
    }

    public static XmlMergerException fileCreatingFailure(String filepath, Exception cause) {
        String message = "Error: Unable to create file \"" + filepath + "\"";
        return new XmlMergerException(message, cause);
    }

    public static XmlMergerException wrongStrategy(String strategy, Exception cause) {
        return new XmlMergerException("Error: Wrong strategy: " + strategy, cause);
    }

    public static XmlMergerException notEnoughParameters() {
        return new XmlMergerException("Error: Not enough parameters entered. At least 4 required: Strategy, target, source1, source2");
    }

    public static XmlMergerException helpNeeded() {
        return new XmlMergerException("");
    }

    private XmlMergerException(String message, Throwable cause) {
        super(message, cause);
    }

    private XmlMergerException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "XmlMergerException{" + super.toString() + "}";
    }
}
