
public class ItemMotorBikeAvito implements IShow{

    private String title;
    private String price;
    private String city;
    private String time;
    private String category;

    public ItemMotorBikeAvito(String title, String price, String city, String time, String category) {

        this.title = title;
        this.price = price;
        this.city = city;
        this.time = time;
        this.category = category;
    }

    public void showInfo() {

        System.out.println(title + "\n" + price + "\n" + city + "\n" + time + "\n" + category + "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
