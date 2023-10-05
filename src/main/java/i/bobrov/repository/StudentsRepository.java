package i.bobrov.repository;

import i.bobrov.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Integer> {

    @Override
    @Query("select s from Student s left join fetch s.evaluation")
    List<Student> findAll();
}
