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
						<a class="btn btn-outline-primary" data-toggle="modal" data-target="#add-modal">Add</a>
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

<div class="modal fade" id="add-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add Modal</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form method="post" action="books">
    	  <div class="modal-body">
       		<div class="form-group">
         		<label for="isbnInput">ISBN</label>
         		<input type="text" class="form-control" name="isbn" id="isbnInput" placeholder="isbn">
      		</div>
       		<div class="form-group">
         		<label for="titleInput">Title</label>
         		<input type="text" class="form-control" name="title" id="titleInput" placeholder="title">
       		</div>
       		<div class="form-group">
         		<label for="descrInput">Description</label>
         		<textarea class="form-control" name="descr" id="descrInput" placeholder="descr"></textarea>
       		</div>
       		
           </div>
           
           <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	       </div>
       </form>
    </div>
  </div>
</div>