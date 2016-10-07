/** JS for sign up form **/

// This function runs when a user uses the sign up form and is run before any data is submitted to the database

$(function(){
	var mapApiCallSuccess = false;
	
    $('#submitSignUp').click(function(e){
		e.preventDefault();
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
				mapApiCallSuccess = true;
				
			// Specific error handling when requests to geocode fail
			// No data is submitted to database if coords cannot be obtained
				
			} else if (status == 'OVER_QUERY_LIMIT' || status == 'UNKNOWN_ERROR') {
				alert("Internal server error; please try again later");
				mapApiCallSuccess = false;
			} else {
				alert("Error in reading address; please verify it is correct");
				mapApiCallSuccess = false;
			}
		});
		
		if(mapApiCallSuccess) {
			$.post("/user", $("#signUpForm").serialize(), function(data){
		
			}, "json")	
		}
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