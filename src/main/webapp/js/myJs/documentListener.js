$(document).ready(function() {



	$('.table-filters input').on('input', function() {
		filterTable($(this).parents('table'));
	});

	$(document).on("click",".record-info", function(){
		var a = $(this).find('.record-rent-id').first().text();
		echoInfo(a);
		$(".record-info").removeClass('fixedRowTable');
		 $(this).addClass('fixedRowTable'); 
	});

	title = "Библиотека";
	$("#titleDesktop").html(title);
	$(".list-group").bind("click", clickListGroup);

	$("#search-rider-ticket").click(function() {
		cleanRentTabElement();
		var reaaderTicketId = $("#input-reader-ticket-id").val();
		if (reaaderTicketId != null) {
			loadReader(reaaderTicketId);
			loadRentalInfoBooks(reaaderTicketId);
		}
	});

	$("#add-record").click(function() {
		$("#init-act-record").hide();
		$("#end-act-record").show();

	});

	$("#edit-record").click(function() {
		$("#init-act-record").hide();
		$("#end-act-record").show();

	});

	$("#delite-record").click(function() {

	});

	$("#cancel-act-record").click(function() {
		$("#init-act-record").show();
		$("#end-act-record").hide();

	});

	$("#confirmation-act-record").click(function() {
		$("#init-act-record").show();
		$("#end-act-record").hide();

	});

});

/*
 * $('tr').click(function(){ //фиксирует строку в таблице(устанавливает ей цвет,
 * и убирает его), чтобы не забывать с чем работаем $('tr').removeClass();
 * //очищает все строки $(this).addClass('fixedRowTable'); // добовляет класс к
 * тикущей строке
 * 
 * });
 */

/*$(document).on("click",".record-info", function(){//обязательно так, если элементы создаются в процессе выполнения программы, иначе не вызовется
	var a = $(this).children('.record-rent-id').first().text();//перется строка, в ней ищется .record-rent-id и берется значение первой колонки
	echoInfo(a);
});*/

/*(эта не работает с созданнымив рунтайме, работает функция под номером 1)
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