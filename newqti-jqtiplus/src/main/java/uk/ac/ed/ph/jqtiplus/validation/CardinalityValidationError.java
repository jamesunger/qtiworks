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

package uk.ac.ed.ph.jqtiplus.validation;

import uk.ac.ed.ph.jqtiplus.exception.QTICardinalityException;
import uk.ac.ed.ph.jqtiplus.node.XmlNode;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;

import java.util.Arrays;


/**
 * Error validation item for cardinality problems.
 * 
 * @author Jiri Kajaba
 */
public class CardinalityValidationError extends ValidationError
{
    /**
     * Constructs validation item.
     *
     * @param source source node of constructed item
     * @param expected expected cardinalities
     * @param found found cardinalities
     */
    public CardinalityValidationError(XmlNode source, Cardinality[] expected, Cardinality[] found)
    {
        super(source, createMessage(expected, found));
    }

    @Override
    public QTICardinalityException createException()
    {
        return new QTICardinalityException(getMessage());
    }

    private static String createMessage(Cardinality[] expected, Cardinality[] found)
    {
        return "Invalid " + Cardinality.CLASS_TAG + ". Expected " + Arrays.toString(expected) + ", but found " + Arrays.toString(found) + ".";
    }
}
