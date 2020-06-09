/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.employee.dao;

import com.udea.employee.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public interface IEmployeeDAO extends CrudRepository<Employee, Long> {
    
}
