

function loadBooksStore() {
		$.ajax({
            url: "service/main/books",
            type: "get",
            success: function(data) {


            	$.each(data, function(i, item) {
                	
            		var id = item.id || "";
            		indx=id;
                    var title = item.title != null ? item.title  : "";            
                	var rowStr = generateBoksList(id, title);
                	$listBoks.append(rowStr);	
            		books.set(id, item);
            		
            		
				});       

        	},
        	error: function(XMLHttpRequest, textStatus, errorThrown) {
        		alert(XMLHttpRequest.status);
        	     alert(XMLHttpRequest.responseText);
        	     alert(XMLHttpRequest.statusText);
        	     alert(textStatus);
        	     alert(errorThrown);
        	  }
        	
		});
	};
	
	
	function loadBooksStore(ReadTicketId) {
		
		
	
	$.ajax({
		url: "service/main/rentalInfoBooks/" + ReadTicketId,
        type: "get",
        success: function(data) {
      
            $.each(data.reader, function(i, item) {
            	
            	var id = item.id || "";
            	var reader=item.reader;
            	var records=item.records;
            	
               
                var storeId = item.store != null ? item.store.id : "";
                var sizeId = item.size != null ? item.size.id : "";
                var typeId = item.type != null ? item.type.id : "";
                var colorId = item.color != null ? item.color.id : "";
                var desc = item.shortDescription;

                var selType = fillOptions(typeId, types, '--Тип--', 'id', 'name');
                var selColor = fillOptions(colorId, colors, '--Цвет--',  'id', 'name');
                var selSize = fillOptions(sizeId, sizes, '--Размер--', 'id', 'name');
                
            	var rowStr = generateClothesRow(id, storeId, typeId, sizeId, colorId, desc);
                $(listId).append(rowStr);
        	});
    	}
	});};
	

	