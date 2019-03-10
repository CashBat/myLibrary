var isAddBookMode =false;
var reaaderTicketId=0;
var addDialog=$('#add-dialog');

$(document).ready(function() {
		
	$('#find-book-archive').click(
		function() {		
			$('#list-storage-list').click();
			isAddBookMode=true;
			$.notify("Выберите книгу для добовления");		
		}
	);


	
$(document).on("click",".book-info", function(){
	var a = $(this).find('.book-id').first().text();
	echoInfo(a);
	$(".book-info").removeClass('fixedRowTable');
	$(this).addClass('fixedRowTable'); 
		
		if(isAddBookMode){			
			$('#list-rent-list').click(); 
			addDialog.find("#id-book").val(a);
			addDialog.modal('show') ;
			isAddBookMode=false;		 
		}
		
});

	$("#search-rider-ticket").click(function() {
		cleanRentTabElement();
		reaaderTicketId = $("#input-reader-ticket-id").val();
		if (reaaderTicketId != null) {
			loadReader(reaaderTicketId);
			loadRentalInfoBooks(reaaderTicketId);
		}
	});

	
$(document).on("click",".record-info", function(){
	var a = $(this).find('.record-rent-id').first().text();
	echoInfo(a);
	$(".record-info").removeClass('fixedRowTable');
	$(this).addClass('fixedRowTable'); 
});	
	

});

