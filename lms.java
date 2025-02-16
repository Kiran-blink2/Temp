import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title, author;
    int issueNumber;
    boolean isAvailable;
    LocalDate borrowedDate;
    
    public Book(String title, String author, int issueNumber) {
        this.title = title;
        this.author = author;
        this.issueNumber = issueNumber;
        this.isAvailable = true;
    }
}

class User {
    String name;
    Book borrowedBook;
    
    public User(String name) {
        this.name = name;
        this.borrowedBook = null;
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    public void addBook(String title, String author, int issueNumber) {
        books.add(new Book(title, author, issueNumber));
    }
    
    public void addUser(String name) {
        users.add(new User(name));
    }
    
    public void borrowBook(String userName, String identifier) {
        User user = findUser(userName);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        for (Book book : books) {
            if ((book.title.equalsIgnoreCase(identifier) || String.valueOf(book.issueNumber).equals(identifier)) && book.isAvailable) {
                book.isAvailable = false;
                book.borrowedDate = LocalDate.now();
                user.borrowedBook = book;
                System.out.println(userName + " borrowed " + book.title);
                return;
            }
        }
        System.out.println("Book not available.");
    }
    
    public void returnBook(String userName) {
        User user = findUser(userName);
        if (user == null || user.borrowedBook == null) {
            System.out.println("No book to return.");
            return;
        }
        
        Book book = user.borrowedBook;
        book.isAvailable = true;
        long daysBorrowed = ChronoUnit.DAYS.between(book.borrowedDate, LocalDate.now());
        user.borrowedBook = null;
        
        System.out.println(userName + " returned " + book.title);
        if (daysBorrowed > 14) {
            System.out.println("Late return! Fine: Rs." + (daysBorrowed - 14) * 10);
        }
    }
    
    private User findUser(String name) {
        for (User user : users) {
            if (user.name.equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
}

public class lms {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("The Alchemist", "Paulo Coelho", 101);
        library.addBook("Java Programming", "James Gosling", 102);
        library.addUser("Alice");
        library.addUser("Bob");
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Borrow Book\n2. Return Book\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                System.out.println("Enter your name:");
                String userName = scanner.nextLine();
                System.out.println("Enter book name or issue number:");
                String bookIdentifier = scanner.nextLine();
                library.borrowBook(userName, bookIdentifier);
            } else if (choice == 2) {
                System.out.println("Enter your name:");
                String userName = scanner.nextLine();
                library.returnBook(userName);
            } else {
                break;
            }
        }
        scanner.close();
    }
}
