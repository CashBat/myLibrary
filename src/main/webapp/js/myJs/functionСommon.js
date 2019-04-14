var id = 0;
var $listBoks = $('#sel2');

function getFileName(xhr) {
	var cd = xhr.getResponseHeader('Content-Disposition');
	var filename = cd && cd.match(/filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/)[1];
	return filename;
}

function getTodayDate() {
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;
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

function cleanRentTabElement() {
	$("#rent-tablr-col table tbody").find("tr").remove();
	$("#fioRider").html("...");
	$("#telRider").html("...");
}

function cleanTabElement(tableBody) {
	tableBody.find("tr").remove();
}

function saveRecord(rentalInfo) {
	var addDialog = $("#add-dialog");

	var actionStatus = addDialog.find('label[name=action-status-label]').text();
	var idBook = addDialog.find('input[name=id-book-input]');
	var quantityRentDay = addDialog.find('input[name=rent-day-input]');
	var dateIssue = addDialog.find('input[name=date-issue-input]');
	var returnDate = addDialog.find('input[name=return-date-input]');

	var alert = addDialog.find('.alert-danger');
	alert.html("");

	var errCount = 0;

	if (!idBook.val()) {
		alert
				.append('<div><strong>Внимание!</strong> Отсутствует внутренний номер книги!</div>');
		idBook.focus();
		errCount++;
	}
	if (!quantityRentDay.val()) {
		alert
				.append('<div><strong>Внимание!</strong> Отсутствует количество арендуемых дней!</div>');
		quantityRentDay.focus();
		errCount++;
	}
	if (!dateIssue.val()) {
		alert.append('<div><strong>Внимание!</strong> Отсутствует дата выдачи книги!</div>');
		dateIssue.focus();
		errCount++;
	}

	if (errCount == 0) {
		rentalInfo.idBook = idBook.val();
		rentalInfo.quantityRentDay = quantityRentDay.val();
		rentalInfo.dateIssue = dateIssue.val();
		rentalInfo.returnDate = returnDate.val();
		performAction(actionStatus, rentalInfo)
		addDialog.modal('hide');
		echoInfo("Отправлено");
	} else {
		alert.show();
	}
}

function performAction(actionStatus, rentalInfo) {
	switch (actionStatus) {
	case '1':

		addRental(rentalInfo);

		break;
	case '2':

		editRental(rentalInfo);

		break;

	default:

		break;
	}
		
}

function filterBookTableForName() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("filter-table-book-by-name");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("book-table");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
	}

function filterTable($table) {
    var $filters = $table.find('.table-filters td');
    var $rows = $table.find('.table-data');
    $rows.each(function (rowIndex) {
        var valid = true;
        $(this).find('td').each(function (colIndex) {
            if ($filters.eq(colIndex).find('input').val()) {
                if ($(this).html().toLowerCase().indexOf(
                $filters.eq(colIndex).find('input').val().toLowerCase()) == -1) {
                    valid = valid && false;
                }
            }
        });
        if (valid === true) {
            $(this).css('display', '');
        } else {
            $(this).css('display', 'none');
        }
    });
}
