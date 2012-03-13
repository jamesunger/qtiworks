/* Copyright (c) 2012, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.jqtiplus.node.outcome.processing;

import uk.ac.ed.ph.jqtiplus.exception.QTIProcessingInterrupt;
import uk.ac.ed.ph.jqtiplus.exception2.RuntimeValidationException;
import uk.ac.ed.ph.jqtiplus.group.expression.ExpressionGroup;
import uk.ac.ed.ph.jqtiplus.node.expression.Expression;
import uk.ac.ed.ph.jqtiplus.node.expression.ExpressionParent;
import uk.ac.ed.ph.jqtiplus.running.ProcessingContext;
import uk.ac.ed.ph.jqtiplus.validation.ValidationContext;
import uk.ac.ed.ph.jqtiplus.value.BaseType;
import uk.ac.ed.ph.jqtiplus.value.BooleanValue;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;
import uk.ac.ed.ph.jqtiplus.value.Value;

/**
 * Abstract parent for all outcomeCondition children with condition (IF, ELSE-IF).
 * 
 * @author Jiri Kajaba
 */
public abstract class OutcomeConditionExpressionChild extends OutcomeConditionChild implements ExpressionParent {

    private static final long serialVersionUID = -3203987096888772050L;

    /**
     * Construct condition rule child.
     * 
     * @param parent parent of this condition rule child
     */
    public OutcomeConditionExpressionChild(OutcomeCondition parent, String localName) {
        super(parent, localName);

        getNodeGroups().add(0, new ExpressionGroup(this, 1, 1));
    }

    /**
     * Gets expression child (condition).
     * 
     * @return expression child (condition)
     * @see #setExpression
     */
    public Expression getExpression() {
        return getNodeGroups().getExpressionGroup().getExpression();
    }

    /**
     * Sets new expression child (condition).
     * 
     * @param expression new expression child (condition)
     * @see #getExpression
     */
    public void setExpression(Expression expression) {
        getNodeGroups().getExpressionGroup().setExpression(expression);
    }

    @Override
    public Cardinality[] getRequiredCardinalities(ValidationContext context, int index) {
        return new Cardinality[] { Cardinality.SINGLE };
    }

    @Override
    public BaseType[] getRequiredBaseTypes(ValidationContext context, int index) {
        return new BaseType[] { BaseType.BOOLEAN };
    }

    @Override
    public boolean evaluate(ProcessingContext context) throws QTIProcessingInterrupt, RuntimeValidationException {
        final Value value = getExpression().evaluate(context);

        if (value == null || value.isNull() || !((BooleanValue) value).booleanValue()) {
            return false;
        }

        super.evaluate(context);

        return true;
    }
}