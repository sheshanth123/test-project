$(function() {
	switch(menu){
		case 'All Products' :
			$('#listProducts').addClass('active');
			break;	
		case 'Manage Products':
			$('#manageProducts').addClass('active');
			break;
		case 'Manage Category' :
			$('#manageCategory').addClass('active');
			break;
		case 'Manage User' :
			$('#manageUsers').addClass('active');
			break;
		default:
			break;
	}
	
	// Adding the csrf token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	if(token.length > 0 && header.length >0 ){
		// Set the token header for ajax request.
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
	}
	
	
	//Timer implementation for dismissing the alert
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		}, 3000)
	}
	
	//-----------------------------------
	/*$('.switch input[type="checkbox"').on( 'change', function(){
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var msg = (checked) ? 'You want to active the product ?' : 'You want to deactivate the product ?'
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size:'medium',
			title:'Product Activation & Deactivation',
			message : msg,
			callback : function(confirmed)
			{
				if(confirmed){
				}
				else{
					checkbox.prop('checked', !checked);
				}
			}
		})
	});*/
	
	
	// -----------------------------Data table for Product. Available only for admin-----------------------
	var $adminProductsTable = $('#adminProductTable');
	// Execute only when products are to be displayed.
	if($adminProductsTable.length){
		
		var jsonURL = window.contextRoot + '/json/data/admin/all/products';
		
		$adminProductsTable.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			columns : [
				{
					data : 'subcategory_id',
				},
				{
					data : 'name',
				},
				{
					data : 'brand',
				},
				{
					data : 'quantity',
					mRender : function(data,type,row){
						if(data < 1)
						{
							return '<span style="color:red">Out of Stock!</span>';
						}
						
						return data;
					}
				},
				{
					data : 'unit_price',
					/*mRender : function(data,type,row){
						return '&#8377;' + data
					}*/
				},
				{
					data : 'is_active',
					bSortable : false,
					mRender : function(data,type,row){
						var str = '';
						str += '<label class="switch">';
						
						if(data){
							str += '<input type="checkbox" checked="checked" value="' + row.id + '"/>';
						}
						else{
							str += '<input type="checkbox" value="' + row.id + '"/>';
						}
						
						str += '<div class="slider"></div></label>';
						return str;
					}
					
				},
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/product" class="btn btn-warning" >';
						str += '<span class="glyphicon glyphicon-pencil" ></span></a>';
						return str;
					
					}
				}
				
			],
			
			initComplete : function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on( 'change', function(){
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var msg = (checked) ? 'You want to activate the product ?' : 'You want to deactivate the product ?'
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size:'medium',
						title:'Product Activation & Deactivation',
						message : msg,
						callback : function(confirmed)
						{
							if(confirmed){
								
								var actURL = window.contextRoot + '/manage/product/' + value + '/activation';
								$.post(actURL, function( data ){
									console.log("post returned" + data);
									/*bootbox.confirm({
										size:'medium',
										title:'Information',
										message : data
									});*/
								});
							}
							else{
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
			
			
		});
	}
	
	
	//------------------------------Data table for Category - Admin -------------------------
	
	var $table = $('#adminCategoryTable');
	// Execute only when categories are to be displayed.
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/admin/all/categories';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			columns : [
				{
					data : 'name',
				},
				{
					data : 'description',
				}
				,
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/categories" class="btn btn-warning" >';
						str += '<span class="glyphicon glyphicon-pencil" ></span></a>';
						return str;
					
					}
				}
				
			]
		});
	}
	
