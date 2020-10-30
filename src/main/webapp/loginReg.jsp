<%@ include file="header.jsp" %>
<%@ include file="navBar.jsp" %>
<style><%@include file="/static/stylesheets/loginReg.css"%></style>

<c:if test = "${not empty errorMessage}">
	<div class="alert alert-danger" role="alert">
	  <c:out value="${errorMessage}"></c:out>
	   <button type="button" class="close" data-dismiss="alert">&times;</button>
	</div>
</c:if>

<div class="container">
  
    <br>
    <br>
    <br>
    <div class="row">
	    <div class="col border rounded m-2">
	        <h1>Login</h1>
	        <c:if test="${signinMsg}">
	        	<h3 class="text-danger">Please sign in to checkout a book</h3>
	        </c:if>
	        <form method="post" action="">
	            <div class="form-group">
	              <label class="text-white" for="exampleInputEmail1">Email address</label>
	              <input type="text" class="form-control" name="username"
	              id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="username">
	              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
	            </div>
	            <div class="form-group">
	              <label class="text-white" for="exampleInputPassword1">Password</label>
	              <input type="password" class="form-control" name="password"
	              id="exampleInputPassword1" placeholder="Password">
	            </div>

	            <button type="submit" class="btn btn-primary login-btn">Submit</button>
	          </form>
	    </div>
	    <div class="col-md-2"></div>
	    <div class="col border rounded m-2">
	        <h1>Register</h1>
	        <form method="post" action="register">
	            <div class="form-group">
	              <label class="text-white" for="exampleInputEmail1">Username</label>
	              <input type="text" class="form-control" name="username"
	              id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
	              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
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
	            <div class="form-check login-radio">
	              <div class="row">
	                    <input type="radio" name="user_type" value="patron" class="form-check-input" id="exampleCheck1" checked>
	                    <label class="form-check-label text-white" for="exampleCheck1">Patron</label>
	                </div>
	                <div class="row">
	                    <input type="radio" name="user_type" value="librarian" class="form-check-input" id="exampleCheck1">
	                    <label class="form-check-label text-white" for="exampleCheck1">Librarian</label>
	                </div>
	            </div>
	            <br>
	            <br>
	            <div class="row">
	          	  <button type="submit" class="btn btn-primary login-btn">Submit</button>
	          	</div>
	         </form>
	    </div>
    </div>
</div>


<%@ include file="footer.jsp" %>