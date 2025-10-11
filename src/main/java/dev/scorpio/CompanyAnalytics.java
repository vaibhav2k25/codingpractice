package dev.scorpio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/*
1. Department -> Role -> List of employee names (Nested Grouping)

Group all employees first by department, then by role, and collect just the names into a nested Map<String, Map<String, List<String>>>.

2. Top earner per department

For each department, find the employee with the highest salary.

3. Average salary of employees who joined in the last 3 years, grouped by department

Filter employees who joined within the last 3 years.

Group them by department.

Compute the average salary per department.

4. Sorted list of departments by total salary paid (descending)

Sum salaries per department.

Return a list of departments sorted by total salary paid, descending.

5. Find the second highest paid employee in the whole company

Don’t use sorted().skip(1).findFirst() — use a custom collector or a more creative stream manipulation.

6. List of employees per year of joining

Group employees by the year they joined (use joiningDate.getYear()), and list their names.

7. Detect if all departments have at least one employee under 25

Return true if every department has at least one employee under 25.

8. The average tenure (in years) of employees per department

Tenure is calculated as the difference between the current date and joiningDate.
 */
//Expected output

/*
1. Nested Grouping:
{IT={Developer=[Alice, Jane], Manager=[Bob], Intern=[Frank]}, HR={Recruiter=[Charlie], Manager=[Grace]}, ...}

2. Top Earner Per Department:
{IT=Bob, HR=Grace, Finance=Ian, Marketing=Eva}

3. Avg Salary (Last 3 Years) by Dept:
{IT=57666.67, HR=50000.0}

4. Departments by Total Salary:
[IT, Finance, Marketing, HR]

5. Second Highest Paid Employee:
Eva (Marketing, Manager, $110000.0)

6. Employees by Year of Joining:
{2022=[Alice], 2023=[Charlie, Jane], 2018=[Bob], ...}

7. All Departments Have < 25?
false

8. Avg Tenure (Years) by Department:
{IT=2.67, HR=3.0, Finance=7.0, Marketing=4.0}
 */


class Employee {
    String name;
    int age;
    String department;
    String role;
    double salary;
    LocalDate joiningDate;

    public Employee(String name, int age, String department, String role, double salary, LocalDate joiningDate) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.role = role;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    // Getters (or use Lombok if preferred)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    @Override
    public String toString() {
        return name + " (" + department + ", " + role + ", $" + salary + ")";
    }
}

public class CompanyAnalytics {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 28, "IT", "Developer", 70000, LocalDate.of(2022, 3, 15)),
                new Employee("Bob", 35, "IT", "Manager", 120000, LocalDate.of(2018, 1, 10)),
                new Employee("Charlie", 26, "HR", "Recruiter", 50000, LocalDate.of(2023, 5, 20)),
                new Employee("David", 45, "Finance", "Analyst", 95000, LocalDate.of(2017, 8, 1)),
                new Employee("Eva", 31, "Marketing", "Manager", 110000, LocalDate.of(2019, 11, 11)),
                new Employee("Frank", 23, "IT", "Intern", 35000, LocalDate.of(2024, 1, 5)),
                new Employee("Grace", 29, "HR", "Manager", 80000, LocalDate.of(2020, 2, 18)),
                new Employee("Helen", 34, "Marketing", "Executive", 75000, LocalDate.of(2021, 6, 9)),
                new Employee("Ian", 41, "Finance", "Manager", 130000, LocalDate.of(2016, 12, 20)),
                new Employee("Jane", 24, "IT", "Developer", 68000, LocalDate.of(2023, 7, 30))
        );

        // 1. Department -> Role -> List of Names
        System.out.println("1. Nested Grouping:");
        System.out.println(nestedGrouping(employees));

        // 2. Top earner per department
        System.out.println("\n2. Top Earner Per Department:");
        System.out.println(topEarnerPerDepartment(employees));

        // 3. Average salary in last 3 years by department
        System.out.println("\n3. Avg Salary (Last 3 Years) by Dept:");
        System.out.println(avgSalaryRecentJoineesByDept(employees));

        // 4. Departments sorted by total salary
        System.out.println("\n4. Departments by Total Salary:");
        System.out.println(departmentsByTotalSalary(employees));

        // 5. Second highest paid employee
        System.out.println("\n5. Second Highest Paid Employee:");
        System.out.println(secondHighestPaid(employees));

        // 6. Employees by Joining Year
        System.out.println("\n6. Employees by Year of Joining:");
        System.out.println(employeesByJoiningYear(employees));

        // 7. All departments have someone under 25?
        System.out.println("\n7. All Departments Have < 25?");
        System.out.println(allDepartmentsHaveUnder25(employees));

        // 8. Average Tenure by Department:
        System.out.println("\n8. Avg Tenure (Years) by Department:");
        System.out.println(avgTenureByDept(employees));
    }

    // 1. Nested grouping by Department -> Role -> Names
    static Map<String, Map<String, List<String>>> nestedGrouping(List<Employee> employees) {
        Map<String, Map<String, List<String>>> map = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.groupingBy(Employee::getRole, Collectors.mapping(Employee::getName, Collectors.toList()))));
        return map; // TODO: implement
    }

    // 2. Top earner per department
    static Map<String, Employee> topEarnerPerDepartment(List<Employee> employees) {
        Map<String, Employee> topEarnerMap = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get)));
        return topEarnerMap;
    }

    // 3. Average salary of recent joinees (last 3 years) by department
    static Map<String, Double> avgSalaryRecentJoineesByDept(List<Employee> employees) {
        LocalDate threeYearsAgo = LocalDate.now().minusYears(3);

        return employees.stream()
                .filter(e -> e.getJoiningDate().isAfter(threeYearsAgo))
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
    }

    // 4. Departments sorted by total salary paid (descending)
    static List<String> departmentsByTotalSalary(List<Employee> employees) {
        List<String> list = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)))
                .entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).toList().stream().map(e -> e.getKey()).toList();
        return list;
    }

    // 5. Second highest paid employee (no skip)
    static Optional<Employee> secondHighestPaid(List<Employee> employees) {
        Optional<Employee> optEmp = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst();
        return optEmp; // TODO: implement
    }

    // 6. List of employees by year of joining
    static Map<Integer, List<String>> employeesByJoiningYear(List<Employee> employees) {
        Map<Integer, List<String>> map = employees.stream().collect(Collectors.groupingBy(e -> e.getJoiningDate().getYear(),Collectors.mapping(Employee::getName, Collectors.toList())));
        return map; // TODO: implement
    }

    // 7. Check if all departments have at least one employee under 25
    static boolean allDepartmentsHaveUnder25(List<Employee> employees) {
        boolean isAgeLT25Present = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment))
                .values().stream().
                allMatch(list->list.stream().anyMatch(e->e.getAge()<25));
//        map.values().stream().filter(list->list)
        return isAgeLT25Present; // TODO: implement
    }

    // 8. Average tenure by department
    static Map<String, Double> avgTenureByDept(List<Employee> employees) {
        LocalDate currentDate = LocalDate.now();
//        return Collections.emptyMap(); // TODO: implement
        Map<String, Double> map = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.averagingDouble(e -> ChronoUnit.DAYS.between(e.getJoiningDate(), currentDate) / 365.0)));
        return map;
    }
}
