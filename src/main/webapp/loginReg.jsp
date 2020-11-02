<%@ include file="header.jsp" %>
<%@ include file="navBar.jsp" %>
<style><%@include file="/static/stylesheets/loginReg.css"%></style>

<c:if test = "${not empty errorMessage}">
	<div class="alert alert-danger" role="alert">
	  <c:out value="${errorMessage}"></c:out>
	   <button type="button" class="close" data-dismiss="alert">&times;</button>
	</div>
</c:if>

<div class="container" style="margin-top: -200px;">
    <div class="row justify-content-around">
	    <div class="col-5 border rounded p-3 frosty-bg">
	        <h1>Login</h1>
	        <c:if test="${signinMsg}">
	        	<h3 class="text-danger">Please sign in to checkout a book</h3>
	        </c:if>
	        <form method="post" action="">
	            <div class="form-group">
	              <label class="text-white" for="exampleInputEmail1">Username</label>
	              <input type="text" class="form-control" name="username"
	              id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="username">
	            </div>
	            <div class="form-group">
	              <label class="text-white" for="exampleInputPassword1">Password</label>
	              <input type="password" class="form-control" name="password"
	              id="exampleInputPassword1" placeholder="Password">
	            </div>
				<div class="row justify-content-end mt-2 mr-2">
		            <button type="submit" class="btn btn-primary">Submit</button>
				</div>
	          </form>
	    </div>
	    <div class="col-5 border rounded frosty-bg p-3">
	        <h1>Register</h1>
	        <form method="post" action="register">
	            <div class="form-group">
	              <label class="text-white" for="exampleInputEmail1">Username</label>
	              <input type="text" class="form-control" name="username"
	              id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
	              <small id="emailHelp" class="form-text text-muted">username needs to be unique</small>
	            </div>
	            <div class="form-group">
	              <label class="text-white" for="first_name">First Name</label>
	              <input type="text" class="form-control" name="first_name"
	              id="first_name" aria-describedby="first_name" placeholder="ex. Casper">
	              <small id="first_name" class="form-text text-muted"></small>
	            </div>
	            <div class="form-group">
	              <label class="text-white" for="exampleInputEmail1">Last Name</label>
	              <input type="text" class="form-control" name="last_name"
	              id="last_name" aria-describedby="emailHelp" placeholder="ex. The Ghost">
	              <small id="last_name" class="form-text text-muted"></small>
	            </div>
	            <div class="form-group">
	              <label class="text-white" for="exampleInputPassword1">Password</label>
	              <input type="password" class="form-control" name="password"
	              id="exampleInputPassword1" placeholder="Password">
	            </div>
	            <div class="form-check">
	              <div class="row ml-2">
	                    <input type="radio" name="user_type" value="patron" class="form-check-input" id="exampleCheck1" checked>
	                    <label class="form-check-label text-white" for="exampleCheck1">Patron</label>
	                </div>
	                <div class="row ml-2">
	                    <input type="radio" name="user_type" value="librarian" class="form-check-input" id="exampleCheck1">
	                    <label class="form-check-label text-white" for="exampleCheck1">Librarian</label>
	                </div>
	            </div>
	            <div class="row justify-content-end m-2">
	          	  <button type="submit" class="btn btn-primary">Submit</button>
	          	</div>
	         </form>
	    </div>
    </div>
</div>


<%@ include file="footer.jsp" %>