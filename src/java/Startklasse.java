
import SQL.DbTools;

/**
 *
 * @author lukiworker
 */
public class Startklasse {
    
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person("Walter", 42);
        Person p3 = new Person("Heinrich", 20);
        Person p4 = new Person("Kurt", 18, false, "Krasnoturjinsk");
        
        p1.setAlter(18);
        
        System.out.println(p1.getAlter());
        System.out.println("Alter von p1: "+p1.getAlter());
        System.out.print(DbTools.exportDb(DbTools.connect()));
        
    }
}
