/** JS for updating user profile data **/

$.put = function(url, data, callback, type){
 
  if ( $.isFunction(data) ){
    type = type || callback,
    callback = data,
    data = {}
  }
 
  return $.ajax({
    url: url,
    type: 'PUT',
    success: callback,
    data: data,
    contentType: type
  });
}

$(function(){
	
    $('#updateProfile').click(function(){

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
			$.put("/user/${session.user.userId}", $("#updateForm").serialize(), function(data){
				window.location = '/profile';
			})
		});
		
	});
	
})