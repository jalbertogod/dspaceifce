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
$.ajax({
		
	      type: 'GET',
	      contentType: 'application/json',
	      //dataType: 'json',
	      data: {},
	      url:'/rest/status',

	      success: function(retorno){
	    	  			atualizarIconsSolicit(elemento,String(retorno.tipoSolicitacaoInterprete));
		      	},
		        error: function(xhr, textStatus, error){
		           console.log(xhr);
		           console.log(textStatus);
		           console.log(error);
		        }
	       });
})(jQuery);
