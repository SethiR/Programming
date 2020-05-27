class BankAccount{
    private int accountNumber;
    private String holderName;

    public BankAccount(int accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    void withdraw(int amt){}

}


public class TypeAndReflectionDemo {

    static void objectDetails(Object obj){
        showName(obj.getClass());
    }

    static void showName(Class theClass){
        System.out.println(theClass.getSimpleName());
        System.out.println(theClass.getConstructors());
        System.out.println(theClass.getDeclaredFields());
    }

    public static void main(String[] args) {
        BankAccount a = new BankAccount(1, "Sam");
        objectDetails(a);
    }

}
