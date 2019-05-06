<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<div class="row">
	
		<!-- Status/Error message -->
		<c:if test="${not empty message}" >
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				
				</div>
			</div>
		</c:if>
	
	
		<div class="col-md-offset-2 col-xs-8">
			<div class="tab" role="tabpanel">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#Section1"
						aria-controls="home" role="tab" data-toggle="tab">Manage Category</a></li>
					<li role="presentation"><a href="#Section2"
						aria-controls="profile" role="tab" data-toggle="tab">Manage SubCategory</a></li>
				</ul>
				
				<!-- Tab panes -->
				<div class="tab-content tabs">
					<div role="tabpanel" class="tab-pane fade in active" id="Section1">
						<%@include file="ManageCategory.jsp"%>
					</div>
				
					<div role="tabpanel" class="tab-pane fade" id="Section2">
						<%@include file="ManageSubCategory.jsp"%>
					</div>
				
				</div>
			</div>
		
		</div>
	
	
	</div>


	<!-- List of all Categories -->
	<div class="row">
		<div class="col-md-offset-2 col-xs-8">
			
		</div>
		
		
		
	
		
	
	</div>



</div>