<%-- 
    Document   : index
    Created on : Nov 15, 2017, 3:27:20 PM
    Author     : mala singh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>imobicoin</title>



        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" >


        <style>
            /* Move down content because we have a fixed navbar that is 3.5rem tall */
            body {
                padding-top: 3.5rem;
            }

            .footer
            {

                background: #e3eff5;
                color: #004a7c;
                font-family: 'Montserrat', 'Helvetica Neue', Helvetica, Arial, sans-serif;
                font-size: 12px;
                line-height: normal;
                padding: 30px 0;
                text-transform: uppercase;
                width: 100%;
            }
        </style>

    </head>
    <body ng-app="mApp">

        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="#">Imobicoin</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <%String username = String.valueOf(session.getAttribute("username"));
                int roll = Integer.parseInt(String.valueOf(session.getAttribute("roll")));
                out.println(roll);%>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" ui-sref="Profile">Home   </a>
                    </li>

                    <% if (roll == 10) {%>
                    <li class="nav-item">   <a class="nav-link" ui-sref="AllUser">All User</a> </li> 

                    <%} else {%>
                    <li class="nav-item">   <a class="nav-link" ui-sref="SendTransaction">Send payment</a> </li> 
                        <%}%>
                    <li class="nav-item">   <a class="nav-link" ui-sref="Transactionreports">Transaction  Report</a> </li>

                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet">  Logout</a>
                    </li>

                </ul>


            </div>
        </nav>
        <div class="container">
            <!-- Example row of columns -->
            <div class="row"> <div class="col-md-9">