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

package uk.ac.ed.ph.jqtiplus.value;

import uk.ac.ed.ph.jqtiplus.exception.QTIParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * row
 * <p>
 * col
 * <p>
 * rowgroup
 * <p>
 * colgroup
 *
 * @author Jiri Kajaba
 */
public enum TableCellScope
{
    /**
     * Row type.
     */
    ROW ("row"),

    /**
     * Col type.
     */
    COL ("col"),
    
    /**
     * Rowgroup type.
     */
    ROWGROUP ("rowgroup"),
    
    /**
     * Colgroup type.
     */
    COLGROUP ("colgroup");

    /** Name of this class in xml schema. */
    public static final String CLASS_TAG = "tableCellScope";

    private static Map<String, TableCellScope> types;

    static
    {
        types = new HashMap<String, TableCellScope>();

        for (TableCellScope type : TableCellScope.values())
            types.put(type.tableCellScope, type);
    }

    private String tableCellScope;

    private TableCellScope(String tableCellScopeType)
    {
        this.tableCellScope = tableCellScopeType;
    }

    /**
     * Returns true if this tableCellScope is row; false otherwise.
     *
     * @return true if this tableCellScope is row; false otherwise
     */
    public boolean isRow()
    {
        return this == ROW;
    }

    /**
     * Returns true if this tableCellScope is col; false otherwise.
     *
     * @return true if this tableCellScope is col; false otherwise
     */
    public boolean isCol()
    {
        return this == COL;
    }

    /**
     * Returns true if this tableCellScope is rowgroup; false otherwise.
     *
     * @return true if this tableCellScope is rowgroup; false otherwise
     */
    public boolean isRowgroup()
    {
        return this == ROWGROUP;
    }

    /**
     * Returns true if this tableCellScope is colgroup; false otherwise.
     *
     * @return true if this tableCellScope is colgroup; false otherwise
     */
    public boolean isColgroup()
    {
        return this == COLGROUP;
    }
    
    @Override
    public String toString()
    {
        return tableCellScope;
    }

    /**
     * Returns parsed <code>TableCellScopeType</code> from given <code>String</code>.
     *
     * @param tableCellScope <code>String</code> representation of <code>TableCellScopeType</code>
     * @return parsed <code>TableCellScopeType</code> from given <code>String</code>
     * @throws QTIParseException if given <code>String</code> is not valid <code>TableCellScopeType</code>
     */
    public static TableCellScope parseTableCellScope(String tableCellScope) throws QTIParseException
    {
        TableCellScope result = types.get(tableCellScope);

        if (result == null)
            throw new QTIParseException("Invalid " + CLASS_TAG + " '" + tableCellScope + "'.");

        return result;
    }

    /**
     * Returns intersection of two given types sets (order is not important).
     *
     * @param firstSet first set of types
     * @param secondSet second set of types
     * @return intersection of two given types sets
     */
    public static TableCellScope[] intersection(TableCellScope[] firstSet, TableCellScope[] secondSet)
    {
        List<TableCellScope> paramTypes = new ArrayList<TableCellScope>();

        for (TableCellScope type : firstSet)
        {
            if (Arrays.binarySearch(secondSet, type) >= 0)
                paramTypes.add(type);
        }

        return paramTypes.toArray(new TableCellScope[] {});
    }
}
