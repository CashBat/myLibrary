function loadPdf(idReport) {	
	var url2 ="service/main/filePdf"+"?idReport=" + idReport;
	var url = location.href+url2;	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4) {
	        if(xhr.status == 200) {
	            console.log(typeof xhr.response); // should be a blob
	        } else if(xhr.responseText != "") {
	            console.log(xhr.responseText);
	        }
	    } else if(xhr.readyState == 2) {
	        if(xhr.status == 200) {
	        	xhr.responseType = "blob";
	        } else {
	        	xhr.responseType = "text";
	        }
	    }
};
	xhr.open('GET', url);
	xhr.onload = function () {
		{			
		    if(xhr.status == 200) {
		    	var myHeader = xhr.getResponseHeader("filename");
		    	 var blob = xhr.response;
			        var link=document.createElement('a');
			        link.href=window.URL.createObjectURL(blob);
			        link.download=getFileName(xhr);
			        document.body.appendChild(link);
			        link.click();
			        document.body.removeChild(link);
			        console.log(xhr.getResponseHeader("Content-Type"));
			        console.log(xhr.getResponseHeader("Content-Disposition"));
		    } else
		    	{
		    	var textError="Код: "+xhr.status+"Сообщение: "+xhr.responseText;
		    	echoInfo(textError);  	
		    	}		    
		}
		
	}
	xhr.send();

}


function loadReportOptions() {
	$.ajax({
		url : "service/main/reports",
		type : "get",
		success : function(data) {

			$.each(data, function(i, report) {
				var id = report.id;
				var shortName = report.shortName;
				var fullName = report.fullName;
				
				$('#panel-report-element').append(
						"<div class='col-4'>"
						+"<div class='row'>"
						+"<div class='col'>"
						+"	<p class='title'>"+shortName+"</p>"
						+"	</div>"
						+"	</div>"
						+"<div class='row'>"
						+"<div class='col image-report'data-id="
						+ id 
						+ ">"
						+ "	<svg width='80' height='80' xmlns='http://www.w3.org/2000/svg'>"
							+ " <g>"
							+ "	  <path d='m73.56118,19.00262c-1.80936,-2.24912 -4.33307,-4.87587 -7.10186,-7.4005s-5.65489,-4.82125 -8.12386,-6.4695c-4.20271,-2.80725 -6.23889,-3.13262 -7.40689,-3.13262l-40.41071,0c-3.59264,0 -6.51786,2.66237 -6.51786,5.9375l0,64.125c0,3.27275 2.92521,5.9375 6.51786,5.9375l59.96429,0c3.59264,0 6.51786,-2.66475 6.51786,-5.9375l0,-46.3125c0,-1.064 -0.35718,-2.92125 -3.43882,-6.74737l-0.00001,-0.00001zm-10.78836,-4.04225c2.50025,2.27762 4.46343,4.33437 5.913,6.03962l-12.54296,0l0,-11.42612c1.87193,1.3205 4.12971,3.10887 6.62996,5.3865zm9.01289,57.10212c0,0.64362 -0.59704,1.1875 -1.30357,1.1875l-59.96429,0c-0.70654,0 -1.30357,-0.54387 -1.30357,-1.1875l0,-64.125c0,-0.64362 0.59704,-1.1875 1.30357,-1.1875c0,0 40.40811,0 40.41071,0l0,16.625c0,1.311 1.168,2.375 2.60714,2.375l18.25,0l0,46.3125l0.00001,0z'/>"
							+ "	  <path d='m58.75,63.75l-36.5,0c-1.43914,0 -2.60714,-1.064 -2.60714,-2.375s1.168,-2.375 2.60714,-2.375l36.5,0c1.43914,0 2.60714,1.064 2.60714,2.375s-1.168,2.375 -2.60714,2.375z' />"
							+ "	  <path d='m58.75,54.25l-36.5,0c-1.43914,0 -2.60714,-1.064 -2.60714,-2.375s1.168,-2.375 2.60714,-2.375l36.5,0c1.43914,0 2.60714,1.064 2.60714,2.375s-1.168,2.375 -2.60714,2.375z' />"
						  + "	  <path d='m58.75,44.75l-36.5,0c-1.43914,0 -2.60714,-1.064 -2.60714,-2.375s1.168,-2.375 2.60714,-2.375l36.5,0c1.43914,0 2.60714,1.064 2.60714,2.375s-1.168,2.375 -2.60714,2.375z' />"
						  + "	 </g>"
						  + "	</svg>"
						+"</div>"
					+"</div>"
					+"<div class='row'>"
						+"<div class='col'>"
							+"<p>"+fullName+"</p>"
						+"</div>"
					+"</div>"
				+"</div>")
				});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			echoInfo(XMLHttpRequest.responseText);

		}
	});
};


