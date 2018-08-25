package by.veromeev.sf.packagemerger;

public class XmlMergerException extends RuntimeException {
    XmlMergerException(String message) {
        super(message);
    }
    XmlMergerException() {
        super();
    }
    XmlMergerException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "XmlMergerException{" + super.toString() + "}";
    }
}
