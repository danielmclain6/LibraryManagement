<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>


	<div class="container" id="book_card">
		<div class="row justify-content-center">
			<div class="col-9 border rounded" id="article">
				<div class="row justify-content-center">
					<p class="text-end isbn">
						ISBN: <c:out value="${book.isbn}"></c:out>
					</p>
					<div class="col-8 frosty-bg" style="padding: 40px;">
						<h2 class="text-center ssp-xiketic"><c:out value="${book.title}"></c:out></h2>
						<p class="ssp-xiketic align-middle" id="book_descr">
							<c:out value="${book.descr}"></c:out>
						</p>
						<div class="row justify-content-between p-4">
							<div class="col">
								<p class="ssp-xiketic">
									added to library: <br>
									<c:out value="${book.added_to_library}"></c:out>
								</p>
							</div>
					<c:if test="${book.rented}">
						<div class="col">
							<a class="btn btn-static btn-outline-danger" href="">Book not available</a>
						</div>
					</c:if>
					<c:if test="${!book.rented && !isLibrarian}">
						<div class="col">
							<a href="checkout_book?book_isbn=${book.isbn}&user_id=${user.id}" 
							   class="btn btn-info shadow">Check Me Out
							</a>
						</div>
					</c:if>

					<c:if test="${isLibrarian}">
						<div class="col text-right">
							<a href="edit_book?isbn=${book.isbn}"
							    data-toggle="modal"
								data-target="#editModal" class="btn btn-info">Edit</a>
						</div>
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
					</div>
				</div>				
			
			</div>
		</div>

</div>

<%@ include file="footer.jsp"%>
