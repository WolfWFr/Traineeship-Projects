package ReadPrefProfiling;

public class Profile {
    private String CustomerName;
    private String[] Books;

    public Profile(String name, String[] books){
        setCustomerName(name);
        setBooks(books);
    }

    public void setCustomerName(String name){
        CustomerName=name;
    }

    public void setBooks(String[] books){
        Books=books;
    }

    public String getCustomerName(){
        return CustomerName;
    }

    public String[] getBooks(){
        return Books;
    }

}
