package net.mytrofanov.controller.rest;

import net.mytrofanov.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(
        name = "RestServlet",
        urlPatterns = "/students/*"
)
public class StudentRestServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(StudentRestServlet.class.getName());

    private final StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("id");
        LOGGER.info(String.format("Trying to get student with Id: %s", studentId));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        if (studentId != null) {
            int id = Integer.parseInt(studentId);
            String studentJson = studentService.getStudent(id);
            if (studentJson != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.print(studentJson);
                out.flush();
            } else {
                response.sendError(HttpServletResponse.SC_NO_CONTENT, "Student is not found");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            List<String> allStudents = studentService.getAllStudents();
            LOGGER.info(String.format("Trying to get all students: %s", allStudents));
            allStudents.forEach(out::print);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentStr = request.getReader()
                                   .lines()
                                   .collect(Collectors.joining(System.lineSeparator()));

        response.setContentType("application/json");
        if (studentService.addStudent(studentStr)) {
            PrintWriter out = response.getWriter();
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print("Success");
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

}
