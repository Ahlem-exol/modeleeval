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

<html lang="en" class="perfect-scrollbar-on"><head>
  <meta charset="utf-8">
  <link rel="apple-touch-icon" sizes="76x76" href="resources/assets//img/apple-icon.png">
  <link rel="icon" type="image/png" href="resources/assets//img/favicon.png">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.css"/>
 
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.js"></script>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>
 Ajouter model
  </title>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no" name="viewport">
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons">
      <link rel="stylesheet" href="resources/assets//demo/bootstrap.min.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- CSS Files -->
  <link href="resources/assets//css/material-dashboard.css?v=2.1.0" rel="stylesheet">
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href="resources/assets//demo/demo.css" rel="stylesheet">
<script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps/api/js/AuthenticationService.Authenticate?1sfile%3A%2F%2F%2FC%3A%2FUsers%2Fhp%2FDesktop%2Fadmin%2Fexamples%2Fajouter_nv_modele_user.html&amp;4sYOUR_KEY_HERE&amp;callback=_xdc_._iv7o5p&amp;key=YOUR_KEY_HERE&amp;token=92200"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script></head>


<script>
function myFunction() {
  var x = document.getElementById("myDIV");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}
</script>
<body class="dark-edition" style="">
  <div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="black" data-image="resources/assets//img/sidebar-1.jpg">
      <div class="logo"><a href="http://www.creative-tim.com" class="simple-text logo-normal">Menu</a></div>
      <div class="sidebar-wrapper ps-container ps-theme-default" data-ps-id="029f9399-28bc-3c83-1406-31fad7022761">      
            <form class="navbar-form">
              <span class="bmd-form-group"><div class="input-group no-border">
                <input type="text" value="" class="form-control" placeholder="Chercher...">
                <button type="submit" class="btn btn-default btn-round btn-just-icon">
                  <i class="material-icons">search</i>
                  <div class="ripple-container"></div>
                </button>
              </div></span>
            </form>
            <ul class="nav">        
        <li class="nav-item  ">
            <a class="nav-link" href="">              
              <p>Accueil</p>
            </a>
          </li><li class="nav-item ">
            <a class="nav-link" href="affichier_tt_les_modeles_user">          
              <p>La liste des modèles</p>
            </a>
          </li><li class="nav-item active ">
            <a class="nav-link" href="ajouter_nv_modele_user">
              <p>Creer un nouveau modéle</p>
            </a>
          </li>
        </ul>
 <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 618px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 312px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 149px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 757px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 284px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 398px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 45px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 757px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div></div>
    <div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(resources/assets//img/sidebar-2.jpg) "></div></div>
    <div class="main-panel ps-container ps-theme-default ps-active-y" data-ps-id="431413b2-6c48-ed7e-cc74-ab9ff7335daf">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top " id="navigation-example" style="">
        <div class="container-fluid">
          
          <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example" style="">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end">
            <form class="navbar-form">
              <span class="bmd-form-group"><div class="input-group no-border">
                <input type="text" value="" class="form-control" placeholder="Chercher...">
                <button type="submit" class="btn btn-default btn-round btn-just-icon">
                  <i class="material-icons">search</i>
                  <div class="ripple-container"></div>
                </button>
              </div></span>
            </form>
            <ul><li>
               <button class="btn btn-primary pull-right" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Download"> 
                Download <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
                      </li></ul>
          </div>
        </div>
      </nav>
      <!-- End Navbar -->
      <div class="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-8">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Créer un nouveau modèle</h4>
                  
                </div>
                <div class="card-body">
                <div  id="myDIV" > 
                <form action="ajouter_nv_modele_user" id="contactForm" method="post" class="contact-form" modelattribute="Form">
                     <label>Ajouter les informations du modèle</label>
                    <div class="row">
                      <div class="col-md-5">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating"> Nom du modèle</label><br>
                        <input type="text" path="nomModel" name="nomModel" id="name" placeholder="Full name" class="form-control error">
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Description du modèle </label><br>
                         <textarea path="explain" name="explain" id="message" rows="10" class="form-control error" placeholder="Enter text here "></textarea>
                        </div>
                      </div>
                      
                    </div><button class="btn btn-primary pull-right"> Confirmer<div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
</form></div>

<br><br><br>
                  
            <div class="row">    
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#modif_dim">Ajouter  la dimension</a> 
                        </div>
                      </div><div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;"  href="#" data-toggle="modal" data-target="#importer_dim">importer de la bibliothéque</a> 
                        </div>
                      </div>      
            </div>
           <div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#modif_sous_dim">Ajouter la sous-dimension</a>
                          
                        </div>
                      </div>
            <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;"  href="#" data-toggle="modal" data-target="#import_sous_dim">importer de la bibliothéque</a> 
                        </div>
                      </div>
                      
                    </div>
            <div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;"  href="#" data-toggle="modal" data-target="#modif_critere">Ajouter  le critére</a>
                          
                        </div>
                      </div>
            <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;"  href="#" data-toggle="modal" data-target="#import_critere">importer de la bibliothéque</a> 
                        </div>
                      </div>
                      
                    </div><div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;"  href="#" data-toggle="modal" data-target="#modif_question">Ajouter  la question</a>
                          
                        </div>
                      </div>
      
                    </div>

           <div class="row">                    
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#ajouter_hypo">Ajouter  l'hypothése</a>                         
                        </div>
                      </div>
                
                    </div>
          </div>
              </div>
            </div>
          </div>
<!--       ##############################  Afficher   ##############################   -->
          <div class="row">
            <div class="col-md-12">
       <div class="card">
                <div class="card-header card-header-primary">
  
                  <c:if test="${not empty noma}">
	  <h4 class="card-title">Voter Modéle : ${noma.nomModel}</h4>
	</c:if>
              </div>
                
       
        <!--  afficher dim -->
	<c:if test="${not empty Dimonsion}">
	<c:forEach items="${Dimonsion}" var="result">  
	<div class="row">
		 <div class="col-sm-4 col-md-4 why-to-choose">
                   <div>
    <h2 class="h4 wtc-title">Dimension:  ${result.nom}</h2>  
                   
<c:if test="${not empty dimFils}">
	<c:forEach items="${dimFils}" var="result1">  
                   <div>
<c:if test="${result.nom  eq  result1.nommere }">               
         <h2 class="h4 wtc-title">sous dimension:  ${result1.nom}</h2>            
<c:if test="${not empty Qstionne}">
  <c:forEach items="${Qstionne}" var="result2">  
     <div>
        <c:if test="${result1.nom  eq  result2.cretaire }">               
          <h2 class="h4 wtc-title">Qeustion:  ${result2.qest}</h2>
      </c:if> 
	</div>	
  </c:forEach>
</c:if> 
</c:if> 

<c:forEach items="${dimFils}" var="result5">  
  <c:if test="${result1.nommere  eq  result5.nom }">               
         <h2 class="h4 wtc-title">sous dimension: ${result1.nom}</h2>            
<c:if test="${not empty Qstionne}">
	<c:forEach items="${Qstionne}" var="result2">  
                   <div>
        <c:if test="${result1.nom  eq  result2.cretaire }">               
                       <h2 class="h4 wtc-title">Qeustion : ${result2.qest}</h2>
            </c:if> 

	</div>	</c:forEach> </c:if> 
                       
            </c:if> 
</c:forEach> 
	</div>	</c:forEach> </c:if> 
</div>
 </div>
	</div>	</c:forEach> </c:if> 

<!--  afficher Creatire  -->
	<c:if test="${not empty Cretaire}">
	<c:forEach items="${Cretaire}" var="result">  
	<div class="row">
		 <div class="col-sm-4 col-md-4 why-to-choose">
<div>
<h2 class="h4 wtc-title" >cretaire :${result.nomC}</h2> 
<c:if test="${not empty Qstionne}">
	<c:forEach items="${Qstionne}" var="result1">  
                   <div>
        <c:if test="${result.nomC  eq  result1.cretaire }">               
                       <h2 class="h4 wtc-title">${result1.qest}</h2>
            </c:if> 
	</div>	</c:forEach> </c:if> 
</div>
</div>
</div>	</c:forEach> </c:if>


           </div></div>
          </div>

        </div>
      </div>
     
    <!-- ########################################### Ajouter Dimension ################################### -->
    <div id="modif_dim" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter la dimension</h4><button type="button" class="close" data-dismiss="modal">×</button>        
      </div>   
        <form action="ajouter_nv_modele_user"  id="contactForm" method="post"  modelAttribute="Dimonsion" >
      <div class="modal-body">
      		
     <div class="form-group">   <input type="text" path="nom"  class="form-control" name="nom" rows="10" placeholder="nom de Dimonsion"></div>
     <div class="form-group">   <textarea  class="form-control" name ="descreption" path ="descreption" rows="10" placeholder="Descreption de Dimonsion "></textarea></div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form> 
    </div>
  </div>
</div>
 <!-- ###########################################Ajouter Qstion  ##############################" -->
  <div id="modif_question" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter Question</h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele_user"  id="contactForm" method="post"  modelAttribute="Qstionne" >
      <div class="modal-body">      		
          <div class="form-group">   <input type="text" path="cretaire"  class="form-control" name="cretaire" rows="10" placeholder="nom de Crétaire"></div>
     <div class="form-group">  <textarea  class="form-control" name ="qest" path ="qest" rows="10" placeholder="Descreption de Question "></textarea></div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>
  </div>
</div>        

<!-- ###########################################Ajouter hypo  ##############################" -->
  <div id="ajouter_hypo" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter l'hypothèse </h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele_user" id="contactForm" method="post" modelattribute="Qstionne">
      <div class="modal-body">          
          <div class="form-group bmd-form-group">   <input type="text" path="cretaire" class="form-control" name="cretaire" rows="10" placeholder="enoncé"></div>
                 
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 72.3928px; top: 38.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 72.3928px; top: 38.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>
  </div>
</div> 
<!-- ###########################################Ajouter Sous dim  ##############################" -->
    <div id="modif_sous_dim" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter la sous-dimension</h4><button type="button" class="close" data-dismiss="modal">×</button>        
      </div>
   <form action="ajouter_nv_modele_user"  id="contactForm" method="post"  modelAttribute="Dimonsion" >
      <div class="modal-body">
    <div class="form-group">   <input type="text" path="nommere"  class="form-control" name="nommere" rows="10" placeholder="nom de Mere Dimonsion"></div>   		
     <div class="form-group">   <input type="text" path="nom"  class="form-control" name="nom" rows="10" placeholder="nom de Dimonsion"></div>
     <div class="form-group">   <textarea  class="form-control" name ="descreption" path ="descreption" rows="10" placeholder="Descreption de Dimonsion "></textarea></div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" >valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>
  </div>
</div>
<!-- ######################################################ajouter  -->

  <div id="modif_question" class="modal fade" role="dialog" style="display: none;">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title">Ajouter Question </h4>
      </div>
        <form action="ajouter_nv_modele_user" id="contactForm" method="post" modelattribute="Qstionne">
      <div class="modal-body">          
          <div class="form-group bmd-form-group">   <input type="text" path="cretaire" class="form-control" name="cretaire" rows="10" placeholder="nom de Crétaire"></div>
     <div class="form-group bmd-form-group">  <textarea class="form-control" name="qest" path="qest" rows="10" placeholder="Descreption de Question "></textarea></div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>
  </div>
</div>
<!-- ###########################################Ajouter Cretaire  ##############################" -->
  <div id="modif_critere" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter le critère</h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele_user" id="contactForm" method="post" modelattribute="Cretaire">
      <div class="modal-body">
          
     <div class="form-group bmd-form-group">   <input type="text" path="nomC" class="form-control" name="nomC" rows="10" placeholder="Le nom du critère"></div>
     <div class="form-group bmd-form-group">   
<input class="form-control" name="descreptionC" path="descreptionC" rows="10" placeholder="La description du critére"><textarea class="form-control" name="descreptionC" path="descreptionC" rows="10" placeholder="La référence bibliographique"></textarea></div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div><div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>

  </div>
</div>


<!-- ###########################################importer_dim ##############################" -->
  <div id="importer_dim" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title"> importer une dimension</h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
         <form action="ajouter_nv_modele_user"  id="contactForm" method="post"  modelAttribute="Format" >
      <div class="modal-body">
      <div class="form-group">   <label> Choisir la dimonsion  </label>
    <select class="valid" id="NDSC"  name="NDSC" class="form-control" >
 <c:forEach items="${listDim}" var="result8" >  
    <option value="${result8.nom}" class="form-control"  > ${result8.nom } </option> 
  </c:forEach>   
     </select>
    </div>   
  
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" >valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>

  </div>
</div>

<!-- ###########################################importer sous dim ##############################" -->
  <div id="import_sous_dim" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Importer une sous dimension</h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele_user" id="contactForm" method="post" modelattribute="Cretaire">
      <div class="modal-body">
          
     <div class="form-group bmd-form-group is-filled">  
    <label class="bmd-label-static">Selectionner une sous-dimension</label><br>
    <select class="valid">
    <option value="0"> sous_dimension 1 (nom du dimension mére) </option>
     <option value="0"> sous_dimension 1 (nom du dimension mére) </option> <option value="0"> sous_dimension 1 (nom du dimension mére) </option> <option value="0"> sous_dimension 1 (nom du dimension mére) </option>


    
    </select></div>
                 
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default">Importer</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div><div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>

  </div>
</div>


<!-- ###########################################Ajouter Cretaire  ##############################" -->
  <div id="import_critere" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Importer un critére </h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
<form action="ajouter_nv_modele_user"  id="contactForm" method="post"  modelAttribute="Format" >
      <div class="modal-body">
      <div class="form-group">   <label> Choisir Le cretaire  </label>
    <select class="valid" id="NCSD"  name="NCSD" class="form-control" >
 <c:forEach items="${listCretair}" var="result9" >  
    <option value="${result9.nomC }" class="form-control"  > ${result9.nomC }(${result9.nomM }) </option> 
  </c:forEach>   
     </select>
    </div>
      
      
      
  
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" >valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>

  </div>
</div>


<!-- #########################################################################" --> 

<!-- ###########################################importer question  ##############################" -->
  <div id="import_question" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Importer une question</h4><button type="button" class="close" data-dismiss="modal">×</button>
      </div>
        <form action="ajouter_nv_modele_user" id="contactForm" method="post" modelattribute="Cretaire">
      <div class="modal-body">
          
     <div class="form-group bmd-form-group is-filled">  
    <label class="bmd-label-static">Selectionner une question</label><br>
    <select class="valid">
    <option value="0"> Nom du question1 (nom du critére) </option>
     <option value="0">Nom du question</option> <option value="0"> Nom du question2 (nom du critére) </option> <option value="0"> Nom du critére1 (nom du critére) </option>
    </select></div>
                 
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default">Importer</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div><div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>

  </div>
</div>

   
<!-- ###########################################Ajouter Cretaire  ##############################" -->
  <div id="import_hypo" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Importer une hypothése </h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele_user" id="contactForm" method="post" modelattribute="Cretaire">
      <div class="modal-body">
          
     <div class="form-group bmd-form-group is-filled">  
    <label class="bmd-label-static">Selectionner une hypothése</label><br>
    <select class="valid">
    
<option value="0"> hypothése(Dimension1, dimension2) </option>
<option value="0"> hypothése(Dimension1, dimension2) </option>
<option value="0"> hypothése(Dimension1, dimension2) </option><option value="0"> hypothése(Dimension1, dimension2) </option>

    </select></div>
                 
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default">Importer</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div><div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>

  </div>
</div>


<!-- #########################################################################" --> 
   
<div id="Download" class="modal fade" role="dialog" style="display: none;">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title">Download</h4>
      </div>
        <form action="ajouter_nv_modele_user"  id="contactForm" method="post"  modelAttribute="Format" >
      <div class="modal-body">     		
    <div class="form-group">   <label> Choisir format </label>
    <select class="valid" id="type"  name="type">
    <option value="0"> txt </option>
    <option value="1">xslt</option>
    <option value="2">doc </option>    
  </select>
    </div>
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" >valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>
  </div>
</div>
   
    
      <script>
        const x = new Date().getFullYear();
        let date = document.getElementById('date');
        date.innerHTML = '&copy; ' + x + date.innerHTML;
      </script>
    <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px; height: 1248px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 832px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 702px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 312px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -211.81px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 211.81px; height: 832px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 141px; height: 554px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 702px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 702px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -489.286px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 489.286px; height: 780px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 301px; height: 479px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -198.571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 198.571px; height: 780px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 122px; height: 479px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -80.5714px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 80.5714px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 59px; height: 690px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 832px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 545px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -96.8571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 96.8571px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 71px; height: 690px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -1208.57px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 1208.57px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 410px; height: 211px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -30.8571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 30.8571px; height: 1872px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 30px; height: 1842px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 204px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 204px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 204px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 460px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -531.429px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 531.429px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 261px; height: 460px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 2496px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 2467px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 2496px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 2467px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 156px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -528px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 528px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 132px; height: 156px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 780px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 243px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -503.571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 503.571px; height: 780px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 157px; height: 243px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 780px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 243px;"></div></div></div>
  </div>
    <script>
function myFunction() {
  var x = document.getElementById("myDIV");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}
</script>
  
  <script src="resources/assets//js/core/jquery.min.js"></script>
  <script src="resources/assets//js/core/popper.min.js"></script>
  <script src="resources/assets//js/core/bootstrap-material-design.min.js"></script>
  <script src="https://unpkg.com/default-passive-events"></script>
  <script src="resources/assets//js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <script async="" defer="" src="https://buttons.github.io/buttons.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <script src="resources/assets//js/plugins/chartist.min.js"></script>
  <script src="resources/assets//js/plugins/bootstrap-notify.js"></script>
  <script src="resources/assets//js/material-dashboard.js?v=2.1.0"></script>
  <script src="resources/assets//demo/demo.js"></script>
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

   <script src="resources/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="resources/assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
              $(document).ready(function () {
                $('#dataTables-example1').dataTable();
            });  
    </script>
    




</body></html>