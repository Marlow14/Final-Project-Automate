

$(function(){
    var table = $("#matchResult").DataTable({
		colReorder: true, 
		select: true,
		"order": [[9, "asc"]],
		"columnDefs": [
			{
    	        "targets": [3],
    	        "visible": false
    	    },
    	    {
    	        "targets": [5],
    	        "visible": false
    	    },
			{
    	        "targets": [8],
    	        "visible": false
    	    },
			{
    	        "targets": [9],
    	        "visible": false
    	    }
    	] 
	})
     
    $('#matchResult tbody').on('click', '.mapButton', function(){
         
        var data = table.row( $(this).closest("tr") ).data();
         
        setTimeout(function(){
         	window.open("https://www.google.com/maps/dir/" + data[3] +"/" + data[8] + "/" + data[9] + "/"+ data[5] +"/", "mapWindow", "width=1200,height=900");
        }, 500);
    });

})