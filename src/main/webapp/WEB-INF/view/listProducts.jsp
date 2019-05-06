<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid" >
	<div class="row">
		<div class="col-md-8" style="padding:0px;">
			<!-- Added breadcrumb component -->
			<div class="row" style="padding:0px 7px 0px 7px;">
				<div class="col-md-12" id="breadcrumbDiv">
					<c:if test="${showAllCategories == true or userClickedHome == true}" >
						<script>
							window.categoryName = '';
							window.currencyIcon = '${currencyIcon}';
						</script>
						<ol class="breadcrumb clearfix">
							<li class="active">Home</li>
						</ol>
						
					</c:if>
				</div>
			</div>
		
			<!-- Categories and product list page -->
			<div class="row" style="padding:0px 7px 0px 7px;">
				<div class="col-md-12" >
					<%@include file="categoriesList.jsp" %>
				</div>
			</div>
			
		</div>
		
		<!-- Cart bar -->
		<div class="col-md-4" style="padding:0px;"> <!-- Removing this padding so that cart bar extends till end of page. -->
			<%@include file="cartbar.jsp" %>
		</div>
		
	</div>
	
</div>