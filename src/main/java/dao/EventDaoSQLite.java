package dao;


import model.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDaoSQLite implements EventDao {

    @Override
    public void add(Event event) {
        try {
            Connection c = ConnectToDb.getConnection();
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);
            String sql = "INSERT INTO Events(name , Date, category, description)" +
                    "VALUES ('" + event.getName() + "','" + event.getDate() + "','" + event.getCategory() +
                    "','" + event.getDescription() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    @Override
    public Event find(int id) {
        try {
            Connection c = ConnectToDb.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Events WHERE id = " + id + ";");
            String name = rs.getString("name");
            String dateString = rs.getString("Date");
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = format.parse(dateString);
            String category = rs.getString("category");
            String description = rs.getString("description");
            Event event = new Event(name, category,date, description);
            event.setId(id);
            rs.close();
            stmt.close();
            return event;
        } catch (Exception e) {
            System.err.println("Wrong id!");
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            Connection c = ConnectToDb.getConnection();
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);
            String sql = "DELETE FROM Events where id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println("Wrong id!");
        }

    }

    @Override
    public List<Event> getAll() {
        List<Event> eventsList = getList("SELECT * FROM Product;");
        return eventsList;
    }

    @Override
    public List<Event> getBy(String category) {
        List<Event> eventListbyCategory = getList("SELECT * FROM Product WHERE supplierID =" +category + ";");
        return eventListbyCategory;
    }

    public static List<Event> getList(String query) {
        List<Event> eventList = new ArrayList<>();
        try {
            Connection c = ConnectToDb.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dateString = rs.getString("Date");
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date date = format.parse(dateString);
                String category = rs.getString("category");
                String description = rs.getString("description");
                Event event = new Event(name, category,date, description);
                event.setId(id);
                eventList.add(event);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return eventList;
    }


}
