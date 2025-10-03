import java.util.*;

// -------------------- Question 2: Employee Management --------------------
class EmployeeManager {
    private HashMap<Integer, String> employeeMap = new HashMap<>();

    // Add Employee
    public void addEmployee(int id, String name) {
        employeeMap.put(id, name);
    }

    // Get Employee Name
    public String getEmployeeName(int id) throws EmployeeNotFoundException {
        if (!employeeMap.containsKey(id)) {
            throw new EmployeeNotFoundException("Employee ID not found!");
        }
        return employeeMap.get(id);
    }

    // Display Employees
    public void displayEmployees() {
        if (employeeMap.isEmpty()) {
            System.out.println("No employees found!");
        } else {
            System.out.println("Employee Map: " + employeeMap);
        }
    }
}

// Custom Exception for Employee Not Found
class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

// -------------------- Question 4: Product Management --------------------
class ProductManager {
    private HashMap<String, Double> productMap = new HashMap<>();

    // Add Product
    public void addProduct(String id, double price) {
        productMap.put(id, price);
    }

    // Apply Discount
    public void applyDiscount(String id, double discountPercent)
            throws ProductNotFoundException, IllegalArgumentException {
        if (!productMap.containsKey(id)) {
            throw new ProductNotFoundException("Product ID not found!");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100!");
        }
        double oldPrice = productMap.get(id);
        double newPrice = oldPrice - (oldPrice * discountPercent / 100);
        productMap.put(id, newPrice);
        System.out.println("New price for " + id + ": $" + newPrice);
    }

    // Display Products
    public void displayProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products found!");
        } else {
            System.out.println("Product Map: " + productMap);
        }
    }
}

// Custom Exception for Product Not Found
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

// -------------------- Main Class (Menu Driven) --------------------
public class labmstHashmap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeManager empManager = new EmployeeManager();
        ProductManager prodManager = new ProductManager();

        int choice;
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Get Employee by ID");
            System.out.println("3. Display Employees");
            System.out.println("4. Add Product");
            System.out.println("5. Apply Discount to Product");
            System.out.println("6. Display Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    empManager.addEmployee(id, name);
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    int eid = sc.nextInt();
                    try {
                        System.out.println("Name for ID " + eid + ": " + empManager.getEmployeeName(eid));
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    empManager.displayEmployees();
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    String pid = sc.next();
                    System.out.print("Enter Product Price: ");
                    double price = sc.nextDouble();
                    prodManager.addProduct(pid, price);
                    break;

                case 5:
                    System.out.print("Enter Product ID: ");
                    String pId = sc.next();
                    System.out.print("Enter Discount %: ");
                    double discount = sc.nextDouble();
                    try {
                        prodManager.applyDiscount(pId, discount);
                    } catch (ProductNotFoundException | IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    prodManager.displayProducts();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
