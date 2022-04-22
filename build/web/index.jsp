<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant</title>
        <!--<link rel="stylesheet" type="text/css" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">-->
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
    </head>

    <body>
        <div class="header">
            <p class="title">Plant Management</p>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="centerBtn">
                        <p class="task">Menu</p>

                        <form method="post" action="MainControler">
                            <input type="submit" name="btnAction" value="Generate Data" />
                        </form>
                        <div>
                            <form method="post" action="CheckDataControler" enctype="multipart/form-data">
                                <input class="upFile" name="data" type="file" />
                                <input type="submit" name="btnAction" value="Check Data" />
                                <p style="color: green; font-size: 18px">
                                    ${requestScope.SUCCESS}
                                </p>
                                <p style="color: red; font-size: 18px">
                                    ${requestScope.FAIL}
                                </p>
                            </form>
                        </div>
                        <div style="margin-top: 10px">
                            <form method="get" action="MainControler">
                                <input type="submit" name="btnAction" value="LoadProduct" />
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>

</html>