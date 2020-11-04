$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
	selectImagem();
	campoData();
	copiar();
	updateNameFile();
	cleanMensagem();
});

function cleanMensagem(){
	var sucesso =  document.getElementById("sucesso");
	var erro =  document.getElementById("erro");
	if(sucesso != null && sucesso != undefined){
		setTimeout(function() {
			$("#sucesso").remove();
		}, 10000);
	}
	else if(erro != null && erro != undefined){
		setTimeout(function() {
			$("#erro").remove();
		}, 10000);
	}
	
}

function labelStatusUsuario(){
	var status = $('#labelStatus').html(); 
	if(status == 'Ativo'){
		$('#labelStatus').html('Inativo'); 
	}
	else if(status == 'Inativo'){
		$('#labelStatus').html('Ativo'); 
	}	
}

function labelStatusNoticia(){
	var status = $('#labelStatus').html(); 
	if(status == 'Disponível'){
		$('#labelStatus').html('Indisponível'); 
	}
	else if(status == 'Indisponível'){
		$('#labelStatus').html('Disponível'); 
	}	
}

function selectImagem(){
	var img = document.getElementById("imagens");
	if(img != null && img != undefined){
		$(".js-select2").select2({
			closeOnSelect : false,
			placeholder : "Selecione",
			allowHtml: true,
			allowClear: true,
			tags: true 
		});
	}
	
}

function resetForm(form){
	$(':input','#'+form)
	  .not(':button, :submit, :reset')
	  .val('').removeAttr('checked').removeAttr('selected');
	  document.getElementById("div-id").hidden = "hidden";
	  $('#info-Label').html("Cadastro de Categoria");
}

function campoData(){
	var campo = document.getElementById("buscar_por_imagem");
	if(campo != null && campo != undefined){
		$('#buscar_por_imagem').datepicker({
			format : "dd-mm-yyyy",
			language : "pt-BR"
		});
	}
}

function updateNameFile(){
	var nameFile =  document.getElementById("imagem");
	if(nameFile != null && nameFile != undefined){
		document.querySelector('.custom-file-input').addEventListener('change',function(e){
			var fileName = document.getElementById("imagem").files[0].name;
			var nextSibling = e.target.nextElementSibling;
			nextSibling.innerText = fileName;
		});	
	}
}

function copiar() {
		var copyTex = document.querySelector('.js-copy-click');
		if(copyTex != null && copyTex != undefined){
			copyTex.addEventListener('click', function(event) {
			document.getElementById("copia-link").type = "text";
			var copyTextarea = document.querySelector('.js-copy-text');
			copyTextarea.focus();
			copyTextarea.select();
			var successful = document.execCommand('copy');
			document.getElementById("copia-link").type = "hidden";
			$("#a_copia").html("<b color='green;'>Copiado</b>");
			sleep();
			
		});
	}
}

function sleep(){
	setTimeout(function() {
		$("#a_copia").html("Copiar link");
	}, 3500);
}

function resetInputFile() {
	  $("#nome_img").html("Adicione uma imagem");
}

function goBack() {
	  window.history.back();
}

function setId(id, url){
	document.getElementById("delete").href = url.replace("codigoid",id.id);
}

function verImg(componente){
	document.getElementById("imgver").src = componente.id;
}