<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<div class="alert alert-danger hidden" id="errorscreateusuario" role="alert"></div>
	
		<fieldset class="form">
			<div class="form-group has-info row">
				<label for="nombre" class="col-sm-3 control-label">Nombre: </label>
				<div class="col-sm-9">
					<input type="text" id="nombre" class="form-control" />
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="username" class="col-sm-3 control-label">Username*: </label>
				<div class="col-sm-9">
					<input type="text" id="username" class="form-control" />
					<div class="alert alert-danger" id="errorusername"  style="display:none" ></div>
					
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="password" class="col-sm-3 control-label">Password*: </label>
				<div class="col-sm-9">
					<input  type="password" class="form-control" id="password"/>
					<div class="alert alert-danger" id="errorpassword"  style="display:none" ></div>
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="password2" class="col-sm-3 control-label">Confirmar*: </label>
				<div class="col-sm-9">
					<input  type="password" class="form-control" id="password2" />
					<div class="alert alert-danger" id="errorpassword2"  style="display:none" ></div>
				</div>
			</div>			
			
			<div class="form-group has-info row">
				<label for="empresa" class="col-sm-3 control-label">Empresa*: </label>
				<div class="col-sm-9">
					<select  class="form-control" id="empresaselect" onchange="getAlmacenes(this.value)"></select>
					<div class="alert alert-danger" id="errorempresa"  style="display:none" ></div>
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="almacen" class="col-sm-3 control-label">Almacen*: </label>
				<div class="col-sm-9">
					<select  class="form-control" id="almacenselect" ></select>
					<div class="alert alert-danger" id="erroralmacen"  style="display:none" ></div>
				</div>
			</div>
			<div class="form-group has-info row">
			<label class="col-sm-3 control-label"></label>
				<label class="col-sm-9 control-label"> <input type="checkbox" name="idactivo"
					id="idactivo" class="ace"  checked onchange="cambiarchecked()"/> <span class="lbl"> Activo</span>
				</label>
			</div>
		</fieldset>
		<fieldset>
				<div class="col-sm-3"></div>
				<div class="col-sm-2">
					<button type="button" class="btn waves-effect waves-light btn-rounded btn-success" id="saveUser" onclick="saveUsuario()" title="Guardar">
					<i class="ace-icon fa fa-floppy-o bigger-160"></i>Guardar
					</button>
				</div>
				</fieldset>
	
	<script type="text/javascript">
	$(function() {
		$.ajax({
            method:'POST',
            url:'${pageContext.request.contextPath}/getEmpresas',         
            data:{},
            dataType:'JSON',
            success: function (data){     
            	
                var options = '<option value="0"> [ Seleccione]  </option>';
                $.each(data, function (key, dat) {	 
                	 $.each(dat, function (key, info) {
                    options += '<option value="'+info.id+'">'+info.nombre+'</option>'  
                	 });
                }); 	                        
                 $("#empresaselect").html( options );                      
            },
            error:function(jqXHR, exception){
                var msg =  errorsAjax(jqXHR, exception);
                alert("MSG: "+ msg)
            }           
        });
		
	});
	function cambiarchecked(){
		if($("#idactivo").is(':checked')) { 
			$(this).prop('checked', true);
		}else{
			$(this).prop('checked', false);
		}
	}
	function getAlmacenes(id){
		if(id == 0)
			return false;
		$.ajax({
			url:"${pageContext.request.contextPath}/getByEmpresa",
			data:{id:id},
			dataType:'json',
			success:function(res){
				var options = '<option value="0"> [ Seleccione]  </option>';
                $.each(res, function (key, dat) {	
                	console.log(dat);
                    options += '<option value="'+dat.id+'">'+dat.nombre+'</option>'                  
                }); 	                        
                 $("#almacenselect").html( options );  
			}
		});
	}
	
	function validaPswd(){
		if(jQuery("#password").val() != jQuery("#password2").val()){
			$("#errorpassword2").html('Contraseñas no coinciden');
			$("#errorpassword2").show();
			setTimeout(function() {
		        $("#errorpassword2").fadeOut(100);
		    },1000);
			return false
		}
		return true
	}
	
	function saveUsuario(){		
		if(validaform()==true){
		if(!validaPswd()){
			return false
		}else{
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			var nombre = document.getElementById("nombre").value;
			var empresa = $("#empresaselect").val();
			var almacen = $("#almacenselect").val();
			var isactivo = $("#idactivo").is(':checked');
			var parametros = {
	                'nombre' :nombre,
	                'username':username,
	                'password'  :password,	                
	                'empresa':empresa,
	                'almacen':almacen,
	                'isactivo':isactivo
	              }
			//console.log(parametros)
			$.ajax({
				data:parametros,
				url:'${pageContext.request.contextPath}/save',
				method:'POST',
				dataType:'JSON',
				cache: false,
				success:function(data){
					 if(data.exito=="true"){
		        		 dtusuario.ajax.reload( null, false )
		        		 $('#modalaps').modal('hide');
		        	 }else{
		        		 var html = "";
		        		 for( i=0;i<data.error.length;i++){
		 					html += '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> '+data.error[i]+'<br/>';
		 				};		 				
		 				var diverrors = jQuery("#errorscreateusuario")
		 				diverrors.removeClass("hidden")
		 				diverrors.html(html)
		            }
				}
			});
		}
		
		}
	}
	function validaform(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var empresa = $("#empresaselect").val();
		var almacen = $("#almacenselect").val();
		
		if(username == null || username.length == 0 || /^\s+$/.test(username)){	
			
			$("#errorusername").html('ERROR: El campo nombre no debe ir vacío o lleno de solamente espacios en blanco');
			$("#errorusername").show();
			setTimeout(function() {
		        $("#errorusername").fadeOut(100);
		    },1000);
			return false;
		}
		if(password == null || password.length == 0 || /^\s+$/.test(password)){
			$("#errorpassword").html('ERROR: El campo nombre no debe ir vacío o lleno de solamente espacios en blanco');
			$("#errorpassword").show();
			setTimeout(function() {
		        $("#errorpassword").fadeOut(100);
		    },1000);
			return false;
		}
		if(empresa == null || empresa == 0){
			$("#errorempresa").html('ERROR: Debe seleccionar una opcion');
			$("#errorempresa").show();
			setTimeout(function() {
		        $("#errorempresa").fadeOut(100);
		    },1000);
			return false;
		}
		if(almacen == null || almacen == 0){
			$("#erroralmacen").html('ERROR: Debe seleccionar una opcion');
			$("#erroralmacen").show();
			setTimeout(function() {
		        $("#erroralmacen").fadeOut(100);
		    },1000);
			return false;
		}
		return true;
	}
	</script>
</body>
</html>