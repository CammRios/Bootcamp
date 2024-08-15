<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container-md text-center">
		<h1>Submitted Info:</h1>
		<ul class="list-group">
			<li class="list-group-item border-0">Name: ${name}</li>
			<li class="list-group-item border-0">Dojo's Location: ${location}</li>
			<li class="list-group-item border-0">Favorite Language: ${language}</li>
			<li class="list-group-item border-0">Comment: ${comment}</li>
		</ul>
		<h2><a href="/" class="btn btn-warning">Go Back!</a></h2>
	</div>

</body>
</html>