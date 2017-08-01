(function($) {
	$('#aspect_eperson_PasswordLogin_field_submit').click(function() {
	     $.ajax({
		
	      type: 'GET',
	      contentType: 'application/json',
	      //dataType: 'json',
	      data: {email:$('#aspect_eperson_PasswordLogin_field_login_email').val(),password:$('#aspect_eperson_PasswordLogin_field_login_password').val()},
	      url:'/rest/login',

	      success: function(retorno){
	    	  			atualizarIconsSolicit(elemento,String(retorno.tipoSolicitacaoInterprete));
		      	},
		        error: function(xhr, textStatus, error){
		           console.log(xhr);
		           console.log(textStatus);
		           console.log(error);
		        }
	       });

	
       });
	$('.bt_like').click(function() {
		var array=$('a.like_uri').attr("href").split("/");		
		//alert(array[array.length-1]);
		$.ajax({
		
		      type: 'POST',
		      contentType: 'application/json',
		      //dataType: 'json',
		      data: {handle1:'123456789',handle2:'3'},
		      url:'/rest/like/set?handle1='+array[array.length-2]+'&handle2='+array[array.length-1],

	      success: function(retorno){
	    
		      	},
			error: function(xhr, textStatus, error){
			   console.log(xhr);
			   console.log(textStatus);
			   console.log(error);
			}
	       });
 });
})(jQuery);
