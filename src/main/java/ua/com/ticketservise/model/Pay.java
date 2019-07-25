package ua.com.ticketservise.model;

public class Pay {
    private long id;
    private String status;

    public Pay() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {

        int a = (int) ( Math.random() * 3 );
        if (a == 0){
            status = "false";
        }if(a == 1){
            status = "true";
        }if (a == 2){
            status = "null";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
