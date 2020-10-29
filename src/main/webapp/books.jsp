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
                <a href="return_book?isbn=${book.isbn}" class="btn btn-outline-primary">Mark as Returned</a>
            </c:if>
            <c:if test="${!book.rented}">
                <a href="edit_book?isbn=${book.isbn}" class="btn btn-outline-secondary" data-toggle="modal" data-target="#editModal">Edit</a>
                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Edit Modal</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form method="post" action="books?action=edit">
				    	  <div class="modal-body">
				       		<div class="form-group">
				         		<input type="hidden" class="form-control" name="isbn" id="isbnInput" placeholder="isbn" value="${book.isbn}">
				      		</div>
				       		<div class="form-group">
				         		<label for="titleInput">Title</label>
				         		<input type="text" class="form-control" name="title" id="titleInput" placeholder="title" value="${book.title}">
				       		</div>
				       		<div class="form-group">
				         		<label for="descrInput">Description</label>
				         		<textarea class="form-control" name="descr" id="descrInput" placeholder="descr"><c:out value="${book.descr}"></c:out></textarea>
				       		</div>
				       		
				           </div>
				           
				           <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					        <button type="submit" class="btn btn-primary">Save changes</button>
					       </div>
				       </form>
				    </div>
				  </div>
				</div>
                <a href="delete_book?isnb=${book.isbn}" class="btn btn-outline-danger">Delete Book</a>
            </c:if>
        </c:if>

        <c:if test="${!isLibrarian}">
            <c:if test="${book.rented}">
                <a href="" class="btn btn-outline-primary">Book Unavailable</a>
            </c:if>
            <c:if test="${!book.rented && !isFrozen}">
                <a href="checkout_book?isbn=${book.isbn}" class="btn btn-outline-primary">Checkout</a>
            </c:if>
            <c:if test="${isFrozen}"><p>Checkout not available, your account is pending approval</p></c:if>
        </c:if>
        <hr>
    </c:forEach>

</div>



<%@ include file="footer.jsp" %>