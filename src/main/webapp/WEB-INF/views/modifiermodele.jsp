<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> 
<%@page import="org.apache.jena.rdf.model.ModelFactory" %>
<%@page import="org.apache.jena.rdf.model.Model" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en" class="perfect-scrollbar-on"><head>
  <meta charset="utf-8">
  <link rel="apple-touch-icon" sizes="76x76" href=" ../resources/assets//img/apple-icon.png">
  <link rel="icon" type="image/png" href=" ../resources/assets//img/favicon.png">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.css"/>
 
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.js"></script>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>
Modele modification 
  </title>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no" name="viewport">
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons">
      <link rel="stylesheet" href=" ../resources/assets/demo/bootstrap.min.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- CSS Files -->
  <link href=" ../resources/assets//css/material-dashboard.css?v=2.1.0" rel="stylesheet">
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href=" ../resources/assets//demo/demo.css" rel="stylesheet">
<script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/7/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps/api/js/AuthenticationService.Authenticate?1sfile%3A%2F%2F%2FC%3A%2FUsers%2Fhp%2FDesktop%2Fadmin%2Fexamples%2FAjoutermodele.html&amp;4sYOUR_KEY_HERE&amp;callback=_xdc_._iv7o5p&amp;key=YOUR_KEY_HERE&amp;token=92200"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/41/8/intl/fr_ALL/util.js"></script></head>
<body class="dark-edition">
  <div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="black" data-image=" ../resources/assets/img/sidebar-1.jpg">

      <div class="logo"><a href="http://www.creative-tim.com" class="simple-text logo-normal">
      Espace Administration
        </a></div>
      <div class="sidebar-wrapper ps-container ps-theme-default" data-ps-id="dd0446d1-c173-b851-16e4-1bb62c11e9d7">
            <ul class="nav">
                   <li class="nav-item active">
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
          <li class="nav-item ">
            <a class="nav-link active" href="../affichier_tt_les_modeles">
              <i class="material-icons">content_paste</i>
              <p>La liste des modèles</p>
            </a>
          </li>
        </ul>

      <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 618px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 312px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 149px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 757px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 284px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 398px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; height: 549px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 45px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div><div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px;"></div></div></div>
    <div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div><div class="sidebar-background" style="background-image: url( assets/img/sidebar-2.jpg) "></div></div>
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
            </ul>
          </div>
        </div>
      </nav>
  <div class="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-xl-12 col-lg-12">
              <div class="card card-chart">
                <div class="card-header card-header-success">
                        <h4 class="bmd-label-floating">Modifier le modèle</h4>                </div>
                  <form action="${model.idmodel}" modelattribute="Form"  method="post" >
                <div class="card-body">
               <div class="row">
                      <div class="col-md-5">
                        <div class="form-group bmd-form-group"> 
                         <label class="bmd-label-floating">Modifier le nom du modèle</label><br>
                          <textarea class="form-control" name="nomModel" rows="4" required>${model.nomModel}</textarea>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Modifier la description du modèle </label><br>
                          <textarea  class="form-control" name="explain" rows="4" required>${model.explain}</textarea>
                        </div>
                      </div>     
                    </div>
                    
                </div>
                <div class="card-footer">
                  <div class="stats">
                  <button class="btn btn-primary pull-right" type="submit"> Confirme <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div>
                      <div class="ripple-container"></div></button>   </div>  
                </div>
 </form>
             
              </div>
            </div>
            </div>
              <div class="row">
            <div class="col-xl-12 col-lg-12">
              <div class="card card-chart">
                <div class="card-header card-header-success">
  <h4 class="card-title">Modifier information de Article</h4>
                </div>
                  <form action="${model.idmodel}" modelattribute="Article"  method="post">
                <div class="card-body">

                    <div class="row">
                      <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Modifier Nom de l'article</label><br>
                          <textarea  name="title" class="form-control"  rows="4"  required>${article.title}</textarea>
                        </div>
                      </div>
                   

                      <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Modifier le Résumé</label><br>
                          <textarea  name="resume"  class="form-control"  rows="4" required>${article.resume}</textarea>
                        </div>
                      </div>
                      <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">modifier les Mots clés</label><br>
                          <textarea name="motecle" type="text" class="form-control"   rows="4" required>${article.motecle}</textarea>
                        </div>
                      </div>
                      <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Modifier le DOI</label><br>
                          <textarea type="text"   rows="4"  name="doi" class="form-control"  >${article.doi}</textarea>
                        </div>
                      </div>
                      
