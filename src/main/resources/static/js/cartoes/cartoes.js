$(document).ready(function() {
    buscar(); 
    init_btns();
});

function init_btns(){
	$('#btnModal').click(function(){
		$.get('cartoes/modal',function(retorno){
			initForm(retorno,"Cadastro", "POST");
		});
	});	
		
}

function salvar(verboHttp){
	if(validar()){
		var dado = {
				'id' : $('#idCard').val(),
				'nome' : $('#formNome').val(),
				'bandeira' : $('#formBandeira').val(),
				'numero' : $('#formNumero').val(),
				'validade' : $('#formValidade').val(),
				'candidato' : { 'id' : $('#formCandidatoId').val()}
		}
		
		$.ajax({
			url : 'cartao_credito',
			type : verboHttp,
			data : JSON.stringify(dado),
			dataType : "json",		 
			contentType : "application/json",
			success : function(msg, status, jqxhr) {
				location.reload();
			},
			error : function(msg, status) {
				Swal.fire({
					  icon: 'error',
					  title: 'Oops...',
					  text: 'Não foi possível cadastrar / atualizar o Cartão!'
					})
			}
		});
	}
}

function deletar(id){
	var dado = {
			'id' : id
	}
	
	$.ajax({
		url : 'cartao_credito',
		type : "DELETE",
		data : JSON.stringify(dado),
		dataType : "json",		 
		contentType : "application/json",
		success : function(msg, status, jqxhr) {
			location.reload();
		},
		error : function(msg, status) {
			console.info(msg.status);
			if(msg.status == 200){
				location.reload();
			}else{
				Swal.fire({
					  title: "Não foi possível deletar o Cartão de Crédito!",
					  text: "Necessário retirar todas as dependências do cartão antes da deleção!!!",
					  icon: "error",
					  button: "ok!",
					  showCloseButton: true,
				  });
			}
			
		}
	});
}

function deletarItem(id){
	Swal.fire({
		title: "Tem certeza que deseja excluir o item? Não terá volta!",
		input: 'checkbox',
		icon: "warning",
		button: "ok!",
		showCloseButton: true,
		showCancelButton: true,
		inputPlaceholder:
		    'Eu concordo em perder permanentemente todos os dados referente à este item a ser excluido.',
		inputValidator: (result) => {
		    return !result && 'Você precisa concordar com o termo!'
		  },
		 preConfirm: () => {
			 deletar(id);			
		}
	  });
}

function initForm(retorno,tipo, verboHttp){
	$('#corpoModal').html(retorno);
	$('#motalTitulo').text(tipo+' - Cartão');
	
	
	 $("#btnSalvar").click(function(event){
	    event.preventDefault();
	    salvar(verboHttp);			
	});
	
	 autoCompleteCandidato();
	
	 
	$('#modal').modal('show');
}

function alterarItem(id){
	$.get('cartoes/modal/'+id,function(retorno){
		initForm(retorno,"Atualizar", "PUT");
	});
}

function buscar(){
	var pesquisa;
	$.get('cartoes/buscar', pesquisa, function(dados) {		
		$('#resultado').html(dados);
		init_data_table('tabela_cartoes');
		
	});
}

function init_data_table(idTabela){
	$('#'+idTabela+' tfoot th').each( function () {
        var title = $(this).text();
        if(title != 'Acao'){
        	$(this).html( '<input type="text" style = "width:100%" placeholder="'+title+'" />' );
        }else{
        	$(this).html('');
        }  
    } );
	
	if($.fn.dataTable.isDataTable('#'+idTabela) ){
		$('#'+idTabela).dataTable().fnDestroy();
		init_data_table('tabela_cartoes');		
	}else{
		$('#'+idTabela).DataTable({
			destroy : true,
	        scrollCollapse: true,
			ordering : true,
			info : true,
			searching : true,
			paging: true,	
			language: {
				url: '//cdn.datatables.net/plug-ins/1.10.24/i18n/Portuguese-Brasil.json'
	        },
			initComplete: function () {
	            // Apply the search
	            this.api().columns().every( function () {
	                var that = this;
	 
	                $( 'input', this.footer() ).on( 'keyup change clear', function () {
	                    if ( that.search() !== this.value ) {
	                        that.search( this.value ).draw();
	                    }
	                } );
	            } );
	        }
		});
	}
}

function autoCompleteCandidato() {
	$('#formCandidato').easyAutocomplete({
		url : function(dado) {
			return 'cartoes/filtrar/' + dado;
		},
		getValue : 'nome',
		minCharNumber : 1,
		requestDelay : 100,
		placeholder : "Digite um nome para pesquisar",
		adjustWidth : true,
		// cssClasses: '',
		list : {
			maxNumberOfElements : 10,
			showAnimation : {
				type : "fade", // normal|slide|fade
				time : 200,
				callback : function() {
				}
			},
			hideAnimation : {
				type : "slide", // normal|slide|fade
				time : 100,
				callback : function() {
				}
			},
			match : {
				enabled : true
			},
			onLoadEvent : function() {
				console.log('carregando...');
				
				cliente = null;
				
				$('#formCandidatoId').val('');
			},
			onChooseEvent : function() {
				candidato = $("#formCandidato").getSelectedItemData();
				$('#formCandidatoId').val(candidato.id);
			}
		}
	});
}