
    // Get the modal
    var modal = document.getElementById("privacyModal");

    // Get the button that opens the modal
    var btn = document.getElementById("privacy");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
		modalTwo.style.display = "none";
    
    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
			modal.style.display = "none";
        }
    }};

    var modalTwo = document.getElementById("termsModal");

    var btnTwo = document.getElementById("terms"); 

    var spanTwo = document.getElementsByClassName("closeTwo")[0]; 

    btnTwo.onclick = function() {
        modalTwo.style.display = "block";
		modal.style.display = "none";

    spanTwo.onclick = function() {
        modalTwo.style.display = "none"; 
    }

    window.onclick = function(event) {
        if (event.target == modalTwo) {
            modalTwo.style.display = "none";
        }
    }};