<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>imobicash</title>



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
            .xbg-custom-dark{

                background: rgba(0, 74, 124, 0);
                font-family: "Montserrat";
                height: 90px;
                position: fixed;
                width: 100%;
                z-index: 99;

            } 
        </style>

    </head>
    <body>

        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="#">Imobicash</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home  </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">  Wallet</a>
                    </li>



                </ul>


            </div>
        </nav>
        <div class="container">
            <!-- Example row of columns -->
            <div class="row"> <div class="col-md-6">