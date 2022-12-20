package com.example;

import java.sql.Date;

public class ItemTaken
{
    private int id;
    private String name;
    private Date date;
    private String status;
    private String location;
    private String description;
    private String contactlost;
    private String contactfound;

    public ItemTaken()
    {
    }

    public ItemTaken(int id, String name, Date date, String status, String location, String description, String contactlost, String contactfound)
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = status;
        this.location = location;
        this.description = description;
        this.contactlost = contactlost;
        this.contactfound = contactfound;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getContactlost()
    {
        return contactlost;
    }

    public void setContactlost(String contactlost)
    {
        this.contactlost = contactlost;
    }

    public String getContactfound()
    {
        return contactfound;
    }

    public void setContactfound(String contactfound)
    {
        this.contactfound = contactfound;
    }
}
