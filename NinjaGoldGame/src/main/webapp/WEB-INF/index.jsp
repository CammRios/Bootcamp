<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ninja Gold Game</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Your Gold: ${gold}</h1>
		<div class="row">
			<div class="col-3 border text-center">
				<h2>Farm</h2>
				<p>Earns 10-20 gold</p>
				<form action="/find" method="post">
					<input type="hidden" name="place" value="farm">
					<input type="submit" class="btn btn-warning" value="Find Gold!">
				</form>
			</div>
			<div class="col-3 border text-center">
				<h2>Cave</h2>
				<p>Earns 5-10 gold</p>
				<form action="/find" method="post">
					<input type="hidden" name="place" value="cave">
					<input type="submit" class="btn btn-warning" value="Find Gold!">
				</form>
			</div>
			<div class="col-3 border text-center">
				<h2>House</h2>
				<p>Earns 2-05 gold</p>
				<form action="/find" method="post">
					<input type="hidden" name="place" value="house">
					<input type="submit" class="btn btn-warning" value="Find Gold!">
				</form>
			</div>
			<div class="col-3 border text-center">
				<h2>Casino</h2>
				<p>Earns/Takes 0-50 gold</p>
				<form action="/find" method="post">
					<input type="hidden" name="place" value="casino">
					<input type="submit" class="btn btn-warning" value="Find Gold!">
				</form>
			</div>
		</div>
		<div class="row">
			<div class="card">
				<c:forEach items="${activities}" var="activity">
				
					<c:if test="${activity.contains('earned')}">
						<p class="text-success">${activity}</p>
					</c:if>
					<c:if test="${activity.contains('lost')}">
						<p class="text-danger">${activity}</p>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>