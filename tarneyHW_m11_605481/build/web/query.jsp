<%-- 
    Document   : webpage
    Created on : Jul 15, 2017, 4:46:36 PM
    Author     : Tarney
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Beartooth Hiking Company (BHC)</title>
        <jsp:useBean id="reservations" class="com.brandontarney.model.Reservations" scope="session" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="css/BeartoothHikingCompany.css" type="text/css" />
        <script type="text/javascript" src="javascript/BeartoothHikingCompany.js"></script>

        <!-- JQUERY MAGIC -->
        <script src="javascript/jquery-3.2.1.js"></script>
        <script src="javascript/jquery-ui-1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
        <style>
            .ui-datepicker {
                background: #c6dbff;
            }
        </style>
    </head>
    <body>
        <div id="page_wrapper">
            <header>
                <h1 id="top_header">Beartooth Hiking Company</h1>
                <p id="slogan">Alaska's Lamest Hiking Company</p>
                <nav>
            </header>
            <section id="form" class="table">
                <h2 id="form_header">Search for Hikes</h2>
                <!--<form action="http://localhost:8084/tarneyHW_m11_605481/Controller" method=GET onSubmit=" return validatePartySize(1, 10)"> -->
                  <form action="http://web6.jhuep.com:80/tarneyHW_m11_605481/Controller" method=GET onSubmit=" return validatePartySize(1, 10)"> 
                    <p class="excited_msg">Pick a Date</p>
                    <input type="text" class="big_text" id="datepicker" name="datepicker" size="10" value="06/01/2017">
                    <input class="biggest_text" type="SUBMIT" name="submit" value="submit" />
                </form>
            </section>
        </section>
        <section id="more_info">
            <h2 id="more_info_header" class="more_info">More Information</h2>
            <p>For more information, checkout the <a href="http://www.wilderness.net/index.cfm?fuse=NWPS&sec=wildView&WID=1"><span id="wilderness_link">wilderness website</span></a></p>
        </section>
        <footer>
            <p>Contact: <a href="mailto:brandon.tarney@gmail.com">Brandon Tarney</a></p>
        </footer>
    </div>
</body>
</html>

