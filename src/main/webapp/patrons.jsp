<%@ include file="header.jsp"%>
<%@ include file="navBar.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-5">
			<c:forEach var="p" items="${patrons}">
				<hr />
				<a href="/LibraryManager/patrons?patron_id=${p.id}">
					<p>
						id =
						<c:out value="${p.id}"></c:out>
					</p>
					<p>
						first_name =
						<c:out value="${p.first_name}"></c:out>
					</p>
					<p>
						last_name =
						<c:out value="${p.last_name}"></c:out>
					</p>
					<p>
						username =
						<c:out value="${p.username}"></c:out>
					</p>
					<p>
						password =
						<c:out value="${p.password}"></c:out>
					</p>
					<p>
						account_frozen =
						<c:out value="${p.account_frozen}"></c:out>
					</p>
				</a>
				<hr />
			</c:forEach>
		</div>

		<div class="col-5">
			<c:if test="${patron == null }">
				<h1>Click on someone</h1>
			</c:if>
			<c:if test="${patron != null }">
				<h1>Chosen person</h1>
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
				<a href="/LibraryManager/patron?id=${patron.id}" class="btn btn-outline-primary">See Patron History</a>
				
				
				<c:if test="${patron.account_frozen}">
					<a href="/LibraryManager/freeze_patron?id=${patron.id}" class="btn btn-outline-success">
						UnFreeze</a>
				</c:if>
				<c:if test="${!patron.account_frozen}">
					<a href="/LibraryManager/freeze_patron?id=${patron.id}" class="btn btn-outline-danger"> Freeze </a>
				</c:if>
				
				<c:if test="${books == null}">
					user not currently checkout out book
				</c:if>
				<c:if test="${books != null}">
					<c:forEach var="book" items="${ books }">
						<a href="/LibraryManager/book?isbn=${book.isbn}" alt="${book.title}">Book <c:out value="${ book.title }" ></c:out></a>
					</c:forEach>
				</c:if>
				
			</c:if>


		</div>
	</div>
	<hr>
	<hr>
	<hr>
</div>
<%@ include file="footer.jsp"%>