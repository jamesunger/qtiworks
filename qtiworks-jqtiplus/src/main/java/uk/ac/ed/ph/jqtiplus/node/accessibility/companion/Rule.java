/* Copyright (c) 2012-2013, University of Edinburgh.
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
 * This software is derived from (and contains code from) QTITools and MathAssessEngine.
 * QTITools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.jqtiplus.node.accessibility.companion;

import uk.ac.ed.ph.jqtiplus.group.accessibility.AccessibilityNode;
import uk.ac.ed.ph.jqtiplus.group.accessibility.companion.DescriptionGroup;
import uk.ac.ed.ph.jqtiplus.group.accessibility.companion.LinearRuleSystemGroup;
import uk.ac.ed.ph.jqtiplus.node.AbstractNode;

/**
 * FIXME: Document this type
 *
 * @author Zack Pierce
 */
public class Rule extends AbstractNode implements CompanionMaterial, AccessibilityNode {

    private static final long serialVersionUID = 7718173939741152563L;

    public static final String QTI_CLASS_NAME = "rule";

    public Rule(final CompanionMaterialsInfo parent) {
        super(parent, QTI_CLASS_NAME);
        getNodeGroups().add(new DescriptionGroup(this, true));
        getNodeGroups().add(new LinearRuleSystemGroup(this));
    }

    /**
     * Convenience for retrieving the Description's textual content.
     * @return
     */
    public String getDescriptionText() {
        return getNodeGroups().getDescriptionGroup().getDescriptionText();

    }

    /**
     * Convenience for setting the Description's textual content.
     * @param descriptionText
     */
    public void setDescriptionText(final String descriptionText) {
        ;
        getNodeGroups().getDescriptionGroup().setDescriptionText(descriptionText);
    }

    public RuleSystemSI getRuleSystemSI() {
        return getNodeGroups().getLinearRuleSystemGroup().getRuleSystemSI();
    }

    public RuleSystemUS getRuleSystemUS() {
        return getNodeGroups().getLinearRuleSystemGroup().getRuleSystemUS();
    }

}
