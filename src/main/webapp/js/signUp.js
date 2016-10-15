/** JS for sign up form **/

// This function runs when a user uses the sign up form and is run before any data is submitted to the database

$(function(){
	
	$("#loginForm").validate({
		onsubmit:false,
		errorClass:"text-danger",
		rules:{
			firstName:{required:true, minlength:1},
			lastName:{required:true, minlength:1},
			gender:{required:true},
			homeAddress:{required:true},
			email:{required:true,
					email:true},
			cellphone:{required:true},
			newUserName:{required:true, minlength:3},
			newPassword:{required:true, minlength:6},
			confirmedPassword:{required:true,
								equalTo:"#newPassword"}
		},
		messages:{
			firstName:{required:"Must enter a first name", minlength:"First name must be at least 2 characters long"},
			lastName:{required:"Must enter a last name", minlength:"Last name must be at least 2 characters long"},
			gender:{required:"Gender required"},
			homeAddress:{required:"Must enter an address"},
			email:{required:"Must enter an email",email:"Please enter a valid email"},
			cellphone:{required:"Must enter a phone number"},
			newUserName:{required:"Must enter a username", minlength:"Must be at least 3 characters long"},
			newPassword:{required:"Must enter a password", minlength:"Password must be at least 6 characters long"}
		}
	})
	
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

				
				$.post("/user", $("#signUpForm").serialize(), function(data){
					window.location = '/profile';
				})
					
				
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


// This stuff goes in the html file:
// 	<!-- Google Maps API js -->
//	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCOZn3F91JHZrrimG2A41q2WAwSNgMs1Cc"
//   defer></script>
//	<!-- DO NOT LOSE THIS GOOGLE MAPS API KEY: AIzaSyCOZn3F91JHZrrimG2A41q2WAwSNgMs1Cc -->
//	
//	<!-- Custom JavaScript -->
//	<script src="js/signUp.js"></script>