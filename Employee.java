import java.util.Objects;

public class Employee {
    private String employeeID;
    private String name;
    private double salary;
    private String department;

    public Employee(String employeeID, String name, double salary, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // Getters
    public String getEmployeeID() { return employeeID; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }

    // Simple method: salary after fixed tax deduction (10%)
    public double salaryAfterTax() {
        double taxRate = 0.10; // 10% tax
        return salary * (1 - taxRate);
    }

    @Override
    public String toString() {
        return "ID: " + employeeID +
               ", Name: " + name +
               ", Salary: " + String.format("%.2f", salary) +
               ", Department: " + department +
               ", Net Salary (after 10% tax): " + String.format("%.2f", salaryAfterTax());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee e = (Employee) o;
        return Objects.equals(employeeID, e.employeeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }
}
