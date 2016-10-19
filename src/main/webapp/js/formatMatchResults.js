
$(function(){
    var table = $("#matchResult").DataTable({
    	colReorder: true, 
    	select: true, 
    	"order": [[ 9, "asc" ]],
    	"columnDefs": [
    	               {
    	                   "targets": [ 3 ],
    	                   "visible": false
    	               },
    	               {
    	                   "targets": [ 4 ],
    	                   "visible": false
    	               },
    	               {
    	                   "targets": [ 5 ],
    	                   "visible": false
    	               },
    	               {
    	                   "targets": [ 7 ],
    	                   "visible": false
    	               },
    	               {
    	                   "targets": [ 10 ],
    	                   "visible": false
    	               },
    	               {
    	                   "targets": [ 11 ],
    	                   "visible": false
    	               },
    	           ]
    
    });
     
    $('#matchResult tbody').on( 'click', '.mapButton', function () {
        
        var data = table.row( $(this).closest("tr") ).data();
        
        setTimeout(function(){
        	 window.open("https://www.google.com/maps/dir/" + data[5] +"/" + data[10] + "/" + data[11] + "/"+ data[7] +"/", "mapWindow", "width=1200,height=900");
        	}, 500);
    } );
    
    $('#matchResult tbody').on( 'click', '.emailButton', function () {
        
        var data = table.row( $(this).closest("tr") ).data();
        $("#name").val(data[1]);
        $("#email").val(data[4]);
        $("#cellPhone").val(data[3]);
        
        $(document).scrollTop( $("#contactForm").offset().top);
        
    } );
    
})
