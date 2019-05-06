<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="CartBarHeading" style="vertical-align:middle">
    <!-- <h4 style="color:white;" >Cart</h4> -->
    <span class="glyphicon glyphicon-shopping-cart"></span>
</div>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>

<div style="height:70vh; overflow-y:auto;">
	<table class="table table-borderless table-hover table-responsive" style="table-layout:fixed; overflow:auto" id="cartTable">
		<thead>
			<tr>
				<th style="width:30%;">Product</th>
				<th style="width:25%; text-align:center">Quantity</th>
				<th style="width:15%;">Price</th>
				<th style="width:15%;">Total</th>
				<th style="width:15%;"> </th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	
	<div id="divAddToCartToStartTranscation">
		<img id="imageAddToCartToStartTranscation" src="${images}/additemstocart.png" class="img-responsive" style="width:80%;height:80%; display: block; margin: auto;" />
	</div>
	
</div>

<!-- Cart pay/hold/delete -->
<div class="cartFooter" style="height:10vh;" >
	<hr style="border-color:green; margin:10px 0px 0px 0px;">
	
	<div class="col-md-6">
		<div class="col-md-6" style="padding:0px 0px 0px 0px;">
			<div><h6><span>Items </span></h6></div>
			<div><h6><span>Discount </span></h6></div>
		</div>
		
		<div class="col-md-6">
			<div><h6><span id="itemsInCart">0</span></h6></div>
			<div><h6><span>${currencyIcon} </span><span id="discount">0</span></h6></div>
		</div>		
	</div>

	<div class="col-md-6" style="padding:0px 15px 0px 0px;">
		<div class="col-md-4" style="padding:0px 0px 0px 0px;">
			<h4>
				<span>Total </span>
			</h4>
		</div>
		<div class="col-md-8" style="padding:0px 0px 0px 0px;">
			<h4>
				<span>${currencyIcon} </span><span id="cartTotal">0.0</span>
			</h4>
		</div>
	</div>
	
	<div style="position: fixed; bottom:1%; width:30vw;">
		<div class="col-md-3">
			<a href="#" class="btn btn-danger btn-block" id="clearCartBtn"> 
			    <span class="glyphicon glyphicon-trash"></span> <span>Clear</span>
			</a>
		</div>
		<div class="col-md-3">
			<a href="#" class="btn btn-info btn-block" id="cartHoldBtn"> 
				<span class="glyphicon glyphicon-pause"></span> <span style="padding-left: 10px;">Hold</span>
			</a>
		</div>
		<div class="col-md-6">
			<a class="btn btn-success btn-block" id="pay"> 
				<span style="vertical-align: middle;" class="glyphicon glyphicon-credit-card"></span> <span style="vertical-align: middle">Pay</span>
			</a>
		</div>
	</div>
</div>