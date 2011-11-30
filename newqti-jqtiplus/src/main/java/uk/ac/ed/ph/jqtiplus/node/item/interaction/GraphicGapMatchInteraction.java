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

package uk.ac.ed.ph.jqtiplus.node.item.interaction;

import uk.ac.ed.ph.jqtiplus.control.AssessmentItemController;
import uk.ac.ed.ph.jqtiplus.control.ValidationContext;
import uk.ac.ed.ph.jqtiplus.group.item.interaction.choice.GapImgGroup;
import uk.ac.ed.ph.jqtiplus.group.item.interaction.graphic.AssociableHotspotGroup;
import uk.ac.ed.ph.jqtiplus.node.XmlNode;
import uk.ac.ed.ph.jqtiplus.node.XmlObject;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.GapChoice;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.GapImg;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.graphic.AssociableHotspot;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.graphic.AssociableHotspotContainer;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.ResponseDeclaration;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.validation.ValidationError;
import uk.ac.ed.ph.jqtiplus.validation.ValidationResult;
import uk.ac.ed.ph.jqtiplus.value.DirectedPairValue;
import uk.ac.ed.ph.jqtiplus.value.ListValue;
import uk.ac.ed.ph.jqtiplus.value.SingleValue;
import uk.ac.ed.ph.jqtiplus.value.Value;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * A graphic gap-match interaction is a graphical interaction with a set 
 * of gaps that are defined as areas (hotspots) of the graphic image and 
 * an additional set of gap choices that are defined outside the image. 
 * The candidate must associate the gap choices with the gaps in the image 
 * and be able to review the image with the gaps filled in context, as 
 * indicated by their choices. Care should be taken when designing these 
 * interactions to ensure that the gaps in the image are a suitable size 
 * to receive the required gap choices. It must be clear to the candidate 
 * which hotspot each choice has been associated with. When associated, 
 * choices must appear wholly inside the gaps if at all possible and, 
 * where overlaps are required, should not hide each other completely. 
 * If the candidate indicates the association by positioning the choice 
 * over the gap (e.g., drag and drop) the system should 'snap' it to the 
 * nearest position that satisfies these requirements.
 * 
 * The graphicGapMatchInteraction must be bound to a response variable 
 * with base-type directedPair and multiple cardinality. The choices 
 * represent the source of the pairing and the gaps in the image (the 
 * hotspots) the targets. Unlike the simple gapMatchInteraction, each 
 * gap can have several choices associated with it if desired, 
 * furthermore, the same choice may be associated with an 
 * associableHotspot multiple times, in which case the corresponding 
 * directed pair appears multiple times in the value of the response 
 * variable.
 * 
 * Contains : gapImg [1..*]
 * An ordered list of choices for filling the gaps. There may be fewer 
 * choices than gaps if required.
 * 
 * Contains : associableHotspot [1..*]
 * The hotspots that define the gaps that are to be filled by the 
 * candidate. If the delivery system does not support pointer-based 
 * selection then the order in which the gaps is given must be the order 
 * in which they are offered to the candidate for selection. For example, 
 * the 'tab order' in simple keyboard navigation. The default hotspot 
 * must not be defined.
 * 
 * @author Jonathon Hare
 *
 */
public class GraphicGapMatchInteraction extends GraphicInteraction implements AssociableHotspotContainer {
    private static final long serialVersionUID = 1L;

    /** Name of this class in xml schema. */
    public static String CLASS_TAG = "graphicGapMatchInteraction";
    
    /**
     * Constructs object.
     *
     * @param parent parent of constructed object
     */
    public GraphicGapMatchInteraction(XmlObject parent) {
        super(parent);

        getNodeGroups().add(new GapImgGroup(this, 1));
        getNodeGroups().add(new AssociableHotspotGroup(this, 1));
    }

    @Override
    public String getClassTag() {
        return CLASS_TAG;
    }

    /**
     * Gets an unmodifiable list of the child elements. Use the other
     * methods on ChoiceInteraction to add children to the correct group.
     */
    @Override
    public List<? extends XmlNode> getChildren() {
        List<XmlNode> children = new ArrayList<XmlNode>();
        
        children.addAll(super.getChildren());
        children.addAll(getNodeGroups().getGapImgGroup().getGapImgs());
        children.addAll(getNodeGroups().getAssociableHotspotGroup().getAssociableHotspots());
        
        return Collections.unmodifiableList(children);
    }

    /**
     * Gets gapImg children.
     *
     * @return gapImg children
     */
    public List<GapImg> getGapImgs()
    {
        return getNodeGroups().getGapImgGroup().getGapImgs();
    }
    
