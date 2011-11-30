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

package uk.ac.ed.ph.jqtiplus.node.expression.operator;

import uk.ac.ed.ph.jqtiplus.node.expression.ExpressionParent;

/**
 * The sum operator takes one or more sub-expressions which have single or multiple or ordered
 * cardinality (it is possible to mix different cardinalities) and have numerical base-types.
 * <p>
 * The result is A single float or, if all sub-expressions are of integer type, A single integer that
 * corresponds to the sum of the numerical values of the sub-expressions.
 * <p>
 * If any of the sub-expressions are NULL then the operator results in NULL.
 *
 * @see uk.ac.ed.ph.jqtiplus.value.Cardinality
 * @see uk.ac.ed.ph.jqtiplus.value.BaseType
 * 
 * @author Jiri Kajaba
 */
public class Sum extends MathMapExpression
{
    private static final long serialVersionUID = 1L;
    
    /** Name of this class in xml schema. */
    public static final String CLASS_TAG = "sum";

    /**
     * Constructs expression.
     *
     * @param parent parent of this expression
     */
    public Sum(ExpressionParent parent)
    {
        super(parent);
    }

    @Override
    public String getClassTag()
    {
        return CLASS_TAG;
    }
    
    @Override
    protected double initialValue() {
        return 0.0;
    }

    @Override
    protected double foldr(double running, double value) {
        return running + value;
    }
}
