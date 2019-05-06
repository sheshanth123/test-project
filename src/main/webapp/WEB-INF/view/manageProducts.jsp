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
	
	
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h4>Add/Edit a Product</h4>
				</div>
				
				<div class="panel-body">
					
					<!-- Form Elements -->
					
					<sf:form autocomplete="off" class="form-horizontal" modelAttribute="product" action="${contextRoot}/manage/products" method="POST" >
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Product Name :</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder="Name of the product" class="form-control"/>
								<sf:errors path="name" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="Brand name">Brand Name :</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Name of the Brand" class="form-control"/>
								<sf:errors path="brand" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description :</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder="Write some description about the item." class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="Bar Code">Bar code :</label>
							<div class="col-md-8">
								<sf:input type="text" path="code" id="code" placeholder="Bar Code of the item." class="form-control"/>
								<sf:errors path="code" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Unit Price : </label>
							<div class="col-md-8">
								<sf:input type="number" step=".01" path="unit_price" id="unitPrice" placeholder="Price of each item." class="form-control"/>
								<sf:errors path="unit_price" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity : </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder="Available quantity." class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="subcategoryId">Select Sub-category : </label>
							<div class="col-md-8">
								<sf:select class="form-control" id="subcategoryId" path="subcategory_id" 
									items="${subcategories}" 
									itemLabel="name" 
									itemValue="id"								
								/>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-5 col-md-7">
								<input type="submit" name="submit" id="submit" value="Save" class="btn btn-primary"/>
								
								<!-- Hidden fields for product -->
								<sf:hidden path="id"/>
								<sf:hidden path="supplier_id"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
								<sf:hidden path="is_active"/>
								
							</div>
						</div>
						
					</sf:form>
					
				</div>
			
			</div>
		
		</div>
	
	
	</div>


	<!-- List of all Products -->
	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products</h3>
		</div>
		
		
		<div class="col-xs-12" >
			<div class="container-fluid">
				<div class="table-responsive">
				
					<table id="adminProductTable"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Sub-Category</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Quantity</th>
								<th>Unit Price</th>
								<th>Active</th>
								<th>Edit</th>
							</tr>
						</thead>
					</table>
					
				</div>
			</div>
		</div>
	
		
	
	</div>
	
	

</div>