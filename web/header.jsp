<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Imobicoin</title>
 
  <link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 
  <link href="./vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <link href="./vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

  <link href="./css/sb-admin.css" rel="stylesheet">
  
  <style>
  .bg-dark {
    background-color: #004C7D !important;
   
}
#mainNav.navbar-dark .navbar-collapse .navbar-sidenav {
    background: #F6F7F9;
}
#mainNav.fixed-top .sidenav-toggler > .nav-item > .nav-link {

    padding: 1em;
    background-color: #F6F7F9;

}
#mainNav.navbar-dark .navbar-collapse .navbar-sidenav > .nav-item > .nav-link:hover {
    color:  #757575!important;
}
#mainNav.navbar-dark .navbar-collapse .navbar-sidenav > .nav-item > .nav-link {
    color: #757575;
}
</style>
</head>
 <%String username = String.valueOf(session.getAttribute("username"));
                int roll = Integer.parseInt(String.valueOf(session.getAttribute("roll")));
                out.println(roll);%>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"  ng-app="mApp">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" ui-sref="Profile"><img src="./logo/imobicash.png" alt="Imobicoin"></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive" style="">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion" style="margin-top:80px;">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Home">
          <a class="nav-link" ui-sref="Profile">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Home</span>
          </a>
        </li>  <% if (roll == 10) {%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="All User">
          <a class="nav-link" ui-sref="AllUser">
            <i class="fa fa-fw fa-area-chart"></i>
            <span class="nav-link-text">All User</span>
          </a>
        </li> <%}else {%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Send Payment">
          <a class="nav-link" ui-sref="SendTransaction">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">Send Payment</span>
          </a>
        </li>  <%}%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Transaction  Report">
          <a class="nav-link" ui-sref="Transactionreports">
            <i class="fa fa-fw fa-area-chart"></i>
            <span class="nav-link-text">Transaction  Report</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Logout">
          <a class="nav-link" href="LogoutServlet">
            <i class="fa fa-fw fa-area-chart"></i>
            <span class="nav-link-text">Logout</span>
          </a>
        </li>
        
  
      
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
    
     
     
        <li class="nav-item">
          <a href="LogoutServlet" class="nav-link" data-toggle="modal" data-target="#exampleModal" style="color:white;">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </nav>
   <div class="content-wrapper">
    <div class="container-fluid">