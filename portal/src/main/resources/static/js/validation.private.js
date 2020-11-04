$(document).ready(function() {
	valCategoria();
	valImagem();
	valUsuario();
	valNoticia();
	valPerfil();
	valSenha();
});

function valCategoria(){
	$("#form_categoria").validate({
		rules : {
			nome : {
				required : true,
				maxlength: 50
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

function valImagem(){
	$("#form_imagem").validate({
		rules : {
			imagem : {
				required : true,
				accept : "image/*",
				extension : "png|jpeg|jpg"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

function valUsuario(){
	$("#form_usuario").validate({
		rules : {
			nomeCompleto : {
				required : true,
				maxlength: 100
			},
			email : {
				required : true,
				email: true,
				maxlength: 50
			},
			telefone : {
				required : true,
				minlength : 16,
				maxlength : 16
			},
			senha : {
				required : true,
				minlength : 10,
				maxlength : 100
			},
			tipoAcesso : {
				required: true,
				campoSelect: true
			}			
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

function valNoticia(){
	$("#form_noticia").validate({
		rules : {
			titulo : {
				required : true,
				maxlength: 100
			},
			imagens : {
				required : true,
				campoSelect: true
			},
			categoria : {
				required : true,
				campoSelect: true
			},
			emLocal : {
				required : true,
				maxlength: 100
			},
			texto : {
				required : true,
				textoNoticia: true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});	
}

function valPerfil(){
	$("#form_perfil").validate({
		rules : {
			nomeCompleto : {
				required : true,
				maxlength: 100
			},
			telefone : {
				required : true,
				minlength: 16,
				maxlength: 16
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

function valSenha(){
	$("#form_senha_perfil").validate({
		rules : {
			senha_atual : {
				required : true,
				minlength: 10,
				maxlength: 100
			},
			nova_senha : {
				required : true,
				minlength: 10,
				maxlength: 100
			},
			confirma_nova_senha : {
				required : true,
				minlength: 10,
				maxlength: 100,
				equalTo : "#nova_senha"
			}
		},
		submitHandler : function(form) {
			form.submit();
}
	});
}

jQuery.validator.addMethod("campoSelect", function(value, element) {
	if (value == "0") {
		return false;
	} else {
		return true;
	}

}, "Este campo &eacute; obrigat\u00f3rio.")

