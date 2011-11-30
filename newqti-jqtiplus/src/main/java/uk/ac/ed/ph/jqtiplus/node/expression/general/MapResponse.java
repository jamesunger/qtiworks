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

package uk.ac.ed.ph.jqtiplus.node.expression.general;

import uk.ac.ed.ph.jqtiplus.attribute.value.IdentifierAttribute;
import uk.ac.ed.ph.jqtiplus.control.ItemProcessingContext;
import uk.ac.ed.ph.jqtiplus.control.ItemValidationContext;
import uk.ac.ed.ph.jqtiplus.control.ProcessingContext;
import uk.ac.ed.ph.jqtiplus.control.ValidationContext;
import uk.ac.ed.ph.jqtiplus.node.expression.AbstractExpression;
import uk.ac.ed.ph.jqtiplus.node.expression.ExpressionParent;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.ResponseDeclaration;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.validation.AttributeValidationError;
import uk.ac.ed.ph.jqtiplus.validation.ValidationResult;
import uk.ac.ed.ph.jqtiplus.value.Value;


/**
 * This expression looks up the value of A response variable and then transforms it using the associated mapping,
 * which must have been declared. The result is A single float. If the response variable has single cardinality then
 * the value returned is simply the mapped target value from the map. If the response variable has multiple or ordered
 * cardinality then the value returned is the sum of the mapped target values. This expression cannot be applied to
 * variables of record cardinality.
 * <p>
 * For example, if A mapping associates the identifiers {A,B,C,D} with the values {0,1,0.5,0} respectively then
 * mapResponse will map the single value 'C' to the numeric value 0.5 and the set of values {C,B} to the value 1.5.
 * <p>
 * If A container contains multiple instances of the same value then that value is counted once only. To continue the
 * example above {B,B,C} would still map to 1.5 and not 2.5.
 * 
 * @author Jiri Kajaba
 * @author Jonathon Hare
 */
public class MapResponse extends AbstractExpression
{
    private static final long serialVersionUID = 1L;
    
    /** Name of this class in xml schema. */
    public static final String CLASS_TAG = "mapResponse";

    /** Name of identifier attribute in xml schema. */
    public static final String ATTR_IDENTIFIER_NAME = "identifier";
    
    /**
     * Constructs expression.
     *
     * @param parent parent of this expression
     */
    public MapResponse(ExpressionParent parent)
    {
        super(parent);
        
        getAttributes().add(new IdentifierAttribute(this, ATTR_IDENTIFIER_NAME));
    }

    @Override
    public String getClassTag()
    {
        return CLASS_TAG;
    }

    /**
     * Gets value of identifier attribute.
     *
     * @return value of identifier attribute
     * @see #setIdentifier
     */
    public Identifier getIdentifier()
    {
        return getAttributes().getIdentifierAttribute(ATTR_IDENTIFIER_NAME).getValue();
    }

    /**
     * Sets new value of identifier attribute.
     *
     * @param identifier new value of identifier attribute
     * @see #getIdentifier
     */
    public void setIdentifier(Identifier identifier)
    {
        getAttributes().getIdentifierAttribute(ATTR_IDENTIFIER_NAME).setValue(identifier);
    }
    
    @Override
    public ValidationResult validate(ValidationContext context) {
        ValidationResult result = super.validate(context);
        
        ItemValidationContext itemContext = (ItemValidationContext) context;
        AssessmentItem item = itemContext.getItem();
        if (item.getResponseDeclaration(getIdentifier()) == null) {
            result.add(new AttributeValidationError(getAttributes().get(ATTR_IDENTIFIER_NAME), 
                    "Cannot find response declaration: " + getIdentifier()));
        }
        else if (item.getResponseDeclaration(getIdentifier()).getMapping() == null) {
            result.add(new AttributeValidationError(getAttributes().get(ATTR_IDENTIFIER_NAME), 
                    "Cannot find mapping for response declaration: " + getIdentifier()));
        }
        
        return result;
    }

    @Override
    protected Value evaluateSelf(ProcessingContext context, int depth) {
        ItemProcessingContext itemContext = (ItemProcessingContext) context;
        ResponseDeclaration responseDeclaration = itemContext.getItem().getResponseDeclaration(getIdentifier());
        Value responseValue = itemContext.lookupVariable(getIdentifier());
        
        return responseDeclaration.getMapping().getTargetValue(responseValue);
    }
    
    @Override
    public boolean isVariable()
    {
        return true;
    }
}
