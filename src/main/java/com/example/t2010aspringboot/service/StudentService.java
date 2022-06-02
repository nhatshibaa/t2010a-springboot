package com.example.t2010aspringboot.service;

import com.example.t2010aspringboot.entity.Student;
import com.example.t2010aspringboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAll (){
        return studentRepository.findAll();
    }

    public Optional<Student> findById (String id){
        return studentRepository.findById(id);
    }

    public Student save (Student student){
        return studentRepository.save(student);
    }

    public void delete (String id){
        studentRepository.deleteById(id);
    }
}
