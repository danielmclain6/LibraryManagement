<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div class="container pt-5">
	<div class="row justify-content-around">
		<div class="col-5 border rounded p-5">
			<h1>My Profile</h1>
			<hr>
			<p>
				Name:
				<c:out value="${patron.first_name}"></c:out>
				<c:out value="${patron.last_name}"></c:out>
			</p>

			<p>
				Username:
				<c:out value="${patron.username}"></c:out>
			</p>
			<p>
				password:
				<c:out value="${patron.password}"></c:out>
			</p>
			<p>
				account_frozen:
				<c:out value="${patron.account_frozen}"></c:out>
			</p>

			<a href="freeze_account?id=${patron.id}">
				<c:if test="${patron.account_frozen && isLibrarian}">
				Reactivate
			</c:if>
				<c:if test="${!patron.account_frozen && isLibrarian}">
				Freeze Account
			</c:if>
			</a>
		</div>
		<img class="col-sm-5 rounded" alt="books image"
			src="./static/images/pumpkinPatch.jpg">
	</div>
	<hr />
	<div class="col mb-4 border rounded p-4 bg-secondary">
		<h2 class="text-white">Current Checkouts</h2>

		<table class="table">
			<thead>
				<tr>
					<th class="text-white" scope="col">Title</th>
					<th class="text-white" scope="col">Book Description</th>
					<th class="text-white" scope="col">Due Date</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${ currentbooks == null }">
					<h4>No checkouts currently</h4>
				</c:if>

				<c:if test="${ currentbooks != null }">
					<c:forEach var="book" items="${currentbooks}">
						<tr>
							<th scope="row" class="text-white">
								<c:out value="${ book.book.title }"></c:out>
							</th>
							<td class="text-white">
								<c:out value="${ book.book.descr }"></c:out>
							</td>
							<td class="text-white">
								<c:out value="${ book.due_date }"></c:out>
							</td>

						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>



	</div>

	<div class="col bg-secondary border rounded p-4">
		<h2 class="text-white">History</h2>
		<table class="table">
			<thead>
				<tr>
					<th class="text-white" scope="col">ISBN</th>
					<th class="text-white" scope="col">Checkout Date</th>
					<th class="text-white" scope="col">Due Date</th>
					<th class="text-white" scope="col">Returned Date</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${ bookcheckouts.size() == 0 }">
					<h1>No history</h1>
				</c:if>
				<c:if test="${ historybookcheckouts.size() != 0 }">
					<c:forEach var="history" items="${historybookcheckouts}">

						<tr>
							<th scope="row" class="text-white">
								<c:out value="${ history.isbn }"></c:out>
							</th>
							<td class="text-white">
								<c:out value="${history.checkedout}"></c:out>
							</td>
							<td class="text-white">
								<c:out value="${history.due_date}"></c:out>
							</td>


							<c:if test="${history.returned == null }">
								<td class="text-danger">Not returned</td>
							</c:if>

							<c:if test="${history.returned != null }">
								<td class="text-white">
									<c:out value="${history.returned}"></c:out>
								</td>
							</c:if>


						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

</div>

<%@ include file="footer.jsp"%>