<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prescriptions & Report</title>
</head>
<body>
<form method="post" action="ReportServlet" ENCTYPE="multipart/form-data">
Patient id : <input type="text" name="pid"><br><hr>
Reports : <input type="file" name="rep"><br><br>
<input type="submit" value="Upload">
</form>
</body>
</html>