<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="global.css"/>
  </head>
  <body>
    <div class="width100 positionMid loginBackground">
      <div class="width50 positionMid flexContentMiddle alignCenter">
        <form action="JobPortal" method="POST" >
          <div class="pad1">
            <span class="">Username: </span>
            <input id="username" type="text" name="username" placeholder="Username">
          </div>
          <div class="pad1">
            <span class="">Password: </span>
            <input id="password" type="password" name="password" placeholder="Password">
          </div>
          <div class="pad1">
            <input type="hidden" value="login" name="cmd">
            <input id="loginButn" type="submit" value="Login">
          </div>
        </form>
        <%
          if (request.getAttribute("username") == null) {
            out.print("<div>Invalid username or password.</div>");
          }
        %>
      </div>
    </div>
  </body>
</html>

