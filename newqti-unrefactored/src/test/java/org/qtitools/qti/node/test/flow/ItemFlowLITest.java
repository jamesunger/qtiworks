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

package org.qtitools.qti.node.test.flow;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ItemFlowLITest extends ItemFlowTest
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
            // preCondition
//            {"ItemFlow-jump-01.xml", new Step[] {
//            {"ItemFlow-jump-02.xml", new Step[] {
//            {"ItemFlow-jump-03.xml", new Step[] {
//            {"ItemFlow-jump-04.xml", new Step[] {
//            {"ItemFlow-jump-05.xml", new Step[] {
//            {"ItemFlow-jump-back-01.xml", new Step[] {
//            {"ItemFlow-jump-back-02.xml", new Step[] {
//            {"ItemFlow-jump-back-03.xml", new Step[] {
//            {"ItemFlow-jump-error-01.xml", new Step[] {
//            {"ItemFlow-jump-error-02.xml", new Step[] {
//            {"ItemFlow-jump-error-03.xml", new Step[] {
//            {"ItemFlow-jump-error-04.xml", new Step[] {
//            {"ItemFlow-jump-error-05.xml", new Step[] {
//            {"ItemFlow-jump-error-06.xml", new Step[] {
//            {"ItemFlow-jump-special-01.xml", new Step[] {
//            {"ItemFlow-jump-special-02.xml", new Step[] {
//            {"ItemFlow-jump-special-03.xml", new Step[] {
//            {"ItemFlow-jump-special-04.xml", new Step[] {
//            {"ItemFlow-jump-special-05.xml", new Step[] {
//            {"ItemFlow-jump-special-06.xml", new Step[] {
//            {"ItemFlow-jump-special-07.xml", new Step[] {
//            {"mathematics.xml", new Step[] {
//                new Step(new Command(Action.NEXT, "I01")),
//                new Step(new Command(Action.SUBMIT)),
//                new Step(new Command(Action.NEXT, "I02")),
//                new Step(new Command(Action.SUBMIT)),
//                new Step(new Command(Action.NEXT, "I03")),
//                new Step(new Command(Action.SUBMIT)),
//                new Step(new Command(Action.NEXT, "I04")),
//                new Step(new Command(Action.SUBMIT)),
//                new Step(new Command(Action.NEXT, "I05")),
//                new Step(new Command(Action.SUBMIT)),
//                new Step(new Command(Action.NEXT, null)),
//            }},
//            {"ItemFlow-pre-02.xml", new Step[] {
//                new Step(new Command(Action.NONE)),
//                new Step(new Command(Action.NEXT, "I01")),
//                new Step(new Command(Action.NEXT, "I02")),
//                new Step(new Command(Action.NEXT, "I03")),
//                new Step(new Command(Action.NEXT, "I04")),
//                new Step(new Command(Action.NEXT, "I05")),
//                new Step(new Command(Action.NEXT, null)),
//            }},
//            {"ItemFlow-pre-03.xml", new Step[] {
//                new Step(new Command(Action.NONE)),
//                new Step(new Command(Action.NEXT, null)),
//            }},
//            {"ItemFlow-pre-04.xml", new Step[] {
//                new Step(new Command(Action.NONE)),
//                new Step(new Command(Action.NEXT, null)),
//            }},
//            {"ItemFlow-pre-05.xml", new Step[] {
//                new Step(new Command(Action.NONE)),
//                new Step(new Command(Action.NEXT, "I05")),
//                new Step(new Command(Action.NEXT, null)),
//            }},
//            {"ItemFlow-pre-06.xml", new Step[] {
//                new Step(new Command(Action.NONE)),
//                new Step(new Command(Action.NEXT, "I01")),
//                new Step(new Command(Action.NEXT, "I03")),
//                new Step(new Command(Action.NEXT, "I05")),
//                new Step(new Command(Action.NEXT, null)),
//            }},
//            // branchRule
//            {"ItemFlow-jump-01.xml", null, new String[][] {{"I01", "I02", "I04", "I05"}}},
//            {"ItemFlow-jump-02.xml", null, new String[][] {{"I01", "I03", "I05"}}},
//            {"ItemFlow-jump-03.xml", null, new String[][] {{"I01", "I05"}}},
//            {"ItemFlow-jump-04.xml", null, new String[][] {{"I01", "I05"}}},
//            {"ItemFlow-jump-05.xml", null, new String[][] {{"I01", "I05"}}},
//            {"ItemFlow-jump-back-01.xml", QTIItemFlowException.class, new String[][] {{"I01", "I02", "I03"}}},
//            {"ItemFlow-jump-back-02.xml", QTIItemFlowException.class, new String[][] {{"I01", "I03"}}},
//            {"ItemFlow-jump-back-03.xml", QTIItemFlowException.class, new String[][] {{"I01", "I05"}}},
//            {"ItemFlow-jump-error-01.xml", QTIItemFlowException.class, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-error-02.xml", QTIItemFlowException.class, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-error-03.xml", QTIItemFlowException.class, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-error-04.xml", QTIItemFlowException.class, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-error-05.xml", QTIItemFlowException.class, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-error-06.xml", QTIItemFlowException.class, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-special-01.xml", null, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-special-02.xml", null, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-special-03.xml", null, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-special-04.xml", null, new String[][] {{"I01"}}},
//            {"ItemFlow-jump-special-05.xml", null, new String[][] {{"I01", "I02", "I03", "I05"}}},
//            {"ItemFlow-jump-special-06.xml", QTIItemFlowException.class, new String[][] {{"I01", "I02", "I03", "I04"}}},
//            {"ItemFlow-jump-special-07.xml", null, new String[][] {{"I01", "I07", "I09"}}},
//            // preCondition + branchRule
//            {"ItemFlow-full-01.xml", null, new String[][] {{"I01", "I04", "I05"}}},
//            {"ItemFlow-full-02.xml", null, new String[][] {{"I01", "I05"}}},
//            {"ItemFlow-full-03.xml", null, new String[][] {{"I01", "I05"}}},
//            {"ItemFlow-full-04.xml", null, new String[][] {{"I03", "I07", "I09"}}},
//            {"ItemFlow-full-05.xml", null, new String[][] {
//                {"I01", "I02", "I03", "I04", "I05", "I06"},
//                {"I07", "I08", "I09", "I10", "I11", "I12", "I13"},
//                {"I14", "I15", "I16", "I17", "I18", "I19"},
//            }},
//            {"ItemFlow-jump-01.xml", new Step[] {
//                new Step(new Command(Action.NONE)),
//                new Step(new Command(Action.NEXT, "I01")),
//                new Step(new Command(Action.NEXT, "I02")),
//                new Step(new Command(Action.NEXT, "I04")),
//                new Step(new Command(Action.NEXT, "I05")),
//                new Step(new Command(Action.NEXT, null)),
//            }},
        });
    }

    public ItemFlowLITest(String fileName, Step[] steps)
    {
        super("li/" + fileName, steps);
    }
}
