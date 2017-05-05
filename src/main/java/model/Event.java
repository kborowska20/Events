package model;

import java.util.Date;


public class Event {
    private int id;
    private String name;
    private Date date;
    private String category;
    private String description;



    public Event(String name, String category,Date date, String description){
        this.id = id;
        this.name = name;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public String getName(){ return name;}
    public String getDescription(){ return description;}
    public Date getDate(){ return date;}
    public String getCategory(){ return category;}
    public void setName(String name){this.name = name;}
    public void setId(int id){this.id = id;}
    public void setDate(Date date){this.date = date;}
    public void setCategory(String category){this.category = category;}
    public void setDescription(String description){this.description = description;}

}
