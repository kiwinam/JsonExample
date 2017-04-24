package charlie.JSONExample;

/**
 * Created by charlie on 2017. 4. 24..
 */

public class Item  {
    private String nation;
    private String name;
    private String address;
    private String age;

    public Item(String nation, String name, String address, String age){
        this.nation = nation;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }
}
