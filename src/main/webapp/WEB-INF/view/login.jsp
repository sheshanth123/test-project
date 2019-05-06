<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

<title>POS Home</title>

<script>
	window.menu = '$(title)';
	window.contextRoot = '${contextRoot}';
</script>

<!-- All the CSS goes here -->

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Theme goes here -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Data table Bootstrap CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Numpad CSS -->
<link href="${css}/numpad.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myApp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" style="color: white"> <span
						style="font-weight: bold">ePOS</span>
					</a>
				</div>
			</div>
		</nav>

		<!-- Page Content -->
		<div style="margin:10px;" >
			<div class="container-fluid">

				<!-- Will be displayed if credentials are wrong -->
				<c:if test="${not empty message}">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">

							<div class="alert alert-danger alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							${message}
							</div>
						</div>
					</div>
				</c:if>

				<!-- Will be displayed if user has logged out -->
				<c:if test="${not empty logout}">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">

							<div class="alert alert-success alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							
								${logout}
							</div>
						</div>
					</div>
				</c:if>


				<div class="row">
					<div class="panel panel-primary">

						<div class="panel-heading">
							<h4 class="text-center">Login</h4>
						</div>

						<div class="panel-body">
							<div class="col-md-8 text-center">
								<label class="control-label" for="password" ><h4>Select a user</h4></label>
								<br/>
							
								<c:forEach items="${users}" var="user">
									<button class="btn btn-primary btn-lg btn-user" id="buttonUser" value="${user.email}">${user.firstName}</button>
								</c:forEach>
							</div>

							<div class="col-md-1 vertical-line"></div>
							<div class="col-md-3">

								<!-- Form Elements -->
								<form class="form-horizontal" action="${contextRoot}/login" method="POST" id="loginForm">
									
									<!-- Hidden fields - User name -->
									<div class="form-group">
										<label class="control-label col-md-4" for="username" style="display: none">Login in as :</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username" class="form-control" style="display: none"/>
										</div>
									</div>  
									
									<label class="control-label col-md-10" for="password" ><h4>Enter your passcode</h4></label>
									<br/>
									
									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<input type="password" name="password" id="password" class="form-control" style="text-align:center"/>
										</div>
									</div>
									
									<!-- Num pad comes here. -->
									<%@include file="numpad.jsp"%>
									
									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<input type="submit" value="Login" class="btn btn-primary btn-lg"/>
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										</div>
									</div>

								</form>


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

		<!-- DataTable Bootstrap script-->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>
		
		<script>
			$('.num').click(function () {
		        var num = $(this);
		        var text = $.trim(num.find('.txt').clone().children().remove().end().text());
		        var telNumber = $('#password');
		        $(telNumber).val(telNumber.val() + text);
		    });
			
		
			$(document).on('click','#buttonUser',function(){
				var user = $(this);
				$('#username').val($(user).attr("value"));
			});
			
			
		</script>
	</div>

</body>

</html>
