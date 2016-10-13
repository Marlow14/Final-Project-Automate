$('#submitLogin').click(function(){
	
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
			$("#dialog").dialog().remove();
			 sessionStorage.setItem('user', JSON.stringify(json));
			 var user = sessionStorage.getItem('user')
			 window.location = "/profile";
		},
		error: function (e, status) {
//			window.location = "/";
            if (e.status == 500 || e.status == 404){
//                alert("Error! User Does Not Exist!");
//            	$( "#dialog" ).dialog({
//                	autoOpen:false,
//                buttons: {
//                    OK: function() {$(this).dialog("close");}
//                 },
//            	 hide: "puff",
//               show : "slide",
//            	 modal: true,
//                });
//                $( "#submitLogin" ).click(function() {
//                    $( "#dialog" ).dialog( "open" );
//                 });
            }
        }
		
	})
	
	$( "#dialog" ).dialog({
    	autoOpen:false,
    	buttons: {
            OK: function() {$(this).dialog("close");}
         },
         hide: "puff",
         show : "slide",
         modal: true,
    });
    
	
})