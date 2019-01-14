$(document).ready(function(){
		
		$('.table-filters input').on('input', function () {
    filterTable($(this).parents('table'));
});
		
		$("#recordRiderTickedPanel table tr").click(function(){
			var a = $(this).find('.idRecordRent').first().text();
			echo(a);	
		});
		
			title="Библиотека";
			$("#titleDesktop").html(title);
			$(".list-group").bind("click", clickListGroup);
			
			$("#list-rent-list").click( function(){ 
				loadReaderTicket(1);
			});
			
			
		
		
});


	/*	$('tr').click(function(){ //фиксирует строку в таблице(устанавливает ей цвет, и убирает его), чтобы не забывать с чем работаем
        $('tr').removeClass(); //очищает все строки
        $(this).addClass('fixedRowTable'); // добовляет класс к тикущей строке
			
		});*/
		
		/*
		$('table tr').on("click", function(){ //удаляет строку из таблицы по которой кликаешь (функция on работает с элементами которые были созданы при загрузке страници, а тагже видит добавленные элементы)
		$(this).remove();
		});
		
		$('tr').click( function(){ //по другому записано, хз в чем разница
		$(this).remove();
		});
		
		$("tr").click(function(){
			var a = $(this).find("th").text(); //беред текст th элемента в строке таблици
			echo(a);	
		});
		
		$("tr").click(function(){  
			var a = $(this).find("td").first().text(); //в текущей строке берутся все td, из них берется первый и извлекается текст.
			echo(a);	
		});
		
		$(".list-group").bind("click", clickListGroup); //отслеживаю нажатие на кнопку лист гроуп, болучаю объект на который нажали, получаю его текст  и изменияю титульник (работает только с элементами которые были созданы при загрузке страницы)
		
		*/