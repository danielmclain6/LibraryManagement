<%@ include file="header.jsp" %>
<%@ include file="navBar.jsp" %>

<div class="container">
    Index    
<br>
<br>
<br>
<br>
<c:if test="${user == null}">
	<a class="btn" href="login">Login</a>
</c:if>
<a class="btn" href="books">Books</a>

</div>



<%@ include file="footer.jsp" %>
