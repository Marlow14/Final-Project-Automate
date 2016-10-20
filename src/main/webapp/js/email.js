

$(function(){
	
	$("#tableContainer").delegate(".emailButton", "click", function(){
		var id = $(this).data("id")

		$.post("/sendEmail/" + id, function(){
			alert("Email sent!");
			
		})
	})
})