package com.mosesomondi.microservices.controller;

import com.mosesomondi.microservices.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Moses",
                "Omondi"
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(student);
    }


    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Mufasa", "Today"));
        students.add(new Student(2, "Mark", "Panera"));
        students.add(new Student(3, "Geoffrey", "Epstein"));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(students);
    }

    // Spring API with Path Variable
    // {id} - URI template variable
    @GetMapping("{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Student(id, "Moses", "Omondi"));
    }

    // Spring REST API with Request Params
    // http://localhost:8080/students/query?id=1&firstName&lastName
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {

        Student student = new Student(id, firstName, lastName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(student);
    }

    // SpringBoot REST API that handles HTTP POST request - creating resource
    // PostMapping and @RequestBody
    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student, int id) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(student);
    }

    // SpringBoot REST API that handles HTTP PUT request
    // PUTMapping http:localhost:8080/students/3/update - updating resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int id) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(student);
    }

    // SpringBoot REST API that handles HTTP Delete request
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        System.out.println(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Student delete successfully !");
    }

}
