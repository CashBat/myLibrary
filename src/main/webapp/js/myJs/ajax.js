
  function addRental(cl) {
      $.ajax({
            type: "POST",
            data: JSON.stringify(cl),
            url: "service/main/clothes/add",
            contentType: "application/json",
			dataType: "json",
            success: function(data) {
            	var id = data.id || "";
                var storeId = data.store != null ? data.store.id : "";
                var sizeId = data.size != null ? data.size.id : "";
                var typeId = data.type != null ? data.type.id : "";
                var colorId = data.color != null ? data.color.id : "";
                var desc = data.shortDescription;
                var rowStr = generateClothesRow(id, storeId, typeId, sizeId, colorId, desc);

                var store = storeId == 2 ? $shopClothes : $stockClothes;
                store.append(rowStr);
                $.notify("Новая одежда добавлена (id: " + id + ")", "success");
            }
      });
    }


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

function loadBooks(tableBook) {
	$.ajax({
		url : "service/main/books",
		type : "get",
		success : function(data) {

			$.each(data, function(i, item) {
				var book = item;
				var bookCode = book.id;
				var bookName = book.name;
				var genre = book.genre;
				var bookGenre = genre.name;
				var bookDescription = book.description;
				var bookAvailability = book.availability;

				tableBook.append(
						
						"<tr class='book-info'>" + 

						"<td class='book-id not-visible-field'>"+bookCode+"</td>" +
						"<td class='not-visible-field'>"+  bookName + "</td>" +
						"<td class='txt-info'>" +

							"<div class='row'>" +
								"<div class='col'>" +
									"<div class='row'>" +

										"<div class='col'>" +
											"<p class='title'>"+bookCode+": "+  bookName + "</p>" +
										"</div>" +
									"</div>" +

									"<div class='row'>" +
										"<div class='col'>" +
											"<p class='specification'>Жанр: " + bookGenre+"</p>" +
										"</div>" +
									"</div>" +

									"<div class='row'>" +
										"<div class='col'>" +
											"<p class='summary'>"+ bookDescription +"</p>" +
										"</div>" +
									"</div>" +

									"<div class='row'>" +
										"<div class='col'>" +
											"<p class='status'>Наличие: "+bookAvailability+"</p>" +
										"</div>" +
									"</div>" +
									"<p></p>" +

								"</div>" +
							"</div>" +
						"</td>" +
					"</tr>"					
						/*
						 * "<tr class='book-info'>" + "<td class='book-id'>" +
						 * bookCode + "</td>" + "<td>" + bookName + "</td>" + "<td>" +
						 * bookGenre + "</td>" + "<td>" + bookDescription + "</td>" + "<td>" +
						 * bookAvailability + "</td>" + "</tr>"
						 */)
			});

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			echoInfo(XMLHttpRequest.responseText);

		}
	});
};

function loadRentalInfoBooks(ReadTicketId) {

	$.ajax({
		url : "service/main/rentalInfoBooks/" + ReadTicketId,
		type : "get",
		success : function(data) {

			$.each(data, function(i, item) {
				var recordRentTictetID = item.idRecordRiderTicket;
				var bookCode = item.idBook;
				var dateIssue = item.dateIssue;
				var quantityRentDay = item.quantityRentDay;
				var returnDate = item.returnDate;
				var statusRental = item.statusRental;

				$("#rent-tablr-info table tbody").append(
						
						
						"<tr class='record-info' data-toggle='modal'"
						+ "data-target='#add-dialog'>"

						+ "<td class='record-rent-id'>"+ recordRentTictetID +"</td>"
						+ "<td>"+ bookCode + "</td>"
						+ "<td>"+ dateIssue + "</td>"
						+ "<td>"+ quantityRentDay + "</td>"
						+ "<td>"+ returnDate + "</td>"
						+ "<td>"+ statusRental + "</td>"
						+ "<td>"
						+ "	<div class='row'>"
						+ "		<div class='col image-two'>"

						+ "			<svg width='37' height='37'"
						+ "				xmlns='http://www.w3.org/2000/svg'>"

							+ "<path"
							+ "								d='m18,1c-9.4,0 -17,7.6 -17,17s7.6,17 17,17s17,-7.6 17,-17s-7.6,-17 -17,-17zm0,32.4c-8.5,0 -15.4,-6.9 -15.4,-15.4s6.9,-15.4 15.4,-15.4s15.4,6.9 15.4,15.4s-6.9,15.4 -15.4,15.4z' />"
							+ "<path"
							+ "					d='m23.5,11l-4,0l0,-0.5c0,-0.3 -0.2,-0.5 -0.5,-0.5l-2,0c-0.3,0 -0.5,0.2 -0.5,0.5l0,0.5l-4,0c-0.6,0 -1,0.4 -1,1s0.4,1 1,1l11,0c0.6,0 1,-0.4 1,-1s-0.4,-1 -1,-1zm-10.5,13c0,0.6 0.4,1 1,1l8,0c0.6,0 1,-0.4 1,-1l0,-10l-10,0l0,10zm6,-7.5c0,-0.3 0.2,-0.5 0.5,-0.5s0.5,0.2 0.5,0.5l0,6c0,0.3 -0.2,0.5 -0.5,0.5s-0.5,-0.2 -0.5,-0.5l0,-6zm-3,0c0,-0.3 0.2,-0.5 0.5,-0.5s0.5,0.2 0.5,0.5l0,6c0,0.3 -0.2,0.5 -0.5,0.5s-0.5,-0.2 -0.5,-0.5l0,-6z' />"

							+ "</svg>"
							+ "</div>"

							+ "	<div class='col image-two'>"

							+ "<svg width='37' height='37'"
							+ "	xmlns='http://www.w3.org/2000/svg'>"

								+ "	<path"
								+ "	d='m19,1c-9.4,0 -17,7.6 -17,17s7.6,17 17,17s17,-7.6 17,-17s-7.6,-17 -17,-17zm0,32.4c-8.5,0 -15.4,-6.9 -15.4,-15.4s6.9,-15.4 15.4,-15.4s15.4,6.9 15.4,15.4s-6.9,15.4 -15.4,15.4z'"
								+ "	id='svg_2' />"
								+ "<path fill-rule='evenodd'"
								+ "d='m10,23.67573l0,4.32427l4.10355,0l10.9428,-11.53137l-4.10355,-4.32427l-10.9428,11.53137zm4.10355,2.88285l-2.7357,0l0,-2.88285l1.36785,0l0,1.44142l1.36785,0l0,1.44142l0,0.00001zm14.08886,-13.40522l-1.77821,1.87385l-4.10355,-4.32427l1.77821,-1.87385a1.36238,1.43566 0 0 1 1.92867,0l2.17488,2.29186c0.53346,0.56215 0.53346,1.47025 0,2.0324l0,0.00001z' />"
				


								+ "</svg>"

								+ "</div>"


								+ "</div>"
								+ "</td>"
								+ "</tr>"
	/*					
						"<tr class='record-info'>" + "<td class='record-rent-id'>"
								+ recordRentTictetID + "</td>" + "<td>"
								+ bookCode + "</td>" + "<td>" + bookName
								+ "</td>" + "<td>" + dateIssue + "</td>"
								+ "<td>" + quantityRentDay + "</td>" + "<td>"
								+ bookCode + "</td>" + "<td>" + statusRental
								+ "</td>" + "</tr>"*/)
			});

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			echoInfo(XMLHttpRequest.responseText);

		}
	});
};

