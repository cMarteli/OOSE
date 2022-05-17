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
        
        //make a new collection to store strings to be added
        List<String> list2 = new ArrayList<>();
        //iterates and adds to list2
        for(String s : list)
        {
            list2.add(s + s);
        }
        //System.out.println(list2);
        //adds collection to original list
        list.addAll(list2);
        
        System.out.println(list);


    } //end main


}
