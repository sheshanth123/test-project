<div class="modal" id="productsModal" data-backdrop="static" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">&times;</button>
				<h4>Search and Add Product..</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="responsive-table">
						<table width="700px" class="table table-bordered" style="margin-bottom: 2em; width: 700px;" id="searchProductTable">
							<thead>
								<tr>
									<th>Code</th>
									<th>Name</th>
									<th>Brand</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<button id="productNotFoundSearch" class="btn btn-default">Search</button>
					<h5 id="productNoFoundWarning" style="color: red">Product Not found. Enter a valid Code.</h5>
				</div>
			</div>
			<div class="modal-footer">
				<button id="productNotFoundAddBtn" class="btn btn-primary">Add</button>
				<button class="btn btn-primary" data-dismiss="modal">Close</button>
				<div></div>
			</div>
		</div>
	</div>
</div>