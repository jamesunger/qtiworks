<%--

This fragment formats the result of locating a QTI resource.

Copyright (c) 2012, The University of Edinburgh.
All Rights Reserved

--%>
<%@ tag body-content="empty" %>
<%@ taglib prefix="utils" uri="http://www.ph.ed.ac.uk/utils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="validator" tagdir="/WEB-INF/tags/validator" %>
<%@ attribute name="rootObjectLookup" required="true" type="uk.ac.ed.ph.jqtiplus.resolution.RootObjectLookup" %>

<c:set var="xmlResourceNotFoundException" value="${rootObjectLookup.notFoundException}"/>
<c:set var="qtiXmlInterpretationException" value="${rootObjectLookup.badResourceException}"/>
<c:choose>
  <c:when test="${xmlResourceNotFoundException==null}">
    <div class="resultPanel success">
      <h3>XML successfully found</h3>
      <div class="details">
        <p>
          The XML resource at path
          <b><c:out value="${utils:extractContentPackagePath(rootObjectLookup.systemId)}"/></b>
          was successfully located within your submitted content package.
        </p>
      </div>
    </div>
  </c:when>
  <c:otherwise>
    <div class="resultPanel failure">
      <h3>XML not found</h3>
      <div class="details">
        <p>
          We could not locate the XML resource at path
          <b><c:out value="${utils:extractContentPackagePath(rootObjectLookup.systemId)}"/></b>
          within your submitted content package.
        </p>
        <p>
          <strong>Note:</strong> We do not currently support loading arbitrary HTTP resources, though that feature
          will be added shortly. We also don't currently have the legacy QTI 2.0 responce processing templates loaded
          into the system.
        </p>
      </div>
    </div>
  </c:otherwise>
</c:choose>