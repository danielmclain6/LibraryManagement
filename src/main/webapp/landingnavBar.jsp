<header>
<nav class="navbar navbar-expand-md navbar-dark align-items-center">
	<div class="container">
		<div class="row justify-content-between align-items-center">
		
		<div class="col-md-4">
		<a class="navbar-brand navbar-brand-img" href="/LibraryManager">
			<img alt="pumpkin" src="static/images/pumpkinWithHat.png">
		</a>
		</div>
		<div class="col-md-4 text-center mx-auto">
			<span class="navbar-text" id="landingNavSite"> Spooky Spooky Library</span>
		
		</div>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					
					<div class="col-md-4 text-right ml-auto">
			
					<ul class="navbar-nav">
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
						<div class="ml-auto">
							<span class="navbar-text ml-auto">Welcome!</span>
						</div>
					</c:if>
					</div>
			</div>
		</div>
	</div>
</nav>
</header>

