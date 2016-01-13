
public class Person {

    //Attribute
    private String nachname;
    private int alter;
    private boolean isWeiblich;
    private String stadt;

    //Konstruktor mit Standardwerten
    public Person() {
        nachname = "Ralph";
        alter = 42;
        isWeiblich = false;
        stadt = "Entenhausen";
    }

    //Konstruktor nur mit Namen und Alter
    public Person(String nachname, int alter) {
        this.nachname = nachname;
        this.alter = alter;
    }

    //Konstrukor mit allen Attributen
    public Person(String nachname, int alter, boolean isWeiblich, String stadt) {
        this.nachname = nachname;
        this.alter = alter;
        this.isWeiblich = isWeiblich;
        this.stadt = stadt;
    }

    public String getNachname() {
        return nachname;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}
