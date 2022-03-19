<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prescriptions </title>
</head>
<body>
<form method="post" action="PrescriptionServlet" ENCTYPE="multipart/form-data">
Patient id : <input type="text" name="pid"><br><hr>
Prescription : <input type="file" name="pres"><br><br>
<input type="submit" value="Upload">
</form>
</body>
</html>