class Organization {
    private double balance;
    private Employee[] employees;
    private int employeeCount;

    public Organization(double initialBalance, int maxEmployees) {
        this.balance = initialBalance;
        this.employees = new Employee[maxEmployees];
        this.employeeCount = 0;
    }

    public void hireEmployee(String name, int empId, int age, String position) {
        if (employeeCount < employees.length) {
            employees[employeeCount++] = new Employee(name, empId, age, position);
        } else {
            System.out.println("Employee limit reached. Cannot hire more employees.");
        }
    }

    public void creditSalary(int empId, double amount) {
        Employee employee = findEmployee(empId);
        if (employee != null) {
            if (amount > balance) {
                System.out.println("Insufficient funds in organization account.");
                return;
            }
            employee.addSalary(amount);
            balance -= amount;
        } else {
            System.out.println("Employee not found.");
        }
    }

    public double getEmployeeTotalSalary(int empId) {
        Employee employee = findEmployee(empId);
        if (employee != null) {
            return employee.getSalary();
        } else {
            System.out.println("Employee not found.");
            return 0;
        }
    }

    public double getOrganizationBalance() {
        return balance;
    }

    private Employee findEmployee(int empId) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getEmpId() == empId) {
                return employees[i];
            }
        }
        return null;
    }
}

class Employee {
    private String name;
    private int empId;
    private int age;
    private String position;
    private double salary;

    public Employee(String name, int empId, int age, String position) {
        this.name = name;
        this.empId = empId;
        this.age = age;
        this.position = position;
        this.salary = 0;
    }

    public void addSalary(double amount) {
        salary += amount;
    }

    public double getSalary() {
        return salary;
    }

    public int getEmpId() {
        return empId;
    }
}

public class OrganizationManagement {
    public static void main(String[] args) {
        Organization organization = new Organization(100000,10);  

        organization.hireEmployee("John Doe", 1, 30, "Manager");
        organization.hireEmployee("Jane Smith", 2, 25, "Developer");

        organization.creditSalary(1, 50000);
        organization.creditSalary(2, 30000);

        System.out.println("Total salary for Employee 1: " + organization.getEmployeeTotalSalary(1));
        System.out.println("Organization balance: " + organization.getOrganizationBalance());
    }
}
