$(document).ready(function() {
	var isAddBookMode = false;
	var addDialog = $('#add-dialog');
	var defaultRentDay = 12;
	var rentalInfo = {
			idRecordRiderTicket:0,
			idRiderTicket:0,
			idBook:0,
			quantityRentDay:0,
			dateIssue:'',
			returnDate:'',
			statusRental:0 
		}
	
	
	
	

	$('#add-record').click(function() {
		if (rentalInfo.idRiderTicket != 0) {
			
			addDialog.find('label[name=action-status-label]').text(1); //осуществляется выбор действия(обновить или добавить)
			addDialog.find(".modal-title").text("Новая запись");
			//addDialog.find("#reaader-ticket").text(reaaderTicketId);
			addDialog.find('input[name=rent-day-input]').val(defaultRentDay);
			addDialog.find('input[name=date-issue-input]').attr("value", getTodayDate());
			$("#return-date-row").addClass('not-visible-field');
			$("#date-issue-row").removeClass('not-visible-field');	
			$("#book-selection-row").removeClass('not-visible-field');	
			addDialog.find('input[name=id-book-input]').prop('disabled',false);
			addDialog.find('button[name=find-book-archive-button]').prop('disabled', false);
			addDialog.modal('show');
			
		}
	});
	
	

	$('#save-record').click(function() {
		saveRecord(rentalInfo);
	});
	
	

	$("#filter-table-book-by-name").keyup(function() {
		filterBookTableForName();
	})
	$('#find-book-archive').click(function() {
		$('#list-storage-list').click();

		isAddBookMode = true;
		$.notify("Выберите книгу для добавления");
	});

	$('.table-filters input').on('input', function() {
		filterTable($(this).parents('table'));
	});

	$(document).on("click", "#book-table tbody tr", function() {
		var idBook = $(this).find('.book-id').first().text();
		echoInfo(idBook);
		$("#book-table tbody tr").removeClass('fixedRowTable');
		$(this).addClass('fixedRowTable');
		if (isAddBookMode) {
			addDialog.find('input[name=id-book-input]').val(idBook);
			isAddBookMode = false;
			$('#list-rent-list').click();
			addDialog.modal('show');
		}
	});

	title = "Библиотека";
	$("#titleDesktop").html(title);
	$(".list-group").bind("click", clickListGroup);

	$("#list-storage-list").click(function() {
		var tableBook = $("#book-table");
		cleanTabElement(tableBook);
		loadBooks(tableBook);

	});

	$("#search-rider-ticket").click(function() {
		cleanRentTabElement();
		rentalInfo.idRiderTicket = $("#info-rider-row").find('input[name=reader-ticket-input]').val();
		if (rentalInfo.idRiderTicket != null) {
			loadReader(rentalInfo.idRiderTicket);
			loadRentalInfoBooks(rentalInfo.idRiderTicket);
		}
	});

	$(document).on("click", ".remove-record", function() {
		var idRecord = $(this).closest("tr").find('.record-rent-id').first().text();
		removeRecordRenatlInfo(rentalInfo.idRiderTicket,idRecord);
	});
	
	$(document).on("click", ".edit-record", function() {
		var selectedTR=$(this).closest("tr")
		rentalInfo.idRecordRiderTicket = selectedTR.find('.record-rent-id').first().text();
		rentalInfo.statusRental = selectedTR.find('.record-rent-status-rental').first().attr("data-id");
		var bookId= selectedTR.find('.record-rent-book-id').first().text();
		var dateIssue = selectedTR.find('.record-rent-date-issue').first().text();
		var quantityRentDay = selectedTR.find('.record-quantity-rent-day').first().text();
		var returnDate = selectedTR.find('.record-rent-return-date').first().text();
		
	
		
		addDialog.find('label[name=action-status-label]').text(2);

		addDialog.find(".modal-title").text("Редактирование записи");	

		//addDialog.find('label[name=id-book-label]').text(bookId);
		addDialog.find('input[name=id-book-input]').val(bookId);
	
		addDialog.find('input[name=rent-day-input]').val(quantityRentDay);

		addDialog.find('input[name=date-issue-input]').attr("value", dateIssue);
		addDialog.find('input[name=return-date-input]').attr("value", returnDate);
		addDialog.find('input[name=id-book-input]').prop('disabled',true);
		addDialog.find('button[name=find-book-archive-button]').prop('disabled', true);
	//	addDialog.find("#book-display-info-form").removeClass('not-visible-field');
		$("#return-date-row").removeClass('not-visible-field');	
	//	addDialog.find("#book-selection-form").addClass('not-visible-field');
		addDialog.modal('show');
		
		//removeRecordRenatlInfo(reaaderTicketId,idRecord);
	});

	// .find('.record-rent-id').first().text()

	//$('#list-storage-list').click();
	
});

/*
 * $(document).on("click",".record-info", function(){ var a =
 * $(this).find('.record-rent-id').first().text(); echoInfo(a);
 * $(".record-info").removeClass('fixedRowTable');
 * $(this).addClass('fixedRowTable'); });
 * 
 * 
 * $('tr').click(function(){ //фиксирует строку в таблице(устанавливает ей цвет,
 * и убирает его), чтобы не забывать с чем работаем $('tr').removeClass();
 * //очищает все строки $(this).addClass('fixedRowTable'); // добовляет класс к
 * тикущей строке
 * 
 * });
 */

/*
 * $(document).on("click",".record-info", function(){//обязательно так, если
 * элементы создаются в процессе выполнения программы, иначе не вызовется var a =
 * $(this).children('.record-rent-id').first().text();//перется строка, в ней
 * ищется .record-rent-id и берется значение первой колонки echoInfo(a); });
 */

/*
 * (эта не работает с созданнымив рунтайме, работает функция под номером 1)
 * $('table tr').on("click", function(){ //удаляет строку из таблицы по которой
 * кликаешь (функция on работает с элементами которые были созданы при загрузке
 * страници, а тагже видит добавленные элементы) $(this).remove(); });
 * 
 * $('tr').click( function(){ //по другому записано, хз в чем разница
 * $(this).remove(); });
 * 
 * $("tr").click(function(){ var a = $(this).find("th").text(); //беред текст th
 * элемента в строке таблици echo(a); });
 * 
 * $("tr").click(function(){ var a = $(this).find("td").first().text(); //в
 * текущей строке берутся все td, из них берется первый и извлекается текст.
 * echo(a); });
 * 
 * $(".list-group").bind("click", clickListGroup); //отслеживаю нажатие на
 * кнопку лист гроуп, болучаю объект на который нажали, получаю его текст и
 * изменияю титульник (работает только с элементами которые были созданы при
 * загрузке страницы)
 * 
 * $("#recordRiderTickedPanel table tdody").find("tr").remove();// типо все тр
 * найдет
 * 
 */

/*
 * $("#add-record").click(function() { $("#init-act-record").hide();
 * $("#end-act-record").show();
 * 
 * });
 * 
 * $("#edit-record").click(function() { $("#init-act-record").hide();
 * $("#end-act-record").show();
 * 
 * });
 * 
 * $("#delite-record").click(function() {
 * 
 * });
 * 
 * $("#cancel-act-record").click(function() { $("#init-act-record").show();
 * $("#end-act-record").hide();
 * 
 * });
 * 
 * $("#confirmation-act-record").click(function() {
 * $("#init-act-record").show(); $("#end-act-record").hide();
 * 
 * });
 */