<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!-- Sliding side bar -->
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="#">Reporting</a>
  <a href="${contextRoot}/history">History</a>
  <a href="#">Calc</a>
  <a href="${contextRoot}/contact">Contact</a>
  <a href="${contextRoot}/about">About</a>
</div>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" style="color:white" href="${contextRoot}/home">
				<span style="font-weight:bold">ePOS</span>
			</a>
		</div>
		
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="openNav">
					<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
				</li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">				
				<!-- Show 'Manage Products', 'Manage Category' and 'Manage Users' only for AMIN -->
				<security:authorize access="hasAuthority('ADMIN')">
					<li id="settings">
						<a href="${contextRoot}/settings">Settings</a>
					</li>
			        
			        <li class="dropdown" style="display:inline-flex; flex-direction:column;">
				        <a class="dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Manage
				          <span class="caret"></span>
				        </a>
				        <ul class="dropdown-menu">
				            <li style="flex: 1"><a id="manageProducts" href="${contextRoot}/manage/products">Products</a></li>
				          	<li style="flex: 1"><a id="manageCategory" href="${contextRoot}/manage/categories">Category</a></li>
				          	<li style="flex: 1"><a id="manageUsers" href="${contextRoot}/manage/users">Users</a></li>
				        </ul>
			    	</li>
				</security:authorize>
				
				<security:authorize access="isAnonymous()">
					<li id="login">
						<a href="${contextRoot}/login">Log In</a>
					</li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown">
						<a href="javascript:void(0)" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							${userModel.fullName}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="${contextRoot}/perform-logout">Logout</a></li>
						</ul>
					</li>
				</security:authorize>
			</ul>
			
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>

<!-- Script for handling opening and closing of side bar -->
<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "15%";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>