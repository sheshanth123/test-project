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

</head>

<body>

<div class="wrapper">

	<!-- Navigation -->
	<%@include file="./shared/navbar.jsp"%>


	<!-- User Page Content -->
	<div class="content">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${userModel.role == 'STAFF'}">
						<h3>Your Sales</h3>
					</c:if>
					<c:if test="${userModel.role == 'ADMIN'}">
						<h3>Total Sales</h3>
					</c:if>
				</div>

				<div class="col-xs-12">
					<div class="tab" role="tabpanel">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#Section1"
								aria-controls="home" role="tab" data-toggle="tab">Today</a></li>
							<li role="presentation"><a href="#Section2"
								aria-controls="profile" role="tab" data-toggle="tab">All</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content tabs">
							<div role="tabpanel" class="tab-pane fade in active"
								id="Section1">
								<div class="table-responsive">
									<table id="todaysHistoryTable"
										class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>Staff ID</th>
												<th>Order ID</th>
												<th>Date Time</th>
												<th>Product ID</th>
												<th>Price</th>
												<th>Item Count</th>
												<th>Discount</th>
												<th>Tax Paid</th>
												<th>Total</th>
												<th>Payment Method</th>
												<th>Customer ID</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="Section2">
								<div class="table-responsive">
									<table id="allHistoryTable"
										class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>Staff ID</th>
												<th>Order ID</th>
												<th>Date Time</th>
												<th>Product ID</th>
												<th>Price</th>
												<th>Item Count</th>
												<th>Discount</th>
												<th>Tax Paid</th>
												<th>Total</th>
												<th>Payment Method</th>
												<th>Customer ID</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



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
