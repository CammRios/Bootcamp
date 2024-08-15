<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dojo Survey Index</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container-md text-center">
		<h1>Dojo Survey:</h1>
		<div class="d-flex flex-column mb-3">
			<form action="/result" method="post">
				<div class="p-2">
					<label>Your Name:</label>
					<input type="text" name="username">
				</div>
						
				<div class="p-2">
					<label>Dojo Location:</label>
					<select name="location">
						<option value="peru">Peru</option>
						<option value="argentina">Argentina</option>
						<option value="mexico">Mexico</option>
						<option value="colombia">Colombia</option>
					</select>
				</div>
					
				<div class="p-2">
					<label>Favorite Language:</label>
					<select name="language">
						<option value="python">Python</option>
						<option value="java">Java</option>
						<option value="javascript">JavaScript</option>
					</select>
				</div>
				<div class="p-2">
					<label>Comment (optional)</label>
				</div>
				
				<textarea name="comment" class="p-2"></textarea>
			 	<div class="p-2">
					<input type="submit" class="btn btn-success" value="send">
				</div>
			</form>
		</div>
	</div>

</body>
</html>