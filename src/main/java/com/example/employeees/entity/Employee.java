package com.example.employeees.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @NotBlank(message = "Lastname is required")
    @Size(min = 2, max = 50, message = "Lastname length must be between 2 and 50")
    @Column(name = "last_name",  length = 50, nullable = false)
    private String lastName;

    @Past(message = "Birthdate must be in the past")
    @NotNull(message = "Birthdate is required")
    @Column(name = "birth_date",  nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @Size(max = 254)
    @Pattern(regexp = "^.+@.+\\.[A-Za-z]{2,}$", message = "Email is not valid")
    @Column(name = "email",  length = 254, nullable = false)
    private String email;

    @NotBlank(message = "Phone is required")
    @Column(name = "phone",   nullable = false)
    private String phone;

    @NotBlank(message = "Job title is required")
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @PositiveOrZero
    @Column(name = "salary")
    private Double salary;

    @NotNull
    @Min(0)
    @Max(1)
    @Column(name = "full_time")
    private Integer fullTime;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getFullTime() {
        return fullTime;
    }

    public void setFullTime(Integer fullTime) {
        this.fullTime = fullTime;
    }

    public Employee orElse(Object o) {
        return null;
    }
}
