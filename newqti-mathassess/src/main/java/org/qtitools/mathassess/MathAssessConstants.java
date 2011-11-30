/*
<LICENCE>

Copyright (c) 2008, University of Southampton
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.

  * Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

  * Neither the name of the University of Southampton nor the names of its
    contributors may be used to endorse or promote products derived from this
    software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

</LICENCE>
*/
package org.qtitools.mathassess;

import uk.ac.ed.ph.jqtiplus.types.Identifier;

/**
 * This defines all the strings used in the mathassess code for fields and attributes
 * 
 * @author  Jonathon Hare
 * @version $Revision: 2608 $
 */
public interface MathAssessConstants {
    
    public static final String MATHASSESS_NAMESPACE_URI = "http://mathassess.qtitools.org/xsd/mathassess";
    public static final String MATHASSESS_SCHEMA_LOCATION = "http://mathassess.qtitools.org/xsd/mathassess.xsd";
    
    public static final String MATH_ENTRY_INTERACTION_CLASS = "org.qtitools.mathassess.MathEntryInteraction";
    
    public static final String CAS_COMPARE_CLASS = "org.qtitools.mathassess.CasCompare";
    public static final String CAS_CONDITION_CLASS = "org.qtitools.mathassess.CasCondition";
    public static final String CAS_PROCESS_CLASS = "org.qtitools.mathassess.CasProcess";
    public static final String SCRIPT_RULE_CLASS = "org.qtitools.mathassess.ScriptRule";
    
    public static final Identifier FIELD_PMATHML_IDENTIFIER = new Identifier("PMathML", false);
    public static final Identifier FIELD_PMATHML_BRACKETED_IDENTIFIER = new Identifier("PMathMLBracketed", false);
    public static final Identifier FIELD_CMATHML_IDENTIFIER = new Identifier("CMathML", false);
    public static final Identifier FIELD_MAXIMA_IDENTIFIER = new Identifier("Maxima", false);
    public static final Identifier FIELD_CANDIDATE_INPUT_IDENTIFIER = new Identifier("CandidateInput", false);
    
    public static final Identifier MATHS_CONTENT_RECORD_VARIABLE_IDENTIFIER = new Identifier("MathsContentClass");
    public static final String MATHS_CONTENT_RECORD_VARIABLE_VALUE = "org.qtitools.mathassess";
    
    public static final String ATTR_SYNTAX_NAME = "syntax";
    public static final String ATTR_ACTION_NAME = "action";
    public static final String ATTR_SIMPLIFY_NAME = "simplify";
    public static final String ATTR_CODE_NAME = "code";
    public static final String ATTR_EXPECTED_LENGTH_NAME = "expectedLength";
    public static final String ATTR_PRINT_IDENTIFIER_NAME = "printIdentifier";
    public static final String ATTR_RETURN_TYPE_NAME = "returnType";
    

    

}
