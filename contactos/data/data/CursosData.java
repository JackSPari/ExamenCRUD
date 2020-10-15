package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursosData {
    List<Cursos> lis = new ArrayList<Cursos>();
    int id = 0;
    Connection cn = Conn.connectSQLite();

    public void create(final Cursos d) {
        final String sql = "INSERT INTO cursos(nombre, descripcion, creditos) " + " VALUES(?,?,?) ";
        int i = 0;
        int res = 0;
        try {
            final PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getDescripcion());
            ps.setString(++i, d.getClase());
            ps.setInt(++i, d.getCreditos());
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("create.res=" + res);
        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
    }

    public List<Cursos> list(final String filter) {
        final List<Cursos> lis2 = new ArrayList<Cursos>();
        final String sql = "SELECT * FROM cursos ";
        try {
            final Statement st = cn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                final Cursos p = new Cursos();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setComentarios(rs.getString("comentarios"));
                p.setClase(rs.getString("clase"));
                p.setCreditos(rs.getInt("creditos"));
                lis2.add(p);
            }
        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
        return lis2;
    }

    public Cursos get(final int id) {
        final Cursos p = new Cursos();
        final String sql = "SELECT * FROM cursos WHERE id = " + id + " ";
        try {
            final Statement st = cn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setComentarios(rs.getString("comentarios"));
                p.setClase(rs.getString("clase"));
                p.setCreditos(rs.getInt("creditos"));
            }
        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
        return p;
    }

    public void update(final Cursos d) {
        final String sql = "UPDATE cursos SET " + "nombre=?, " + "comentarios=?, "+"clase=? " + "creditos=? " + "WHERE id=?";
        int i = 0;
        int res = 0;
        try {
            final PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getComentarios());
            ps.setClase(++i, d.getString());
            ps.setInt(++i, d.getCreditos());
            ps.setInt(++i, d.getId());
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("update.res=" + res);

        } catch (final Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void delete(final int id) {
        final String sql = "DELETE FROM Cursos WHERE id = ?";
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