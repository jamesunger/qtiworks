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

package uk.ac.ed.ph.jqtiplus.node.test;

import uk.ac.ed.ph.jqtiplus.attribute.value.BooleanAttribute;
import uk.ac.ed.ph.jqtiplus.attribute.value.IntegerAttribute;
import uk.ac.ed.ph.jqtiplus.control.ValidationContext;
import uk.ac.ed.ph.jqtiplus.node.AbstractObject;
import uk.ac.ed.ph.jqtiplus.validation.ValidationError;
import uk.ac.ed.ph.jqtiplus.validation.ValidationResult;


/**
 * The selection class specifies the rules used to select the child elements of A section for each test
 * session. If no selection rules are given we assume that all elements are to be selected.
 * 
 * @author Jiri Kajaba
 */
public class Selection extends AbstractObject {
    
    private static final long serialVersionUID = 1716825756388015143L;

    /** Name of this class in xml schema. */
    public static final String CLASS_TAG = "selection";

    /** Name of select attribute in xml schema. */
    public static final String ATTR_SELECT_NAME = "select";

    /** Name of withReplacement attribute in xml schema. */
    public static final String ATTR_WITH_REPLACEMENT_NAME = "withReplacement";
    /** Default value of withReplacement attribute. */
    public static final Boolean ATTR_WITH_REPLACEMENT_DEFAULT_VALUE = false;

    /**
     * Constructs object.
     *
     * @param parent parent of created object.
     */
    public Selection(AssessmentSection parent)
    {
        super(parent);

        getAttributes().add(new IntegerAttribute(this, ATTR_SELECT_NAME));
        getAttributes().add(new BooleanAttribute(this, ATTR_WITH_REPLACEMENT_NAME, ATTR_WITH_REPLACEMENT_DEFAULT_VALUE));
    }

    @Override
    public AssessmentSection getParent()
    {
        return (AssessmentSection) super.getParent();
    }

    @Override
    public String getClassTag()
    {
        return CLASS_TAG;
    }

    /**
     * Gets value of select attribute.
     *
     * @return value of select attribute
     * @see #setSelect
     */
    public Integer getSelect()
    {
        return getAttributes().getIntegerAttribute(ATTR_SELECT_NAME).getValue();
    }

    /**
     * Sets new value of select attribute.
     *
     * @param select new value of select attribute
     * @see #getSelect
     */
    public void setSelect(Integer select)
    {
        getAttributes().getIntegerAttribute(ATTR_SELECT_NAME).setValue(select);
    }

    /**
     * Gets value of withReplacement attribute.
     *
     * @return value of withReplacement attribute
     * @see #setWithReplacement
     */
    public Boolean getWithReplacement()
    {
        return getAttributes().getBooleanAttribute(ATTR_WITH_REPLACEMENT_NAME).getValue();
    }

    /**
     * Sets new value of withReplacement attribute.
     *
     * @param withReplacement new value of withReplacement attribute
     * @see #getWithReplacement
     */
    public void setWithReplacement(Boolean withReplacement)
    {
        getAttributes().getBooleanAttribute(ATTR_WITH_REPLACEMENT_NAME).setValue(withReplacement);
    }

    @Override
    protected ValidationResult validateAttributes(ValidationContext context)
    {
        ValidationResult result = super.validateAttributes(context);

        if (getParent() != null && getSelect() != null)
        {
            int required = 0;
            for (SectionPart part : getParent().getSectionParts())
                if (part.getRequired())
                    required++;

            if (getSelect() < required)
                result.add(new ValidationError(this, "Invalid selection. Required at least: " + required + ", but found: " + getSelect()));

            if (!getWithReplacement() && getSelect() > getParent().getSectionParts().size())
                result.add(new ValidationError(this, "Invalid selection. Required no more than: " + getParent().getSectionParts().size() + ", but found: " + getSelect()));
        }

        return result;
    }
}
