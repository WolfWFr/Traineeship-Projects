package ReadPrefProfiling;

import java.io.*;
import java.util.*;

public class ProfileGenerator {
    String[] profileData;
    ArrayList<Profile> ProfileList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void readText() {
        int i;

        try {
            File file = new File("C:\\Repo\\java-opdrachten-code\\src\\main\\resources\\profiling-data.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);

            String line;
            while ((line = buffReader.readLine()) != null) {
                profileData = line.split(", ");
                for(i=0;i<profileData.length;i++){
                    if(profileData[i].endsWith(" ")){
                        profileData[i]=profileData[i].substring(0,(profileData[i].length()-1));
                    }
                    profileData[i]=profileData[i].replace("  "," ");
                }
                String[] bookList = new String[profileData.length-1];
                for(i=0;i<bookList.length;i++){
                    bookList[i]=profileData[i+1];
                }
                ProfileList.add(new Profile(profileData[0],bookList));
            }
        } catch (Exception ex) {
            System.out.println("Oops! Exception!");
            ex.printStackTrace();
        }
    }

    public void ask(){

        String searchChoice;
        String[] searchOptions = {"[1:find customer by name]", "[2:find customer by book]","[3:find customer recommendation]"};

        System.out.print("What is your desired action? \n"+searchOptions[0]+"\n"+searchOptions[1]+"\n"+searchOptions[2]+"\n");
        searchChoice = scanner.nextLine().toLowerCase();

        if(searchChoice.equals(searchOptions[0])||searchChoice.equals("1")){
            System.out.println("Customer name: ");
            String[] searchTokens = getSearchTerms();
            getCustomerByName(searchTokens);
        }
        if(searchChoice.equals(searchOptions[1])||searchChoice.equals("2")){
            System.out.println("Book name: ");
            String[] searchTokens = getSearchTerms();
            getCustomerByBook(searchTokens);
        }
        if(searchChoice.equals(searchOptions[2])||searchChoice.equals("3")){
            System.out.print("Customer name: ");
            String[] searchTokens = getSearchTerms();
            getCustomerRec(searchTokens);
        }
    }

    public String[] getSearchTerms(){
        int i;
        String searchName;
        String[] searchTokens;

        searchName = scanner.nextLine();
        searchTokens = searchName.split(" ");
        for(i=0;i<searchTokens.length;i++){
            searchTokens[i].replace(" ","");
        }
        return searchTokens;
    }

    public void getCustomerByName(String[] searchTokens){
        int i;
        int j;
        int tokenIndex;
        boolean found = false;
        for(i=0;i<ProfileList.size();i++){
            for(tokenIndex=0;tokenIndex<searchTokens.length;tokenIndex++){
                if(ProfileList.get(i).getCustomerName().toLowerCase().contains(searchTokens[tokenIndex].toLowerCase())){
                    Profile customer = ProfileList.get(i);
                    found = true;
                    System.out.println("\n"+customer.getCustomerName()+" read: ");
                    for(j=0;j<customer.getBooks().length;j++){
                        if(j==customer.getBooks().length-1){
                            System.out.print(customer.getBooks()[j]+".\n\n");
                        }else{
                            System.out.print(customer.getBooks()[j]+", ");
                        }
                    }
                    continue;
                }
            }
        }
        if(found==false){
            System.out.println("\nCustomer not found.");
        }
    }

    public void getCustomerByBook(String[] searchTokens){
        boolean found = false;
        boolean found2 = false;
        String fullSearchTerm = "";
        String output="";
        ArrayList<String> foundBooks = new ArrayList<>();
        int i;
        int j;

        ArrayList<String> searchTokenList = new ArrayList<>(Arrays.asList(searchTokens));
        ArrayList<String> genericWords = new ArrayList<>(Arrays.asList("the","is","a","not","in","on","of","to"));
        for(i=0;i<searchTokenList.size();i++){
            if(genericWords.contains(searchTokenList.get(i).toLowerCase())){
                searchTokenList.remove(searchTokenList.get(i));
            }
        }

        for(i=0;i<searchTokens.length;i++){
            if(i==searchTokens.length-1){
                fullSearchTerm+=searchTokens[i];
            }else{
                fullSearchTerm+=searchTokens[i]+" ";
            }
        }

        for(i=0;i<ProfileList.size();i++){
            for(String book:ProfileList.get(i).getBooks()){
                if(book.toLowerCase().equals(fullSearchTerm.toLowerCase())){
                    found = true;
                    output+=ProfileList.get(i).getCustomerName()+"\n";
                }
            }
        }
        if(found){
            System.out.println("\nBook found! "+fullSearchTerm+" was read by:\n"+output);
        }

        if(!found){
            for(i=0;i<ProfileList.size();i++){
                for(String book:ProfileList.get(i).getBooks()){
                    if(!foundBooks.contains(book)){
                        for(String term:searchTokenList){
                            if(book.toLowerCase().contains(term.toLowerCase())){
                                found2 = true;
                                output+=book+", which was read by:\n";
                                foundBooks.add(book);
                                for(Profile profile:ProfileList){
                                    for(String book2:profile.getBooks()){
                                        if(book2.equals(book)){
                                            output+=profile.getCustomerName()+"\n";
                                            break;
                                        }
                                    }
                                }
                                output+="\n";
                                break;
                            }
                        }
                    }
                }
            }
            if(found2){
                System.out.println("\nThe title provided did not correspond to any book in the list.\n" +
                        "Here are books (and their readers) that match (some of) the separate search terms:\n"+output);
            }else{
                System.out.println("\nNo book with that name or any of the terms was found.");
            }
        }
    }

    public void getCustomerRec(String[] searchTokens){
        int nameMatchCounter = 0;
        String fullSearchName = "";
        String output = "";
        String customerName = "";
        boolean singleFullNameMatch = false;
        boolean found = false;
        boolean done = false;
        boolean customerMatch = false;
        ArrayList<String> booksOfCustomer = new ArrayList<>();
        ArrayList<String> sameBooks = new ArrayList<>();
        ArrayList<Profile> matchedCustomers = new ArrayList<>();

        for(String token:searchTokens){
            if(token.equals(searchTokens[searchTokens.length-1])){
                fullSearchName+=token;
            }else{
                fullSearchName+=token+" ";
            }
        }

        for(Profile profile:ProfileList){
            if(profile.getCustomerName().toLowerCase().equals(fullSearchName.toLowerCase())){
                for(String CustomerBook:profile.getBooks()){
                    booksOfCustomer.add(CustomerBook);
                }
                customerName=profile.getCustomerName();
                nameMatchCounter++;
            }
        }
        if(nameMatchCounter==1){
            singleFullNameMatch=true;
            found=true;
        }

        if(!singleFullNameMatch){
            output+="The name either did not match any registered users or it matched multiple users!\n" +
                    "Please enter the full name (as registered).\n" +
                    "You can find your registered name by selecting option 1 in the main menu. Give any part of your name and any match will be shown.";
        }else{
            bookProfileLoop:
            for(Profile bookProfile:ProfileList){
                for(String book:booksOfCustomer){
                    for(String book2:bookProfile.getBooks()){
                        if(book2.equals(book)){
                            sameBooks.add(book2);
                            if(sameBooks.size()==3){
                                customerMatch=true;
                                matchedCustomers.add(bookProfile);
                                continue bookProfileLoop;
                            }
                            break;
                        }
                    }
                }
                if(sameBooks.size()>2){
                    sameBooks.clear();
                }
            }
        }

        if(!found){
            System.out.println("\nCustomer was not found.");
        }else{
            if(customerMatch){
                while(!done){
                    int randomMatch = (int) (Math.random()*matchedCustomers.size());
                    int randomBook = (int) (Math.random()*matchedCustomers.get(randomMatch).getBooks().length);
                    if(!booksOfCustomer.contains(matchedCustomers.get(randomMatch).getBooks()[randomBook])){
                        output+=customerName+", we recommend '"+matchedCustomers.get(randomMatch).getBooks()[randomBook]+"' to you.\n";
                        done=true;
                    }
                }
            }else{
                output+="Our apologies, there are no recommendations at this time.\n";
            }
        }
        System.out.println(output);
    }

    public static void main(String[] args){
        ProfileGenerator PG = new ProfileGenerator();
        PG.readText();
        PG.ask();
    }
}