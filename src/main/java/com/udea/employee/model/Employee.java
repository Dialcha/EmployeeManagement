package com.udea.employee.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author diego
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "All details about the employee. ")
@Entity
public class Employee implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(notes="The database generated employee ID")
    @Column(name="idemployee")
    private Long idEmployee;
    
    @ApiModelProperty(notes="The first name")
    @Column(name="firstname", nullable=false, length=80)
    private @NonNull String firstName;
    
    @ApiModelProperty(notes="The last name")
    @Column(name="lastname", nullable=false, length=80)
    private @NonNull String lastName;
    
    @ApiModelProperty(notes="The personal email")
    @Column(name="email", nullable=false, length=80)
    private @NonNull String email;
    
    @ApiModelProperty(notes="The employee's home address")
    @Column(name="homeaddress", nullable=false, length=80)
    private @NonNull String homeAddress;
    
    @ApiModelProperty(notes="The employee's base salary")
    @Column(name="basesalary", nullable=false, length=9)
    private @NonNull Double baseSalary;
    
    @ApiModelProperty(notes="The employee's position in the company")
    @Column(name="position", nullable=false, length=30)
    private @NonNull String position;
    
    @ApiModelProperty(notes="The office in what the employee works")
    @Column(name="office", nullable=false, length=30)
    private @NonNull String office;
    
    @ApiModelProperty(notes="Dependency in which the employee works")
    @Column(name="dependency", nullable=false, length=50)
    private @NonNull String dependency;
    
    @ApiModelProperty(notes="Employee's date of attachment")
    @Column(name="attachment", nullable=false, length=30)
    private LocalDate attachmentDate;
}
