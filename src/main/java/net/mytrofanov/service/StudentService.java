package net.mytrofanov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mytrofanov.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<Student> students = new ArrayList<>();

    public boolean addStudent(String studentJson) {
        Student student = jsonToStudent(studentJson);
        LOGGER.info(String.format("Adding student: %s to the list", student));
        return students.add(student);
    }

    public String getStudent(int studentId) {
        Student student = students.get(studentId - 1);
        LOGGER.info(String.format("Getting student from the list: %s from the list", student));
        return studentToJson(student);
    }

    public List<String> getAllStudents() {
        return students.stream()
                       .map(this::studentToJson)
                       .collect(Collectors.toList());
    }

    private String studentToJson(Student student) {
        String studentStr = null;
        try {
            studentStr = objectMapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            LOGGER.warning(e.toString());
            e.printStackTrace();
        }
        return studentStr;

    }

    private Student jsonToStudent(String studentJson) {
        Student student = null;
        try {
            student = objectMapper.readValue(studentJson, Student.class);
        } catch (IOException e) {
            LOGGER.warning(e.toString());
            e.printStackTrace();
        }
        return student;
    }

}
