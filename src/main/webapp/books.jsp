<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div class="container" style="margin-top:-200px;">

<div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style="width: 500px;
	margin: 0 auto;">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="static/images/pumpkin.jpg" class="d-block w-100" alt="pumpkin">
    </div>
    <div class="carousel-item">
      <img src="static/images/image1.jpg" class="d-block w-100" alt="spooky desk">
    </div>
    <div class="carousel-item">
      <img src="static/images/jackolanterns.jpg" class="d-block w-100" alt="jacko lanterns ">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

	<div class="dropdown ml-3">
  <button class="btn btn-secondary dropdown-toggle" type="button" 
  id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" 
  aria-expanded="false">
    Filter
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	<a class="dropdown-item" href="/LibraryManager/books?which_filter=all">All Books</a>
	<a class="dropdown-item" href="/LibraryManager/books?which_filter=all_rented">Rented Books</a>
	<a class="dropdown-item" href="/LibraryManager/books?which_filter=all_available">Available Books</a>
  </div>
</div>
	<div class="row justify-content-around">
		<c:forEach var="book" items="${books}">
			<div class="col-sm-3 m-1 books_card">
				<h4 class="text-white">
					<c:out value="${book.title}"></c:out>
				</h4>
				
				<img alt="book image" src="./static/images/book.png">
				<p class="descr text-white">
					<c:out value="${book.descr}" ></c:out> 
				</p>
				<a class="btn btn-outline-secondary" href="book?isbn=${book.isbn}">Book Details</a>
				<hr>

				<%-- <p class="text-danger">
					<c:out value="${isLibrarian}"></c:out>
				</p> --%>
				<c:if test="${isLibrarian}">
					<c:if test="${book.rented}">
						<a href="return_book?isbn=${book.isbn}"
							class="btn btn-outline-primary">Mark as Returned</a>
						
					</c:if>
					<c:if test="${!book.rented}">
						<a href="edit_book?isbn=${book.isbn}"
							class="btn btn-outline-secondary" data-toggle="modal"
							data-target="#editModal">Edit</a>
						<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Edit Modal</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form method="post" action="books?action=edit">
										<div class="modal-body">
											<div class="form-group">
												<input type="hidden" class="form-control" name="isbn"
													id="isbnInput" placeholder="isbn" value="${book.isbn}">
											</div>
											<div class="form-group">
												<label for="titleInput">Title</label>
												<input type="text" class="form-control" name="title"
													id="titleInput" placeholder="title" value="${book.title}">
											</div>
											<div class="form-group">
												<label for="descrInput">Description</label>
												<textarea class="form-control" name="descr" id="descrInput"
													placeholder="descr"><c:out value="${book.descr}"></c:out></textarea>
											</div>
											
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-primary">Save
												changes</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- 
						 <a href="delete_book?isnb=${book.isbn}"
							class="btn btn-outline-danger">Delete Book</a>
						 -->
						
					</c:if>
				</c:if>

				<c:if test="${!isLibrarian}">
					<c:if test="${book.rented}">
						<a href="" class="btn btn-danger">Book Unavailable</a>
					</c:if>
					<c:if test="${!book.rented}">
						<a href="checkout_book?book_isbn=${book.isbn}"
							class="btn-checkout">Checkout</a>
					</c:if>
				</c:if>
			</div>
		</c:forEach>


	</div>

</div>



<%@ include file="footer.jsp"%>