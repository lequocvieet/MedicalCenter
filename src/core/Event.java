package core;

import java.util.Date;

public class Event {
    private String name;
    private String description;
    private Date date;

    public Event(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;

    }

    public Event() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
