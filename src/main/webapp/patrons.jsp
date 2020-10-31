<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div class="container pt-5">
	<div class="row justify-content-around">
		<div class="col-sm-5 border rounded m-1 frosty-bg" id="patrons-table">

			<div class="dropdown m-1">
				<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
					id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Patrons </a>

				<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<a class="dropdown-item"
						href="/LibraryManager/patrons?which_filter=all">All Patrons</a>
					<a class="dropdown-item"
						href="/LibraryManager/patrons?which_filter=all_frozen">Frozen
						Patrons</a>
					<a class="dropdown-item"
						href="/LibraryManager/patrons?which_filter=all_available">Active
						Patrons</a>
				</div>
			</div>

			<table class="table overflow-auto">
				<thead class="thead-dark">
					<tr>
						<th scope="col">id</th>
						<th scope="col">username</th>
						<th scope="col">Is Frozen</th>
						<!--<th scope="col"># of Books</th>-->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${patrons}">

						<c:if test="${p.account_frozen}">
							<tr class="table-danger">
						</c:if>
						<c:if test="${!p.account_frozen}">
							<tr>
						</c:if>
						<c:if test="${filter eq 'all' or filter eq null}">
							<td>
								<a
									href="/LibraryManager/patrons?patron_id=${p.id}&which_filter=all">
									<c:out value="${p.id}"></c:out>
								</a>
							</td>
						</c:if>
						<c:if test="${filter eq 'all_available'}">
							<td>
								<a
									href="/LibraryManager/patrons?patron_id=${p.id}&which_filter=all_available">
									<c:out value="${p.id}"></c:out>
								</a>
							</td>
						</c:if>
						<c:if test="${filter eq 'all_frozen'}">
							<td>
								<a
									href="/LibraryManager/patrons?patron_id=${p.id}&which_filter=all_frozen">
									<c:out value="${p.id}"></c:out>
								</a>
							</td>
						</c:if>

						<td>
							<c:out value="${p.username}"></c:out>
						</td>
						<td>
							<c:out value="${p.account_frozen}"></c:out>
						</td>
						<!-- <td>1234</td> -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="col-sm-5 m-1">
			<div class="row text-center justify-content-center"
				id="preview-patron-row">
				<div class="col-12 border rounded preview-parton frosty-bg pb-4">
					<c:if test="${patron == null }">
						<h1>Click on someone</h1>
					</c:if>
					<c:if test="${patron != null }">
						<h2>
							<c:out value="${patron.username}"></c:out>
						</h2>
						<div class="row justify-content-center text-center">
							<div class="col-10 border rounded" id="preview-patron-info-card">
								<div class="row">
									<c:if test="${ patron.account_frozen }">
										<img class="frozen-overlay"
											src="static/images/treesAndPumpkin.jpg" alt="halloween"
											id="small-prof-pic">
									</c:if>
									<c:if test="${ !patron.account_frozen }">
										<img src="static/images/treesAndPumpkin.jpg" alt="halloween"
											id="small-prof-pic">
									</c:if>
									<div class="col text-center">
										<p>
											<c:out value="${patron.first_name}"></c:out>
											<c:out value="${patron.last_name}"></c:out>
										</p>
										<p>Account:</p>
										<c:if test="${patron.account_frozen}">
											<p class="text-danger">FROZEN</p>
										</c:if>
										<c:if test="${!patron.account_frozen}">
											<p class="text-success">ACTIVE</p>
										</c:if>
									</div>
								</div>
								<div class="row justify-content-around"
									id="preview-patron-card-row">
									<a href="/LibraryManager/patron?id=${patron.id}"
										class="btn btn-sm btn-primary">See Account</a>
									<c:if test="${patron.account_frozen}">
										<a href="/LibraryManager/freeze_patron?id=${patron.id}"
											class="btn btn-sm btn-success"> UnFreeze</a>
									</c:if>
									<c:if test="${!patron.account_frozen}">
										<a href="/LibraryManager/freeze_patron?id=${patron.id}"
											class="btn btn-sm btn-outline-danger"> Freeze</a>
									</c:if>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>




			<div class="row justify-content-center mt-4">
				<div class="col-12 pt-3 border rounded preview-parton frosty-bg">
					<c:if test="${ patron == null }">
						<img src="static/images/bats.png" alt="halloween" />
						<img src="static/images/images.png" alt="halloween" />
					</c:if>
					<c:if test="${ patron != null }">
						<c:if test="${ books == null }">
							<c:out value="${patron.username }"></c:out> Does not have any checked out books
						</c:if>
						<c:if test="${ books != null }">
							<h4>
								<strong><c:out value="${patron.first_name }"></c:out>'s</strong>
								Checkout History

							</h4>
							<table class="table">
								<thead class="thead-dark">
									<tr>
										<th scope="col">Title</th>
										<th scope="col">DueDate</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${ books }">
										<tr>
											<td scope="row">
												<a href="/LibraryManager/book?isbn=${book.book.isbn}"
													alt="${book.book.title}">
													<c:out value="${ book.book.title }"></c:out>
												</a>
											</td>
											<td>
												<c:out value="${ book.due_date }"></c:out>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>