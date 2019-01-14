
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

function loadReaderTicket(ReadTicketId) {

	$.ajax({
		url : "service/main/rentalInfoBooks/" + ReadTicketId,
		type : "get",
		success : function(data) {
			
			var reader = data.record.readerTicket;
			$("#fioRider").html(reader.fio);
			$("#telRider").html(reader.tel);
			/*
			$.each(data.records, function(i, item) {
				var recordRentTictetID=item.id;
				var book=item.book;
				var dateIssue =
				var quantityRentDay
				var returnDate
				var statusRental
				
				$("#recordRiderTickedPanel table").append(
						"<tr class='recordRent'>"+
						"<td class='idRecordRent'>"+item.id+"</td>"+
						"<td>"+item.id+"</td>"+
						"<td>Otto</td>"+
						"<td>@mdo</td>"+
						"</tr>"
				)
				

			});*/
		}
	});
};

