$(document).ready(function(){
	
	var validateLogin = function(){
		return $("#loginForm").validate({
			errorClass:"text-danger",
			rules:{
				userName:{required:true, minlength:2},
				password:{required:true, minlength:2}	
			},
			messages:{
				userName:{required:"Must enter a username", minlength:"Must enter a valid username to continue!"},
				password:{required:"Must enter a password", minlength:"Must enter a valid password to continue!"}
			}
		})
	}
	
	
	$('#submitLogin').click(function(e){
		e.preventDefault()
		var validator = validateLogin();
		
		if (!$("#loginForm").valid()){
			return false;
		}
		
		var data = JSON.stringify($('#loginForm').serializeObject());
		
		$.ajax({
			beforeSend: function(xhrObj){
				xhrObj.setRequestHeader("Content-Type","application/json");
				xhrObj.setRequestHeader("Accept","application/json");
				
			},
		
			type:"POST",
			contentType:"application/json",
			url:"/userlogin",
			data:data,
			dataType:'json',
			success:function(json){
//				$("#dialog").dialog().remove();
				 sessionStorage.setItem('user', JSON.stringify(json));
				 var user = sessionStorage.getItem('user')
				 window.location = "/profile";
			},
			error: function (e, status) {
	//			window.location = "/";
	            if (e.status == 500 || e.status == 409){
//	                alert("Error! User Does Not Exist!");
	            	$( "#loginDialog" ).dialog( "open" );
	            }
	            
	        }
			
		})
			
		$( "#loginDialog" ).dialog({
	    	autoOpen:false,
	    	buttons: {
	            OK: function() {$(this).dialog("close");}
	         },
	         hide: "puff",
	         show : "slide",
//	         modal: true,
	    });
    
	})
})