package Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepositoryInterface {
    List<Student> findAll();
    Student save(Student s);
    void deleteById(String id);
    Student updateNameById(String idStudent, String newName);
    List<Student> findWhereNameLike(String query);
}