<div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Modifier l'ISSN</label><br>
                          <textarea type="text"  rows="4"  name="issn" class="form-control"   >${article.issn}</textarea>
                        </div>
                      </div>
<div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Modifier l'URL</label><br>
                          <textarea  name="url"  rows="4"  class="form-control" required >${article.url}</textarea>
                        </div>
                      </div>

                      <div class="col-md-4">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">modifier la référence bibliograhique</label><br>
                          <textarea name="refernceArticle" class="form-control"  rows="4" required>${article.refernceArticle}</textarea>
                        </div>
                      </div>
 </div>
                    </div>
                <div class="card-footer">
                  <div class="stats">
                     <button class="btn btn-primary pull-right" type="submit"> Confirme <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
                  </div>
                </div>
                </form>
              </div>
            </div>
      
          </div>
   
   <!-- MODIFIER AUTEUR -->
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Modifier les Auteurs</h4>    
                </div>
                <div class="card-body table-responsive">
               
                 <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                                     <thead>
                                                                      
                      <tr>
                <th>
            
                <form  >
             
                <input name="nom" type="text" class="form-control" style="width:10%" value="Nom" Style="color:#bac0cc;" disabled="disabled">
                <input name="prenom" type="text" class="form-control" style="width:10%" disabled="disabled" value="Prenom" Style="color:#bac0cc;">
                <input name="univers" type="text" class="form-control" style="width:20%" disabled="disabled" value="Université" Style="color:#bac0cc;">
                <input  type="text" class="form-control" style="width:20%" disabled="disabled" value="Date de naissance" Style="color:#bac0cc;">
                <input  type="text" class="form-control" style="width:10%" disabled="disabled" value="Position" Style="color:#bac0cc;">                     
              
           </form>                     
                   </th>  
                   <th>                   
                <form  >
                                         
                     <input  type="text" class="form-control" style="width:100%" disabled="disabled" value="Suprimer" Style="color:#bac0cc;"> 
           </form>
                   
                   </th>
                      </tr>   
                                                                      </thead>
     
           
                                                                    <tbody>
                                                
                                                                        
                    <c:forEach items="${auteurs}" var="result">  
                        
 <tr>
                <th>
            
                <form action="${model.idmodel}" modelattribute="Auteur"  method="post" >
                <input  name ="idauterur"  value="${ result.idauterur}" style="width:0%" type="hidden" class="form-control">
                <input name="nom" type="text" class="form-control" style="width:10%" value="${ result.nom}" Style="color:#bac0cc;" >
                <input name="prenom" type="text" class="form-control" style="width:10%" value="${ result.prenom}" Style="color:#bac0cc;">
                <input name="univers" type="text" class="form-control" style="width:20%" value="${ result.univers}" Style="color:#bac0cc;">
                <input name="datedenaissance" type="text" class="form-control" style="width:20%" value="${ result.datedenaissance}" Style="color:#bac0cc;">
                <input name="position" type="text" class="form-control" style="width:10%" value="${ result.position}" Style="color:#bac0cc;">                     
                <button class="btn btn-primary pull-right" style="width:15%" type="submit" > 
                Confirme <div class="ripple-container" ></div>
                     </button>
           </form>                     
                   </th>  
                   <th >                   
                <form action="delet/${model.idmodel}" modelattribute="Auteur"  method="post" >
                <input  name ="idauterur"  value="${ result.idauterur}" style="width:0%" style="padding:0px;" type="hidden" class="form-control">                                    
                <button class="btn btn-primary pull-right" type="submit" style="width:100%; "> 
                Suprimer <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form>
                   
                   </th>
                      </tr>
                    </c:forEach> 

                                                             </tbody>
                                                             </table>    
                </div>

              </div>
            </div>
            
            
            <div class="col-lg-12 col-md-12">
              <div class="card">
  <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Modifier les dimesnions</h4>
     
                </div>
                <div class="card-body table-responsive">
                 <table class="table table-striped table-bordered table-hover" id="dataTables-example1">
                                                                    <thead>
                                                                     <tr>
                <th>
               <form>

               <input  class="form-control" disabled="disabled" value="Nom" Style="color:#bac0cc;" >
               <input  class="form-control" disabled="disabled" value="Descreption" Style="color:#bac0cc;">
               <input class="form-control" disabled="disabled" value="Nom de Mére" Style="color:#bac0cc;">
               <input  class="form-control" disabled="disabled" value="Type" Style="color:#bac0cc;">
                 
           </form>
                   </th>  
                   <th>         
                <form> 
                
                   <input  class="form-control" value="Supprime" disabled="disabled" Style="color:#bac0cc;">                         
           </form>
                   
                   </th>
                      </tr>
                                                                      </thead>
     
           
                                                                    <tbody>
 
                  <c:forEach items="${dim}" var="result5">   
             <tr>
                <th>
               <form action="${model.idmodel}" modelattribute="Dimonsion"  method="post" >
               <input  name ="iddim" style="width:0%" value="${result5.iddim}" Style="color:#bac0cc;" type="hidden" class="form-control" >
               <input name="nom" type="text" class="form-control" value="${result5.nom}" Style="color:#bac0cc;" >
               <input name="descreption" type="text" class="form-control" value="${result5.descreption}" Style="color:#bac0cc;">
               <input name="nommere" type="text" class="form-control" value="${result5.nommere}" Style="color:#bac0cc;">
               <input name="rdftype" type="text" class="form-control" value="${result5.rdftype}" Style="color:#bac0cc;">
        
                <button class="btn btn-primary pull-right" type="submit" > 
                Confirme <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form>
                      
                   </th>  
                    <th>
                   
                <form action="delet/${model.idmodel}" modelattribute="Dimonsion"  method="post" >
        <input  name ="iddim"  value="${result5.iddim}" Style="color:#bac0cc;" type="hidden" class="form-control" > 
                <button class="btn btn-primary pull-right" type="submit" > 
                Suprimer <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form>
                   
                   </th>
                      </tr>
                    </c:forEach> 

                                                             </tbody>
                                                             </table>    

                </div>
              </div>
            </div>
          </div>
        </div>


          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Modifier les Questions</h4>
     
                </div>
                <div class="card-body table-responsive">
                 <table class="table table-striped table-bordered table-hover" id="dataTables-example2">
                                                                     <thead>
                                                                            <tr>
                <th>
               <form>

               <input  class="form-control"  style="width:10%" disabled="disabled" value="Cretaire" Style="color:#bac0cc;" >
               <input  class="form-control"  style="width:70%" disabled="disabled" value="Qeustion" Style="color:#bac0cc;">
               <input class="form-control"  style="width:15%" disabled="disabled" value="Modifier" Style="color:#bac0cc;">               
           </form>
                   </th>  
                   <th>       
                <form  >                    
                   <input  class="form-control" value="Supprime" disabled="disabled" Style="color:#bac0cc;">          
           </form>
                   
                   </th>
                      </tr>
                                                                      </thead>

                                                                    <tbody>
                     
                  <c:forEach items="${qst}" var="result2">                  
 <tr>
                <th>
            
                <form action="${model.idmodel}" modelattribute="Qstionne"  method="post" >
                 <input  name ="idqstion" style="width:0%"  value="${ result2.idqstion}" type="hidden" Style="color:#bac0cc;" class="form-control">
               <input name="cretaire" style="width:10%" type="text" class="form-control" value="${ result2.cretaire}" Style="color:#bac0cc;" >
                <input name="qest" type="text" style="width:70%" class="form-control" value="${result2.qest}" Style="color:#bac0cc;">
        
                <button class="btn btn-primary pull-right" style="width:15%" type="submit" > 
                Confirme <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form>
                      
                   </th>  
                    <th>
                   
                <form action="delet/${model.idmodel}" modelattribute="Qstionne"  method="post" >
            <input  name ="idqstion"  value="${ result2.idqstion}" type="hidden" Style="color:#bac0cc;" class="form-control">
                <button class="btn btn-primary pull-right" type="submit" > 
                Suprimer <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form></th>
                      </tr>
                    </c:forEach> 

                                                             </tbody>
                                               
                                                             </table>
                </div>

              </div>
            </div>
            
        
            <div class="col-lg-12 col-md-12">
              <div class="card">
  <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Modifier les hypothses</h4>
     
                </div>
                <div class="card-body table-responsive">
                 <table class="table table-striped table-bordered table-hover" id="dataTables-example3">
                                                                     <thead>
                                                                    <tr>
                <th>
               <form>
               <input  class="form-control"  disabled="disabled" value="enonce hypothesée" Style="color:#bac0cc;" >
               <input  class="form-control"  disabled="disabled" value="Dimension cible" Style="color:#bac0cc;" >
               <input  class="form-control"  disabled="disabled" value="Dimension depondent" Style="color:#bac0cc;">

           </form>  
           </th>
           <th>
            <form>                   
                   <input  class="form-control" value="Supprime" disabled="disabled" Style="color:#bac0cc;">
           </form>
                   
                   </th>
                      </tr>
                                                                      </thead>

                                                                    <tbody>
                                                                    


 <c:forEach items="${hypo}" var="result4">   
 <tr> 
   <th>
            
                <form action="${model.idmodel}" modelattribute="Hypothse"  method="post" >
                 <input  name ="idhypo"  value="${ result4.idhypo}" type="hidden" class="form-control">
                 <input name="enonceHypothse" type="text" class="form-control" value="${ result4.enonceHypothse}" Style="color:#bac0cc;" >
                 <input name="dimonsiondep" type="text" class="form-control" value="${result4.dimonsiondep}" Style="color:#bac0cc;">
                 <input name="dimonsionindp" type="text" class="form-control" value="${ result4.dimonsionindp}" Style="color:#bac0cc;" >
  
                <button class="btn btn-primary pull-right" type="submit" > 
                Confirme <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form>                      
                   </th>       
                   
                         <th>
                   
           <form action="delet/${model.idmodel}" modelattribute="Hypothse"  method="post" >
           <input  name ="idhypo"  value="${ result4.idhypo}" type="hidden" class="form-control">
                <button class="btn btn-primary pull-right" type="submit" > 
                Suprimer <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form></th>
                        
                        </tr>
                    </c:forEach>
                                                             </tbody>
                                                             </table>
                </div>

              </div>
            </div>
          </div>
         
        </div>


          <div class="row">

            <div class="col-lg-12 col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Modifier les Choix de qeustion</h4>
     
                </div>
                <div class="card-body table-responsive">
                 <table class="table table-striped table-bordered table-hover" id="dataTables-example4">
                                                                     <thead>
                                                            <tr>
                <th>
               <form>
               <input  class="form-control"   disabled="disabled" value="Question" Style="color:#bac0cc;" >
               <input  class="form-control"   disabled="disabled" value="Choix" Style="color:#bac0cc;" >

           </form>  
           </th>
           <th>
            <form>                   
                   <input  class="form-control" value="Supprime" disabled="disabled" Style="color:#bac0cc;">
           </form>
                   
                   </th>
                      </tr>
                                                                      </thead>

                                                                    <tbody>

