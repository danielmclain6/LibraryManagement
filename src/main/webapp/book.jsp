<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div id="book_card" class="container-fluid">

	<div class="container">

		<div id="article">
			<p id="isbn" class="text-light">
				ISBN =
				<c:out value="${book.isbn}"></c:out>
			</p>
			<article class="shadow">

				<h2>
					<c:out value="${book.title}"></c:out>
				</h2>
				<p id="book_descr">
					<c:out value="${book.descr}"></c:out>
				</p>

				<div class="row d-flex justify-content-between">
					<%-- <p>
				<c:out value="${book.rented}"></c:out>
			</p> --%>
					<p>
						added to library: <br>
						<c:out value="${book.added_to_library}"></c:out>
					</p>
					<c:if test="${book.rented}">
						<a href="">Book not available</a>
					</c:if>
					<c:if test="${!book.rented && !isLibrarian}">
						<a href="checkout_book?book_isbn=${book.isbn}&user_id=${user.id}">
							<button class="btn btn-info shadow">Check Me Out</button>
						</a>
					</c:if>

					<c:if test="${isLibrarian}">
						<a href="edit_book?isbn=${book.isbn}"
						    data-toggle="modal"
							data-target="#editModal"><button class="btn btn-info">Edit</button></a>
						
						
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
					</c:if>
				</div>
			</article>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>
