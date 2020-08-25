<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> 
<%@page import="org.apache.jena.rdf.model.ModelFactory" %>
<%@page import="org.apache.jena.rdf.model.Model" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en" ><head>
  <meta charset="utf-8">
  <link rel="apple-touch-icon" sizes="76x76" href="../resources/assets//img/apple-icon.png">
  <link rel="icon" type="image/png" href="../resources/assets//img/favicon.png">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.css"/>
  <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.js"></script>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>
Detaille de modele 
  </title>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no" name="viewport">
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons">
      <link rel="stylesheet" href="../resources/assets/demo/bootstrap.min.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- CSS Files -->
  <link href="../resources/assets//css/material-dashboard.css?v=2.1.0" rel="stylesheet">
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href="../resources/assets//demo/demo.css" rel="stylesheet">
<script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps/api/js/AuthenticationService.Authenticate?1sfile%3A%2F%2F%2FC%3A%2FUsers%2Fhp%2FDesktop%2Fadmin%2Fexamples%2FAjoutermodele.html&amp;4sYOUR_KEY_HERE&amp;callback=_xdc_._iv7o5p&amp;key=YOUR_KEY_HERE&amp;token=92200"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script></head>
<body class="dark-edition">
  <div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="black" data-image="../resources/assets//img/sidebar-1.jpg">

      <div class="logo"><a href="http://www.creative-tim.com" class="simple-text logo-normal">
    Menu
        </a></div>
      <div class="sidebar-wrapper ps-container ps-theme-default" data-ps-id="dd0446d1-c173-b851-16e4-1bb62c11e9d7">
      <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="../dach">
           <i class="material-icons"> dashboard</i>
              <p>Tableau de bord</p>
            </a>
          </li>
          <li class="nav-item  ">
            <a class="nav-link" href="../ajouter_nv_modele">
              <i class="material-icons">person</i>
              <p>Ajouter un nouveau modéle</p>
            </a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="../affichier_tt_les_modeles">
              <i class="material-icons">content_paste</i>
              <p>La liste des modèles</p>
            </a>
          </li>
        </ul>
        </div>
        <div class="sidebar-background" style="background-image: url(../resources/assets1/img1/sidebar-2.jpg) "></div>
<form class="navbar-form">
              <span class="bmd-form-group"><div class="input-group no-border">
                <input type="text" value="" class="form-control" placeholder="Chercher...">
                <button type="submit" class="btn btn-default btn-round btn-just-icon">
                  <i class="material-icons">search</i>
                  <div class="ripple-container"></div>
                </button>
              </div></span>
            </form>

    
    </div>
    <div class="main-panel ps-container ps-theme-default ps-active-y" data-ps-id="f50a261f-921f-e57b-eb13-7bb4af368a45">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top " id="navigation-example">
        <div class="container-fluid">
         
          <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end">
           <div class="collapse navbar-collapse justify-content-end">
            <form class="navbar-form" method="post" action="" class="form-inline" modelAttribute="Search">
              <span class="bmd-form-group"><div class="input-group no-border">
                <input type="text" name="Ask" path="Ask"   class="form-control" placeholder="Chercher...">
                <button type="submit" class="btn btn-default btn-round btn-just-icon">
                  <i class="material-icons">search</i>
                  <div class="ripple-container"></div>
                </button>
              </div></span>
            </form>
            <ul class="navbar-nav">
           <li class="nav-item">
                <a class="nav-link" href="../logoute">
                  Deconnecter
                  <p class="d-lg-none d-md-block">
                    Account
                  </p>
                <div class="ripple-container"></div></a>
              </li>
           
        <li>
               <button class="btn btn-primary pull-right" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Download"> 
                Download <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
                      
                      </li>
   
            </ul>
          </div>
        </div>
      </nav>
      <!-- End Navbar -->
      <div class="content">
        <div class="container-fluid">
          <div class="card">
          
            <div class="card-header card-header-primary">
              <h4 class="card-title">${model.nomModel}</h4>
              <p class="card-category"><b>Description de modèle : </b></b><a href="http://bootstrap-notify.remabledesigns.com/" target="_blank">${model.explain }</a>
              </p>
            </div>
            
            <div class="card-body">
              <div class="row">
                
                <div class="col-md-6">
                  <h4 class="card-title">Information de l'article</h4>
                  <div class="alert alert-info alert-with-icon" data-notify="container">
                    
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                      <i class="material-icons">close</i>
                    </button>
                  
                    <span data-notify="message"> <h4>${article.title}</h4><b>Résumé</b>${article.resume}</span>
                  </div>
                  <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                      <i class="material-icons">close</i>
                    </button>
                    <span>
                      	 
                      <b>Mot clé :</b> ${article.motecle}</span>
                  </div>
                  <div class="alert alert-warning">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                      <i class="material-icons">close</i>
                    </button>
                    <span>
                      <b> DOI</b> : ${article.doi} <br>
