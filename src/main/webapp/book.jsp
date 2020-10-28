
<%@ include file="header.jsp" %>
Book
<p>isbn = <c:out value="${book.isbn}"></c:out></p>
<p>title = <c:out value="${book.title}"></c:out></p>
<p>descr = <c:out value="${book.descr}"></c:out></p>
<p>rented = <c:out value="${book.rented}"></c:out></p>
<p>added_to_library = <c:out value="${book.added_to_library}"></c:out></p>
<c:if test="${book.rented}">
    <a href="">Book not avaiable</a>
</c:if>
<c:if test="${!book.rented}">
    <a href="checkout_book?book_isbn=${book.isbn}&user_id=${user.id}">Checkout book</a>
</c:if>



<%@ include file="footer.jsp" %>
