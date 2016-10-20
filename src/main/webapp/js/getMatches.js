$('#getMatches').click(function(){
	
	$.get("/userMatch", function(data){
		window.location = "/matches";
			
	})
	
})

$('#getMatches2').click(function(){
	
	$.get("/userMatch", function(data){
		window.location = "/matches";
			
	})
	
})