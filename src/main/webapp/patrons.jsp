<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-5 border rounded m-1">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">id</th>
						<th scope="col">username</th>
						<th scope="col">Is Frozen</th>
						<th scope="col">BooksCheckout</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${patrons}">
						<tr>
							<td>
								<a href="/LibraryManager/patrons?patron_id=${p.id}">
									<c:out value="${p.id}"></c:out>
							</td>
							</a>
							<td>
								<c:out value="${p.username}"></c:out>
							</td>
							<td>
								<c:out value="${p.account_frozen}"></c:out>
							</td>
							<td>arraylength</td>
							</a>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="col-5 border rounded m-1">
			<c:if test="${patron == null }">
				<h1>Click on someone</h1>
			</c:if>
			<c:if test="${patron != null }">
				<div class="row">
					<img src="static/images/treesAndPumpkin.jpg" alt="halloween" id="small-prof-pic">
					<h2>
						<c:out value="${patron.username}"></c:out>
					</h2>
					<div class="col">
						<c:out value="${patron.first_name}"></c:out>
						<c:out value="${patron.last_name}"></c:out>
						<a href="/LibraryManager/patron?id=${patron.id}" class="btn btn-outline-primary">See Patron
							History</a>
						<c:if test="${patron.account_frozen}">
							<a href="/LibraryManager/freeze_patron?id=${patron.id}" class="btn btn-outline-success">
								UnFreeze</a>
						</c:if>
						<c:if test="${!patron.account_frozen}">
							<a href="/LibraryManager/freeze_patron?id=${patron.id}" class="btn btn-outline-danger">
								Freeze </a>
						</c:if>

					</div>
				</div>
				<div class="row">
					<c:if test="${books == null}">
						user not currently checkout out book
					</c:if>
					<c:if test="${books != null}">

						<p>Books in <c:out value="${patron.username}"></c:out> has checkout</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">First</th>
									<th scope="col">Last</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="book" items="${ books }">
									<tr>
										<th scope="row">somethign</th>
										<td><a href="/LibraryManager/book?isbn=${book.isbn}" alt="${book.title}">see book </a></td>
										<td><c:out value="${ book.title }"></c:out>/td>
									</tr>
									
								</c:forEach>
							</tbody>
						</table>

					</c:if>
				</div>
			</c:if>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>