/** JS for sign up form **/

// This function runs when a user uses the sign up form and is run before any data is submitted to the database

$(function(){
	
	var validateSignUp = function (){
		return $("#signUpForm").validate({
	
		errorClass:"text-danger",
		rules:{
			firstName:{required:true, minlength:2},
			lastName:{required:true, minlength:2},
			gender:{required:true},
			homeAddress:{required:true},
			email:{required:true, email:true},
			cellphone:{required:true},
			newUserName:{required:true, minlength:3},
			newPassword:{required:true, minlength:6},
			confirmedPassword:{required:true, equalTo:"#newPassword"}
		},
		messages:{
			firstName:{required:"Must enter a first name", minlength:"First name must be at least 2 characters long"},
			lastName:{required:"Must enter a last name", minlength:"Last name must be at least 2 characters long"},
			gender:{required:"Gender required"},
			homeAddress:{required:"Must enter an address"},
			email:{required:"Must enter an email",email:"Please enter a valid email"},
			cellphone:{required:"Must enter a phone number"},
			newUserName:{required:"Must enter a username", minlength:"Must be at least 3 characters long"},
			newPassword:{required:"Must enter a password", minlength:"Password must be at least 6 characters long"},
			confirmedPassword:{required:"Must confirm password", equalTo:"Does not match password"}
			}
		})

	}
	
    $('#submitSignUp').click(function(){

		var signUpValid = validateSignUp();
    	
    	if (!$("#signUpForm").valid()){
			return false;
		}
	
		var geocode1 = $.Deferred();
		var geocode2 = $.Deferred();
	
		var homeAddress = $("#homeAddress").val();
		var workAddress = $("#workAddress").val();
		var geocoder;
		geocoder = new google.maps.Geocoder();
		
		// Uses Google Maps API to geocode the user's address as a pair of lag/long coords
		
		geocoder.geocode( { 'address': homeAddress}, function(results, status) {
			if (status == 'OK') {
				var homeLat = results[0].geometry.location.lat();
				var homeLng = results[0].geometry.location.lng();
				$("#homeLat").val(homeLat);
				$("#homeLng").val(homeLng);	

				geocode1.resolve();
					
				
			// Specific error handling when requests to geocode fail
			// No data is submitted to database if coords cannot be obtained
				
			} else if (status == 'OVER_QUERY_LIMIT' || status == 'UNKNOWN_ERROR') {
				alert("Internal server error; please try again later");
			} else {
				alert("Error in reading home address; please verify it is correct");
			}
		});
		
		geocoder.geocode( { 'address': workAddress}, function(results, status) {
			if (status == 'OK') {
				var workLat = results[0].geometry.location.lat();
				var workLng = results[0].geometry.location.lng();
				$("#workLat").val(workLat);
				$("#workLng").val(workLng);	

				geocode2.resolve();
				
			// Specific error handling when requests to geocode fail
			// No data is submitted to database if coords cannot be obtained
				
			} else if (status == 'OVER_QUERY_LIMIT' || status == 'UNKNOWN_ERROR') {
				alert("Internal server error; please try again later");
			} else {
				alert("Error in reading work address; please verify it is correct");
			}
		});
		
		$.when(geocode1, geocode2).then(function() {
			$.post("/user", $("#signUpForm").serialize(), function(data){
				window.location = '/profile';
			})
		})
		
		$("#signupDialog").dialog({
	    	autoOpen:false,
	    	buttons: {
	            OK: function() {$(this).dialog("close");}
	        },
	        hide: "puff",
	        show : "slide"
	    });
		
	});
	
})


// This stuff goes in the html file:
// 	<!-- Google Maps API js -->
//	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCOZn3F91JHZrrimG2A41q2WAwSNgMs1Cc"
//   defer></script>
//	<!-- DO NOT LOSE THIS GOOGLE MAPS API KEY: AIzaSyCOZn3F91JHZrrimG2A41q2WAwSNgMs1Cc -->
//	
//	<!-- Custom JavaScript -->
//	<script src="js/signUp.js"></script>