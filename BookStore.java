package ASM2;
import java.util.Scanner;

// main
public class BookStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Queue<Order> orders = new Queue<>();
        BookList bookList = new BookList();

        int nextOrderId = 1;

        bookList.add(new Book(1, "Book One", "Author A", 29.99, 10));
        bookList.add(new Book(6, "Book Six", "Author A", 39.99, 7));
        bookList.add(new Book(2, "Book Two", "Author B", 19.99, 5));
        bookList.add(new Book(3, "Book Three", "Author B", 39.99, 8));
        bookList.add(new Book(4, "Book Four", "Author C", 39.99, 9));
        bookList.add(new Book(7, "Book Five", "Author C", 39.99, 4));
        bookList.add(new Book(9, "Book Five", "Author C", 39.99, 4));
        bookList.add(new Book(15, "Book Five", "Author C", 39.99, 4));
        bookList.add(new Book(10, "Book Five", "Author C", 39.99, 4));

        Order currentOrder = null;

        int choice;

        do {
            System.out.println("----- BOOK STORE MENU -----");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Sort Books");
            System.out.println("5. Place Order");
            System.out.println("6. Search Order");
            System.out.println("7. Process Order");
            System.out.println("8. Display Orders");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            long startTime = System.currentTimeMillis(); // Thời gian bắt đầu

            switch (choice) {
                case 1:
                    boolean addingBooks = true;
                    while (addingBooks) {
                        int id;
                        boolean idExists;

                        do {
                            System.out.print("Enter book ID: ");
                            id = scanner.nextInt();
                            scanner.nextLine();

                            idExists = false;
                            for (int i = 0; i < bookList.size(); i++) {
                                if (bookList.get(i).getId() == id) {
                                    idExists = true;
                                    break;
                                }
                            }

                            if (idExists) {
                                System.out.println("ID already exists. Please enter a different ID.");
                            }
                        } while (idExists);

                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter book price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter book quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();

                        bookList.add(new Book(id, title, author, price, quantity));
                        System.out.println("Book added successfully!");

                        System.out.print("Do you want to add another book? (yes/no): ");
                        addingBooks = scanner.nextLine().equalsIgnoreCase("yes");
                    }
                    break;


                case 2:
                    System.out.println("Books in store: " + bookList);
                    System.out.println("Enter 1 to return to menu");
                    scanner.nextInt();
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.print("Enter book ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    Book foundBook = null;
                    for (int i = 0; i < bookList.size(); i++) {
                        Book book = bookList.get(i);
                        if (book.getId() == searchId) {
                            foundBook = book;
                            break;
                        }
                    }
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook);
                    } else {
                        System.out.println("Book not found with ID: " + searchId);
                    }
                    break;

                case 4:
                    System.out.println("Sort books by:");
                    System.out.println("1. Title");
                    System.out.println("2. Author");
                    System.out.println("3. Price");
                    System.out.println("4. ID");
                    System.out.print("Enter your choice: ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (sortChoice) {
                        case 1:
                            bookList.sortByTitle();
                            System.out.println("Books sorted by title: " + bookList);
                            break;

                        case 2:
                            bookList.sortByAuthor();
                            System.out.println("Books sorted by author: " + bookList);
                            break;

                        case 3:
                            bookList.sortByPrice();
                            System.out.println("Books sorted by price: " + bookList);
                            break;

                        case 4:
                            bookList.sortById();
                            System.out.println("Books sorted by ID: " + bookList);
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Customer Address: ");
                    String customerAddress = scanner.nextLine();

                    currentOrder = new Order(nextOrderId, customerName, customerAddress);
                    placeOrder(scanner, bookList, currentOrder);


                    orders.offer(currentOrder, 1);

                    nextOrderId++; // Tăng ID đơn hàng cho lần tạo tiếp theo
                    System.out.println("Order placed successfully!");
                    break;

                case 6:
                    searchOrder(bookList);
                    System.out.println("Enter 1 to return to menu");
                    scanner.nextInt();
                    scanner.nextLine();
                    break;

                case 7:
                    // Xử lý đơn hàng
                    if (currentOrder != null) {
                        System.out.println("Processing order: " + currentOrder);
                        currentOrder = null;
                    } else {
                        System.out.println("No order to process.");
                    }
                    break;

                case 8:
                    if (orders.isEmpty()) {
                        System.out.println("No orders available.");
                    } else {
                        System.out.println("Orders in queue:");
                        System.out.println(orders);
                    }
                    System.out.println("Enter 1 to return to menu");
                    scanner.nextInt();
                    scanner.nextLine();
                    break;

                case 9:
                    System.out.println("Exiting the application.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            long endTime = System.currentTimeMillis(); // Thời gian kết thúc
            long duration = endTime - startTime; // Thời gian thực thi
            System.out.println("Time taken: " + (duration) + " ms");

        } while (choice != 9);

        scanner.close();
    }

    private static void placeOrder(Scanner scanner, BookList bookList, Order currentOrder) {
        System.out.println("Available books: " + bookList);
        boolean keepAdding = true;

        while (keepAdding) {
            System.out.print("Enter the book ID to add to order (or -1 to finish): ");
            int bookId = scanner.nextInt();
            scanner.nextLine();
            if (bookId == -1) {
                keepAdding = false;
            } else {
                Book selectedBook = null;
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getId() == bookId) {
                        selectedBook = bookList.get(i);
                        break;
                    }
                }
                if (selectedBook != null) {
                    System.out.print("Enter quantity to add to order: ");
                    int quantityToAdd = scanner.nextInt();
                    scanner.nextLine();

                    if (quantityToAdd > selectedBook.getQuantity()) {
                        System.out.println("Insufficient number of books available.");
                    } else {
                        // Giảm số lượng sách trong danh sách sách
                        selectedBook.setQuantity(selectedBook.getQuantity() - quantityToAdd);

                        // Tạo bản sao sách với số lượng đã đặt và thêm vào bookCart
                        Book bookToAdd = new Book(selectedBook.getId(), selectedBook.getTitle(), selectedBook.getAuthor(), selectedBook.getPrice(), quantityToAdd);
                        currentOrder.bookCart.offer(bookToAdd, quantityToAdd); // Thêm sách vào bookCart
                    }
                } else {
                    System.out.println("Book not found.");
                }
            }
        }
    }




    private static void searchOrder(BookList orders) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search by:");
        System.out.println("   a) Order ID");
        System.out.println("   b) Customer Name");
        System.out.print("Choose search option (a/b): ");
        char searchOrderChoice = scanner.next().charAt(0);
        scanner.nextLine();

        switch (searchOrderChoice) {
            case 'a':
                System.out.print("Enter Order ID to search: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                boolean foundById = false;
                for (int i = 0; i < orders.size(); i++) {
                    Book order = orders.get(i);
                    if (order.getId() == orderId) {
                        System.out.println("Order found: " + order);
                        foundById = true;
                        break;
                    }
                }
                if (!foundById) {
                    System.out.println("Order not found with ID: " + orderId);
                }
                break;

            case 'b':
                System.out.print("Enter Customer Name to search: ");
                String customerName = scanner.nextLine();
                boolean foundByName = false;
                if (!foundByName) {
                    System.out.println("Order not found with Customer Name: " + customerName);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}