    /**
     * Gets associableHotspot children.
     *
     * @return associableHotspot children
     */
    public List<AssociableHotspot> getAssociableHotspots()
    {
        return getNodeGroups().getAssociableHotspotGroup().getAssociableHotspots();
    }
    
    @Override
    public ValidationResult validate(ValidationContext context) {
        ValidationResult result = super.validate(context);
        
        if (getResponseIdentifier() != null)
        {
            ResponseDeclaration declaration = getResponseDeclaration();
            if (declaration != null && declaration.getBaseType() != null && !declaration.getBaseType().isDirectedPair())
                result.add(new ValidationError(this, "Response variable must have directed pair base type"));
            
            if (declaration != null && declaration.getCardinality() != null && !declaration.getCardinality().isMultiple())
                result.add(new ValidationError(this, "Response variable must have multiple cardinality"));
        }
        
        return result;
    }
    
    @Override
    public boolean validateResponse(AssessmentItemController itemController, Value responseValue) {
        /* Extract response values */
        List<DirectedPairValue> responseAssociations = new ArrayList<DirectedPairValue>();
        if (responseValue.isNull()) {
            /* Empty response */
        }
        else if (responseValue.getCardinality().isList()) {
            /* (Container response) */
            for (SingleValue association : (ListValue) responseValue) {
                responseAssociations.add((DirectedPairValue) association);
            }
        }
        else {
            /* (Single response) */
            responseAssociations.add((DirectedPairValue) responseValue);
        }
        
        /* Create hashes that will track the number of associations for each <gapImg> */
        Map<Identifier, Integer> gapImgAssociationCounts = new HashMap<Identifier, Integer>();
        List<GapImg> gapImgs = getGapImgs();
        for (GapChoice gapImg : gapImgs) {
            gapImgAssociationCounts.put(gapImg.getIdentifier(), Integer.valueOf(0));
        }
        /* Same for each <associableChoice> */
        Set<Identifier> associableHotspotIdentifiers = new HashSet<Identifier>();
        Map<Identifier, Integer> responseAssociableHotspotCounts = new HashMap<Identifier, Integer>();
        List<AssociableHotspot> associableHotspots = getAssociableHotspots();
        getAssociableHotspots();
        for (AssociableHotspot hotspot : associableHotspots) {
            Identifier hotspotIdentifier = hotspot.getIdentifier();
            associableHotspotIdentifiers.add(hotspotIdentifier);
            responseAssociableHotspotCounts.put(hotspotIdentifier, Integer.valueOf(0));
        }
        /* Go through each association in the response and tally things up */
        for (DirectedPairValue responseAssociation : responseAssociations) {
            Identifier gapImgIdentifier = responseAssociation.sourceValue();
            Identifier hotspotIdentifier = responseAssociation.destValue();

            Integer count = responseAssociableHotspotCounts.get(hotspotIdentifier);
            if (count==null) { /* (Bad identifier in response) */
                return false;
            }
            responseAssociableHotspotCounts.put(hotspotIdentifier, count+1);
            
            count = gapImgAssociationCounts.get(gapImgIdentifier);
            if (count==null) { /* (Bad identifier in response) */
                return false;
            }
            gapImgAssociationCounts.put(gapImgIdentifier, count+1);
        }
        
        /* Make sure the correct number of associations were made to <gapImg> */
        for (GapChoice gapImg : gapImgs) {
            if (!validateChoice(gapImg, gapImgAssociationCounts.get(gapImg.getIdentifier()))) {
                return false;
            }
        }
        
        /* Same for <associableHotspot> */
        for (AssociableHotspot hotspot : associableHotspots) {
            if (!validateChoice(hotspot, responseAssociableHotspotCounts.get(hotspot.getIdentifier()))) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean validateChoice(GapChoice choice, int responseAssociateCount) {
        int matchMin = choice.getMatchMin();
        int matchMax = choice.getMatchMax();
        if (responseAssociateCount < matchMin) {
            return false;
        }
        else if (matchMax!=0 && responseAssociateCount > matchMax) {
            return false;
        }
        return true;
    }
    
    private boolean validateChoice(AssociableHotspot hotspot, int associateCount) {
        int matchMin = hotspot.getMatchMin();
        int matchMax = hotspot.getMatchMax();
        if (associateCount < matchMin) {
            return false;
        }
        else if (matchMax!=0 && associateCount > matchMax) {
            return false;
        }
        return true;
    }
}
