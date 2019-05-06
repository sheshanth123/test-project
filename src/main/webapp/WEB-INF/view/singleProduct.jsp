<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" >
	<!-- Breadcrumb -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.name}</li>
			</ol>
		</div>
	
	</div>
	
	
	<div class="row">
		<!-- Product Image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img src="${images}/product.png" class="img img-responsive"/>
			</div>
		</div>
		
		<!-- Product Description -->
		<div class="col-xs-12 col-sm-8">
			<h3>${product.name}</h3>
			<hr/>
			
			<p>${product.description}</p>
			<hr/>
			
			<h4>Price : <strong> ${product.unit_price} /-</strong></h4>
			<hr/>
			
			<!-- Quantity number / Out of stock handling -->
			<c:choose>
				<c:when test="${product.quantity < 1}">
					<h6>Qty Available: <span style="color:red">Out of Stock!</span></h6>
				</c:when>
			
				<c:otherwise>
					<h6>Qty Available: ${product.quantity}</h6>
				</c:otherwise>
			</c:choose>
			
			<a href="${contextRoot}/home" class="btn btn-primary">Back</a>
			
		</div>
	
	</div>

</div>