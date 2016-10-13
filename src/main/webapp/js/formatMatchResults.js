

$(function(){
    var table = $("#matchResult").DataTable({colReorder: true, select: true})
     
    $('#matchResult tbody').on( 'click', 'tr', function () {
        
        var data = table.row( this ).data();
        $("#name").val(data[0] + " " + data[1]);
        $("#email").val(data[2]);
        $("#cellPhone").val(data[3])
    } );

})