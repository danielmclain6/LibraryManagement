<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="/LibraryManager">
			<img alt="pumpkin" src="static/images/pumpkinWithHat.png" style="height:50px;" id="iconImage">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="row justify-content-between">
				<ul class="navbar-nav">
					<li class="nav-item nav-link">
						<a class="btn btn-outline-primary" href="/LibraryManager/books">Books</a>
					</li>
					<c:if test="${isLibrarian}">
					<li class="nav-item nav-link">
						<a class="btn btn-outline-primary" href="/LibraryManager/addBook">Add</a>
					</li>
					<li class="nav-item nav-link">
						<a class="btn btn-outline-primary" href="/LibraryManager/patrons">Patrons</a>
					</li>
					</c:if>
				</ul>
				<div class="navbar-nav">
					<c:if test="${user != null}">
						welcome, <c:out value="${user.username}"></c:out>
						<a class="nav-item nav-link btn btn-outline-primary" href="/LibraryManager/logout">LogOut</a>
					</c:if>
					<c:if test="${user == null}">
						<a class="nav-item nav-link btn btn-outline-primary" href="/LibraryManager/login">LogIn</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</nav>