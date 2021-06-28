package net.mytrofanov.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(name = "HelloForm", urlPatterns = "/helloForm")
public class HelloForm extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(HelloForm.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        String title = "Using GET Method to Read Form Data";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        LOGGER.info(String.format("incoming request received with name %s", req.getParameter("first_name")));

        out.print(docType +
                  "<html>\n" +
                  "<head><title>" + title + "</title></head>\n" +
                  "<body bgcolor = \"#f0f0f0\">\n" +
                  "<h1 align = \"center\">" + title + "</h1>\n" +
                  "<ul>\n" +
                  "  <li><b>First Name</b>: "
                  + req.getParameter("first_name") + "\n" +
                  "  <li><b>Last Name</b>: "
                  + req.getParameter("last_name") + "\n" +
                  "</ul>\n" +
                  "</body>" +
                  "</html>");
    }

}
