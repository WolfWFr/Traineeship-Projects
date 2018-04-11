package QuoteSelectorPackage;

import java.util.regex.*;
import java.time.LocalDate;

public class Quote {
    String[][] quotes = {
            {"galileo", "eppur si muove"},
            {"archimedes", "eureka!"},
            {"erasmus", "in regione caecorum rex est luscus"},
            {"socrates", "I know nothing except the fact of my ignorance"},
            {"rené descartes", "cogito, ergo sum"},
            {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
    };

    public String[] getQuote(int Date){
        int i;
        String[] quote = quotes[Date];
        String[] NameParts = quote[0].split(" ");
        quote[0]="";
        for(i=0;i<NameParts.length;i++){
            NameParts[i]=NameParts[i].substring(0,1).toUpperCase()+NameParts[i].substring(1);
            quote[0]+=(NameParts[i]+" ");
            if(i!=(NameParts.length-1)){
                quote[0]+=" ";
            }
        }
        quote[1]=quote[1].substring(0,1).toUpperCase()+quote[1].substring(1);
        if(!Pattern.matches("\\p{Punct}",quote[1].substring((quote[1].length()-1)))){
            quote[1]+=".";
        }
        return quotes[Date];
    }

    public static void main(String... args) {
        Quote Select = new Quote();
        LocalDate calendar = LocalDate.now();
        System.out.println("Date: "+calendar.getDayOfMonth()+"/"+calendar.getMonth()+"/"+calendar.getYear());
        int Date = calendar.getDayOfYear();
        while(Date>6){
            Date-=6;
        }
        String[] quote = Select.getQuote(Date-1);
        System.out.println("\""+quote[1]+"\" -- "+quote[0]);
    }
}
