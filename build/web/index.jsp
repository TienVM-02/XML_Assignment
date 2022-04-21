<%-- 
    Document   : index
    Created on : Apr 19, 2022, 8:25:22 PM
    Author     : vomin
--%>

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form method="post" action="MainControler">
            <input type="submit" name="btnAction" value="Generate Data" />
        </form>
        <div style="margin-top: 10px">
                <form method="post" action="CheckDataControler" enctype="multipart/form-data">
                    <input name="data" type="file" />
                    <input type="submit" name="btnAction" value="Check Data" />
                </form>
        </div>
        <div style="margin-top: 10px">
                <form method="get" action="MainControler">
                    <input type="submit" name="btnAction" value="LoadProduct" />
                </form>
        </div>
    </body>
</html>
