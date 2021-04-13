package com.csrohit.springsecurity.repositories;

import com.csrohit.springsecurity.models.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

}
