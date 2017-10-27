public class Person {

    private String date;
    private double debt;
    private String name;

    public Person(){
        this.date = "";
        this.debt = 0;
        this.name = "";
    }

    public Person(String da, String na, double de){
        this.date = da;
        this.debt = de;
        this.name = na;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDebt() {
        return debt;
    }

    public void setdebt(double price) {
        this.debt = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}