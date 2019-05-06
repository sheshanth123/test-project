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


<title>POS Home</title>

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

<!-- Numpad CSS -->
<link href="${css}/numpad.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">
	
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">
		
			<!-- Loading the Home contents -->
			<c:if test="${userClickedHome == true }">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- About handling -->
			<c:if test="${userClickedAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- Contact handling -->
			<c:if test="${userClickedContact == true }">
				<%@include file="contact.jsp"%>
			</c:if>
			
			<!-- All products/list products handling. Cart bar will be shown till payment page. -->
			<c:if test="${showAllCategories == true or showAllProducts == true or showAllSubCategories == true or userClickedOrderPayment == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>
			
			<!-- Show only when user clicks a single product -->
			<c:if test="${userClickedShowProduct == true }">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			
			<!-- Show only when user clicks a manage products -->
			<c:if test="${userClickedManageProducts == true }">
				<%@include file="manageProducts.jsp"%>
			</c:if>
			
			<!-- Show only when user clicks a manage categories -->
			<c:if test="${userClickedManageCategory == true }">
				<%@include file="manageCategoriesAndSubCategories.jsp"%>
			</c:if>
			
			<!-- Show only when user clicks a manage categories -->
			<c:if test="${userClickedManageUser == true }">
				<%@include file="manageUsers.jsp"%>
			</c:if>
			
			<!-- Show only when user clicks a Settings -->
			<c:if test="${userClickedSettings == true }">
				<%@include file="settings.jsp"%>
			</c:if>
			
			<!-- Modal dialog for product search -->
			<%@include file="productSearch.jsp"%>

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
		<script src="${js}/paymentpage.js"></script>
	</div>
	
</body>

</html>
