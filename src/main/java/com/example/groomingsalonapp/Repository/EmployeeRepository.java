package com.example.groomingsalonapp.Repository;

import com.example.groomingsalonapp.Domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmployeeId(Long employeeId);


}
