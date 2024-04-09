package cn.edu.hit.action;

import java.io.*;
import java.net.HttpCookie;

import cn.edu.hit.dao.UserDao;
import cn.edu.hit.dao.impl.UserDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        PrintWriter out = response.getWriter();
        UserDao dao = new UserDaoImpl();
        if (dao.login(username, password)) {
            if (rememberMe != null) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            }
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            /*response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.html");
        }*/
            out.print("{\"success\": true}");
        } else {
            out.print("{\"success\": false, \"message\": \"用户名或密码错误\"}");
        }
        out.flush();




        /*if(username.equals("admin") && password.equals("123")) {
            if(rememberMe != null) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60*60*24*7);
                response.addCookie(cookie);
            }
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.html");
        }*/
        }
    }
