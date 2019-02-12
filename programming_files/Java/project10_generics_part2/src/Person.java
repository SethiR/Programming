import java.net.Inet4Address;
import java.util.Comparator;

public class Person
{
    private int age;
    private String name;

    public Person(String name, int age)
    {
        this.age = age;
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    @Override
    public String toString()
    {
        return "Person{name = " + name + ", age = " + age + "}";
    }

    public static Comparator<Person> BY_AGE = new Comparator<Person>()
    {
        @Override
        public int compare(Person o1, Person o2)
        {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
    };
}
