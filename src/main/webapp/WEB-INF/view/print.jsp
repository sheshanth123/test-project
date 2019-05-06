<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>Print</title>

<script>
   	window.menu = '${title}';
   	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Theme goes here -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Data table Bootstrap CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myApp.css" rel="stylesheet">

</head>
<!-- jQuery -->
	<script src="${js}/jquery.js"></script>

	<!-- Bootstrap core JavaScript -->
	<script src="${js}/bootstrap.min.js"></script>

	<!-- DataTable plugin -->
	<script src="${js}/jquery.dataTables.js"></script>

	<!-- DataTable Bootstrap script-->
	<script src="${js}/dataTables.bootstrap.js"></script>

	<!-- Bootbox script-->
	<script src="${js}/bootbox.min.js"></script>


	<!-- Self coded javascript -->
	<script src="${js}/myapp.js"></script>
</div>

</body>

</html>
