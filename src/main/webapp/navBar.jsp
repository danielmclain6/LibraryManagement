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
					<span>Page Title</span>
				</div>
				<div class="col-md-4 ml-auto">
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
					
					<c:if test="${user != null}">
					<li class="nav-item nav-link">
						<a class="btn btn-outline-primary" href="/LibraryManager/logout">LogOut</a>
					</li>
					</ul>
					<div class="ml-auto">
    					<span class="navbar-text">
							Welcome, <c:out value="${user.username}"></c:out>!
						</span>
					</div>
					</c:if>
					
					<c:if test="${user == null}">
						<ul class="navbar-nav">
						<li class="nav-item nav-link">
							<a class="btn btn-outline-primary" href="/LibraryManager/login">LogIn</a>
						</li>
						</ul>
						<div class="ml-auto">
    						<span class="navbar-text ">Welcome!</span>
    					</div>
					</c:if>
					</div>
			</div>
		</div>
	</div>
</nav>


</header>
<div class="container">