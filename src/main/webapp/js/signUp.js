/** JS for sign up form **/

// This function runs when a user uses the sign up form and is run before any data is submitted to the database


$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


$(function(){
	
    $('#submitSignUp').click(function(){

		var homeAddress = $("#homeAddress").val();
		var geocoder;
		geocoder = new google.maps.Geocoder();
		
		// Uses Google Maps API to geocode the user's address as a pair of lag/long coords
		
		geocoder.geocode( { 'address': homeAddress}, function(results, status) {
			if (status == 'OK') {
				var lat = results[0].geometry.location.lat();
				var lng = results[0].geometry.location.lng();
				$("#homeLat").val(lat);
				$("#homeLng").val(lng);	

				var data = JSON.stringify($("#signUpForm").serializeObject());
				
				$.ajax({
					beforeSend: function(xhrObj){
						xhrObj.setRequestHeader("Content-Type","application/json");
						xhrObj.setRequestHeader("Accept","application/json");
					},
					type: "POST",
					url: "/user",       
					data: data,               
					dataType: "json",
					success: function(json){
					   console.log(json);
					}
				});
					
				
			// Specific error handling when requests to geocode fail
			// No data is submitted to database if coords cannot be obtained
				
			} else if (status == 'OVER_QUERY_LIMIT' || status == 'UNKNOWN_ERROR') {
				alert("Internal server error; please try again later");
			} else {
				alert("Error in reading address; please verify it is correct");
			}
		});
		
	});
	
})

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
			console.log(json);
			 sessionStorage.setItem('user', JSON.stringify(json));
			 var user = sessionStorage.getItem('user')
			console.log(user);
			 window.location = "/userpage";
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
	
})

$( function() {
    $( "#dialog" ).dialog({
    	autoOpen:false,
    	buttons: {
            OK: function() {$(this).dialog("close");}
         },
         hide: "puff",
         show : "slide",
         modal: true,
    });
    $( "#submitLogin" ).click(function() {
        $( "#dialog" ).dialog( "open" );
     });
});

$('#getMatches').click(function(){
	
	$.get("/userHomeMatch", function(data){
		console.log(data);
		window.location = "/matches";
			
	})
	
})


// This stuff goes in the html file:
// 	<!-- Google Maps API js -->
//	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCOZn3F91JHZrrimG2A41q2WAwSNgMs1Cc"
//   defer></script>
//	<!-- DO NOT LOSE THIS GOOGLE MAPS API KEY: AIzaSyCOZn3F91JHZrrimG2A41q2WAwSNgMs1Cc -->
//	
//	<!-- Custom JavaScript -->
//	<script src="js/signUp.js"></script>