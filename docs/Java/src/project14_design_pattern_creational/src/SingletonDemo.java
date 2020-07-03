import sun.security.pkcs11.Secmod;

class DBSingleton {

    private static DBSingleton instance = null;

    private DBSingleton(){}  // private constructor

    // the only public method will return that single instance which was created above.
    public static DBSingleton getInstance(){
        if (instance == null){
            instance = new DBSingleton();
        }
        return instance;
    }
}

public class SingletonDemo{
    public static void main(String[] args) {

        DBSingleton instance = DBSingleton.getInstance();
        System.out.println(instance);

    }
}