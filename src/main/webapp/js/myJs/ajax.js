function loadBooksStore() {
	$.ajax({
		url : "service/main/books",
		type : "get",
		success : function(data) {

			$.each(data, function(i, item) {

				var id = item.id || "";
				indx = id;
				var title = item.title != null ? item.title : "";
				var rowStr = generateBoksList(id, title);
				$listBoks.append(rowStr);
				books.set(id, item);

			});

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.responseText);
			alert(XMLHttpRequest.statusText);
			alert(textStatus);
			alert(errorThrown);
		}

	});
};

function loadReader(ReadTicketId) {
	$.ajax({
		url : "service/main/reader/" + ReadTicketId,
		type : "get",
		success : function(data) {

			var reader = data;
			$("#fioRider").html(reader.fio);
			$("#telRider").html(reader.tel);

		}
	});
};

function loadRentalInfoBooks(ReadTicketId) {

	$.ajax({
		url : "service/main/rentalInfoBooks/" + ReadTicketId,
		type : "get",
		success : function(data) {

			$.each(data, function(i, item) {
				var record = item.record;
				var recordRentTictetID = record.id;
				var book = record.book;
				var bookCode = book.id;
				var bookName = book.name;
				var dateIssue = record.dateIssue;
				var quantityRentDay = record.quantityRentDay;
				var returnDate = record.returnDate;
				var statusRental = item.statusRental;

				$("#rent-tablr-info table tbody").append(
						"<tr class='record-info'>" + "<td class='record-rent-id'>"
								+ recordRentTictetID + "</td>" + "<td>"
								+ bookCode + "</td>" + "<td>" + bookName
								+ "</td>" + "<td>" + dateIssue + "</td>"
								+ "<td>" + quantityRentDay + "</td>" + "<td>"
								+ bookCode + "</td>" + "<td>" + statusRental
								+ "</td>" + "</tr>")
			});

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			echoInfo(XMLHttpRequest.responseText);

		}
	});
};

