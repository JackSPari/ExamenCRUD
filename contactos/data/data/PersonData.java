package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Java.entities.Person;

public class PersonData {
    List<Person> lis = new ArrayList<Person>();
    int id = 0;
    Connection cn = Conn.connectSQLite();

    public void create(final Person d) {
        // p.setId(++id);
        // lis.add(p);
        final String sql = "INSERT INTO persons(name, sex, age) " + " VALUES(?,?,?) ";
        int i = 0;
        int res = 0;
        try {
            final PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getName());
            ps.setString(++i, d.getSex());
            ps.setInt(++i, d.getAge());
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("create.res=" + res);

        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
    }

    public List<Person> list(final String filter) {
        final List<Person> lis2 = new ArrayList<Person>();
        final String sql = "SELECT * FROM persons ";
        try {
            final Statement st = cn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                final Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setSex(rs.getString("sex"));
                p.setAge(rs.getInt("age"));
                lis2.add(p);
            }
        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
        return lis2;
    }

    public Person get(final int id) {
        final Person p = new Person();
        final String sql = "SELECT * FROM persons WHERE id = " + id + " ";
        try {
            final Statement st = cn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setSex(rs.getString("sex"));
                p.setAge(rs.getInt("age"));
            }
        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
        return p;
    }

    // update(Person, int): void (o update(Person): void)
    public void update(final Person d) {
        final String sql = "UPDATE persons SET " + "name=?, " + "sex=?, " + "age=? " + "WHERE id=?";
        int i = 0;
        int res = 0;
        try {
            final PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getName());
            ps.setString(++i, d.getSex());
            ps.setInt(++i, d.getAge());
            ps.setInt(++i, d.getId());
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("update.res=" + res);

        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void delete(final int id) {
        final String sql = "DELETE FROM persons WHERE id = ?";
        int res = 0;
        try {
            final PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("delete.res=" + res);

        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
    }

}
