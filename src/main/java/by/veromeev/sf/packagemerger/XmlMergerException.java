package by.veromeev.sf.packagemerger;

public class XmlMergerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	static XmlMergerException fileParsingFailure(String filepath, Exception cause) {
        String message = "Unable to build the document \"" + filepath + "\"";
        return new XmlMergerException(message, cause);
    }

    static XmlMergerException mergeNotCalled() {
        return new XmlMergerException("Merge function is not called");
    }

    static XmlMergerException fileCreatingFailure(String filepath, Exception cause) {
        String message = "Unable to create file \"" + filepath + "\"";
        return new XmlMergerException(message, cause);
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
