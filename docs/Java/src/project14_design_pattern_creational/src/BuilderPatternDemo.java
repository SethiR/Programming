class LunchMenu{
    public String getBread() {
        return bread;
    }

    public String getCheese() {
        return cheese;
    }

    public String getVegges() {
        return vegges;
    }

    public String getMeat() {
        return meat;
    }

    // builder class within the actual lunchmenu class.
    public static class Builder{
        private String bread;
        private String cheese;
        private String vegges;
        private String meat;

        Builder(String bread){  // bread is mandatory in sandwich
            this.bread = bread;
        }


        // rest of these are optionals
        public Builder cheese(String cheese){
            this.cheese = cheese;
            return this;
        }

        public Builder vegees(String vegges){
            this.vegges = vegges;
            return this;
        }

        public Builder meat(String meat){
            this.meat = meat;
            return this;
        }

        public LunchMenu build(){
            return new LunchMenu(this);
        }
    }

    private String bread;
    private String cheese;
    private String vegges;
    private String meat;

    private LunchMenu(Builder builder){
        this.bread = builder.bread;
        this.cheese = builder.cheese;
        this.meat = builder.meat;
        this.vegges = builder.vegges;

    }

}

public class BuilderPatternDemo {
    public static void main(String[] args) {

        LunchMenu.Builder builder = new LunchMenu.Builder("Herbs and cheese");
        builder.cheese("swis").meat("chicken");

        LunchMenu sandwhich1 = builder.build();

        System.out.println(sandwhich1.getBread());
        System.out.println(sandwhich1.getCheese());
        System.out.println(sandwhich1.getMeat());
        System.out.println(sandwhich1.getVegges());

    }
}
