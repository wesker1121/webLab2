<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="cn.edu.hit.dao.StudentDao, cn.edu.hit.dao.impl.StudentDaoImpl" %>
<%@ page import="cn.edu.hit.entity.Student, java.util.List, java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - index</title>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        out.println("非法用户，请<a href='login.html'>返回</a>登录！");
        return;
    }
    StudentDao dao = new StudentDaoImpl();
    List<Student> list = dao.getAll("select * from student order by sid");
    String sid, name, birthday, gender;
    int age;
%>
<a href="add.html">增加学生</a>
<table border="1">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>生日</th>
        <th>修改</th>
        <th>删除</th>
    </tr>
    <%
        for (Student student : list) {
            sid = student.getSid();
            name = student.getName();
            age = student.getAge();
            birthday = student.getBirthday();
            gender = student.getGender();
    %>
    <tr>
        <td><%=sid%>
        </td>
        <td><%=name%>
        </td>
        <td><%=gender.equals("m") ? "男" : "女"%>
        </td>
        <td><%=age%>
        </td>
        <td><%=birthday%>
        </td>
        <td><a href="modify.jsp?sid=<%=sid%>">修改</a></td>
        <td><a href="student-servlet?action=remove&sid=<%=sid%>"
               onclick="return confirm('确定要删除<%=sid%>号同学吗？')">删除</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>