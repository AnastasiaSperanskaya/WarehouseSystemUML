package entities;

public class Client {
    private ContactInfo info;

    @Override
    public String toString() {
        return "Client{" +
                "info=" + info +
                '}';
    }

    public ContactInfo getInfo() {
        return info;
    }

    public void setInfo(ContactInfo info) {
        this.info = info;
    }
}
