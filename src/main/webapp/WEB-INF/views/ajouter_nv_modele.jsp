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
Ajouter modéle
  </title>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no" name="viewport">
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons">
      <link rel="stylesheet" href="resources/assets/demo/bootstrap.min.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- CSS Files -->
  <link href="resources/assets//css/material-dashboard.css?v=2.1.0" rel="stylesheet">
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href="resources/assets//demo/demo.css" rel="stylesheet">
<script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps/api/js/AuthenticationService.Authenticate?1sfile%3A%2F%2F%2FC%3A%2FUsers%2Fhp%2FDesktop%2Fadmin%2Fexamples%2FAjoutermodele.html&amp;4sYOUR_KEY_HERE&amp;callback=_xdc_._iv7o5p&amp;key=YOUR_KEY_HERE&amp;token=92200"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script></head>
<body class="dark-edition">
  <div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="black" data-image="resources/assets//img/sidebar-1.jpg">

      <div class="logo"><a href="http://www.creative-tim.com" class="simple-text logo-normal">
      Espace Administration
        </a></div>
      <div class="sidebar-wrapper ps-container ps-theme-default" data-ps-id="dd0446d1-c173-b851-16e4-1bb62c11e9d7">
              <ul class="nav">
          <li class="nav-item ">
            <a class="nav-link" href="dach">
              <i class="material-icons"> dashboard</i>
              <p>Tableau de bord</p>
            </a>
          </li>
          <li class="nav-item active ">
            <a class="nav-link" href="ajouter_nv_modele">
              <i class="material-icons">person</i>
              <p>Ajouter un nouveau modéle</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="affichier_tt_les_modeles">
              <i class="material-icons">content_paste</i>
              <p>La liste des modèles</p>
            </a>
          </li>
        </ul>

      <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 618px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 312px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 149px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 757px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 284px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 398px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 45px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div></div>
    <div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url(../assets/img/sidebar-2.jpg) "></div></div>
    <div class="main-panel ps-container ps-theme-default ps-active-y" data-ps-id="29164c0b-7e4b-3399-9d17-2728122318e6">
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
          <div class="row">
            <div class="col-md-8">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Ajouter le modéle</h4>
                  
                </div>

                <div class="card-body">
       <form action="ajouter_nv_modele" modelattribute="Form"  method="post">
    <label>Ajouter les informations du modèle</label>
          <div class="row">
                      <div class="col-md-5">
                        <div class="form-group bmd-form-group">
              
                          <label class="bmd-label-floating">Nom du modèle</label><br>
                          <input name="nomModel" type="text" class="form-control" required>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Description du modèle </label>

                            <textarea  name ="explain"  rows="10" class="form-control" required></textarea>
                        </div>
                      </div>
                      
                    </div><button class="btn btn-primary pull-right" type="submit"> Confirme <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
<br><br>
</form>
       <form action="ajouter_nv_modele" modelattribute="Article"  method="post">
<label>Ajouter les informations d'article</label>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating"> Titre de l'article</label>
                          <input name="title" type="text" class="form-control" required>
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Résumé</label>
                          <textarea name="resume" rows="10"  class="form-control" required></textarea>
                        </div>
                      </div>
                      <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">DOI</label>
                          <input name="doi" type="text" class="form-control">
                        </div>
                      </div>
                      
                       <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">ISSN</label>
                          <input name="issn" type="text" class="form-control" required>
                        </div>
                      </div>
                     <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">URL</label>
                          <input name="url" type="text" class="form-control" required>
                        </div>
                      </div>
                   <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating"> Mots clés</label>
                          <textarea name="motecle" type="text" class="form-control" required></textarea>
                        </div>
                      </div><div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Référence bibliograhique</label>
                          <textarea name="refernceArticle" type="text" class="form-control" required></textarea>
                        </div>
                      </div>
                    </div>


<button class="btn btn-primary pull-right" type="submit" >Confirmer</a> <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
<br><br><br>
</form>


       <form action="ajouter_nv_modele" modelattribute="Auteur"  method="post">
