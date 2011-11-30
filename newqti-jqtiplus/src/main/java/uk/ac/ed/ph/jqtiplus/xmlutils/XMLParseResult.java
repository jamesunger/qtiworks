/* $Id: XMLParseResult.java 2749 2011-07-08 08:43:51Z davemckain $
 *
 * Copyright 2011 University of Edinburgh.
 * All Rights Reserved
 */
package uk.ac.ed.ph.jqtiplus.xmlutils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 * FIXME: Document this type!
 * 
 * @author  David McKain
 * @version $Revision: 2749 $
 */
public class XMLParseResult implements Serializable, ErrorHandler {

    private static final long serialVersionUID = -6558013135849907488L;
    
    private final String systemId;
    private boolean validated;
    private final List<SAXParseException> warnings;
    private final List<SAXParseException> errors;
    private final List<SAXParseException> fatalErrors;
    private final List<String> supportedSchemaNamespaces;
    private final List<String> unsupportedSchemaNamespaces;
    
    public XMLParseResult(String systemId) {
        this.systemId = systemId;
        this.validated = false;
        this.warnings = new ArrayList<SAXParseException>();
        this.errors = new ArrayList<SAXParseException>();
        this.fatalErrors = new ArrayList<SAXParseException>();
        this.supportedSchemaNamespaces = new ArrayList<String>();
        this.unsupportedSchemaNamespaces = new ArrayList<String>();
    }
    
    public String getSystemId() {
        return systemId;
    }


    public boolean isValidated() {
        return validated;
    }
    
    public void setValidated(boolean validated) {
        this.validated = validated;
    }


    public List<SAXParseException> getWarnings() {
        return warnings;
    }
    
    public List<SAXParseException> getErrors() {
        return errors;
    }
    
    public List<SAXParseException> getFatalErrors() {
        return fatalErrors;
    }

    public List<String> getSupportedSchemaNamespaces() {
        return supportedSchemaNamespaces;
    }
    
    public List<String> getUnsupportedSchemaNamespaces() {
        return unsupportedSchemaNamespaces;
    }

    public boolean isSchemaValid() {
        return validated
            && fatalErrors.isEmpty()
            && errors.isEmpty()
            && warnings.isEmpty()
            && unsupportedSchemaNamespaces.isEmpty();
    }
    
    //---------------------------------------------------------

    public void warning(SAXParseException exception) {
        warnings.add(exception);
    }
    
    public void error(SAXParseException exception) throws SAXParseException {
        errors.add(exception);
        throw exception;
    }
    
    public void fatalError(SAXParseException exception) throws SAXParseException {
        fatalErrors.add(exception);
        throw exception;
    }
    
    //---------------------------------------------------------
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode()
            + "(systemId=" + systemId
            + ",validated=" + validated
            + ",warnings=" + warnings
            + ",errors=" + errors
            + ",fatalErrors=" + fatalErrors
            + ",supportedSchemaNamespaces=" + supportedSchemaNamespaces
            + ",unsupportedSchemaNamespaces=" + unsupportedSchemaNamespaces
            + ")";
    }
}