//------------------------------Data table for SubCategory - Admin -------------------------
	
	var $table = $('#adminSubCategoryTable');
	// Execute only when Sub-categories are to be displayed.
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/admin/all/subcategories';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			drawCallback: function ( settings ) {
				var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	 
	            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                if ( last !== group ) {
	                    $(rows).eq( i ).before(
	                        '<tr class="group" style="background-color:black;"><td colspan="5">'+group+'</td></tr>'
	                    );
	 
	                    last = group;
	                }
	            } );
	    },
			columns : [
				{
					data : 'category_Id',
					visible : false,
				},
				{
					data : 'name',
				},
				{
					data : 'description',
				}
				,
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/subcategories" class="btn btn-warning" >';
						str += '<span class="glyphicon glyphicon-pencil" ></span></a>';
						return str;
					
					}
				}
				
			]
		});
	}
	
	
	//------------------------------Data table for User - Admin -------------------------
	var $table = $('#adminUserTable');
	// Execute only when categories are to be displayed.
	if($table.length){
		console.log("Inside the table!");
		
		var jsonURL = window.contextRoot + '/json/data/admin/all/users';
		console.log(jsonURL);
		
		$table.DataTable({
			lengthMenu : [[5,10,30,-1],['5','10','30','All']],
			pageLength : 10,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			columns : [
				{
					data : 'firstName',
				},
				{
					data : 'lastName',
				},
				{
					data : 'address',
				},
				{
					data : 'userName',
				},
				{
					data : 'email',
				},
				{
					data : 'contactNumber',
				},
				{
					data : 'role',
				},
				{
					data : 'enabled',
					bSortable : false,
					mRender : function(data,type,row){
						var str = '';
						str += '<label class="switch">';
						
						if(data){
							str += '<input type="checkbox" checked="checked" value="' + row.id + '"/>';
						}
						else{
							str += '<input type="checkbox" value="' + row.id + '"/>';
						}
						
						str += '<div class="slider"></div></label>';
						return str;
					}
					
				},
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/users" class="btn btn-warning" >';
						str += '<span class="glyphicon glyphicon-pencil" ></span></a>';
						return str;
					
					}
				}
				
			],
			
			initComplete : function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on( 'change', function(){
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var msg = (checked) ? 'You want to enable the user ?' : 'You want to disable the user ?'
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size:'medium',
						title:'User enabling & disabling',
						message : msg,
						callback : function(confirmed)
						{
							if(confirmed){
								
								var actURL = window.contextRoot + '/manage/user/' + value + '/activation';
								$.post(actURL, function( data ){
									console.log("post returned" + data);
									/*bootbox.confirm({
										size:'medium',
										title:'Information',
										message : data
									});*/
								});
							}
							else{
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
			
		});
	}
	
	//------------------------------ Todays/All days history----------------------------
	var $table = $('#todaysHistoryTable');
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/sales/today';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			drawCallback: function ( settings ) {
				var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	 
	            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                if ( last !== group ) {
	                    $(rows).eq( i ).before(
	                        '<tr class="group" style="background-color:black;"><td colspan="10">'+group+'</td></tr>'
	                    );
	 
	                    last = group;
	                }
	            } );
			},
			columnDefs : [
		    	{
		    		"targets": "_all",
		    	    "className": "text-center",
		    	},
		    ],
			columns : [
				{
					data : 'staffId',
					visible : false,
				},
				{
					data : 'id',
				},
				{
					data : 'dateTime',
				}
				,
				{
					data : 'productId',
				}
				,
				{
					data : 'byingPrice',
				}
				,
				{
					data : 'productCount',
				}
				,
				{
					data : 'discount',
				}
				,
				{
					data : 'taxPaid',
				}
				,
				{
					data : 'total',
				}
				,
				{
					data : 'paymentMethod',
					mRender : function(data, type, row){
						console.log('INput data customer ID - ' + data);
						var str = '';
						if(data == 1){
							str = 'Cash';
						}else if(data == 2){
							str = 'Credit\Debit Card';
						}else if(data == 3){
							str = 'Gift Card';
						}
						
						console.log('Output str - ' + str);
						return str;
					}
				}
				,
				{
					data : 'customerId',
				}

			]
		});
	}
	
	var $table = $('#allHistoryTable');
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/sales/complete';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			drawCallback: function ( settings ) {
				var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	 
	            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                if ( last !== group ) {
	                    $(rows).eq( i ).before(
	                        '<tr class="group" style="background-color:black;"><td colspan="10">'+group+'</td></tr>'
	                    );
	 
	                    last = group;
	                }
	            } );
		    },
		    columnDefs : [
		    	{
		    		"targets": "_all",
		    	    "className": "text-center",
		    	},
		    ],
			columns : [
				{
					data : 'staffId',
					visible : false,
				},
				{
					data : 'id',
				},
				{
					data : 'dateTime',
				}
				,
				{
					data : 'productId',
				}
				,
				{
					data : 'byingPrice',
				}
				,
				{
					data : 'productCount',
				}
				,
				{
					data : 'discount',
				}
				,
				{
					data : 'taxPaid',
				}
				,
				{
					data : 'total',
				}
				,
				{
					data : 'paymentMethod',
					mRender : function(data, type, row){
						var str = '';
						if(data == 1){
							str = 'Cash';
						}else if(data == 2){
							str = 'Credit\Debit Card';
						}else if(data == 3){
							str = 'Gift Card';
						}
						
						console.log('Output str - ' + str);
						return str;
					}
				}
				,
				{
					data : 'customerId',
				}
			]
		});
	}
	
	
	//------------------------------Login form validation----------------------------
	var $loginForm = $('#loginForm');
	if($loginForm.length){
		$loginForm.validate({
			rules : {
				username : {
					required: true,
					//email: true
				},
				password:{
					required: true
				}
			},
			
			message : {
				username : {
					required: "Please enter the User ID."
				},
				password:{
					required: "Please enter the Password."
				}
			},
			
			errorElement : 'em',
			errorPlacement : function(error, element){
				error.addClass('help-block');
				error.insertAfter(element);
			}
			
		});
	}
	
	//--------------------- Cart bar functionality ---------------------------------------------
	// Key-down function - Waiting for scanner input. 
	var barcode='';
    $(document).keypress(function(e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if(code==13)// Enter key hit
        {
        	var $cartTable = $('#cartTable');
        	if($('#cartTable').length)
        	{
        		addScannedItemToCart();
        	}
        	else if($('#adminProductTable').length)
    		{
        		addScannedItemToProductList();
    		}
        	
            barcode = '';
        }
        else if(code==9)// Tab key hit
        {
            barcode='';
        }
        else
        {
            barcode = barcode + String.fromCharCode(code);
        }
    });
    
    function addScannedItemToProductList()
    {
    	var barCodeEdit = document.getElementById("code");
		if(barCodeEdit == null){
			console.log("Faild to find barcode box");
			return;
		}
		
		console.log("Succesfully found barcode box");
		barCodeEdit.value = barcode;
    }
    
    function addScannedItemToCart()
    {
    	var request = $.ajax({
    		url: '/search/products',
    		async: false,
    		type: "POST",
    		data: { code: barcode },
    		success: function(product)
    		{        			
    			console.log("Mapping Successful" + product.id);
    			if(product.id != null)
				{
    				addProduct(product.id,product.unit_price,product.name);
				}
    			else
				{
    				if($('#searchProductTable').length)
    				{
    					updateCodeEditDialog(barcode,"","","","");
    					$('#productsModal').modal('show');
    				}
				}
    		},
    		
    		error: function (error) {
    			console.log("Error : " + error);
    		}
    	});
    }
    
  //--------------------- Search button click for barcode from the modal dialog ---------------------------------------------
    $(document).on('click','#productNotFoundSearch',function(){
    	var userEnteredCode = searchProductTable.rows[1].cells[0].children[0].value;
    	var request = $.ajax({
    		url: '/search/products',
    		async: false,
    		type: "POST",
    		data: { code: userEnteredCode },
    		success: function(product)
    		{        			
    			console.log("Mapping Successful" + product.id);
    			(product.id != null) ? updateCodeEditDialog(userEnteredCode,product.name,product.brand,product.unit_price,product.id) :
    				updateCodeEditDialog(userEnteredCode,"","","","");
    		},
    		
    		error: function (error) {
    			console.log("Error : " + error);
    		}
    	});
	});
    
    function updateCodeEditDialog(code,name,brand,price,productId){
    	$("#searchProductTable tbody").html("");
		$("#searchProductTable").append('<tbody>' +
			'<tr>' +
				'<td> <input type="text" style="color:blue;width: 100%;" autofocus value="' + code + '" spellcheck="false" onfocus="var temp_value=this.value; this.value=\'\'; this.value=temp_value"></td>' + 
				'<td>' + name + '</td>' + 
				'<td>' + brand + '</td>' + 
				'<td>' + price + '</td>' + 
				'<td style="display:none">' + productId + '</td>' + 
			'</tr>' +
			'</tbody>');
		if(!name){
			$("#productNoFoundWarning").show();
			$('#productNotFoundAddBtn').prop('disabled', true);
		}
		else{
			$("#productNoFoundWarning").hide();
			$('#productNotFoundAddBtn').prop('disabled', false);
		}
    }
    
    //----------------- Category button click ---------------------------------------------------------------
    $(document).on('click','#buttonCategory',function(){
		var catButton = $(this);
		if(catButton != null)
		{
			getAndDisplaySubCategories($(catButton).text(),$(catButton).attr("categoryId"));
		}
	});
    
  //----------------- Sub-Category button click ---------------------------------------------------------------
    $(document).on('click','#buttonSubCategory',function(){
		var subCatButton = $(this);
		if(subCatButton != null)
		{
			var subcategoryName = $(subCatButton).text();
			var subcategoryId = $(subCatButton).attr("subcategoryId");
			$.ajax({ 
				url: '/get/products',
	    		async: false,
	    		type: "GET",
		        data: { id: $(subCatButton).attr("subcategoryId") },
		        success : function(response)
		        {
		        	var jsonArrayList = JSON.parse(response);
		        	populateProducts(jsonArrayList,subcategoryName);
		        },
				error: function (error) {
	    			console.log("Error : " + error);
	    		}
			});
		}
	});
    
    $(document).on('click','#brdCmbShowSubCategory',function(){
		var breadcrumbCmpt = $(this);
		if(breadcrumbCmpt != null)
		{
			getAndDisplaySubCategories($(breadcrumbCmpt).text(),$(breadcrumbCmpt).attr("categoryId"));
		}
	});
    
    $(document).on('click','#brdCmbShowCategory',function(){
		var breadcrumbCmpt = $(this);
		if(breadcrumbCmpt != null)
		{
			var categoryAndProductsDiv = 
				'<div id="catSubCatProductdiv" style="height:30vh;"> </div>' + 
				'<br> <br> <br>' +
				'<div id="Productdiv" style="height:30vh;"> </div>';
			$('#posMainDiv').html(categoryAndProductsDiv);
			
			getAndDisplayCategories();
			getAndDisplayHotProducts();
		}
	});
    
    function getAndDisplaySubCategories(categoryName,categoryId){
		$.ajax({ 
			url: '/get/subcategories',
    		async: false,
    		type: "GET",
	        data: { id: categoryId },
	        success : function(response)
	        {
	        	var jsonArrayList = JSON.parse(response);
	        	populateSubCategories(jsonArrayList,categoryName,categoryId);
	        },
			error: function (error) {
    			console.log("Error : " + error);
    		}
		});
    }
    
    function getAndDisplayCategories(){
    	$.ajax({ 
			url: '/get/categories',
    		async: false,
    		type: "GET",
	        success : function(response)
	        {
	        	var jsonArrayList = JSON.parse(response);
	        	populateCategories(jsonArrayList);
	        },
			error: function (error) {
    			console.log("Error : " + error);
    		}
		});
    }
    
    function getAndDisplayHotProducts(){
    	$.ajax({ 
			url: '/get/hotProducts',
    		async: false,
    		type: "GET",
	        success : function(response)
	        {
	        	var jsonArrayList = JSON.parse(response);
	        	populateHotProducts(jsonArrayList);
	        },
			error: function (error) {
    			console.log("Error : " + error);
    		}
		});
    }
    
    
  //--------------------- Add to cart button from the Modal dialog ---------------------------------------------
    $(document).on('click','#productNotFoundAddBtn',function(){
    	addProduct(searchProductTable.rows[1].cells[4].innerHTML,
    			searchProductTable.rows[1].cells[3].innerHTML,
    			searchProductTable.rows[1].cells[1].innerHTML);
    	$('#productsModal').modal('toggle');
	});

  //--------------------- Add to cart from the button product(listproducts.jsp) ---------------------------------------------
	$(document).on('click','#buttonProduct',function(){
		var user = $(this);
		if(user != null)
		{
			addProduct($(user).attr("productId"),$(user).attr("value"),$(user).text());
		}
	});
	
	function populateCategories(categoryArrayList){
		enableCartButtons();
		var newDivContent = '';
		categoryArrayList.forEach(
    			function(category){
    				var catbtnContent = '<button type="button" class="btn btn-primary btn-item btn-item-product" categoryid="' + category.id + '" id="buttonCategory">' + category.name +'</button>';
    				newDivContent += catbtnContent;
    			});
		$('#catSubCatProductdiv').html(newDivContent);
		
		var breadcrumbCnt = '<ol class="breadcrumb clearfix">' + 
								'<li class="active">Home</li>' +
							'</ol>';
		$('#breadcrumbDiv').html(breadcrumbCnt);
	}
	
	function populateSubCategories(subcategoryArrayList,categoryName,categoryId){
		var newDivContent = '';
		subcategoryArrayList.forEach(
    			function(subcategory){
    				var subCatbtnContent = '<button type="button" class="btn btn-primary btn-item btn-item-product" id="buttonSubCategory" subcategoryId="' + subcategory.id + '">' + subcategory.name + '</button>';
    				newDivContent += subCatbtnContent;
    			});
		$('#catSubCatProductdiv').html(newDivContent);
		
		var breadcrumbCnt = '<ol class="breadcrumb">' + 
								'<li id="brdCmbShowCategory"><a href="#">Home</a></li>' +
								'<li id="brdCmbShowSubCategory" categoryId="' + categoryId + '" class="active">' + categoryName + '</li>' +
							'</ol>';
		$('#breadcrumbDiv').html(breadcrumbCnt);
	}
	
	function populateProducts(productList,subcategoryName){
		var newDivContent = '';
		productList.forEach(
    			function(product){
    			var productBtnCnt = '<button type="button" class="btn btn-primary btn-item btn-item-product" id="buttonProduct" value="' + product.price + '"productId="' + product.id + '">' + 
    								 	'<span class="button-left-top" >' + product.name + '</span>' + 
    								 	'<span class="button-left-bottom" >' + product.price + '</span>' +
    								 '</button>';
    				newDivContent += productBtnCnt;
    			});
		$('#catSubCatProductdiv').html(newDivContent);
		
		var breadcrumbCnt = '<ol class="breadcrumb">' + 
								'<li id="brdCmbShowCategory"><a href="#">Home</a></li>' +
								'<li id="brdCmbShowSubCategory" categoryId="' + $('#brdCmbShowSubCategory').attr("categoryId") + '" ><a href="#">' + $('#brdCmbShowSubCategory').text() + '</a></li>' +
								'<li id="brdCmbShowProducts" class="active">' + subcategoryName + '</li>' +
							'</ol>';				
		$('#breadcrumbDiv').html(breadcrumbCnt);
	}
	
	function populateHotProducts(productList){
		enableCartButtons();
		var newDivContent = '';
		productList.forEach(
			function(product){
				var productBtnCnt = '<button type="button" class="btn btn-primary btn-item btn-item-product" id="buttonProduct" value="' + product.price + '"productId="' + product.id + '">' + 
									 	'<span class="button-left-top" >' + product.name + '</span>' + 
									 	'<span class="button-left-bottom" >' + product.price + '</span>' +
									 '</button>';
				newDivContent += productBtnCnt;
			});
		$('#Productdiv').html(newDivContent);
	}
	
	 $('#cartTable').on('click', 'tbody tr', function () {
		 var row = $(this);
		 if(row.attr('selectedItem') != 'true')
		{
			 removeCurrentSelection();
			 selectRow(row);
		}
			
	 });
	
	function addProduct(productId, productValue, productName){
		var tableCart = document.getElementById("cartTable").getElementsByTagName('tbody')[0];
		if((tableCart == null) || (productId == null) || (productValue == null) ||(productName == null)){
			return;
		}
		
		removeCurrentSelection();
		removeAddToCartImage();
		
		// Check whether the product already exists using the productId.
		var row = $('tr[productId="' + productId + '"]');
		if(row.index() == -1)
		{
	        var tdProduct = document.createElement("td");
	        tdProduct.className = "col-md-4 Product";
	        tdProduct.setAttribute('style', "vertical-align:middle");
	        //tdProduct.innerHTML = '<h5 style="font-size: 15px; color:black" >' + productName + '</h5>';
	        tdProduct.innerHTML = productName.trim();
	        
	        var tdQuantity = document.createElement("td");
	        tdQuantity.className = "col-md-5 text-center quantity";
	        tdQuantity.setAttribute('style', "vertical-align:middle");
	        tdQuantity.innerHTML = getProductQuantityColumn(1);
	        
	        var tdPrice = document.createElement("td");
	        tdPrice.className = "col-md-1 itemPrice";
	        tdPrice.setAttribute('style', "vertical-align:middle");
	        tdPrice.innerHTML = productValue;
	        
	        var tdTotal = document.createElement("td");
	        tdTotal.className = "col-md-1 totalItemPrice";
	        tdTotal.setAttribute('style', "vertical-align:middle");
	        tdTotal.innerHTML = productValue;
	        
	        var tdRemove = document.createElement("td");
	        tdRemove.className = "col-md-1 removeItem";
	        tdRemove.setAttribute('style', "vertical-align:middle");
	        tdRemove.innerHTML = getDeleteProductColumn();
	        
	        var newRow = document.createElement("tr");
	        newRow.className = "bg-info";	// To display color on selected item.
	        newRow.setAttribute("height", 90);
	        newRow.setAttribute('productId', productId);
	        newRow.setAttribute('selectedItem', true);
	        //newRow.setAttribute('align', "center");
	        newRow.appendChild(tdProduct);
	        newRow.appendChild(tdQuantity);
	        newRow.appendChild(tdPrice);
	        newRow.appendChild(tdTotal);
	        newRow.appendChild(tdRemove);
	        
	        tableCart.prepend(newRow);
	        //tableCart.appendChild(newRow);
	        
	        // Update the cart total.
	        updateCartValues(productValue,1,true);
			
			//$("cartTable tr:last").scrollintoview();
			//var rowpos = $('#cartTable tr:last').position();
			//$('#container').scrollTop(rowpos.top);
			//row = newRow;
			
		}else{
			// Product exists. Increase the count of existing product.
			incrementProductCount(row);
			selectRow(row);
		}
	}
	
	function removeAddToCartImage()
	{
		if ($('#divAddToCartToStartTranscation').children().length && $('#imageAddToCartToStartTranscation').length){
			$('#divAddToCartToStartTranscation').empty();
		}
	}
	
	function insertAddToCartImage()
	{
		var cartItemCount = $("#cartTable tbody").children().length;
		if(cartItemCount == 0)
		{
			var imageItem = '<img id="imageAddToCartToStartTranscation" src="' + window.contextRoot + '/resources/images' + '/additemstocart.png" class="img-responsive" style="width:80%;height:80%; display: block; margin: auto;" />'; 
			$('#divAddToCartToStartTranscation').prepend(imageItem);
		}
	}
	
	function updateCartValues(productValue, itemCountToUpdate, addItem)
	{
		var updatedCartTotal = 0.0;
		var updatedItemCount = 0;
		var currentCartVal = parseFloat($('#cartTotal').text(), 10);
		var currentItemCount = parseInt($('#itemsInCart').text(), 10);
		if(addItem)
		{
	        var newItemPrice = parseFloat(productValue, 10);
	        updatedCartTotal = precise_round((currentCartVal+newItemPrice),2);
	        updatedItemCount = currentItemCount+itemCountToUpdate;
		}
		else
		{
			updatedCartTotal = precise_round((currentCartVal-productValue),2);
			updatedItemCount = currentItemCount-itemCountToUpdate;
		}
		
		$('#cartTotal').text(updatedCartTotal);
		$('#itemsInCart').text(updatedItemCount);
	}
	
	function selectRow(updatedRow){
		updatedRow.attr('selectedItem', true);
		updatedRow.attr('class', "bg-info");
		updatedRow.attr('height', 90);
	    
	    var curItemCount = parseInt(updatedRow.closest('tr').find('.labelQuantity').text(), 10);
	    updatedRow.children('td').eq(1).html(getProductQuantityColumn(curItemCount));
	    updatedRow.children('td').eq(4).html(getDeleteProductColumn());
	    enableDecrementButton(updatedRow);
	}
	
	function getProductQuantityColumn(count){
		return '<a type="button" class="btn btn-info btn-circle" id="itemCountMinus" disabled="disabled">' +
		'<span class="glyphicon glyphicon-minus"></span>' +
		'</a>' +
		'<label class="labelQuantity">&nbsp;&nbsp;' + count + '&nbsp;&nbsp;</label>' +
		'<a type="button" class="btn btn-info btn-circle" id="itemCountPlus" >' +
			'<span class="glyphicon glyphicon-plus"></span>' +
		'</a>'
	}
	
	function getDeleteProductColumn(){
		return '<a  type="button" class="btn icon-btn btn-danger" id="removeCartItemBtn">' + 
		'<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span></a>';
	}
	
	function removeCurrentSelection(){
		var row = $('tr[selectedItem="' + true + '"]');
		if(row.index() != -1)
		{
			row.attr('selectedItem', false);
			row.removeClass('bg-info');
			row.attr('height', 40);
			
			var curItemCount = parseInt(row.closest('tr').find('.labelQuantity').text(), 10);
			row.children('td').eq(1).html('<label class="labelQuantity">&nbsp;&nbsp;' + curItemCount + '&nbsp;&nbsp;</label>');
			row.children('td').eq(4).html('');
		}
	}
	
	$(document).on('click','#removeCartItemBtn',function(){
		// Reduce the price of item removed.
		var itemRemoved = parseFloat($(this).closest('tr').children('td.totalItemPrice').text(), 10);
		var curItemCount = parseInt($(this).closest('tr').find('.labelQuantity').text(), 10);
		updateCartValues(itemRemoved,curItemCount,false);
		$(this).closest('tr').remove();
		insertAddToCartImage();
	});
	
	$(document).on('click','#itemCountPlus',function(){
		incrementProductCount($(this));
	});
	
	function incrementProductCount(row){
		if(row === undefined)
			return;
			
		var curItemCount = parseInt(row.closest('tr').find('.labelQuantity').text(), 10);
		var itemPrice = parseFloat(row.closest('tr').find('.itemPrice').text(), 10);
		row.closest('tr').find('.labelQuantity').html( '&nbsp;&nbsp;' + (curItemCount+1) + '&nbsp;&nbsp;');
		row.closest('tr').find('.totalItemPrice').text( precise_round(itemPrice*(curItemCount+1),2));
		updateCartValues(itemPrice,1,true);
		enableDecrementButton(row);
	};
	
	function enableCartButtons(){
		$("#clearCartBtn").removeClass('disabled');
		$("#cartHoldBtn").removeClass('disabled');
		$("#pay").removeClass('disabled');
	}
	
	function enableDecrementButton(row){
		// Disable the minus button if count is <= 1 
		var curItemCount = parseInt(row.closest('tr').find('.labelQuantity').text(), 10);
		if((curItemCount) <= 1){
			row.closest('tr').find('#itemCountMinus').attr('disabled','disabled');
		}
		else{
			row.closest('tr').find('#itemCountMinus').removeAttr('disabled');
		}
	}
	
	
	$(document).on('click','#itemCountMinus:enabled',function(){
		var curItemCount = parseInt($(this).closest('tr').find('.labelQuantity').text(), 10);
		var itemPrice = parseFloat($(this).closest('tr').find('.itemPrice').text(), 10);
		
		$(this).closest('tr').find('.labelQuantity').html( '&nbsp;&nbsp;' + (curItemCount-1) + '&nbsp;&nbsp;');
		$(this).closest('tr').find('.totalItemPrice').text( precise_round(itemPrice*(curItemCount-1),2));
		updateCartValues(itemPrice,1,false);
		enableDecrementButton($(this).closest('tr'));
	});
	
	
	$(document).on('click','#clearCartBtn',function(){
		// Remove all the rows in cart.
		var tableCart = document.getElementById("cartTable");
		if(tableCart !=null )
		{
			tableCart.innerHTML = '<thead>' + 
					'<tr>' + 
						'<th style="width:30%;">Product</th>' + 
						'<th style="width:25%; text-align:center">Quantity</th>' + 
						'<th style="width:15%;">Price</th>' + 
						'<th style="width:15%;">Total</th>' + 
						'<th style="width:15%;"></th>' + 
					'</tr>' + 
					'</thead>' +
					'<tbody>' +
					'</tbody>';
			
			insertAddToCartImage();
			
			$('#cartTotal').text("0.0");
			$('#itemsInCart').text("0");
		}
	});
	
	function precise_round(num,decimals) {
	    var sign = num >= 0 ? 1 : -1;
	    return (Math.round((num*Math.pow(10,decimals)) + (sign*0.001)) / Math.pow(10,decimals)).toFixed(decimals);
	}
	
});