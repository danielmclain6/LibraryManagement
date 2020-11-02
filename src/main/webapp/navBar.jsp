<header>
<nav class="navbar navbar-expand-sm navbar-dark">
	<div class="container">
		<div class="row justify-content-between align-items-center">
		<div class="col-md-4">
		<a class="navbar-brand navbar-brand-img" href="/LibraryManager">
			<img alt="pumpkin" src="static/images/pumpkinWithHat.png">

		</a>
		<a href="index.jsp">
			<span class="navbar-text text-center display-2" id="navSite"> Spooky Spooky Library</span>
		</a>
		</div>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">

				<div class="col-md-4 mx-auto">
					  %><span>Page Title</span>
				</div>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>


				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<c:set var="context" value="${pageContext.request.requestURI}" />
					<c:out value="${context}"/>

					<c:if test="${context == '/LibraryManager/loginReg.jsp'}">
						<div class="col-md-4 mx-auto">
							<span>Login Page</span>
						</div>
					</c:if>
					<div class="col-md-4 ml-auto">
						<ul class="navbar-nav">
							<li class="nav-item nav-link"><a
									class="btn btn-outline-primary" href="/LibraryManager/books">Books</a>
							</li>


							<c:if test="${user!=null && !isLibrarian}">
								<li class="nav-item nav-link"><a class="btn btn-secondary"
										href="/LibraryManager/patron?id=${user.id}">My Profile</a></li>
							</c:if>
							<c:if test="${isLibrarian && user!=null}">

								<li class="nav-item nav-link"><a class="btn btn-secondary"
										href="/LibraryManager/patrons">Patrons</a></li>

								<li class="nav-item nav-link"><a class="btn btn-secondary"
										data-toggle="modal" data-target="#add-modal">Add Book</a></li>
							</c:if>
						</ul>
						<c:if test="${user == null}">
							<ul class="navbar-nav">
								<li class="nav-item nav-link"><a
										class="btn btn-outline-primary" href="/LibraryManager/login">LogIn</a>
								</li>
							</ul>
							<div class="ml-auto">
								<span class="navbar-text">Welcome!</span>
							</div>
						</c:if>

						<!-- class="col navbar-nav" -->
						<c:if test="${user != null}">
							<ul class="navbar-nav">
								<li class="nav-item nav-link"><a
										class="btn btn-outline-primary" href="/LibraryManager/logout">Log
										Out</a></li>
							</ul>
							<span class="navbar-text">Welcome, <c:out
									value="${user.username}"></c:out>
						</c:if>

					</div>
				</div>
			</div>


			<div class="modal fade" id="add-modal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Add Modal</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form method="post" action="books?action=add">
							<div class="modal-body">
								<div class="form-group">
									<label for="isbnInput">ISBN</label>
									<input type="text" class="form-control" name="isbn"
										id="isbnInput" placeholder="isbn" pattern=".{10,10}"
										title="ISBN must be 10 characters">
								</div>
								<div class="form-group">
									<label for="titleInput">Title</label>
									<input type="text" class="form-control" name="title"
										id="titleInput" placeholder="title">
								</div>
								<div class="form-group">
									<label for="descrInput">Description</label>
									<textarea class="form-control" name="descr" id="descrInput"
										placeholder="descr"></textarea>
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
	</nav>
	<%@ include file="googyHeader.jsp"%>

</header>

