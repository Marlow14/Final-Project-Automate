

$(function(){
    var table = $("#matchResult").DataTable({
    	colReorder: true, 
    	select: true, 
    	"order": [[ 6, "asc" ]],
    	"columnDefs": [
    	               {
    	                   "targets": [ 3 ],
    	                   "visible": false
    	               },
    	               {
    	                   "targets": [ 7 ],
    	                   "visible": false
    	               },
    	           ]
    
    });
     
    $('#matchResult tbody').on( 'click', 'tr', function () {
        
        var data = table.row( this ).data();
        $("#name").val(data[1]);
        $("#email").val(data[7]);
        $("#cellPhone").val(data[3]);

    } );

})
