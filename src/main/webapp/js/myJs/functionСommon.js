var id =0;
	var $listBoks = $('#sel2');
	
	
	

	  function addClothes(nameBook) {
		  var rowStr = generateClothesRow(nameBook);
		  $listBoks.append(rowStr);
	     
	    }

	  function handleAddButton(el) {
		  var addBookForm = $("#add-book-form");
	        var nameBook = addBookForm.find('input[name=nameBook]').val();
	          addClothes(nameBook);     
	    }
		 function echo(el) {
		 
		   $.notify("Одежда сохранена (id: " + el + ")", "success");   
	    }


	  function generateClothesRow(nameBook) {
	    	var html = [];
	    	id=id+1;  	        
	    	html.push(
			'<option>'+ id +'</option>',
	    	'<option onclick="echo(this.value)" value='+ id +'>' + nameBook + '</option>'
	   			);
	        return html.join("");
	    }
		
		
		
		
		var title;
		
	function clickListGroup (event) {
	refreshDesktop(event);
	}
	
	function refreshDesktop (event) {
	 $("#titleDesktop").html(title+": "+event.target.text);
	}