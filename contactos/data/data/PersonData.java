package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entities.Person;

public class PersonData {
    List<Person> lis = new ArrayList<Person>();
    int id = 0;
    Connection cn = Conn.connectSQLite();

    public void create(Person d) {
        //p.setId(++id);
        //lis.add(p);
        String sql = "INSERT INTO persons(name, sex, age) "
                + " VALUES(?,?,?) ";
        int i = 0;
        int res =0;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getName());
            ps.setString(++i, d.getSex());
            ps.setInt(++i, d.getAge());
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("create.res=" + res);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public List<Person> list(String filter) {
        List<Person> lis2 = new ArrayList<Person>();
        String sql = "SELECT * FROM persons ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setSex(rs.getString("sex"));
                p.setAge(rs.getInt("age"));
                lis2.add(p);                
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return lis2;
    }

    public Person get(int id) {
        Person p =new Person();
        String sql = "SELECT * FROM persons WHERE id = "+id+" ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setSex(rs.getString("sex"));
                p.setAge(rs.getInt("age"));               
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return p;
    }

    // update(Person, int): void (o update(Person): void)
    public void update(Person d) {
        String sql = "UPDATE persons SET "
                + "name=?, "
                + "sex=?, "
                + "age=? "
                + "WHERE id=?";
        int i = 0;
        int res =0;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getName());
            ps.setString(++i, d.getSex());
            ps.setInt(++i, d.getAge());
            ps.setInt(++i, d.getId());
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("update.res=" + res);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM persons WHERE id = ?";
        int res =0;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id );
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("delete.res=" + res);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

}