function removeRecordRenatlInfo(reaaderTicketId, idRecord) {
	$.ajax({
		type : "DELETE",
		url : "service/main/rental/remove/" + idRecord,
		success : function(data, statusText, jqXHR) {
			loadRentalInfoBooks(reaaderTicketId);
			// $.notify(jqXHR.origUrl, "success");
			$.notify("Запись удалена", "success");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			echoInfo(XMLHttpRequest.responseText);
		}
	});
}

function saveClothes(cl) {
	$.ajax({
		type : "PUT",
		url : "service/main/clothes/save",
		data : JSON.stringify(cl),
		contentType : "application/json",
		success : function(data) {
			$.notify("Одежда сохранена (id: " + cl.id + ")", "success");
		}
	});
}

function editRental(rentalInfo) {
	$.ajax({
		type : "PUT",
		data : JSON.stringify(rentalInfo),
		url : "service/main/rental/edit",
		contentType : "application/json",

		success : function(data, statusText, jqXHR) {
			loadRentalInfoBooks(rentalInfo.idRiderTicket);
			// $.notify(jqXHR.origUrl, "success");
			$.notify("Запись обновлена", "success");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			echoInfo(XMLHttpRequest.responseText);
		}
	});
}

function addRental(rentalInfo) {
	$.ajax({
		type : "POST",
		data : JSON.stringify(rentalInfo),
		url : "service/main/rental/add",
		contentType : "application/json",

		success : function(data, statusText, jqXHR) {
			loadRentalInfoBooks(rentalInfo.idRiderTicket);
			// $.notify(jqXHR.origUrl, "success");
			$.notify("Запись создана", "success");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			echoInfo(XMLHttpRequest.responseText);
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

				tableBook.find('tbody').append(

						"<tr>" +

						"<td class='book-id not-visible-field'>" + bookCode
								+ "</td>" + "<td class='not-visible-field'>"
								+ bookName + "</td>" + "<td class='txt-info'>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<div class='row'>" +

								"<div class='col'>" + "<p class='title'>"
								+ bookCode + ": " + bookName + "</p>"
								+ "</div>" + "</div>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<p class='specification'>Жанр: " + bookGenre
								+ "</p>" + "</div>" + "</div>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<p class='summary'>" + bookDescription
								+ "</p>" + "</div>" + "</div>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<p class='status'>Наличие: "
								+ bookAvailability + "</p>" + "</div>"
								+ "</div>" + "<p></p>" +

								"</div>" + "</div>" + "</td>" + "</tr>"
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

				tableBook.find('tbody').append(

						"<tr>" +

						"<td class='book-id not-visible-field'>" + bookCode
								+ "</td>" + "<td class='not-visible-field'>"
								+ bookName + "</td>" + "<td class='txt-info'>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<div class='row'>" +

								"<div class='col'>" + "<p class='title'>"
								+ bookCode + ": " + bookName + "</p>"
								+ "</div>" + "</div>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<p class='specification'>Жанр: " + bookGenre
								+ "</p>" + "</div>" + "</div>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<p class='summary'>" + bookDescription
								+ "</p>" + "</div>" + "</div>" +

								"<div class='row'>" + "<div class='col'>"
								+ "<p class='status'>Наличие: "
								+ bookAvailability + "</p>" + "</div>"
								+ "</div>" + "<p></p>" +

								"</div>" + "</div>" + "</td>" + "</tr>"
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

	var rentTablrInfotBody = $("#rent-tablr-col table tbody");
	cleanTabElement(rentTablrInfotBody);

	$
			.ajax({
				url : "service/main/rentalInfoBooks/" + ReadTicketId,
				type : "get",
				success : function(data) {

					$
							.each(
									data,
									function(i, item) {
										var recordRentTictetID = item.idRecordRiderTicket;
										var bookCode = item.idBook;
										var dateIssue = item.dateIssue;
										var quantityRentDay = item.quantityRentDay;
										var returnDate = item.returnDate != null ? item.returnDate
												: "";
										var statusRental = item.statusRental;
										var statusRentalText = "";
										if (item.statusRental == -1) {
											statusRentalText = "Просрочено";
										} else {
											if (item.returnDate == null) {
												statusRentalText = "В прокате";
											} else {
												statusRentalText = "Возвращено";
											}
										}

										rentTablrInfotBody
												.append(

												"<tr class='record-info'>"
														+ "<td class='record-rent-id'>"
														+ recordRentTictetID
														+ "</td>"
														+ "<td class='record-rent-book-id'>"
														+ bookCode
														+ "</td>"
														+ "<td class='record-rent-date-issue'>"
														+ dateIssue
														+ "</td>"
														+ "<td class='record-quantity-rent-day'>"
														+ quantityRentDay
														+ "</td>"
														+ "<td class='record-rent-return-date'>"
														+ returnDate
														+ "</td>"
														+ "<td class='record-rent-status-rental' data-id="
														+ statusRental
														+ ">"
														+ statusRentalText
														+ "</td>"
														+ "<td>"
														+ "	<div class='row'>"
														+ "		<div class='col image-two'>"

														+ "			<svg class='remove-record' width='37' height='37'"
														+ "				xmlns='http://www.w3.org/2000/svg'>"
														+ "<title>Удалить запись</title>"
														+ "<path"
														+ "								d='m18,1c-9.4,0 -17,7.6 -17,17s7.6,17 17,17s17,-7.6 17,-17s-7.6,-17 -17,-17zm0,32.4c-8.5,0 -15.4,-6.9 -15.4,-15.4s6.9,-15.4 15.4,-15.4s15.4,6.9 15.4,15.4s-6.9,15.4 -15.4,15.4z' />"
														+ "<path"
														+ "					d='m23.5,11l-4,0l0,-0.5c0,-0.3 -0.2,-0.5 -0.5,-0.5l-2,0c-0.3,0 -0.5,0.2 -0.5,0.5l0,0.5l-4,0c-0.6,0 -1,0.4 -1,1s0.4,1 1,1l11,0c0.6,0 1,-0.4 1,-1s-0.4,-1 -1,-1zm-10.5,13c0,0.6 0.4,1 1,1l8,0c0.6,0 1,-0.4 1,-1l0,-10l-10,0l0,10zm6,-7.5c0,-0.3 0.2,-0.5 0.5,-0.5s0.5,0.2 0.5,0.5l0,6c0,0.3 -0.2,0.5 -0.5,0.5s-0.5,-0.2 -0.5,-0.5l0,-6zm-3,0c0,-0.3 0.2,-0.5 0.5,-0.5s0.5,0.2 0.5,0.5l0,6c0,0.3 -0.2,0.5 -0.5,0.5s-0.5,-0.2 -0.5,-0.5l0,-6z' />"

														+ "</svg>"
														+ "</div>"

														+ "	<div class='col image-two'>"

														+ "<svg class='edit-record' width='37' height='37'"
														+ "	xmlns='http://www.w3.org/2000/svg'>"
														+ "<title>Редактировать запись</title>"
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
												 * "<tr class='record-info'>" + "<td class='record-rent-id'>" +
												 * recordRentTictetID + "</td>" + "<td>" +
												 * bookCode + "</td>" + "<td>" +
												 * bookName + "</td>" + "<td>" +
												 * dateIssue + "</td>" + "<td>" +
												 * quantityRentDay + "</td>" + "<td>" +
												 * bookCode + "</td>" + "<td>" +
												 * statusRental + "</td>" + "</tr>"
												 */)
									});

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					echoInfo(XMLHttpRequest.responseText);

				}
			});
};

