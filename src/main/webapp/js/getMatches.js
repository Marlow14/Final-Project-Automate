$('#getMatches').click(function(){
	
	$.get("/userHomeMatch", function(data){
		window.location = "/matches";
			
	})
	
})