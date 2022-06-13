<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Book Lister</title>

<script type="text/javascript">

function deleteLinkOnClick(){
	
	var answer = confirm("Do you really want to delete the book?");

	if (answer){
	
		console.log('user has pressed ok/yes')
	}else{
		console.log('user has pressed no/cancel')
	}	
	
	return answer;
}

</script>

</head>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<body>


	

	<h3>Listing all the books</h3>
	
	<hr>
	
	
	<a href = "/LibraryManagement2/books/begin-add" 
		class = "btn btn-primary btn-sm-mb-3">Add New Book</a>
	
	<div class="container">

		<form action="/LibraryManagement2/books/search" class="form-inline">
			
			<input type="search" name = "name" placeholder = "For book name" class="form-control-sm m1-5 mr-2 mb-3" />
			<input type="search" name = "author" placeholder = "For book's author" class="form-control-sm m1-5 mr-2 mb-3">
			
			<button type = "submit" class = "btn btn-success btn-sm mb-3">Search Books</button>
			
		</form>
	
	</div>

	<div class="container">

		<table class = "table table-bordered table-striped">
			
			<thead class = "thead-dark">
				<tr>
					<th>Name</th>
					<th>Author</th>	
					<th>Category</th>	
					<th>Action</th>
				</tr>
			</thead>
			
			<tbody>					
				
				<core:forEach items = "${bookCollection}" var = "aBookObject" >
				
					<tr>
						<td><core:out value = "${aBookObject.name}" /></td>
						<td><core:out value = "${aBookObject.author}" /></td>
						<td><core:out value = "${aBookObject.category}" /></td>	
						
						<td>

							<a 
							href = "/LibraryManagement2/books/begin-update?bookId=${aBookObject.id}">Update
							</a>
							
							<a 
							href = "/LibraryManagement2/books/delete?bookId=${aBookObject.id}"
							onclick="if (!(confirm ('Do you really want to delete the book'))) return false"
							>Delete
							</a>
							
						</td>
										
					</tr>
				
					
				</core:forEach>
				
				
				
			</tbody>
		
		</table>	
	
	</div>

</body>
</html>