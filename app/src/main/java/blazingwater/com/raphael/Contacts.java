package blazingwater.com.raphael;

public class Contacts {

    private String name, phone, amount;

    public Contacts(String name, String phone, String amount)
    {
        this.setName(name);
        this.setPhone(phone);
        this.setAmount(amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
