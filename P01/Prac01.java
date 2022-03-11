import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Caio Marteli
 */
public class Prac01 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        list.add("jkl");
        for(String s : list)
        {
            list.add(s + s);
        }
        System.out.println(list);




    } //end main


}
