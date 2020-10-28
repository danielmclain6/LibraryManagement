<%@ include file="header.jsp" %>
<%@ include file="navBar.jsp" %>

<div class="container">

    Books route
    <hr>
    <hr>
    <hr>
    <hr>
    <a href="/LibraryManager/books?which_filter=all_rented">All rented books</a>
    <a href="/LibraryManager/books?which_filter=all_available">all_available</a>
    <c:if test="${ isLibrarian }">
        I am a librarian
    </c:if>
    <c:if test="${ !isLibrarian }">
        I am a NOT librarian
    </c:if>
    <c:forEach var="book" items="${books}">
        <hr>
        <p>isbn = <c:out value="${book.isbn}"></c:out>
        </p>
        <p>title = <c:out value="${book.title}"></c:out>
        </p>
        <p>descr = <c:out value="${book.descr}"></c:out>
        </p>
        <p>rented = <c:out value="${book.rented}"></c:out>
        </p>
        <p>added_to_library = <c:out value="${book.added_to_library}"></c:out>
        </p>
        <a href="book?isbn=${book.isbn}" class="btn btn-outline-primary">BookDetails</a>
        <hr>

        <p class="text-danger">
            <c:out value="${isLibrarian}"></c:out>
        </p>
        <c:if test="${isLibrarian}">
            <c:if test="${book.rented}">
                <a href="return_book?isnb=${book.isbn}" class="btn btn-outline-primary">Book Returned</a>
            </c:if>
            <c:if test="${!book.rented}">
                <a href="edit_book?isnb=${book.isbn}" class="btn btn-outline-secondary">Edit</a>
                <a href="delete_book?isnb=${book.isbn}" class="btn btn-outline-danger">Delete Book</a>
            </c:if>
        </c:if>

        <c:if test="${!isLibrarian}">
            <c:if test="${book.rented}">
                <a href="checkout_book?isnb=${book.isbn}" class="btn btn-outline-primary">Cannot checkout</a>
            </c:if>
            <c:if test="${!book.rented}">
                <a href="checkout_book?isnb=${book.isbn}" class="btn btn-outline-primary">Checkout</a>
            </c:if>
        </c:if>
        <hr>
    </c:forEach>

</div>

<%@ include file="footer.jsp" %>