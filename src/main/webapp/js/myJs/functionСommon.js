var id = 0;
var $listBoks = $('#sel2');

function getTodayDate() {
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    var today = year + "-" + month + "-" + day; 
   return today;
}

function addClothes(nameBook) {
	var rowStr = generateClothesRow(nameBook);
	$listBoks.append(rowStr);

}

function handleAddButton(el) {
	var addBookForm = $("#add-book-form");
	var nameBook = addBookForm.find('input[name=nameBook]').val();
	addClothes(nameBook);
}
function echoSucces(el) {

	$.notify("Одежда сохранена (id: " + el + ")", "success");
}

function echoInfo(el) {

	$.notify(el, "info");
}

function generateClothesRow(nameBook) {
	var html = [];
	id = id + 1;
	html.push('<option>' + id + '</option>',
			'<option onclick="echo(this.value)" value=' + id + '>' + nameBook
					+ '</option>');
	return html.join("");
}

var title;


function clickListGroup(event) {
	refreshDesktop(event);
}

function refreshDesktop(event) {
	$("#titleDesktop").html(title + ": " + event.target.text);
}

function cleanRentTabElement(){
	$("#rent-tablr-info table tbody").find("tr").remove();
	$("#fioRider").html("...");
	$("#telRider").html("...");	
}


function cleanTabElement(table){
	table.find("tr").remove();	
}



function fieldValidation() {
    	var addDialog = $("#add-dialog");
   
        var riaderTicket = addDialog.find('label[name=reaader-ticket-label]');
		var codeBook = addDialog.find('input[name=id-book-input]');
		var rentDay = addDialog.find('input[name=rent-day-input]');
		var dateIssue = addDialog.find('input[name=date-issue-input]');

		
	      
		var rental = {
				idRiderTicket: riaderTicket.text(),
				idBook: codeBook.val(),
				quantityRentDay: rentDay.val(),
				dateIssue: dateIssue.val()
				}
		
        var alert = addDialog.find('.alert-danger');
        
        var errCount = 0;
    /*    if (!riaderTicket.text()) {
          alert.append('<div><strong>Внимание!</strong> Отсутствует читательский билет!</div>');
          riaderTicket.focus();
          errCount++;
        }
        if (!codeBook.val()) {
          alert.append('<div><strong>Внимание!</strong> Отсутствует внутренний номер книги!</div>');
          codeBook.focus();
          errCount++;
        }
		 if (!rentDay.val()) {
          alert.append('<div><strong>Внимание!</strong> Отсутствует количество арендуемых дней!</div>');
          rentDay.focus();
          errCount++;
        }*/
        if (errCount == 0) {
        	addRental(rental);
          addDialog.modal('hide');
		  echoInfo("Отправлено");
        } else {
          alert.show();
        }
    }

