
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
	<head>
		<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8" charset="utf8" >
		<title>APS Sistem Solutions</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<!-- Favicon icon -->
	    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon.png">
	    <title>AdminSystem</title>	
	     <!-- Bootstrap Core CSS -->
	    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath}/resources/css/perfect-scrollbar.css" rel="stylesheet">
		<!-- 	     jquery -->
		<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet">

	    <!--Toaster Popup message CSS -->
	    <link href="${pageContext.request.contextPath}/resources/css/jquery.toast.css" rel="stylesheet">
	    <!-- Custom CSS -->
	    <link href="${pageContext.request.contextPath}/resources/css/template/style.css" rel="stylesheet">
	    <!-- Dashboard 1 Page CSS -->
	    <link href="${pageContext.request.contextPath}/resources/css/template/dashboard1.css" rel="stylesheet">
	    <!-- You can change the theme colors from here -->
	    <link href="${pageContext.request.contextPath}/resources/css/template/default.css" id="theme" rel="stylesheet">
	    
		<!-- 	Data table  dataTables.bootstrap.min  -->
	    <link href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css" id="theme" rel="stylesheet">
	    <link href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css" id="theme" rel="stylesheet">
		<!-- 	    alert chidos -->	   
	    <link href="${pageContext.request.contextPath}/resources/css/sweetalert.css" rel="stylesheet">
	    <!-- Bootstrap Core CSS 2 -->
	    <link href="${pageContext.request.contextPath}/resources/css/steps.css" rel="stylesheet">
	    
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
			
	</head>

	<body class="fix-header fix-sidebar card-no-border">
		 <!-- ============================================================== -->
	    <!-- Preloader - style you can find in spinners.css -->
	    <!-- ============================================================== -->
	    <div class="preloader">
	        <div class="loader">
	            <div class="loader__figure"></div>
	            <p class="loader__label">Admin System Lokers</p>
	        </div>
	    </div>
		<!-- ============================================================== -->
	    <!-- Main wrapper - style you can find in pages.scss -->
	    <!-- ============================================================== -->
	    <div id="main-wrapper">
		    <!-- ============================================================== -->
	        <!-- Topbar header - style you can find in pages.scss -->
	        <!-- ============================================================== -->
	        <header class="topbar">
	            <nav class="navbar top-navbar navbar-expand-md navbar-light">
	                <!-- ============================================================== -->
	                <!-- Logo -->
	                <!-- ============================================================== -->
	                <div class="navbar-header">
	                    <a class="navbar-brand" href="index.jsp">
	                        <!-- Logo icon --><b>
	                            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
	                            <!-- Dark Logo icon -->
	                            <img src="${pageContext.request.contextPath}/resources/images/logo-icon.png" alt="homepage" class="dark-logo" />
	                            <!-- Light Logo icon -->
	                            <img src="${pageContext.request.contextPath}/resources/images/logo-light-icon.png" alt="homepage" class="light-logo" />
	                        </b>
	                        <!--End Logo icon -->
	                        <!-- Logo text --><span>
	                         <!-- dark Logo text -->
	                         <img src="${pageContext.request.contextPath}/resources/images/logo-text.png" alt="homepage" class="dark-logo" />
	                         <!-- Light Logo text -->    
	                         <img src="${pageContext.request.contextPath}/resources//images/logo-light-text.png" class="light-logo" alt="homepage" /></span> </a>
	                </div>
	                <!-- ============================================================== -->
	                <!-- End Logo -->
	                <!-- ============================================================== -->
	                <div class="navbar-collapse">
	                    <!-- ============================================================== -->
	                    <!-- toggle and nav items -->
	                    <!-- ============================================================== -->
	                    <ul class="navbar-nav mr-auto">
	                        <!-- This is  -->
	                        <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up waves-effect waves-dark" href="javascript:void(0)"><i class="sl-icon-menu"></i></a> </li>
	                        <li class="nav-item"> <a class="nav-link sidebartoggler hidden-sm-down waves-effect waves-dark" href="javascript:void(0)"><i class="sl-icon-menu"></i></a> </li>
	                        <!-- ============================================================== -->
	                        <!-- Search -->
	                        <!-- ============================================================== -->
	                        <li class="nav-item hidden-xs-down search-box"> <a class="nav-link hidden-sm-down waves-effect waves-dark" href="javascript:void(0)"><i class="icon-Magnifi-Glass2"></i></a>
	                            <form class="app-search">
	                                <input type="text" class="form-control" placeholder="Search & enter"> <a class="srh-btn"><i class="ti-close"></i></a> </form>
	                        </li>
	                    </ul>
	                    <!-- ============================================================== -->
	                    <!-- User profile and search -->
	                    <!-- ============================================================== -->
	                    <ul class="navbar-nav my-lg-0">
	                        
	                        <!-- ============================================================== -->
	                        <!-- Comment -->
	                        <!-- ============================================================== -->
	                        <li class="nav-item dropdown">
	                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="icon-Bell"></i>
	                                <div class="notify"> <span class="heartbit"></span> <span class="point"></span> </div>
	                            </a>
	                            <div class="dropdown-menu dropdown-menu-right mailbox animated bounceInDown">
	                                <ul>
	                                    <li>
	                                        <div class="drop-title">Notificaciones</div>
	                                    </li>
	                                    <li>
	                                        <div class="message-center">
	                                            <!-- Message -->
	                                            <a href="#">
	                                                <div class="btn btn-danger btn-circle"><i class="fa fa-link"></i></div>
	                                                <div class="mail-contnet">
	                                                    <h5>Proximo camion a salir</h5> <span class="mail-desc">Oaxaca - Mexico</span> <span class="time">9:30 AM</span> </div>
	                                            </a>
	                                            <!-- Message -->
	                                            <a href="#">
	                                                <div class="btn btn-success btn-circle"><i class="ti-calendar"></i></div>
	                                                <div class="mail-contnet">
	                                                    <h5>Ultimo retiro</h5> <span class="mail-desc"> por Daniel ... a las </span> <span class="time">9:10 AM</span> </div>
	                                            </a>
	                                            <!-- Message -->
	                                            <a href="#">
	                                                <div class="btn btn-info btn-circle"><i class="ti-settings"></i></div>
	                                                <div class="mail-contnet">
	                                                    <h5>Llamar a cliente</h5> <span class="mail-desc">Recordar a de equipajes a Fernando</span> <span class="time">9:08 AM</span> </div>
	                                            </a>
	                                            <!-- Message -->
