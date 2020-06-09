
package com.udea.employee.service;

import com.udea.employee.dao.IEmployeeDAO;
import com.udea.employee.model.Employee;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class EmployeeService {
    
    @Autowired
    private IEmployeeDAO dao;
    
    public Employee save(Employee t) {return dao.save(t);}
    public Employee update(Employee t) {return dao.save(t);}
    public void delete(Employee t) {dao.delete(t);}
    public Iterable<Employee> list() {return dao.findAll();}
    public Optional<Employee> listId(long id) {return dao.findById(id);}
}
