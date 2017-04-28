package library;

import java.util.ArrayList;

public class Library {

    private static ArrayList<Book> bookList = new ArrayList<>();
    
    public static void main(String[] args) {

        BookViewFrame mainFrame = new BookViewFrame();
        mainFrame.setBookList(bookList);
        mainFrame.setVisible(true);
    }
}
