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

import uk.ac.ed.ph.jqtiplus.value.BooleanValue;
import uk.ac.ed.ph.jqtiplus.value.NullValue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.qtitools.qti.node.expression.ExpressionAcceptTest;

/**
 * Test of <code>Or</code> expression.
 *
 * @see uk.ac.ed.ph.jqtiplus.node.expression.operator.Or
 */
@RunWith(Parameterized.class)
public class OrAcceptTest extends ExpressionAcceptTest
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
            // null
            {"<or>" +
                "<null/>" +
            "</or>", null},
            {"<or>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
                "<null/>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
            "</or>", null},
            // false
            {"<or>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
            "</or>", false},
            {"<or>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
            "</or>", false},
            // true
            {"<or>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
            "</or>", true},
            {"<or>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
            "</or>", true},
            {"<or>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
            "</or>", true},
            {"<or>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
                "<null/>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
            "</or>", true},
            {"<or>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
                "<baseValue baseType='boolean'>true</baseValue>" +
                "<null/>" +
                "<baseValue baseType='boolean'>false</baseValue>" +
            "</or>", true},
        });
    }

    /**
     * Constructs <code>Or</code> expression test.
     *
     * @param xml xml data used for creation tested expression
     * @param expectedValue expected evaluated value
     */
    public OrAcceptTest(String xml, Boolean expectedValue)
    {
        super(xml, (expectedValue != null) ? BooleanValue.valueOf(expectedValue) : NullValue.INSTANCE);
    }
}
