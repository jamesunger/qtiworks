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

import uk.ac.ed.ph.jqtiplus.value.IntegerValue;
import uk.ac.ed.ph.jqtiplus.value.ListValue;
import uk.ac.ed.ph.jqtiplus.value.MultipleValue;
import uk.ac.ed.ph.jqtiplus.value.NullValue;
import uk.ac.ed.ph.jqtiplus.value.OrderedValue;
import uk.ac.ed.ph.jqtiplus.value.SingleValue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.qtitools.qti.node.expression.ExpressionAcceptTest;

/**
 * Test of <code>Delete</code> expression.
 *
 * @see uk.ac.ed.ph.jqtiplus.node.expression.operator.Delete
 */
@RunWith(Parameterized.class)
public class DeleteAcceptTest extends ExpressionAcceptTest
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
            {"<delete>" +
                "<null/>" +
                "<null/>" +
            "</delete>", null},
            {"<delete>" +
                "<baseValue baseType='integer'>1</baseValue>" +
                "<null/>" +
            "</delete>", null},
            {"<delete>" +
                "<null/>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                "</multiple>" +
            "</delete>", null},
            {"<delete>" +
                "<null/>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                "</ordered>" +
            "</delete>", null},
            // multiple
            {"<delete>" +
                "<baseValue baseType='integer'>1</baseValue>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                "</multiple>" +
            "</delete>", null},
            {"<delete>" +
                "<baseValue baseType='integer'>1</baseValue>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                "</multiple>" +
            "</delete>", null},
            {"<delete>" +
                "<baseValue baseType='integer'>0</baseValue>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</multiple>" +
            "</delete>", new MultipleValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(2),
                    new IntegerValue(3)})},
            {"<delete>" +
                "<baseValue baseType='integer'>1</baseValue>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</multiple>" +
            "</delete>", new MultipleValue(new SingleValue[] { new IntegerValue(2), new IntegerValue(3)})},
            {"<delete>" +
                "<baseValue baseType='integer'>2</baseValue>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</multiple>" +
            "</delete>", new MultipleValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(3)})},
            {"<delete>" +
                "<baseValue baseType='integer'>3</baseValue>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</multiple>" +
            "</delete>", new MultipleValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(2)})},
            {"<delete>" +
                "<baseValue baseType='integer'>2</baseValue>" +
                "<multiple>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</multiple>" +
            "</delete>", new MultipleValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(1),
                    new IntegerValue(3)})},
            // ordered
            {"<delete>" +
                "<baseValue baseType='integer'>1</baseValue>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                "</ordered>" +
            "</delete>", null},
            {"<delete>" +
                "<baseValue baseType='integer'>1</baseValue>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                "</ordered>" +
            "</delete>", null},
            {"<delete>" +
                "<baseValue baseType='integer'>0</baseValue>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</ordered>" +
            "</delete>", new OrderedValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(2),
                    new IntegerValue(3)})},
            {"<delete>" +
                "<baseValue baseType='integer'>1</baseValue>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</ordered>" +
            "</delete>", new OrderedValue(new SingleValue[] { new IntegerValue(2), new IntegerValue(3)})},
            {"<delete>" +
                "<baseValue baseType='integer'>2</baseValue>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</ordered>" +
            "</delete>", new OrderedValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(3)})},
            {"<delete>" +
                "<baseValue baseType='integer'>3</baseValue>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</ordered>" +
            "</delete>", new OrderedValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(2)})},
            {"<delete>" +
                "<baseValue baseType='integer'>2</baseValue>" +
                "<ordered>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>1</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>2</baseValue>" +
                    "<baseValue baseType='integer'>3</baseValue>" +
                "</ordered>" +
            "</delete>", new OrderedValue(new SingleValue[] { new IntegerValue(1), new IntegerValue(1),
                    new IntegerValue(3)})},
        });
    }

    /**
     * Constructs <code>Delete</code> expression test.
     *
     * @param xml xml data used for creation tested expression
     * @param expectedValue expected evaluated value
     */
    public DeleteAcceptTest(String xml, ListValue expectedValue)
    {
        super(xml, (expectedValue != null) ? expectedValue : NullValue.INSTANCE);
    }
}
