/*
<LICENCE>

Copyright (c) 2008, University of Southampton
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.

  *    Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

  *    Neither the name of the University of Southampton nor the names of its
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

package org.qtitools.qti.node.expression.operator;

import uk.ac.ed.ph.jqtiplus.exception.QTIBaseTypeException;
import uk.ac.ed.ph.jqtiplus.exception.QTICardinalityException;
import uk.ac.ed.ph.jqtiplus.exception.QTIRuntimeException;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.qtitools.qti.node.expression.ExpressionRefuseTest;

/**
 * Test of <code>PatternMatch</code> expression.
 *
 * @see uk.ac.ed.ph.jqtiplus.node.expression.operator.PatternMatch
 */
@RunWith(Parameterized.class)
public class PatternMatchRefuseTest extends ExpressionRefuseTest
{
    /**
     * Creates test data for this test.
     *
     * @return test data for this test
     */
    @Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]
        {
            // multiple
            {"<patternMatch pattern='A'>" +
                "<multiple>" +
                    "<baseValue baseType='string'>string</baseValue>" +
                "</multiple>" +
            "</patternMatch>", QTICardinalityException.class},
            // ordered
            {"<patternMatch pattern='A'>" +
                "<ordered>" +
                    "<baseValue baseType='string'>string</baseValue>" +
                "</ordered>" +
            "</patternMatch>", QTICardinalityException.class},
            // record
            {"<patternMatch pattern='A'>" +
                "<recordEx identifiers='key_1'>" +
                    "<baseValue baseType='string'>string</baseValue>" +
                "</recordEx>" +
            "</patternMatch>", QTICardinalityException.class},
            // identifier
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='identifier'>identifier</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // boolean
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // integer
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='integer'>1</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // float
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='float'>1</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // point
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='point'>1 1</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // pair
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='pair'>identifier_1 identifier_2</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // directedPair
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='directedPair'>identifier_1 identifier_2</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // duration
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='duration'>1</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // file
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='file'>file</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
            // uri
            {"<patternMatch pattern='A'>" +
                "<baseValue baseType='uri'>uri</baseValue>" +
            "</patternMatch>", QTIBaseTypeException.class},
        });
    }

    /**
     * Constructs <code>PatternMatch</code> expression test.
     *
     * @param xml xml data used for creation tested expression
     * @param expectedException expected exception during evaluation of tested expression
     */
    public PatternMatchRefuseTest(String xml, Class<? extends QTIRuntimeException> expectedException)
    {
        super(xml, expectedException);
    }
}