<b> ISSN </b>:${article.issn}<br>
    <b>url</b>:${article.url}<br>
<b>Réference bibliographique</b>:${article.refernceArticle}
                  </span></div>
                  
                </div>
    <div class="col-md-6">
                  <h4 class="card-title">Information de l'auteur</h4>
                  <div class="alert alert-info">
                  <c:forEach items="${auteurs}" var="result"> 
						
							<span>-<b>${ result.nom} ${result.prenom}</b> ${result.univers} ${result.position}</span>	
						
							</c:forEach> 
          
                  </div>
     <h4 class="card-title">Information de modèle</h4>
                  <div class="alert alert-info">
                      <span><b>Les dimensions :</b><br>
                      <ul>  <c:forEach items="${dim}" var="result5">   
    <li>${result5.nom }</li>
     </c:forEach> </ul>
                      <b>Les critères :</b>  <br>
                      <ul> <c:forEach items="${Cret}" var="result6">   
    <li>${result6.nomC}</li>
     </c:forEach> </ul>
                      <b>Les hypothèses :</b> 
                        <ul>
                         <c:forEach items="${hypo}" var="result4">   
    <li>${result4.enonceHypothse}</li>
     </c:forEach> 
                          
                          </ul>
                      
                      </span>
                  </div>
                  
                  
                  
                  
                </div>
              </div>
            </div>
         <div class="col-md-12">
              <div class="places-buttons">
                <div class="row">
                  <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Le questionnaire du modéle</h4>
                  </div>
              
                <div class="card-body">
            
                         <div class="card-body table-responsive">
                 <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                
                             <thead>
                        <tr>
                         <th>Crétaire</th>      
                         <th>Question</th>    
                         <th>Choix</th>   
                        </tr>
                       </thead>
                           <tbody>
                         <c:forEach items="${Cret}" var="result1">                
                       
                            
                         <c:forEach items="${qst}" var="result2">  
                         <c:if test="${ result2.cretaire eq result1.nomC }">  
                           <tr>
                           <th>${result2.cretaire}</th>    
                          <th> ${result2.qest}</th>                   
                          <c:if test="${empty choix}"  >
                          <th>Tout à fait d'accird |d'accord  |  plutot d'accord |Indifférent | Plutot pas d'accord | pas d'accord  | Pas du tout d'accord</th>
                          </c:if> 
                         <c:if test="${not empty choix}">
                         <th>
                          <c:forEach items="${choix}" var="result44">  
                             <c:if test="${ result44.qstion eq result2.qest }"> ${ result44.choix}  </c:if>
                          </c:forEach> 
                             </th>
                               </c:if> 
                                </tr>
                                </c:if> 
                              </c:forEach>                   
                
                    	</c:forEach> 
                      </tbody>
                  
                    </table>
              </div>

            
                </div>
              </div>
               
                
                  </div>
              </div>
            </div>
            </div>
          </div>
        </div>
      </div>
                
              

      <script>
        const x = new Date().getFullYear();
        let date = document.getElementById('date');
        date.innerHTML = '&copy; ' + x + date.innerHTML;
      </script>
    <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px; height: 624px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 380px;"></div></div><div class="ps-scrollbar-x-rail" style="width: 838px; left: 0px; bottom: -150.857px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 804px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 150.857px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 84px; height: 348px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -452.571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 452.571px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 262px; height: 361px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 790px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 753px;"></div></div></div>

 <!-- ###########################################download  ##############################" -->
	<div id="Download" class="modal fade" role="dialog" style="display: none;">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title">Download</h4>
      </div>
        <form action="${model.idmodel}"  id="contactForm" method="post"  modelAttribute="Format" >
      <div class="modal-body">     		
    <div class="form-group">   <label> Choisir format </label><br><br>
    <select class="valid" id="type"  name="type">
    <option value="0"> txt </option>
    <option value="1">excel</option>
    <option value="2">docx </option>    
  </select>
    </div>
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;" >valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>
  </div>
