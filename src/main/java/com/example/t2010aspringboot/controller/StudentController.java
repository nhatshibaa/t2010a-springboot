package com.example.t2010aspringboot.controller;

import com.example.t2010aspringboot.entity.Student;
import com.example.t2010aspringboot.repository.StudentRepository;
import com.example.t2010aspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getList() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable String id) {
        Optional<Student> optionalAccount = studentService.findById(id);
        if (!optionalAccount.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalAccount.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student student) {
        Optional<Student> optionalStudent = studentService.findById(id);
        if (!optionalStudent.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Student existStudent = optionalStudent.get();
        // map object
        existStudent.setFullName(student.getFullName());
        existStudent.setPhone(student.getPhone());
        existStudent.setAddress(student.getAddress());
        return ResponseEntity.ok(studentService.save(existStudent));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!studentService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        studentService.delete(id);
        System.out.println("Success");
        return ResponseEntity.ok().build();
    }

}
