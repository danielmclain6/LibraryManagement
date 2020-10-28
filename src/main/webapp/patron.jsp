
<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div class="container">
	<h1>Patrons page</h1>
	<div class="col m-3 border rounded">
		<h1>Patron page</h1>
		<p>
			id =
			<c:out value="${patron.id}"></c:out>
		</p>
		<p>
			first_name =
			<c:out value="${patron.first_name}"></c:out>
		</p>
		<p>
			last_name =
			<c:out value="${patron.last_name}"></c:out>
		</p>
		<p>
			username =
			<c:out value="${patron.username}"></c:out>
		</p>
		<p>
			password =
			<c:out value="${patron.password}"></c:out>
		</p>
		<p>
			account_frozen =
			<c:out value="${patron.account_frozen}"></c:out>
		</p>

		<c:if test="${patron.account_frozen && isLibrarian}">
			<a href="reactivate_account?id=${patron.id}">reactivate</a>
		</c:if>

		<c:if test="${!patron.account_frozen && isLibrarian}">
			<a href="freeze_account?id=${patron.id}">freeze account</a>
		</c:if>

	</div>
	<hr />
	<div class="col m-3 border rounded">
		<c:if test="${book == null}">
		no book checked out
	</c:if>
		<c:if test="${ book != null }">
			<p>
				isbn =
				<c:out value="${book.isbn}"></c:out>
			</p>
			<p>
				title =
				<c:out value="${book.title}"></c:out>
			</p>
			<p>
				descr =
				<c:out value="${book.descr}"></c:out>
			</p>
			<p>
				rented =
				<c:out value="${book.rented}"></c:out>
			</p>
			<p>
				added_to_library =
				<c:out value="${book.added_to_library}"></c:out>
			</p>
		</c:if>
	</div>

	<div class="col border rounded">
		<h1>History</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Checkout Date</th>
					<th scope="col">dueDate</th>
					<th scope="col">returned</th>
					<th scope="col">book isbn</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${ bookcheckouts.length == 0 }">
					<h1>No history</h1>
				</c:if>
				<c:if test="${ bookcheckouts.length != 0 }">

					<c:forEach val="history" items="bookcheckouts">
						<tr>
							<th scope="row"><c:out value="${ history.isbn }"></c:out></th>
							<td><c:out value="${ history.checkoutDate }"></c:out></td>
							<td><c:out value="${ history.due_date }"></c:out></td>
							<td><c:out value="${ history.isbn }"></c:out></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

</div>

<%@ include file="footer.jsp"%>