<label>Ajouter les informations de l'auteur</label>
                    <div class="row">
                      <div class="col-md-5">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating"> Nom</label>
                          <input  name ="nom" type="text" class="form-control" required>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Prénom </label>
                          <input name="prenom" type="text" class="form-control" required>
                        </div>
                      </div>
                      
                    </div>
    
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Université</label>
                          <input name="univers" type="text" class="form-control" required>
                        </div>
                      </div>
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-static">Date de naissance</label>
                          <input name="datedenaissance" type="date" class="form-control" required>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Position</label>
                          <input name="position" type="text" class="form-control" required>
                        </div>
                      </div>

                    </div>                  
<button class="btn btn-primary pull-right" type="submit">Confirmer</a> <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>

                    <br><br>
                    </form>

                    
                    <div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#modif_dim">Ajouter dimension</a> 
                        </div>
                      </div>

                      
                    </div>
<div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#modif_sous_dim">Ajouter sous-dimension</a>
                          
                        </div>
                      </div>
                      
                    </div>

    <div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#modif_question">Ajouter question</a>
                          
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
                    
                        <div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#Ajouter_choix">Ajouter  Choix pour question</a>
                          
                        </div>
                      </div>
                      
                    </div>
                        <div class="row">
                      
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <a style="color: #000;" href="#" data-toggle="modal" data-target="#Ajouter_Moderateur">Ajouter  Moderateur</a>
                          
                        </div>
                      </div>
                      
                    </div>

                   
                    
                </div>
              </div>
            </div>
                    
    <!-- ###########################################Ajouter DIm ##############################" -->
    <div id="modif_dim" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter la dimension</h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
      <form action="ajouter_nv_modele" id="contactForm" method="post" modelattribute="Dimonsion">
      <div class="modal-body">
          
     <div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="nom" class="form-control" name="nom" rows="10" placeholder="Nom du dimension"></div>
     <div class="form-group bmd-form-group">   <textarea Style="color:#000000;"  class="form-control" name="descreption" path="descreption" rows="10" placeholder="Description du dimension"></textarea></div>            
 <div class="form-group bmd-form-group">
 <select class="valid" id="type"  name="type" class="form-control" >  
    <option value="0" class="form-control"  >Dimonsion</option> 
        <option value="1" class="form-control"  >Crétaire</option>    
     </select>
   </div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 34.3928px; top: 1.85712px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
     </form> 
    </div>

  </div>
</div>
 <!-- ###########################################Ajouter Qstion  ##############################" -->
  <div id="ajouter_hypo" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter l'hypothése</h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele" id="contactForm" method="post" modelattribute="Hypothse">
      <div class="modal-body">          
<div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="dimonsionindp" class="form-control" name="dimonsionindp" placeholder="dimension source"></div>
<div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="dimonsiondep" class="form-control" name="dimonsiondep"  placeholder="dimension cible"></div>
<div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="enonceHypothse" class="form-control" name="enonceHypothse" rows="10" placeholder="enoncé de l'hypothése"></div>
                 
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 72.3928px; top: 38.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 72.3928px; top: 38.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>
  </div>
</div>        
<!-- ###########################################Ajouter hypo  ##############################" -->
  <div id="modif_question" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter Question </h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele" id="contactForm" method="post" modelattribute="Qstionne">
      <div class="modal-body">          
          <div class="form-group bmd-form-group">   <input Style="color:#000000;"type="text" path="cretaire" class="form-control" name="cretaire" rows="10" placeholder="cretaire"></div>
          <div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="qest" class="form-control" name="qest" rows="10" placeholder="enoncé"></div>
                  
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
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
      <form action="ajouter_nv_modele" id="contactForm" method="post" modelattribute="Dimonsion">
      <div class="modal-body">
    <div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="nommere" class="form-control" name="nommere" rows="10" placeholder="Nom de dimension mére" Style="color:#000000;"></div>       
     <div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="nom" class="form-control" name="nom" rows="10" placeholder="Nom su dimension" Style="color:black;" ></div>
     <div class="form-group bmd-form-group">   <textarea Style="color:#000000;"class="form-control" name="descreption" path="descreption" rows="10" placeholder="Description du dimension" Style="color:black;"></textarea></div>            
 <div class="form-group bmd-form-group">
 <select class="valid" id="type"  name="type" class="form-control" >  
    <option value="0" class="form-control"  >Dimonsion</option> 
        <option value="1" class="form-control"  >Crétaire</option>    
     </select>
   </div>    
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 69.3928px; top: 7.85712px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>

  </div>
