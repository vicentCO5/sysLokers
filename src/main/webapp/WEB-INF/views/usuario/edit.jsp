<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page import="com.aps.beans.catalogos.Empresa"%>
     <%@page import="com.aps.beans.catalogos.Almacen"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<div class="alert alert-danger hidden" id="errorsupdateusuario" role="alert"></div>
	<form:form  method="post" id="formeditEmpresa">
		<fieldset class="form">
		<input type=hidden id="iduser" name="iduser" value="${iduser}"/>
			<div class="form-group has-info row">
				<label for="nombre" class="col-sm-3 control-label">Nombre: </label>
				<div class="col-sm-9">
					<input type="text" id="nombreup" value="${nombre}" class="form-control" />
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="username" class="col-sm-3 control-label">Username*: </label>
				<div class="col-sm-9">
					<input type="text" id="usernameup" value="${username}" class="form-control" />
					<div class="alert alert-danger" id="errorusernameup"  style="display:none" ></div>
					
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="password" class="col-sm-3 control-label">Password*: </label>
				<div class="col-sm-9">
					<input  type="password" class="form-control" id="passwordup"/>
					<div class="alert alert-danger" id="errorpasswordup"  style="display:none" ></div>
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="password2" class="col-sm-3 control-label">Confirmar*: </label>
				<div class="col-sm-9">
					<input  type="password" class="form-control" id="password2up" />
					<div class="alert alert-danger" id="errorpassword2up"  style="display:none" ></div>
				</div>
			</div>			
			
			<div class="form-group has-info row">
				<label for="empresa" class="col-sm-3 control-label">Empresa*: </label>
				<div class="col-sm-9">
					<select  class="form-control" id="empresaselectup" onchange="getAlmacenesup(this.value)">					
  						  <%
  						@SuppressWarnings("unchecked")
                                    List<Empresa> lista = (List<Empresa>) request.getAttribute("lista");
									int empresa_id = (int)request.getAttribute("empresa_id");
                                    int i = 0;
                                    String selected="";
                                    for (Empresa empresa : lista) {
                                        String nombre = (empresa.getNombre());
                                        int id =(empresa.getId());
                                        if(id==empresa_id){
                                        	selected="selected='selected'";
                                        }else{
                                        	selected="";
                                        }
                                        out.println("<option value="+ id +" "+selected+" >" + nombre + "</option>");
                                    }

                                %>
					</select>
					<div class="alert alert-danger" id="errorempresaup"  style="display:none" ></div>
				</div>
			</div>
			<div class="form-group has-info row">
				<label for="almacen" class="col-sm-3 control-label">Almacen*: </label>
				<div class="col-sm-9">
					<select  class="form-control" id="almacenselectup" >
					<%
									@SuppressWarnings("unchecked")
                                    List<Almacen> listaalmacen = (List<Almacen>) request.getAttribute("listalmacen");
									int almacen_id = (int)request.getAttribute("almacen_id");
                                   
                                    String selectedalm="";
                                    for (Almacen almacen : listaalmacen) {
                                        String nomalm = (almacen.getNombre());
                                        int idalm =(almacen.getId());
                                        if(idalm==almacen_id){
                                        	selectedalm="selected='selected'";
                                        }else{
                                        	selectedalm="";
                                        }
                                        out.println("<option value="+ idalm +" "+selectedalm+" >" + nomalm + "</option>");
                                    }

                                %>
					</select>
					<div class="alert alert-danger" id="erroralmacenup"  style="display:none" ></div>
				</div>
			</div>
			<div class="form-group has-info row">
			<label class="col-sm-3 control-label"></label>
				<label class="col-sm-9 control-label"> 
				<% boolean check=(boolean)request.getAttribute("estatus");
				String val="";
				if(check==true){
					val="checked='checked'";
				}else{
					val="";
				}
				%>	
				<input type="checkbox" class="ace" name="idactivoup"
					id="idactivoup" <%= val %>  onchange="cambiarcheck()"/> <span class="lbl"> Activo</span>
				</label>
			</div>
		</fieldset>
		<fieldset>
				<div class="col-sm-3">-</div>
				<div class="col-sm-2">
					<button type="button" class="btn waves-effect waves-light btn-rounded btn-success" id="updateUser" onclick="updateUsuario()" title="Guardar">
					<i class="ace-icon fa fa-floppy-o bigger-160"></i> Guardar
					</button>
				</div>
				</fieldset>
	</form:form>
	<script type="text/javascript">
	$(function() {
		var check=<%=request.getAttribute("estatus")%>
		console.log(check)
	});
	function cambiarcheck(){
		if($("#idactivoup").is(':checked')) { 
			$(this).prop('checked', true);
		}else{
			$(this).prop('checked', false);
		}
	}
	function getAlmacenesup(id){
		if(id == 0)
			return false;
		$.ajax({
			url:"${pageContext.request.contextPath}/getByEmpresa",
			data:{id:id},
			dataType:'json',
			success:function(res){
				var options = '<option value="0"> [ Seleccione]  </option>';
                $.each(res, function (key, dat) {	                        	
                    options += '<option value="'+dat.id+'">'+dat.nombre+'</option>'                  
                }); 	                        
                 $("#almacenselectup").html( options );  
			}
		});
	}
	function validaPswdup(){
		if(jQuery("#passwordup").val() != jQuery("#password2up").val()){
			$("#errorpassword2up").html('Contraseñas no coinciden');
			$("#errorpassword2up").show();
			setTimeout(function() {
		        $("#errorpassword2up").fadeOut(100);
		    },1000);
			return false
		}
		return true
	}
	function updateUsuario(){
		if(validaformup()==true){
			if(!validaPswdup()){
				return false
			}else{
				var username = document.getElementById("usernameup").value;
				var password = document.getElementById("passwordup").value;
				var nombre = document.getElementById("nombreup").value;
				var iduser = document.getElementById("iduser").value;
				var empresa = $("#empresaselectup").val();
				var almacen = $("#almacenselectup").val();
				var isactivo = $("#idactivoup").is(':checked');
				var parametros = {
						'iduser':iduser,
		                'nombre' :nombre,
		                'username':username,
		                'password'  :password,	                
		                'empresa':empresa,
		                'almacen':almacen,
		                'isactivo':isactivo
		              }
				console.log(parametros)
				$.ajax({
					data:parametros,
					url:'${pageContext.request.contextPath}/updateUser',
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
			 				var diverrors = jQuery("#errorsupdateusuario")
			 				diverrors.removeClass("hidden")
			 				diverrors.html(html)
			            }
					}
				});
			}
			
			}
	}
	function validaformup(){
		var username = document.getElementById("usernameup").value;
		var password = document.getElementById("passwordup").value;
		var empresa = $("#empresaselectup").val();
		var almacen = $("#almacenselectup").val();
		
		if(username == null || username.length == 0 || /^\s+$/.test(username)){	
			
			$("#errorusernameup").html('ERROR: El campo nombre no debe ir vacío o lleno de solamente espacios en blanco');
			$("#errorusernameup").show();
			setTimeout(function() {
		        $("#errorusernameup").fadeOut(100);
		    },1000);
			return false;
		}
		if(password == null || password.length == 0 || /^\s+$/.test(password)){
			$("#errorpasswordup").html('ERROR: El campo nombre no debe ir vacío o lleno de solamente espacios en blanco');
			$("#errorpasswordup").show();
			setTimeout(function() {
		        $("#errorpasswordup").fadeOut(100);
		    },1000);
			return false;
		}
		if(empresa == null || empresa == 0){
			$("#errorempresaup").html('ERROR: Debe seleccionar una opcion');
			$("#errorempresaup").show();
			setTimeout(function() {
		        $("#errorempresaup").fadeOut(100);
		    },1000);
			return false;
		}
		if(almacen == null || almacen == 0){
			$("#erroralmacenup").html('ERROR: Debe seleccionar una opcion');
			$("#erroralmacenup").show();
			setTimeout(function() {
		        $("#erroralmacenup").fadeOut(100);
		    },1000);
			return false;
		}
		return true;
	}
	</script>
</body>
</html>