<!-- 	                                            <a href="#"> -->
<!-- 	                                                <div class="btn btn-primary btn-circle"><i class="ti-user"></i></div> -->
<!-- 	                                                <div class="mail-contnet"> -->
<!-- 	                                                    <h5>Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:02 AM</span> </div> -->
<!-- 	                                            </a> -->
	                                        </div>
	                                    </li>
	                                    <li>
	                                        <a class="nav-link text-center" href="javascript:void(0);"> <strong>Ver notificaciones</strong> <i class="fa fa-angle-right"></i> </a>
	                                    </li>
	                                </ul>
	                            </div>
	                        </li>
	                        <!-- ============================================================== -->
	                        <!-- End Comment -->
	                        <!-- ============================================================== -->
	                        <!-- ============================================================== -->
	                        <!-- Messages -->
	                        <!-- ============================================================== -->
	                        <li class="nav-item dropdown">
	                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" id="2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="icon-Mail"></i>
	                                <div class="notify"> <span class="heartbit"></span> <span class="point"></span> </div>
	                            </a>
	                            <div class="dropdown-menu mailbox dropdown-menu-right animated bounceInDown" aria-labelledby="2">
	                                <ul>
	                                    <li>
	                                        <div class="drop-title">Tiene 4 nuevos mensajes</div>
	                                    </li>
	                                    <li>
	                                        <div class="message-center">
	                                            <!-- Message -->
	                                            <a href="#">
	                                                <div class="user-img"> <img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="img-circle"> <span class="profile-status online pull-right"></span> </div>
	                                                <div class="mail-contnet">
	                                                    <h5>Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:30 AM</span> </div>
	                                            </a>
	                                            <!-- Message -->
	                                            <a href="#">
	                                                <div class="user-img"> <img src="${pageContext.request.contextPath}/resources/images/users/2.jpg" alt="user" class="img-circle"> <span class="profile-status busy pull-right"></span> </div>
	                                                <div class="mail-contnet">
	                                                    <h5>Sonu Nigam</h5> <span class="mail-desc">I've sung a song! See you at</span> <span class="time">9:10 AM</span> </div>
	                                            </a>
	                                            <!-- Message -->
	                                            <a href="#">
	                                                <div class="user-img"> <img src="${pageContext.request.contextPath}/resources/images/users/3.jpg" alt="user" class="img-circle"> <span class="profile-status away pull-right"></span> </div>
	                                                <div class="mail-contnet">
	                                                    <h5>Arijit Sinh</h5> <span class="mail-desc">I am a singer!</span> <span class="time">9:08 AM</span> </div>
	                                            </a>
	                                            <!-- Message -->
	                                            <a href="#">
	                                                <div class="user-img"> <img src="${pageContext.request.contextPath}/resources/images/users/4.jpg" alt="user" class="img-circle"> <span class="profile-status offline pull-right"></span> </div>
	                                                <div class="mail-contnet">
	                                                    <h5>Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:02 AM</span> </div>
	                                            </a>
	                                        </div>
	                                    </li>
	                                    <li>
	                                        <a class="nav-link text-center" href="javascript:void(0);"> <strong>See all e-Mails</strong> <i class="fa fa-angle-right"></i> </a>
	                                    </li>
	                                </ul>
	                            </div>
	                        </li>
	                        <!-- ============================================================== -->
	                        <!-- End Messages -->
	                        <!-- ============================================================== -->
	                        <!-- ============================================================== -->
	                        <!-- mega menu -->
	                        <!-- ============================================================== -->
	                        <li class="nav-item dropdown mega-dropdown"> <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="icon-Box-Close"></i></a>
	                            <div class="dropdown-menu animated bounceInDown">
	                                <ul class="mega-dropdown-menu row">
	                                    <li class="col-lg-3 col-xlg-2 m-b-30">
	                                        <h4 class="m-b-20">Maletas o bultos</h4>
	                                        <!-- CAROUSEL -->
	                                        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
	                                            <div class="carousel-inner" role="listbox">
	                                                <div class="carousel-item active">
	                                                    <div class="container"> <img class="d-block img-fluid" src="${pageContext.request.contextPath}/resources/images/big/img1.jpg" alt="First slide"></div>
	                                                </div>
	                                                <div class="carousel-item">
	                                                    <div class="container"><img class="d-block img-fluid" src="${pageContext.request.contextPath}/resources/images/big/img2.jpg" alt="Second slide"></div>
	                                                </div>
	                                                <div class="carousel-item">
	                                                    <div class="container"><img class="d-block img-fluid" src="${pageContext.request.contextPath}/resources/images/big/img3.jpg" alt="Third slide"></div>
	                                                </div>
	                                            </div>
	                                            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Anteior</span> </a>
	                                            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next"> <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only">Siguiente</span> </a>
	                                        </div>
	                                        <!-- End CAROUSEL -->
	                                    </li>
	                                    <li class="col-lg-3 col-xlg-4 m-b-30">
	                                      -----
	                                    </li>
	                                    <li class="col-lg-3 m-b-30">
	                                        <h4 class="m-b-20">DESCRIPCION</h4>
	                                        <!-- Accordian -->
	                                        <div id="accordion" class="nav-accordion" role="tablist" aria-multiselectable="true">
	                                            <div class="card">
	                                                <div class="card-header" role="tab" id="headingOne">
	                                                    <h5 class="mb-0">
	                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	                                                   Item #1
	                                                </a>
	                                              </h5> </div>
	                                                <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
	                                                    <div class="card-body"> Precio $20 x hora </div>
	                                                </div>
	                                            </div>
	                                            <div class="card">
	                                                <div class="card-header" role="tab" id="headingTwo">
	                                                    <h5 class="mb-0">
	                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	                                                   Item #2
	                                                </a>
	                                              </h5> </div>
	                                                <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
	                                                    <div class="card-body"> Precio $25 x hora. </div>
	                                                </div>
	                                            </div>
	                                            <div class="card">
	                                                <div class="card-header" role="tab" id="headingThree">
	                                                    <h5 class="mb-0">
	                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
	                                                  Item #3
	                                                </a>
	                                              </h5> </div>
	                                                <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree">
	                                                    <div class="card-body"> Precio $30 x hora.. </div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </li>

	                                    <li class="col-lg-3 col-xlg-4 m-b-30">
	                                        -----
	                                    </li>
	                                </ul>
	                            </div>
	                        </li>
	                        <!-- ============================================================== -->
	                        <!-- End mega menu -->
	                        <!-- ============================================================== -->	                       
	                        <!-- ============================================================== -->
	                        <!-- Profile -->
	                        <!-- ============================================================== -->
	                        <li class="nav-item dropdown u-pro">
	                            <a class="nav-link dropdown-toggle waves-effect waves-dark profile-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="" /> <span class="hidden-md-down">Administrador &nbsp;<i class="fa fa-angle-down"></i></span> </a>
	                            <div class="dropdown-menu dropdown-menu-right animated flipInY">
	                                <ul class="dropdown-user">
	                                    <li>
	                                        <div class="dw-user-box">
	                                            <div class="u-img"><img src="${pageContext.request.contextPath}/resources/images/1.jpg" alt="user"></div>
	                                            <div class="u-text">
	                                                <h4>Administrador</h4>
	                                                <p class="text-muted">admin@gmail.com</p><a href="pages-profile.html" class="btn btn-rounded btn-danger btn-sm">Ver Perfil</a></div>
	                                        </div>
	                                    </li>
	                                    <li role="separator" class="divider"></li>
	                                    <li><a href="#"><i class="ti-user"></i> Mi perfil</a></li>
	                                    <li><a href="#"><i class="ti-wallet"></i> Agenda </a></li>
	                                    <li><a href="#"><i class="ti-email"></i> Mensajes</a></li>
	                                    <li role="separator" class="divider"></li>
	                                    <li><a href="#"><i class="ti-settings"></i> Configurar cuenta</a></li>
	                                    <li role="separator" class="divider"></li>
	                                    <li><a href="#"><i class="fa fa-power-off"></i> Salir</a></li>
	                                </ul>
	                            </div>
	                        </li>
	                    </ul>
	                </div>
	            </nav>
	        </header>
	        <!-- ============================================================== -->
	        <!-- End Topbar header -->
	        <!-- ============================================================== -->
		    <!-- ============================================================== -->
	        <!-- Left Sidebar - style you can find in sidebar.scss  -->
	        <!-- ============================================================== -->
	        <aside class="left-sidebar">
	            <!-- Sidebar scroll-->
	            <div class="scroll-sidebar">
	                <!-- Sidebar navigation-->
	                 <!-- Menu system -->
	                <nav class="sidebar-nav">
	                    <ul id="sidebarnav">
	                	</ul>
	                </nav>
	               
	                <!-- End Sidebar navigation -->
	            </div>
	            <!-- End Sidebar scroll-->
	        </aside>
	        <!-- ============================================================== -->
	        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
	        <!-- ============================================================== -->
        	 <!-- ============================================================== -->
	        <!-- Page wrapper  -->
	        <!-- ============================================================== -->
	        <div class="page-wrapper">
	        	 <!-- ============================================================== -->
	            <!-- Container fluid  -->
	            <!-- ============================================================== -->
	            <div class="container-fluid" style=" padding: 15px 10px;">					
                	<div class="row" id="divprincipal">
	                	
                    <!-- Column -->
                    <div class="col-lg-3 col-md-6">
                        <div class="card">
                            <div class="card-body">
	                                <div class="d-flex p-10 no-block">
	                                    <span class="align-slef-center">
	                                       <a href="#" onclick="jQuery.ajax({type:'POST',url:'/aps/indexcontrol',beforeSend: function() {},success:function(data,textStatus){ jQuery('#divprincipal').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){  alert('Error: '+errorThrown );}});return false;"> 
	                                       <h2 class="m-b-0">Nuevo Equipaje</h2>
	                                       </a>
