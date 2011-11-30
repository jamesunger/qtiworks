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

package uk.ac.ed.ph.jqtiplus.node.content.mathml;

import uk.ac.ed.ph.jqtiplus.attribute.Attribute;
import uk.ac.ed.ph.jqtiplus.attribute.value.StringAttribute;
import uk.ac.ed.ph.jqtiplus.control.JQTIController;
import uk.ac.ed.ph.jqtiplus.control.ValidationContext;
import uk.ac.ed.ph.jqtiplus.node.XmlNode;
import uk.ac.ed.ph.jqtiplus.node.XmlObject;
import uk.ac.ed.ph.jqtiplus.node.block.UnsupportedBlock;
import uk.ac.ed.ph.jqtiplus.node.content.BodyElement;
import uk.ac.ed.ph.jqtiplus.node.content.basic.BlockStatic;
import uk.ac.ed.ph.jqtiplus.node.content.basic.FlowStatic;
import uk.ac.ed.ph.jqtiplus.node.content.basic.InlineStatic;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.validation.ValidationResult;


import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * Implements a QTI MathML block. Only supports children in the form of UnsupportedBlock,
 * so validation of the content is left to the user. (Obviously xml schema validation 
 * could help!) 
 * 
 * @author Jonathon Hare
 *
 */
public class Math extends BodyElement implements BlockStatic, FlowStatic, InlineStatic {
    private static final long serialVersionUID = 1L;

    /** Name of this class in xml schema. */
    public static String CLASS_TAG = "math";
    
    /** Name of xmlns attribute in xml schema. */
    public static final String ATTR_DEFAULT_NAME_SPACE_NAME = "xmlns";
    /** Value of xmlns attribute. */
    public static final String ATTR_DEFAULT_NAME_SPACE_VALUE = "http://www.w3.org/1998/Math/MathML";
    
    /** Children of this block. */
    private List<XmlNode> children;

    /**
     * Constructs object.
     *
     * @param parent parent of constructed object
     */
    public Math(XmlObject parent) {
        super(parent);
    
        getAttributes().add(0, new StringAttribute(this, ATTR_DEFAULT_NAME_SPACE_NAME, ATTR_DEFAULT_NAME_SPACE_VALUE, null, true));
        
        children = new ArrayList<XmlNode>();
    }

    @Override
    public String getClassTag() {
        return CLASS_TAG;
    }

    @Override
    public List<? extends XmlNode> getChildren() {
        return children;
    }
    
    @Override
    protected void readChildren(JQTIController jqtiController, Element element)
    {
        children.clear();

        NodeList nodes = element.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++)
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE ||
                nodes.item(i).getNodeType() == Node.TEXT_NODE)
                readChildNode(jqtiController, nodes.item(i));
    }

    @Override
    protected void readChildNode(JQTIController jqtiController, Node node)
    {
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            UnsupportedBlock unsupportedBlock = new UnsupportedBlock(this, node.getLocalName());
            children.add(unsupportedBlock);
            unsupportedBlock.load(jqtiController, (Element) node);
        }
        else if (node.getNodeType() == Node.TEXT_NODE)
        {
            String textContent = node.getTextContent().trim();
            if (textContent.length() > 0)
            {
                TextRun textRun = new TextRun(this, textContent);
                children.add(textRun);
            }
        }
    }

    @Override
    protected String bodyToXmlString(int depth, boolean printDefaultAttributes)
    {
        StringBuilder builder = new StringBuilder();

        for (XmlNode child : children)
            builder.append(child.toXmlString(depth + 1, printDefaultAttributes));

        return builder.toString();
    }

    @Override
    public void load(JQTIController jqtiController, Element sourceElement) {
        super.load(jqtiController, sourceElement);
        
        if (getAttributes().getStringAttribute(ATTR_DEFAULT_NAME_SPACE_NAME).getValue() == null)
            getAttributes().getStringAttribute(ATTR_DEFAULT_NAME_SPACE_NAME).setValue(ATTR_DEFAULT_NAME_SPACE_VALUE);
    }

    @Override
    protected ValidationResult validateAttributes(ValidationContext context) {
        //mark all attributes as supported... we currently don't properly validate them
        for (Attribute attribute : getAttributes()) {
            attribute.setSupported(true);
        }
        
        return super.validateAttributes(context);
    }
}
