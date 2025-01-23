import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private String email;
    private String address;
    private int id;
    private double salary;

    public Employee(String name, String email, String address, int id, double salary) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.id = id;
        this.salary = salary;
    }

    public abstract void work();

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public int getId() {
        return id;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

class Manager extends Employee {
    public Manager(String name, String email, String address, int id) {
        super(name, email, address, id, 20000);
    }

    @Override
    public void work() {
        System.out.println("Managing team and resources.");
    }
}

class Engineer extends Employee {
    public Engineer(String name, String email, String address, int id) {
        super(name, email, address, id, 10000);
    }

    @Override
    public void work() {
        System.out.println("Developing and maintaining software.");
    }
}

class UserInterface {
    private ArrayList<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int currentId = 1;
    private double managerSalary = 20000;
    private double employeeSalary = 10000;

    public void start() {
        while (true) {
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Update Salary");
            System.out.println("6. Exit");
            System.out.println("Choose an option:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    viewEmployees();
                    break;
                case 5:
                    updateSalary();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addEmployee() {
        System.out.println("Enter employee type (1 for Manager, 2 for Engineer): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();

        Employee employee = null;
        switch (type) {
            case 1:
                employee = new Manager(name, email, address, currentId++);
                break;
            case 2:
                employee = new Engineer(name, email, address, currentId++);
                break;
            default:
                System.out.println("Invalid employee type.");
        }

        if (employee != null) {
            employees.add(employee);
            System.out.println("Employee added successfully.");
        }
    }

    private void deleteEmployee() {
        System.out.println("Enter employee id to delete: ");
        int id = scanner.nextInt();

        Employee toRemove = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                toRemove = employee;
                break;
            }
        }

        if (toRemove != null) {
            employees.remove(toRemove);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void updateEmployee() {
        System.out.println("Enter employee id to update: ");
        int id = scanner.nextInt();

        Employee toUpdate = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                toUpdate = employee;
                break;
            }
        }

        if (toUpdate != null) {
            System.out.println("Enter new name: ");
            String name = scanner.next();
            System.out.println("Enter new email: ");
            String email = scanner.next();
            System.out.println("Enter new address: ");
            String address = scanner.next();

            toUpdate.setName(name);
            toUpdate.setEmail(email);
            toUpdate.setAddress(address);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void viewEmployees() {
        for (Employee employee : employees) {
            String role = (employee instanceof Manager) ? "Manager" : "Engineer";
            System.out.println("Role: " + role +
                    ", ID: " + employee.getId() +
                    ", Name: " + employee.getName() +
                    ", Email: " + employee.getEmail() +
                    ", Address: " + employee.getAddress() +
                    ", Salary: " + employee.getSalary());
            employee.work();
        }
    }

    private void updateSalary() {
        System.out.println("1. Update Manager Salary");
        System.out.println("2. Update Employee Salary");
        System.out.println("Choose an option:");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter new Manager salary: ");
                managerSalary = scanner.nextDouble();
                System.out.println("Manager salary updated successfully.");
                for (Employee employee : employees) {
                    if (employee instanceof Manager) {
                        employee.setSalary(managerSalary);
                    }
                }
                break;
            case 2:
                System.out.println("Enter new Employee salary: ");
                employeeSalary = scanner.nextDouble();
                System.out.println("Employee salary updated successfully.");
                for (Employee employee : employees) {
                    if (employee instanceof Engineer) {
                        employee.setSalary(employeeSalary);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
