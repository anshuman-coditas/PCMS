<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter Personal Details</title>
</head>
<body>
<form method="post" action="PersonalServlet">
Patient Id : <input type="text" name="pid"><br><hr>
Patient Name : <input type="text" name="name"><br><hr>
Age : <input type="text" name="age"><br><hr>
Address : <textarea rows="50" cols="50" name="add"></textarea><br><hr>
Male : <input type="radio" name="gender" value="male">
Female : <input type="radio" name="gender" value="female">
Others : <input type="radio" name="gender" value="others"><br><hr>
Contact No. : <input type="text" name="phone"><br><hr>
Email Id : <input type="email" name="email"><br><hr>
<input type="submit" value="Add to Database">
</form>
</body>
</html>