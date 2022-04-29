package com.jobportal;

import com.database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JobPortal extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String cmd = request.getParameter("cmd");

    switch (cmd) {
      case "login":
        login(request, response);
        break;
      case "logout":
        logout(request, response);
        break;
      default:
        throw new AssertionError();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

  private void login(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session;
    RequestDispatcher rd;

    try {
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      DatabaseConnection dc = new DatabaseConnection();
      String userAuthSql = "SELECT * FROM jobportal.user WHERE userActive = '1' AND username = 'replaceUsername' AND password = 'replacePassword';";
      userAuthSql = userAuthSql.replace("replaceUsername", username);
      userAuthSql = userAuthSql.replace("replacePassword", password);

      ResultSet rs = dc.execute(userAuthSql);
      if (rs.first()) {
        session = request.getSession(false);
        session.setAttribute("username", rs.getString("username"));
        request.setAttribute("username", rs.getString("username"));
        String getFunctionsSql = "SELECT * FROM jobportal.function WHERE userId = replaceUserId;";
        getFunctionsSql = getFunctionsSql.replace("replaceUserId", rs.getString("userId"));
        rs = dc.execute(getFunctionsSql);
        request.setAttribute("functionListRs", rs);

        rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request, response);
      } else {
        rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.forward(request, response);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void logout(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(true);
    RequestDispatcher rd;

    try {
      if (session != null) {
        session.invalidate();
      }

      rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
