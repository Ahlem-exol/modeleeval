<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> 
<%@page import="org.apache.jena.rdf.model.ModelFactory" %>
<%@page import="org.apache.jena.rdf.model.Model" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import= "java.io.*,java.lang.*,java.util.*,java.net.*,java.util.*,java.text.*"%>
<%@ page import="javax.activation.*,org.apache.commons.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<html lang="en"><head>
  <meta charset="utf-8">
  <link rel="apple-touch-icon" sizes="76x76" href="resources/assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="resources/assets/img/favicon.png">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>
   Ontology
  </title>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no" name="viewport">
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <link href="resources/assets/css/material-dashboard.css?v=2.1.0" rel="stylesheet">
  <link href="resources/assets/demo/demo.css" rel="stylesheet">
  <script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script></head>
   <link rel="stylesheet" href="resources/assets/css/styles.css">
<body class="offline-doc dark-edition">
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top " id="navigation-example">
    <div class="container-fluid">
      <div class="navbar-wrapper">
        <a class="navbar-brand" href="#" data-toggle="modal" data-target="#login">
        Espace administrateur
        <div class="ripple-container"></div></a>
      </div>
      <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example">
        <span class="sr-only">Toggle navigation</span>
        <span class="navbar-toggler-icon icon-bar"></span>
        <span class="navbar-toggler-icon icon-bar"></span>
        <span class="navbar-toggler-icon icon-bar"></span>
      </button>
    
    </div>
  </nav>
 

  <div class="page-header ">
    <div class="page-header-image" style="background-image: url('resources/assets/img/sidebar-1.jpg'); "></div>
    <div class="content-center">
      <div class="col-md-8 ml-auto mr-auto">
        <div class="brand"><br><br>
          <h2 class="title">ACCEDER A LA BIBLIOTHEQUE DES MODELES</h2>          
          <br>
       
          <a class="btn btn-primary btn-round btn-lg" href="affichier_tt_les_modeles_user">ACCEDER</a><br><br>
<span class="bmd-form-group"><div class="input-group no-border" style="margin-left:90px">
	<form method="post" action="" class="form-inline" modelAttribute="Search">
                <input type="text" value="" class="form-control"  size=50 name="Ask" path="Ask" placeholder="Recherche  des modèles par mote clé">
                <button type="submit" class="btn btn-default btn-round btn-just-icon">
                  <i class="material-icons">search</i>
                  <div class="ripple-container"></div>
                </button>
                	</form>
              </div></span>
				 <aside class="row widget widget_tag_cloud">
                        <h4 class="widget-title">Mots clé</h4>
                          <div class="tagcloud">
                            <p  class="tag-link-1" >Informatique</p>
                            <p  class="tag-link-1">Système d'information</p>
                            <p class="tag-link-1" >Evaluation</p>
                            <p class="tag-link-1" >Web</p>
                            <p class="tag-link-1" >Satisfiabilité</p>
                            <p class="tag-link-1" >Delone et mclean</p>
                            <p class="tag-link-1" >Modèle</p>   
                        </div>
                    </aside>                   
        </div>
    <div class="container-fluid">
      <nav class="float-left">
        <ul>
          <li>
            <a href="Bibloitheque"> Download Bibliothèque </a>
          </li>
        </ul>
      </nav> 
    </div>
      </div>
    </div>
  </div>
    <c:if test="${not empty search}">
   <c:forEach items="${search}" var="result">  
    <div class ="row">
     <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title ">
                  <a href="afficher_detaille_model_user/${result.idmodel}">${result.nomModel}</a></h4></div>
                 <p> ${result.explain }</p>
  </div></div></div>
    </c:forEach></c:if>
    
    <c:if test="${ empty search}">
   
    <div class ="row">
     <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title ">Pas de résultat</h4></div>
 
  </div></div></div>
 </c:if>
 <!-- model -->
  <div id="login" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Connexion </h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="login" id="contactForm" method="post" modelattribute="User">
      <div class="modal-body">
          
     <div class="form-group bmd-form-group is-filled">  
    <label class="bmd-label-static">Nom d'utilisateur</label><br>
    <input class="form-control" name="username" required>
</div>
               
     <div class="form-group bmd-form-group is-filled">  
    <label class="bmd-label-static">Mot de passe</label><br>
    <input class="form-control" type="password" name="motedepase" required >
</div>            
</div>
      <div class="modal-footer">
     <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">Login</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div><div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>

  </div>
</div>
 
 
  <!--   Core JS Files   -->
  <script src="resources/assets/js/core/jquery.min.js"></script>
  <script src="resources/assets/js/core/popper.min.js"></script>
  <script src="resources/assets/js/core/bootstrap-material-design.min.js"></script>
  <script src="https://unpkg.com/default-passive-events"></script>
  <script src="resources/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Place this tag in your head or just before your close body tag. -->
  <script async="" defer="" src="https://buttons.github.io/buttons.js"></script>
  <script src="resources/assets/js/plugins/chartist.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="resources/assets/js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="resources/assets/js/material-dashboard.js?v=2.1.0"></script>
  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
  <script src="resources/assets/demo/demo.js"></script>
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


</body></html>