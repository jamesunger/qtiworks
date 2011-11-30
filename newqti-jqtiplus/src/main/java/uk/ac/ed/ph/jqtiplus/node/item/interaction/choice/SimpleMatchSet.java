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

package uk.ac.ed.ph.jqtiplus.node.item.interaction.choice;


import uk.ac.ed.ph.jqtiplus.group.item.interaction.choice.SimpleAssociableChoiceGroup;
import uk.ac.ed.ph.jqtiplus.node.AbstractObject;
import uk.ac.ed.ph.jqtiplus.node.XmlObject;

import java.util.List;

/**
 * Contains : simpleAssociableChoice [*]
 * An ordered set of choices for the set.
 * 
 * @author Jonathon Hare
 *
 */
public class SimpleMatchSet extends AbstractObject implements SimpleAssociableChoiceContainer {
    private static final long serialVersionUID = 1L;

    /** Name of this class in xml schema. */
    public static final String CLASS_TAG = "simpleMatchSet";
    
    /**
     * Constructs object.
     *
     * @param parent parent of constructed object
     */
    public SimpleMatchSet(XmlObject parent) {
        super(parent);

        getNodeGroups().add(new SimpleAssociableChoiceGroup(this));
    }

    /**
     * Gets simpleAssociableChoice children.
     *
     * @return simpleAssociableChoice children
     */
    public List<SimpleAssociableChoice> getSimpleAssociableChoices() {
        return getNodeGroups().getSimpleAssociableChoiceGroup().getSimpleAssociableChoices();
    }
    
    @Override
    public String getClassTag() {
        return CLASS_TAG;
    }
}
