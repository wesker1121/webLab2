package cn.edu.hit.action;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.dao.impl.StudentDaoImpl;
import cn.edu.hit.entity.Student;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "studentServlet", value = "/student-servlet")
public class StudentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        StudentDao dao = new StudentDaoImpl();
        if (action.equals("add")) {
            String sid = request.getParameter("sid");
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");
            Student student = new Student(sid, name, age, birthday, gender);
            dao.add(student);
        } else if (action.equals("modify")) {
            String sid = request.getParameter("sid");
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");
            Student student = new Student(sid, name, age, birthday, gender);
            dao.modify(student);
        } else if (action.equals("remove")) {
            String sid = request.getParameter("sid");
            dao.remove(sid);
        }
        response.sendRedirect("index.jsp");
    }
}