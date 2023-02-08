import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Burulai Urbaeva
 */
public class MethodQuery {
    static LocalDate date() { return LocalDate.now();}
    static LocalTime time(){return LocalDateTime.now().toLocalTime();};
    static String reverse(String word){
        return new StringBuilder(word.substring(word.indexOf(' ')+1, word.length()))
                .reverse().toString();}
    static   String upper(String word){
        String res = word.substring(word.indexOf(' ')+1, word.length()).toUpperCase();
        return res;
    }
}
