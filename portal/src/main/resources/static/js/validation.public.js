$(document).ready(function() {
	valLogin();
	valEmailSenha();
	valNovaSenha();
	valContato();
	valNewsletter();
});

function valLogin() {
	$("#form_login").validate({
		rules : {
			username : {
				required : true,
				maxlength: 50
			},
			password : {
				required : true,
				maxlength: 50
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

function valEmailSenha() {
	$("#form_email_senha").validate({
		rules : {
			email : {
				required : true,
				email: true,
				maxlength: 50
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

function valNovaSenha() {
	$("#form_nova_senha").validate({
		rules : {
			nova_senha : {
				required : true,
				minlength: 10,
				maxlength: 50
			},
			confirme_nova_senha : {
				required : true,
				minlength: 10,
				maxlength: 50,
				equalTo : "#nova_senha"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

function valContato() {
	$("#form_contato").validate({
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
			tipo : {
				required: true,
				campoSelect: true
			},
			assunto : {
				required : true,
				maxlength: 50
			},
			mensagem : {
				required : true,
				maxlength: 255
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});	
}

function valNewsletter() {
	$("#form_newsletter").validate({
		rules : {
			nomeCompleto : {
				required : true,
				maxlength: 100
			},
			email : {
				required : true,
				email : true,
				maxlength: 50
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}

jQuery.validator.addMethod("campoSelect", function(value, element) {
	if (value == "SELECIONE") {
		return false;
	} else {
		return true;
	}

}, "Este campo &eacute; obrigat\u00f3rio.")