<c:forEach items="${choix}" var="result2">  
    <c:if test="${not empty choix.choix}">
    <tr>
    <th>
            
                <form action="${model.idmodel}" modelattribute="choix"  method="post" >
                 <input  name ="idchoix"  value="${ result2.idchoix}" type="hidden" class="form-control">
                 <input name="qstion" type="text" class="form-control" value="${ result2.qstion}" Style="color:#bac0cc;" >
                 <input name="choix" type="text" class="form-control" value="${result2.choix}" Style="color:#bac0cc;">

  
                <button class="btn btn-primary pull-right" type="submit" > 
                Confirme <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form>
                      
                   </th>  
                    <th>
                   
           <form action="delet/${model.idmodel}" modelattribute="choix"  method="post" >
           <input  name ="idchoix"  value="${ result2.idchoix}" type="hidden" class="form-control">
                <button class="btn btn-primary pull-right" type="submit" > 
                Suprimer <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form></th>
                        
 
                        </tr></c:if>
                    </c:forEach>


                                                             </tbody>
                                                             </table>
                </div>

              </div>
            </div>
           
        
            <div class="col-lg-12 col-md-12">
              <div class="card">
            <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Modifier les Moderateur</h4>
     
                </div>
                <div class="card-body table-responsive">
                 <table class="table table-striped table-bordered table-hover" id="dataTables-example5">
                                                                     <thead>
                                                                         <tr>
                <th>
               <form>
               <input  class="form-control"  disabled="disabled" value="Enoncé d'hypothes" Style="color:#bac0cc;" >
               <input  class="form-control"   disabled="disabled" value="Moderateur" Style="color:#bac0cc;" >
               
               
           </form>  
           </th>
           <th>
            <form>                   
                   <input  class="form-control" value="Supprime" disabled="disabled" Style="color:#bac0cc;">
           </form>
                   
                   </th>
                      </tr>
                                                                      </thead>

                                                                          <tbody>
        <c:forEach items="${moder}" var="result24"> <tr>
   <th>     
                <form action="${model.idmodel}" modelattribute="Moderateur"  method="post" >
                 <input  name ="idmoder"  value="${ result24.idmoder}" type="hidden" class="form-control">
                 <input name="enoncehypothesees" type="text" class="form-control" value="${ result24.enoncehypothesees}" Style="color:#bac0cc;" >
                 <input name="moderateur" type="text" class="form-control" value="${result24.moderateur}" Style="color:#bac0cc;">

                <button class="btn btn-primary pull-right" type="submit" > 
                Confirme <div class="ripple-container"></div>
                <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form>            
                   </th>
                   
                    <th>
                   
           <form action="delet/${model.idmodel}" modelattribute="Moderateur"  method="post" >
                <input  name ="idmoder"  value="${ result24.idmoder}" type="hidden" class="form-control">
                <button class="btn btn-primary pull-right" type="submit" > 
                Suprimer <div class="ripple-container"></div>
                      <div class="ripple-container"></div><div class="ripple-container"></div><div class="ripple-container"></div></button>
           </form></th>
                        
                        
                    </tr></c:forEach>


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


      <script>
        const x = new Date().getFullYear();
        let date = document.getElementById('date');
        date.innerHTML = '&copy; ' + x + date.innerHTML;
      </script>
    <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: -29.7143px;"><div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 29.7143px; right: 0px; height: 2496px;"><div class="ps-scrollbar-y" tabindex="0" style="top: 29px; height: 2467px;"></div></div></div>
  </div>
 

  

  <!--   Core JS Files   -->
  <script src=" ../resources/assets//js/core/jquery.min.js"></script>
  <script src=" ../resources/assets//js/core/popper.min.js"></script>
  <script src=" ../resources/assets//js/core/bootstrap-material-design.min.js"></script>
  <script src="https://unpkg.com/default-passive-events"></script>
  <script src=" ../resources/assets//js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Place this tag in your head or just before your close body tag. -->
  <script async="" defer="" src="https://buttons.github.io/buttons.js"></script>

  <script src=" ../resources/assets//js/plugins/chartist.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src=" ../resources/assets//js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src=" ../resources/assets//js/material-dashboard.js?v=2.1.0"></script>
  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
  <script src=" ../resources/assets//demo/demo.js"></script>
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

   <script src=" ../resources/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src=" ../resources/assets/js/dataTables/dataTables.bootstrap.js"></script>
  <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
        $(document).ready(function () {
            $('#dataTables-example1').dataTable();
        });
        $(document).ready(function () {
            $('#dataTables-example2').dataTable();
        });
        $(document).ready(function () {
            $('#dataTables-example3').dataTable();
        });
        $(document).ready(function () {
            $('#dataTables-example4').dataTable();
        });
        $(document).ready(function () {
            $('#dataTables-example5').dataTable();
        });
        
        $(document).ready(function () {
            $('#dataTables-example6').dataTable();
        });
        $(document).ready(function () {
            $('#dataTables-example7').dataTable();
        });
               
    </script>

</body></html>
       