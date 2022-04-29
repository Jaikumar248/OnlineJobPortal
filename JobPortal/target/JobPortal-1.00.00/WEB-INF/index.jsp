<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home page</title>
  </head>
  <body>
    <div>
      <h1>Welcome <% out.print(request.getAttribute("username"));%></h1>
    </div>

    <div>
      <div>
        <form action="JobPortal" method="POST" >
          <div class="pad1">
            <input type="hidden" value="logout" name="cmd">
            <input id="logoutButn" type="submit" value="Logout">
          </div>
        </form>
      </div>

      <%
        ResultSet rs = (ResultSet) request.getAttribute("functionListRs");
        rs.beforeFirst();
        while (rs.next()) {
          out.print("<div style='padding: 5px 0;'>");
          out.print("<form action='JobPortal' method='POST' >");
          out.print("<div class='pad1'>");
          out.print("<input type='hidden' value='" + rs.getString("FunctionName") + "' name='cmd'>");
          out.print("<input type='submit' value='" + rs.getString("FunctionName") + "'>");
          out.print("</div>");
          out.print("</form>");
          out.print("</div>");
        }
      %>

    </div>
  </body>
</html>
