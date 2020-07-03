import java.util.ArrayList;
import java.util.Collection;

public class Main4 {
    public static void main(String[] args) {

        Collection<Integer> values = new ArrayList<>();  // Now we are specifying that we want only integers in the collection.

        values.add(10);
        values.add(20);

        for(int value : values){
            System.out.println(value);
        }

    }
}
