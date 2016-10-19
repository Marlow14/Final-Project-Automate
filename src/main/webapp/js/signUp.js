/** JS for sign up form **/

// This function runs when a user uses the sign up form and is run before any data is submitted to the database

$(function(){
	
    $('#submitSignUp').click(function(){
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
				alert("Error in reading addresses; please verify the addresses are correct");
			}
		});
		
		geocoder.geocode({ 'address': workAddress}, function(results, status) {
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
				alert("Error in reading addresses; please verify the addresses are correct");
			}
		});
		
		$.when(geocode1, geocode2).then(function() {
			$.post("/user", $("#signUpForm").serialize(), function(data){
				window.location = '/profile';
			})
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