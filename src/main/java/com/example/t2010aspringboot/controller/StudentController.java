package com.example.t2010aspringboot.controller;

import com.example.t2010aspringboot.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    static List<Student> studentList = new ArrayList<>();
    {
        studentList.add(new Student("S001", "duyoccut", "0967238412", "Thai Binh", LocalDateTime.now(), 1));
        studentList.add(new Student("S002", "duyoccho", "0968878712", "Ha Noi", LocalDateTime.now(), 1));
        studentList.add(new Student("S003", "duypussyface", "038687291", "Nam Dinh", LocalDateTime.now(), 1));
        studentList.add(new Student("S004", "duyoclon", "0127868326", "Thai Nguyen", LocalDateTime.now(), 1));
        studentList.add(new Student("S005", "duyml", "0878723445", "Hai Phong", LocalDateTime.now(), 1));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll(){
        return studentList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student save(@RequestBody Student student){
        studentList.add(student);
        return student;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Student findById(@PathVariable String id){
        int foundStudent = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber().equals(id)){
                foundStudent = i;
                break;
            }
        }
        if (foundStudent == -1){
            return null;
        }
        return studentList.get(foundStudent);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Student update(@PathVariable String id, @RequestBody Student updateStudent){
        int foundStudent = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber().equals(id)){
                foundStudent = i;
                break;
            }
        }
        if (foundStudent == -1){
            return null;
        }
        Student existStudent = studentList.get(foundStudent);
        existStudent.setFullName(updateStudent.getFullName());
        existStudent.setAddress(updateStudent.getAddress());
        return existStudent;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public boolean delete(@PathVariable String id){
        int foundStudent = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber().equals(id)){
                foundStudent = i;
                break;
            }
        }
        if (foundStudent ==-1){
            return false;
        }
        studentList.remove(foundStudent);
        return true;
    }
}
