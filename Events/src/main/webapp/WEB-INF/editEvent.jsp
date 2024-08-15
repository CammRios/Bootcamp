<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Event</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="row">
			<form:form action="/update_event/${event.id}" method="post" modelAttribute="event">
				<div>
					<form:label path="eventName">Event Name:</form:label>
					<form:input path="eventName" class="form-control" />
					<form:errors path="eventName" class="text-danger" />
				</div>
				<div>
					<form:label path="eventDate">Event Date:</form:label>
					<form:input path="eventDate" class="form-control" type="date" />
					<form:errors path="eventDate" class="text-danger" />
				</div>
				<div>
					<form:label path="location">Location:</form:label>
					<form:input path="location" class="form-control" />
					<form:errors path="location" class="text-danger" />
				</div>
				<div>
					<form:label path="province">Province:</form:label>
					<form:select path="province" class="form-select">
						<c:forEach items="${provincesList}" var="province">
							<form:option value="${province}">${province}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<form:hidden value="${userInSession.id}" path="host" />
				<input type="hidden" name="_method" value="PUT">
				<input type="submit" class="btn btn-success mt-3" value="Save" >
			</form:form>
		</div>

</body>
</html>