<!-- 	                                        <h6 class="text-muted m-b-0">Ultimo ingreso</h6> -->
	                                    </span>	                                    
	                                    <div class="align-self-center display-6 ml-auto"><i class="text-success icon-Target-Market"></i></div>
	                                </div>            
                            </div>
                            <div class="progress">
                                <div class="progress-bar bg-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
<!--                     <div class="col-lg-3 col-md-6"> -->
<!--                         <div class="card"> -->
<!--                             <div class="card-body"> -->
<!--                                 <div class="d-flex p-10 no-block"> -->
<!--                                     <span class="align-slef-center"> -->
<!--                                         <h2 class="m-b-0">Salida Equipaje</h2> -->
<!--                                         <h6 class="text-muted m-b-0">Ultima salida</h6> -->
<!--                                     </span> -->
<!--                                     <div class="align-self-center display-6 ml-auto"><i class="text-info icon-Dollar-Sign"></i></div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             <div class="progress"> -->
<!--                                 <div class="progress-bar bg-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
                    <!-- Column -->
                    <!-- Column -->
                    <div class="col-lg-3 col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex p-10 no-block">
                                    <span class="align-slef-center">
                                        <a href="#" onclick="jQuery.ajax({type:'POST',url:'/aps/getconsultar',beforeSend: function() {},success:function(data,textStatus){ jQuery('#divprincipal').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){  alert('Error: '+errorThrown );}});return false;">
                                        <h2 class="m-b-0">Buscar Equipaje</h2>
                                        </a>
