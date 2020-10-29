
<%@ include file="header.jsp"%>

<div id="book_card" class="container-fluid">
	
	<div class="container">

		<div id="article">
			<p id="isbn" class="text-color-white">
				isbn =
				<c:out value="${book.isbn}"></c:out>
			</p>
			<article>

				<h2>
					<c:out value="${book.title}"></c:out>
				</h2>
				<p id="book_descr">
					<c:out value="${book.descr}"></c:out>
				</p>

				<div class="row d-flex justify-content-between">
					<%-- <p>
				<c:out value="${book.rented}"></c:out>
			</p> --%>
					<p>
						added to library: <br>
						<c:out value="${book.added_to_library}"></c:out>
					</p>
					<c:if test="${book.rented}">
						<a href="">Book not available</a>
					</c:if>
					<c:if test="${!book.rented}">
						<a href="checkout_book?book_isbn=${book.isbn}&user_id=${user.id}">
							<button class="btn btn-info">Check Me Out</button>
						</a>
					</c:if>
				</div>
			</article>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>
