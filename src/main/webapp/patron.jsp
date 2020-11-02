<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div class="container" style="margin-top:-200px;">
	<div class="row justify-content-around">
	
		<div id="patron_card" class="col-5 p-5 rounded">
			<img alt="pumpkin" src="static/images/pumpkinWithHat.png"
				style="height: 110px;" id="profile_picture">

			<div class="container">

				<div id="patron_article">

					<%-- <p id="patron_id" class="text-light" ; style="text-align: center">
						Username:
						<c:out value="${patron.username}"></c:out>
					</p> --%>
					<article class="shadow">
						<h5>

							<c:out value="${patron.first_name}"></c:out>
							<c:out value="${patron.last_name}"></c:out>
						</h5>

						<%-- <p class="pt-5"style="text-align: center">
							Currently Reading: <br>

							<c:out value="${book.title}"></c:out>
						</p> --%>
						<br>
						<p style="text-align: right">
							Account:
							<c:if
								test="${patron.account_frozen == true || patron.account_frozen == null }">
								<span style="color: red"> Deactivated </span>
							</c:if>

							<c:if test="${patron.account_frozen == false}">
								<span style="color: green"> Active </span>

							</c:if>
						</p>


						<a href="freeze_patron?id=${patron.id}">
							<c:if
								test="${patron.account_frozen == true || patron.account_frozen == null && isLibrarian}">
								<button type="button" class="btn btn-success btn">
									<span style="color: black"> Activate</span>
								</button>
							</c:if>
							<c:if test="${patron.account_frozen == false && isLibrarian}">

								<button type="button" class="btn btn-danger btn">
									<span style="color: black"> Deactivate</span>
								</button>
							</c:if>
						</a>

					</article>
				</div>
			</div>
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
				<c:if test="${ currentbooks.size() ==0 }">
					<tr><td class="text-white font-weight-bold">No checkouts currently</td></tr>
				</c:if>

				<c:if test="${ currentbooks.size() > 1 ||currentbooks != null }">
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
				<c:if test="${ historybookcheckouts.size() == 0 }">
					<tr><td class="text-white font-weight-bold">No checkout history</td></tr>
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