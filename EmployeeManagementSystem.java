import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeManagementSystem app = new EmployeeManagementSystem();
        app.run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> {
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please pick 1-5.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println("=== Employee Management System ===");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employees");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
    }

    private void addEmployee() {
        System.out.println("--- Add Employee ---");
        String id = readString("Enter Employee ID: ").trim();
        if (findEmployeeByID(id) != null) {
            System.out.println("Employee with ID " + id + " already exists. Use update instead.");
            return;
        }
        String name = readString("Enter Name: ");
        double salary = readDouble("Enter Salary: ");
        String department = readString("Enter Department: ");
        Employee emp = new Employee(id, name, salary, department);
        employees.add(emp);
        System.out.println("Employee added successfully.");
    }

    private void viewEmployees() {
        System.out.println("--- View Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees to show.");
            return;
        }
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

    private void updateEmployee() {
        System.out.println("--- Update Employee ---");
        String id = readString("Enter Employee ID to update: ").trim();
        Employee e = findEmployeeByID(id);
        if (e == null) {
            System.out.println("Employee not found with ID: " + id);
            return;
        }
        System.out.println("Found: " + e.toString());
        System.out.println("1. Update Salary");
        System.out.println("2. Update Department");
        int opt = readInt("Choose option: ");
        if (opt == 1) {
            double newSalary = readDouble("Enter new salary: ");
            e.setSalary(newSalary);
            System.out.println("Salary updated.");
        } else if (opt == 2) {
            String newDept = readString("Enter new department: ");
            e.setDepartment(newDept);
            System.out.println("Department updated.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private void deleteEmployee() {
        System.out.println("--- Delete Employee ---");
        String id = readString("Enter Employee ID to delete: ").trim();
        Employee e = findEmployeeByID(id);
        if (e == null) {
            System.out.println("Employee not found with ID: " + id);
            return;
        }
        employees.remove(e);
        System.out.println("Employee with ID " + id + " deleted.");
    }

    // Helper methods
    private Employee findEmployeeByID(String id) {
        for (Employee e : employees) {
            if (e.getEmployeeID().equals(id)) return e;
        }
        return null;
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