</div>

 
   
 
  <!--   Core JS Files   -->
  <script src="../resources/assets//js/core/jquery.min.js"></script>
  <script src="../resources/assets//js/core/popper.min.js"></script>
  <script src="../resources/assets//js/core/bootstrap-material-design.min.js"></script>
  <script src="https://unpkg.com/default-passive-events"></script>
  <script src="../resources/assets//js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Place this tag in your head or just before your close body tag. -->
  <script async="" defer="" src="https://buttons.github.io/buttons.js"></script>

  <script src="../resources/assets//js/plugins/chartist.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="../resources/assets//js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="../resources/assets//js/material-dashboard.js?v=2.1.0"></script>
  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
  <script src="../resources/assets//demo/demo.js"></script>
  <script>
    $(document).ready(function() {
      $().ready(function() {
        $sidebar = $('.sidebar');

        $sidebar_img_container = $sidebar.find('.sidebar-background');

        $full_page = $('.full-page');

        $sidebar_responsive = $('body > .navbar-collapse');

        window_width = $(window).width();

        $('.fixed-plugin a').click(function(event) {
          // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
          if ($(this).hasClass('switch-trigger')) {
            if (event.stopPropagation) {
              event.stopPropagation();
            } else if (window.event) {
              window.event.cancelBubble = true;
            }
          }
        });

        $('.fixed-plugin .active-color span').click(function() {
          $full_page_background = $('.full-page-background');

          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data-color', new_color);
          }

          if ($full_page.length != 0) {
            $full_page.attr('filter-color', new_color);
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.attr('data-color', new_color);
          }
        });

        $('.fixed-plugin .background-color .badge').click(function() {
          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('background-color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data-background-color', new_color);
          }
        });

        $('.fixed-plugin .img-holder').click(function() {
          $full_page_background = $('.full-page-background');

          $(this).parent('li').siblings().removeClass('active');
          $(this).parent('li').addClass('active');


          var new_image = $(this).find("img").attr('src');

          if ($sidebar_img_container.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
            $sidebar_img_container.fadeOut('fast', function() {
              $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
              $sidebar_img_container.fadeIn('fast');
            });
          }

          if ($full_page_background.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

            $full_page_background.fadeOut('fast', function() {
              $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
              $full_page_background.fadeIn('fast');
            });
          }

          if ($('.switch-sidebar-image input:checked').length == 0) {
            var new_image = $('.fixed-plugin li.active .img-holder').find("img").attr('src');
            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

            $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
            $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.css('background-image', 'url("' + new_image + '")');
          }
        });

        $('.switch-sidebar-image input').change(function() {
          $full_page_background = $('.full-page-background');

          $input = $(this);

          if ($input.is(':checked')) {
            if ($sidebar_img_container.length != 0) {
              $sidebar_img_container.fadeIn('fast');
              $sidebar.attr('data-image', '#');
            }

            if ($full_page_background.length != 0) {
              $full_page_background.fadeIn('fast');
              $full_page.attr('data-image', '#');
            }

            background_image = true;
          } else {
            if ($sidebar_img_container.length != 0) {
              $sidebar.removeAttr('data-image');
              $sidebar_img_container.fadeOut('fast');
            }

            if ($full_page_background.length != 0) {
              $full_page.removeAttr('data-image', '#');
              $full_page_background.fadeOut('fast');
            }

            background_image = false;
          }
        });

        $('.switch-sidebar-mini input').change(function() {
          $body = $('body');

          $input = $(this);

          if (md.misc.sidebar_mini_active == true) {
            $('body').removeClass('sidebar-mini');
            md.misc.sidebar_mini_active = false;

            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

          } else {

            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

            setTimeout(function() {
              $('body').addClass('sidebar-mini');

              md.misc.sidebar_mini_active = true;
            }, 300);
          }

          // we simulate the window Resize so the charts will get updated in realtime.
          var simulateWindowResize = setInterval(function() {
            window.dispatchEvent(new Event('resize'));
          }, 180);

          // we stop the simulation of Window Resize after the animations are completed
          setTimeout(function() {
            clearInterval(simulateWindowResize);
          }, 1000);

        });
      });
    });
  </script>

   <script src="../resources/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="../resources/assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
              $(document).ready(function () {
                $('#dataTables-example1').dataTable();
            });  
    </script>
</body></html>