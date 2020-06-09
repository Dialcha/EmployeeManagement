
package com.udea.employee.controller;

import com.udea.employee.exception.ModelNotFoundException;
import com.udea.employee.model.Employee;
import com.udea.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
@Api(value = "Employee system", description = "RESTfull operations to manage employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @ApiOperation(value = "Add Employee")
    @PostMapping("/save")
    public long save(@ApiParam(value = "Employee saved in the BD", required = true) @RequestBody Employee employee) {
        employeeService.save(employee);
        return employee.getIdEmployee();
    }

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/listAll")
    public Iterable<Employee> listAllEmployees() {
        return employeeService.list();
    }

    @ApiOperation(value = "Get an employee by Id")
    @GetMapping("/list/{id}")
    public Employee listEmployeeById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable("id") int id) {
        Optional<Employee> employee = employeeService.listId(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new ModelNotFoundException("ID de persona invalido");
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete an employee by Id")
    public void deleteEmployee(@PathVariable Long id) {
        Employee employeeToBeDeleted = employeeService.listId(id).orElseThrow(() -> new ModelNotFoundException("Employee doesn't exists"));
        employeeService.delete(employeeToBeDeleted);
    }

    @ApiOperation(value = "Increase salary if Attachment date > 2 years")
    @PutMapping("/update/increasesalary/{id}")
    public String increaseSalary(@PathVariable Long id) {
        LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
        Optional<Employee> newEmployee = employeeService.listId(id);
        if (newEmployee.isPresent()) {
            if (newEmployee.get().getAttachmentDate().isBefore(twoYearsAgo)) {
                double salaryWithInc = (newEmployee.get().getBaseSalary() * 0.1) + newEmployee.get().getBaseSalary();
                newEmployee.get().setBaseSalary(salaryWithInc);
                employeeService.save(newEmployee.get());
                return "Nuevo salario = " + newEmployee.get().getBaseSalary().toString();
            } else {
                return "La persona no cumple con la antiguedad necesaria";
            }
        }
        throw new ModelNotFoundException("Persona no encontrada");
    }

    @ApiOperation(value = "Update an employee")
    @PutMapping("/update/{id}")
    public Employee replaceEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.listId(id)
                .map(newEmployee -> {
                    newEmployee.setFirstName(employee.getFirstName());
                    newEmployee.setLastName(employee.getLastName());
                    newEmployee.setEmail(employee.getEmail());
                    newEmployee.setHomeAddress(employee.getHomeAddress());
                    newEmployee.setBaseSalary(employee.getBaseSalary());
                    newEmployee.setPosition(employee.getPosition());
                    newEmployee.setOffice(employee.getOffice());
                    newEmployee.setDependency(employee.getDependency());
                    newEmployee.setAttachmentDate(employee.getAttachmentDate());
                    return employeeService.save(newEmployee);
                })
                .orElseGet(() -> {
                    employee.setIdEmployee(id);
                    return employeeService.save(employee);
                });
    }

}
