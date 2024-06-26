package allergyDetection.db.xml.utils;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public interface ErrorHandler {
	 /**
     * Receive notification of a warning.
     *
     * <p>SAX parsers will use this method to report conditions that
     * are not errors or fatal errors as defined by the XML
     * recommendation.  The default behaviour is to take no
     * action.</p>
     *
     * <p>The SAX parser must continue to provide normal parsing events
     * after invoking this method: it should still be possible for the
     * application to process the document through to the end.</p>
     *
     * <p>Filters may use this method to report other, non-XML warnings
     * as well.</p>
     *
     * @param exception The warning information encapsulated in a
     *                  SAX parse exception.
     * @throws org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.SAXParseException
     */
    public abstract void warning (SAXParseException exception)
        throws SAXException;


    /**
     * Receive notification of a recoverable error.
     *
     * <p>This corresponds to the definition of "error" in section 1.2
     * of the W3C XML 1.0 Recommendation.  For example, a validating
     * parser would use this callback to report the violation of a
     * validity constraint.  The default behaviour is to take no
     * action.</p>
     *
     * <p>The SAX parser must continue to provide normal parsing
     * events after invoking this method: it should still be possible
     * for the application to process the document through to the end.
     * If the application cannot do so, then the parser should report
     * a fatal error even if the XML recommendation does not require
     * it to do so.</p>
     *
     * <p>Filters may use this method to report other, non-XML errors
     * as well.</p>
     *
     * @param exception The error information encapsulated in a
     *                  SAX parse exception.
     * @throws org.xml.sax.SAXException Any SAX exception, possibly
     *            wrapping another exception.
     * @see org.xml.sax.SAXParseException
     */
    public abstract void error (SAXParseException exception)
        throws SAXException;


    /**
     * Receive notification of a non-recoverable, fatal error.
     *
     * <p>
     * As defined in section 1.2 of the W3C XML 1.0 Recommendation, fatal errors
     * are those that would make it impossible for a parser to continue normal
     * processing. These include violation of a well-formedness constraint,
     * invalid encoding, and forbidden structural errors as described in the
     * W3C XML 1.0 Recommendation.
     *
     * @apiNote An application must assume that the parser can no longer perform
     * normal processing after reporting a fatal error and may stop by throwing
     * a {@link SAXException} without calling {@link ContentHandler#endDocument()}.
     * In addition, the parser cannot be expected to be able to return accurate
     * information about the logical structure on the rest of the document even
     * if it may be able to resume parsing.
     *
     * @implNote After invoking this method, the parser may stop processing by
     * throwing a {@link SAXException}, or implement a feature that can direct
     * it to continue after a fatal error. In the later case, it may report
     * events on the rest of the document without any guarantee of correctness.
     *
     * @param exception The error information encapsulated in a
     *                  {@link SAXParseException}.
     * @throws SAXException if the application chooses to discontinue the parsing
     */
    public abstract void fatalError (SAXParseException exception)
        throws SAXException;

}
