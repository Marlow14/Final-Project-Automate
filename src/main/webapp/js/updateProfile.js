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

				
				$.put("/user/${session.user.userId}", $("#updateForm").serialize(), function(data){
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