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

package uk.ac.ed.ph.jqtiplus.node.item.response.processing;

import uk.ac.ed.ph.jqtiplus.control.ProcessingContext;
import uk.ac.ed.ph.jqtiplus.control.ValidationContext;
import uk.ac.ed.ph.jqtiplus.node.XmlObject;
import uk.ac.ed.ph.jqtiplus.node.outcome.declaration.LookupTable;
import uk.ac.ed.ph.jqtiplus.node.outcome.declaration.MatchTable;
import uk.ac.ed.ph.jqtiplus.node.outcome.declaration.OutcomeDeclaration;
import uk.ac.ed.ph.jqtiplus.validation.ValidationError;
import uk.ac.ed.ph.jqtiplus.validation.ValidationResult;
import uk.ac.ed.ph.jqtiplus.value.BaseType;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;
import uk.ac.ed.ph.jqtiplus.value.DurationValue;
import uk.ac.ed.ph.jqtiplus.value.FloatValue;
import uk.ac.ed.ph.jqtiplus.value.NumberValue;
import uk.ac.ed.ph.jqtiplus.value.Value;


/**
 * The lookupResponseValue rule sets the value of an response variable to the value obtained by looking up
 * the value of the associated expression in the lookupTable associated with the response's declaration.
 * 
 * @author Jonathon Hare
 */
public class LookupOutcomeValue extends ProcessResponseValue
{
    private static final long serialVersionUID = 1L;
    
    /** Name of this class in xml schema. */
    public static final String CLASS_TAG = "lookupOutcomeValue";

    /**
     * Constructs rule.
     *
     * @param parent parent of this rule
     */
    public LookupOutcomeValue(XmlObject parent)
    {
        super(parent);
    }

    @Override
    public String getClassTag()
    {
        return CLASS_TAG;
    }

    public Cardinality[] getRequiredCardinalities(ValidationContext context, int index)
    {
        return new Cardinality[] {Cardinality.SINGLE};
    }

    public BaseType[] getRequiredBaseTypes(ValidationContext context, int index)
    {
        if (getIdentifier() != null)
        {
            OutcomeDeclaration declaration = context.getOwner().getOutcomeDeclaration(getIdentifier());
            if (declaration != null && declaration.getLookupTable() != null)
            {
                if (declaration.getLookupTable() instanceof MatchTable)
                    return new BaseType[] {BaseType.INTEGER};
            }
        }

        return new BaseType[] {BaseType.INTEGER, BaseType.FLOAT, BaseType.DURATION};
    }

    @Override
    public ValidationResult validate(ValidationContext context)
    {
        ValidationResult result = super.validate(context);

        if (getIdentifier() != null)
        {
            OutcomeDeclaration declaration = context.getOwner().getOutcomeDeclaration(getIdentifier());
            if (declaration == null) {
                result.add(new ValidationError(this, "Cannot find " + OutcomeDeclaration.CLASS_TAG + ": " + getIdentifier()));
            }
            else if (declaration.getLookupTable() == null) {
                result.add(new ValidationError(this, "Cannot find any " + LookupTable.DISPLAY_NAME + " in " + OutcomeDeclaration.CLASS_TAG + ": " + getIdentifier()));
            }
        }

        return result;
    }

    @Override
    public void evaluate(ProcessingContext context) {
        Value value = getExpression().evaluate(context);
        NumberValue numberValue = null;
        if (!value.isNull()) {
            if (value.getBaseType().isDuration()) {
                value = new FloatValue(((DurationValue) value).doubleValue());
            }
            numberValue = (NumberValue) value;
        }

        OutcomeDeclaration declaration = context.getOwner().getOutcomeDeclaration(getIdentifier());
        context.setOutcomeValueFromLookupTable(declaration, numberValue);
    }
}
