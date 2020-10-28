<%@ include file="header.jsp" %>
<%@ include file="navBar.jsp" %>

<c:if test = "${not empty errorMessage}">
	<div class="alert alert-danger" role="alert">
	  <c:out value="${errorMessage}"></c:out>
	   <button type="button" class="close" data-dismiss="alert">&times;</button>
	</div>
</c:if>

<div class="container">
    <h1>Login/Register</h1>
    <br>
    <br>
    <br>
    hello?
    <br>
    <div class="col border rounded m-2">
        <h1>Login</h1>
        <form method="post" action="">
            <div class="form-group">
              <label for="exampleInputEmail1">Email address</label>
              <input type="text" class="form-control" name="username"
              id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="username">
              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Password</label>
              <input type="password" class="form-control" name="password"
              id="exampleInputPassword1" placeholder="Password">
            </div>
            <!--  div class="form-check">
                <div class="row">
                    <input type="radio" name="user_type" value="patron" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Patron</label>
                </div>
                <div class="row">
                    <input type="radio" name="user_type" value="librarian" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Librarian</label>
                </div>
            </div-->
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
    </div>
    <div class="col border rounded m-2">
        <h1>Register</h1>
        <form method="post" action="register">
            <div class="form-group">
              <label for="exampleInputEmail1">Username</label>
              <input type="text" class="form-control" name="username"
              id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
              <label for="first_name">First Name</label>
              <input type="text" class="form-control" name="first_name"
              id="first_name" aria-describedby="first_name" placeholder="ex. Casper">
              <small id="first_name" class="form-text text-muted"></small>
            </div>
            <div class="form-group">
              <label for="exampleInputEmail1">LastName</label>
              <input type="text" class="form-control" name="last_name"
              id="last_name" aria-describedby="emailHelp" placeholder="ex. The Ghost">
              <small id="last_name" class="form-text text-muted">alsdk</small>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Password</label>
              <input type="password" class="form-control" name="password"
              id="exampleInputPassword1" placeholder="Password">
            </div>
            <div class="form-check">
              <div class="row">
                    <input type="radio" name="user_type" value="patron" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Patron</label>
                </div>
                <div class="row">
                    <input type="radio" name="user_type" value="librarian" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Librarian</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
    </div>
</div>


<%@ include file="footer.jsp" %>