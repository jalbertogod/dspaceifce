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
	$('.bt_like').click(function(element) {
		var array=$('a.like_uri').attr("href").split("/");		
		//alert(array[array.length-1]);
		var btLike = $(this);
		var btLikeTmp=btLike.html();
		btLike.html("Aguarde");
		$.ajax({
		
		      type: 'POST',
		      contentType: 'application/json',
		      //dataType: 'json',
		      data: {handle1:'123456789',handle2:'3'},
		      url:'/rest/like/set?handle1='+array[array.length-2]+'&handle2='+array[array.length-1],

		      success: function(retorno){
		    	  	if(retorno.erro == false){
		    	  		btLike.html("LIKE:"+retorno.countLike);
		    	  	}else{

					 btLike.html(retorno.mensagem);
			   		setTimeout(function(){ btLike.html(btLikeTmp); }, 3000);
				}
		      	},
			error: function(xhr, textStatus, error){
				 btLike.html(textStatus);
				   setTimeout(function(){ btLike.html(btLikeTmp); }, 3000);
			   /*console.log(xhr);
			   console.log(textStatus);
			   console.log(error);*/
			  
			}
	       });
	 });
		$('.bt_denuncia').click(function(element) {
			var array=$('a.like_uri').attr("href").split("/");		
			//alert(array[array.length-1]);
			var btDenuncia = $(this);
			var btDenunciaTmp=btDenuncia.html();
			btDenuncia.html("Aguarde");
			$.ajax({
			
			      type: 'POST',
			      contentType: 'application/json',
			      //dataType: 'json',
			      data: {handle1:'123456789',handle2:'3'},
			      url:'/rest/denuncia/set?handle1='+array[array.length-2]+'&handle2='+array[array.length-1],

			      success: function(retorno){
			    	  	if(retorno.erro == false){
			    	  		btDenuncia.html("DENUNCIA:"+retorno.countDenuncia);
			    	  	}else{

						 btDenuncia.html(retorno.mensagem);
				   		setTimeout(function(){ btDenuncia.html(btDenunciaTmp); }, 3000);
					}
			      	},
				error: function(xhr, textStatus, error){
					 btDenuncia.html(textStatus);
					   setTimeout(function(){ btDenuncia.html(btDenunciaTmp); }, 3000);
				   /*console.log(xhr);
				   console.log(textStatus);
				   console.log(error);*/
				  
				}
		       });
		});
 
})(jQuery);
