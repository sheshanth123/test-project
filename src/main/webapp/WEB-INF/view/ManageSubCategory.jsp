<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="panel panel-primary">

	<div class="panel-heading">
		<h4>Add/Edit a Sub-Category</h4>
	</div>

	<div class="panel-body">

		<!-- Form Elements -->
		<sf:form autocomplete="off" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/subcategories" method="POST">
			<div class="form-group">
				<label class="control-label col-md-4" for="name">Sub-Category Name :</label>
				<div class="col-md-8">
					<sf:input type="text" path="name" id="subcategory_name" placeholder="Name of the sub-category" class="form-control" />
					<sf:errors path="name" cssClass="help-block" element="em" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-4" for="description">Description :</label>
				<div class="col-md-8">
					<sf:textarea path="description" id="subcategory_description" rows="4" placeholder="Write some description about the sub-category." class="form-control" />
					<sf:errors path="description" cssClass="help-block" element="em" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-4" for="categoryId">Select Category : </label>
				<div class="col-md-8">
					<sf:select class="form-control" id="categoryId" path="id" 
						items="${categories}" 
						itemLabel="name" 
						itemValue="id"								
					/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-offset-5 col-md-7">
					<input type="submit" name="submit" id="submit" value="Save" class="btn btn-primary" />

					<!-- Hidden fields for product -->
					<sf:hidden path="id" />
					<sf:hidden path="active" />

				</div>
			</div>
		</sf:form>
	</div>
</div>


<br> 
<br> 
<h3><u>Available Sub-categories</u></h3>
<br>

<div class="container-fluid" style="padding: 0px;">
	<div class="table-responsive">

		<table id="adminSubCategoryTable" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Category</th>
					<th>Name</th>
					<th>Description</th>
					<th>Edit</th>
				</tr>
			</thead>
		</table>
	</div>
</div>