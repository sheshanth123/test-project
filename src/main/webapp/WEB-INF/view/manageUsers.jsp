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
					<h4>Add/Edit a User</h4>
				</div>
				
				<div class="panel-body">
					
					<!-- Form Elements -->
					
					<sf:form autocomplete="off" class="form-horizontal" modelAttribute="user" 
						action="${contextRoot}/manage/users"
						method="POST"
						>
						<div class="form-group">
							<label class="control-label col-md-4" for="name">First Name :</label>
							<div class="col-md-8">
								<sf:input type="text" path="firstName" id="firstName" placeholder="First Name" class="form-control"/>
								<sf:errors path="firstName" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="Brand name">Last Name :</label>
							<div class="col-md-8">
								<sf:input type="text" path="lastName" id="lastName" placeholder="Last Name" class="form-control"/>
								<sf:errors path="lastName" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Address :</label>
							<div class="col-md-8">
								<sf:textarea path="address" id="address" rows="4" placeholder="Enter the address." class="form-control" />
								<sf:errors path="address" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="userName">User Name : </label>
							<div class="col-md-8">
								<sf:input type="text" path="userName" id="userName" placeholder="Enter a valid user Name." class="form-control"/>
								<sf:errors path="userName" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="password">Passcode : </label>
							<div class="col-md-8">
								<sf:input type="number" path="password" id="password" placeholder="Enter a valid passcode(Only numbers)" class="form-control"/>
								<sf:errors path="password" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="email">Email : </label>
							<div class="col-md-8">
								<sf:input type="text" path="email" id="email" placeholder="Enter a valid email address." class="form-control"/>
								<sf:errors path="email" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="contactNumber">Contact Number : </label>
							<div class="col-md-8">
								<sf:input type="text" path="contactNumber" id="contactNumber" placeholder="Enter a contact number." class="form-control"/>
								<sf:errors path="contactNumber" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="role">Role : </label>
							<div class="col-md-8">
								
								<sf:select class="form-control" path="role">
					                <sf:option value="ADMIN">ADMIN</sf:option>
					                <sf:option value="STAFF">STAFF</sf:option>
					                <sf:option value="CUSTOMER">CUSTOMER</sf:option>
					                <sf:option value="SUPPLIER">SUPPLIER</sf:option>
					            </sf:select >
							</div>
						</div>
						
						
						<div class="form-group">
							<div class="col-md-offset-5 col-md-7">
								<input type="submit" name="submit" id="submit" value="Save" class="btn btn-primary"/>
								
								<!-- Hidden fields for user -->
								<sf:hidden path="id"/>
								<sf:hidden path="enabled"/>
							</div>
						</div>
						
					</sf:form>
					
				</div>
			
			</div>
		
		</div>
	
	
	</div>


	<!-- List of all users -->
	<div class="row">
		<div class="col-xs-12">
			<h3>Available Users</h3>
		</div>
		
		<div class="col-xs-12" >
			<div class="container-fluid">
				<div class="table-responsive">
				
					<table id="adminUserTable"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Address</th>
								<th>User Name</th>
								<th>Email</th>
								<th>Contact Number</th>
								<th>Role</th>
								<th>Enable</th>
								<th>Edit</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	
</div>