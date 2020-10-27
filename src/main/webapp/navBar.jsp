<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="LibraryManager/">
			<img alt="pumpkin" src="static/images/pumpkinWithHat.png" id="iconImage">
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
						<a class="btn btn-outline-primary" href="/books">Books</a>
					</li>
					<li class="nav-item nav-link">
						<a class="btn btn-outline-primary" href="/addBook">Add</a>
					</li>
					<li class="nav-item nav-link">
						<a class="btn btn-outline-primary" href="/">Patrons</a>
					</li>
				</ul>
				<div class="navbar-nav">
					<c:if test="${userId}">
						<a class="nav-item nav-link btn btn-outline-primary" href="/logout">LogOut</a>
					</c:if>
					<c:if test="${userId == null}">
						<a class="nav-item nav-link btn btn-outline-primary" href="/login">LogIn</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</nav>