</div>


  <div id="Ajouter_choix" class="modal fade" role="dialog" style="display: none;">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title">Ajouter Choix </h4>
      </div>
        <form action="ajouter_nv_modele" id="contactForm" method="post" modelattribute="choix">
      <div class="modal-body">          
          <div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="choix" class="form-control" name="choix" rows="10" placeholder="nom de CHOIX"></div>
     <div class="form-group bmd-form-group">  <textarea Style="color:#000000;" class="form-control" name="qstion" path="qstion" rows="10" placeholder="Descreption de Question "></textarea></div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
      </div>
      </form>
    </div>
  </div>
</div>
<!-- ###########################################Ajouter Cretaire  ##############################" -->
  <div id="Ajouter_Moderateur" class="modal fade" role="dialog" style="display: none;" aria-hidden="true">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Ajouter le moderateurs</h4><button type="button" class="close" data-dismiss="modal">×</button>
        
      </div>
        <form action="ajouter_nv_modele" id="contactForm" method="post" modelattribute="Moderateur">
      <div class="modal-body">
          
     <div class="form-group bmd-form-group">   <input Style="color:#000000;" type="text" path="moderateur" class="form-control" name="moderateur" rows="10" placeholder="Le nom moderateur"></div>
     <div class="form-group bmd-form-group">   
<textarea Style="color:#000000;" class="form-control" name="enoncehypothesees" path="enoncehypothesees" rows="10" placeholder="Enonce Hypothese"></textarea></div>            
</div>
      <div class="modal-footer">
    <button type="submit" class="btn btn-default" style="background-color:#ff7a46;">valider</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">fermer<div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div><div class="ripple-container"><div class="ripple-decorator ripple-on ripple-out" style="left: 59.3928px; top: 12.8571px; background-color: rgb(255, 255, 255); transform: scale(13.192);"></div></div></button>
      </div>
      </form>
    </div>

  </div>
</div>


<!-- #########################################################################" --> 
   
          </div>
        </div>
      </div>

      <script>
        const x = new Date().getFullYear();
        let date = document.getElementById('date');
        date.innerHTML = '&copy; ' + x + date.innerHTML;
      </script>
    <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px; height: 1248px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 832px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 702px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 312px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -211.81px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 211.81px; height: 832px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 141px; height: 554px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 702px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 702px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -489.286px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 489.286px; height: 780px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 301px; height: 479px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -198.571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 198.571px; height: 780px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 122px; height: 479px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -80.5714px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 80.5714px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 59px; height: 690px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 832px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 545px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -96.8571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 96.8571px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 71px; height: 690px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -1208.57px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 1208.57px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 410px; height: 211px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -30.8571px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 30.8571px; height: 1872px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 30px; height: 1842px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 204px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 204px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 624px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 204px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 460px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -531.429px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 531.429px; height: 936px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 261px; height: 460px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 2496px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 2467px;"></div></div></div>
  </div>
  

  <!--   Core JS Files   -->
  <script src="resources/assets//js/core/jquery.min.js"></script>
  <script src="resources/assets//js/core/popper.min.js"></script>
  <script src="resources/assets//js/core/bootstrap-material-design.min.js"></script>
  <script src="https://unpkg.com/default-passive-events"></script>
  <script src="resources/assets//js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Place this tag in your head or just before your close body tag. -->
  <script async="" defer="" src="https://buttons.github.io/buttons.js"></script>
  <!--  Google Maps Plugin    -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <!-- Chartist JS -->
  <script src="resources/assets//js/plugins/chartist.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="resources/assets//js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="resources/assets//js/material-dashboard.js?v=2.1.0"></script>
  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
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