package dev.scorpio;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class EmployeeGrouping {

    static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " (" + salary + ")";
        }
    }

    // 🧠 Implement this method using Java Streams
    public static Map<String, List<Employee>> groupAndSortEmployees(List<Employee> employees) {
        // TODO: Use streams to group by department and sort by salary descending
        Map<String, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment
                , Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList()))));
        return map;
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering", 95000),
                new Employee("Bob", "Engineering", 87000),
                new Employee("Charlie", "HR", 65000),
                new Employee("David", "HR", 62000),
                new Employee("Eve", "Marketing", 72000)
        );

        Map<String, List<Employee>> grouped = groupAndSortEmployees(employees);

        // Pretty print the result
        grouped.forEach((dept, emps) -> {
            System.out.println(dept + ": " + emps);
        });
    }
}