<!--                                         <h6 class="text-muted m-b-0">-</h6> -->
                                    </span>
                                    <div class="align-self-center display-6 ml-auto"><i class="text-primary icon-Inbox-Forward"></i></div>
                                </div>
                            </div>
                            <div class="progress">
                                <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
                    <div class="col-lg-3 col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex p-10 no-block">
                                    <span class="align-slef-center">
                                        <h2 class="m-b-0">0%</h2>
                                        <h6 class="text-muted m-b-0">% Capacidad</h6>
                                    </span>
                                    <div class="align-self-center display-6 ml-auto"><i class="text-danger icon-Contrast"></i></div>
                                </div>
                            </div>
                            <div class="progress">
                                <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
                </div>
	            <!-- ============================================================== -->
	            <!-- End Container fluid  -->
	            <!-- ============================================================== -->
	        	</div>
	            <!-- ============================================================== -->
	            <!-- End Container fluid  -->
	            <!-- ============================================================== -->
	        	<!-- ============================================================== -->
	            <!-- footer -->
	            <!-- ============================================================== -->
	            <footer class="footer"> © 2018 SystemSolutions </footer>
	            <!-- ============================================================== -->
	            <!-- End footer -->
	            <!-- ============================================================== -->
		 	 </div>
	        <!-- ============================================================== -->
	        <!-- End Page wrapper  -->
	        <!-- ============================================================== -->
		 </div>
	    <!-- ============================================================== -->
	    <!-- End Wrapper -->
		<!-- ============================================================== -->
	 <%-- Modal mediano --%>
	  <!-- sample modal content -->
            <div id="myModal-medium" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">Modal Heading</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        </div>
                        <div class="modal-body" id="modal-body-medium">                            
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->  
		 <%-- Modal sin scrolling --%>  
  <div class="modal fade bs-example-modal-lg" id="modalaps" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
      aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">                                       
                    <h4 class="modal-title" id="modal-header">Large modal</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body" id="modal-body">
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
              </div>
            </div>
             <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <%--  Modal detalles--%>
    <div class="modal fade" id="modaleasi-detalle" tabindex="1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog" style="width:95%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" id="modal-detalle">×</button>
                    <div id="modal-header-detalle"></div>
                </div>
                <div class="modal-body modal-body-scrolling" id="modal-body-detalle">
                </div>
            </div>
        </div>
    </div>
    <!-- LOADING -->
		<div class="modal fade" id="loader" tabindex="15" role="basic" aria-hidden="false">
		  <div class="modal-dialog">
		     <br>			 
			  <img src="${pageContext.request.contextPath}/resources/images/DoubleRing.gif" alt="" >
			  </div>
		  </div>			  			  
	     </div>
		

		<!-- basic scripts -->
		<!-- All Jquery -->
    	<!-- ============================================================== -->
		 <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		 <script src="${pageContext.request.contextPath}/resources/js/jquery-2.2.4.min.js"></script>
         
		  <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
		<!-- Bootstrap popper Core JavaScript -->
	    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    	<!-- slimscrollbar scrollbar JavaScript -->
    	<script src="${pageContext.request.contextPath}/resources/js/perfect-scrollbar.jquery.min.js"></script>
    	 <!--Wave Effects -->
	    <script src="${pageContext.request.contextPath}/resources/js/waves.js"></script>
	    <!--Menu sidebar -->
	    <script src="${pageContext.request.contextPath}/resources/js/sidebarmenu.js"></script>
	    <!--Custom JavaScript -->
	    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	    <!-- ============================================================== -->
	     <!-- Popup message jquery -->
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.toast.js"></script>
	    <!-- Chart JS -->
	    <script src="${pageContext.request.contextPath}/resources/js/dashboard1.js"></script>
	
	    <!-- ============================================================== -->
	    <!-- data table -->
	    <!-- ============================================================== -->
	     <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
	     <script src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
	    <!-- ============================================================== -->
	    <!-- Sweet-Alert  -->
	    <!-- ============================================================== -->
	    <script src="${pageContext.request.contextPath}/resources/js/sweetalert.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.sweet-alert.custom.js"></script>
            <!--stickey kit -->
	    <script src="${pageContext.request.contextPath}/resources/js/sticky-kit.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.sparkline.min.js"></script>
	        <!--Custom JavaScript -->
	    <script src="${pageContext.request.contextPath}/resources/js/moment.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.steps.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
        
          <!--  ustom JavaScript  -->
          <script src="${pageContext.request.contextPath}/resources/js/jquery.PrintArea.js"></script>	    
	    
	<!-- inline scripts related to this page -->
	<!-- ============================================================== -->
        <!-- Style switcher -->
        <!-- ============================================================== -->
        <script src="${pageContext.request.contextPath}/resources/js/jQuery.style.switcher.js"></script>
        
	<script type="text/javascript">
		$(function(){

			$.ajax({
		        async:false,
		       url:'${pageContext.request.contextPath}/getviewurl',
		       dataType:'html',
		       success: function (data){
		         $("#sidebarnav").append(data);
   
		      },
		      error: function (data){ 
		    	  alert(data);
		        alert("Ocurrio un error Carga Inicio");       
		      }
		       
		    });
			$('#modal-detalle').click(function() {
				$('#modaleasi-detalle').modal('hide');
			});
		});	
				
		function loader_open() {

			$("#loader").modal("show");
			$('#loader').modal({
				keyboard : false,
				backdrop : 'static'
			});
			$(document).find('body').css('overflow', 'auto');
		}
		function loader_close() {
			setTimeout(function() {
				$('#loader').modal('hide');
			}, 150);
		}
		/*
		@Funcion para mostrar los diferentes tipos de errores en ajax para peticiones 
		*/
		function errorsAjax( jqXHR, exception ){
		     var msg = '';
		          if (jqXHR.status === 0) {
		              msg = 'Not connect.\n Verify Network.';
		          } else if (jqXHR.status == 404) {
		              msg = 'Requested page not found. [404]';
		          } else if (jqXHR.status == 500) {
		              msg = 'Internal Server Error [500].';
		          } else if (exception === 'parsererror') {
		              msg = 'Requested JSON parse failed.';
		          } else if (exception === 'timeout') {
		              msg = 'Time out error.';
		          } else if (exception === 'abort') {
		              msg = 'Ajax request aborted.';
		          } else {
		              msg = 'Uncaught Error.\n' + jqXHR.responseText;
		          }			             
              msg_error( msg );
          	  console.log(exception);
		}
		function msg_error(text_msg){
			 $.toast({
                 heading: 'System Solutions',
                 text: text_msg,
                 position: 'top-right',
                 loaderBg:'#ff6849',
                 icon: 'error',
                 hideAfter: 3500,
                 stack: 6
             })
		}
		function msg_ok(text_msg){
			 $.toast({
                heading: 'System Solutions',
                text: text_msg,
                position: 'top-right',
                loaderBg:'#ff6849',
                icon: 'success',
                hideAfter: 3500,
                stack: 6
            })
		}
		
	</script>
		
	</body>
	
</html>
