package com.example.springg;

import Model.Student;
import Repository.StudentRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ControllerStudents {

        @GetMapping("/students")
        public ResponseEntity <List<Student>> getStudent() {
                StudentRepository client = StudentRepository.getInstance();
                client.connectDataBase();
                List<Student> out = client.findAll();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(out);
        }
        @GetMapping("/students/{name}")
        public ResponseEntity <List<Student>> getIfNameLike(@PathVariable String name) {
            StudentRepository client = StudentRepository.getInstance();
            client.connectDataBase();
            List<Student> out = client.findWhereNameLike(name);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(out);
        }

        @PostMapping("/students")
        public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) throws IOException {
            StudentRepository client = StudentRepository.getInstance();
            client.connectDataBase();
            Student out = client.save(newStudent);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(out);
        }

        @DeleteMapping("/students/{idStudent}")
        public ResponseEntity deleteStudent(@PathVariable String idStudent) throws IOException {
            StudentRepository client = StudentRepository.getInstance();
            client.connectDataBase();
            client.deleteById(idStudent);
            return ResponseEntity.ok().body("{}");
        }

        @PatchMapping("/students/{idStudent}/{newName}")
        public ResponseEntity<Student> updateInfoStudent(@PathVariable String idStudent, @PathVariable String newName) throws IOException {
            StudentRepository client = StudentRepository.getInstance();
            client.connectDataBase();
            Student out = client.updateNameById(idStudent, newName);
            System.out.println(idStudent+" et "+newName);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(out);
        }
}
