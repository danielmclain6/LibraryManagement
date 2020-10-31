<%@ include file="header.jsp" %>
<%@ include file="landingnavBar.jsp" %>



	<%-- <c:if test="${user == null}">
		<a class="btn" href="login">Login</a>
	</c:if> --%>
	<!-- <a class="btn" href="books">Books</a> -->

	<div class='container'>
		<!-- <label for='login-button'>Login</label> -->
		<div class='row'>
			<div class='col'>

				<a class="btn login-button btn-lg" href="login" id='login-button'>Login</a>
			</div>
			<div class='col'>
				<a class="btn book-button btn-lg" href="books">Books</a>
			</div>
		</div>
	</div>




<%@ include file="footer.jsp"%>
