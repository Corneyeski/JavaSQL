package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import DTO.RankingDTO;
import DTO.AgruparDTO;
import model.Jugador;
import model.Team;

public class Basketbbdd {

    private Connection conexion;

    public Basketbbdd() {}

    public void insertarEquipo(Team t) throws SQLException {

        String insert = "insert into cocinero values (?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setString(1, t.getNombre());
        ps.setString(2, t.getLocalidad());
        ps.setDate(3, java.sql.Date.valueOf(t.getFechac()));

        ps.executeUpdate();

        ps.close();
    }

    public void insertarJugador(Jugador j) throws SQLException {

        String insert = "insert into cocinero values (?,?,?,?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setString(1, j.getNombre());
        ps.setDate(2, java.sql.Date.valueOf(j.getFechan()));
        ps.setInt(3, j.getCanasto());
        ps.setInt(4, j.getAsisto());
        ps.setInt(5, j.getReboto());
        ps.setString(6, j.getPosicion());
        ps.setString(7, j.getEquipo().getNombre());

        ps.executeUpdate();

        ps.close();
    }

    public void modificarCRA(String nombre, int i, int j, int k) throws SQLException {

        String update = "update into Jugador SET canasto = ?, asisto = ?, reboto = ? where nombre=?";
        PreparedStatement ps = conexion.prepareStatement(update);

        ps.setInt(1, i);
        ps.setInt(2, j);
        ps.setInt(3, k);
        ps.setString(4, nombre);

        ps.executeUpdate();

        ps.close();

    }

    public void modificarJugadorT(String nombre, Team equipo) throws SQLException {

        String update = "update into Jugador SET equipo=? where nombre=?";
        PreparedStatement ps = conexion.prepareStatement(update);

        ps.setString(1, equipo.getNombre());
        ps.setString(2, nombre);
    }

    public void deletePlayer(String nombre) throws SQLException {
        String update = "delete * from jugador where nombre = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, nombre);

        ps.executeUpdate();

        ps.close();
    }

    public Jugador devolverJugador(String nombre) throws SQLException {
        String obtener = "select * from jugador where nombre =" + nombre;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);


        return createJugador(ps);
    }

    public List<Jugador> listJugadores(String nombre) throws SQLException {

        List<Jugador> lista = new ArrayList<>();

        String obtener = "select * from jugador where nombre like '%" + nombre + "%'";
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {

            lista.add(createJugador(ps));
        }

        return lista;
    }

    public List<Jugador> listJugadoresC (int c) throws SQLException{

        List<Jugador> lista = new ArrayList<>();

        String obtener = "select * from jugador where canasto >="+c;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {

            lista.add(createJugador(ps));
        }

        return lista;
    }


    public List<Jugador> listJugadoresA (int a) throws SQLException{

        List<Jugador> lista = new ArrayList<>();

        String obtener = "select * from jugador where asisto >="+a;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {

            lista.add(createJugador(ps));
        }

        return lista;
    }

    public List<Jugador> listJugadoresP (String pos)throws SQLException{

        List<Jugador> lista = new ArrayList<>();

        String obtener = "select * from jugador where posicion ="+pos;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {

            lista.add(createJugador(ps));
        }

        return lista;
    }

    public List<Jugador> jugadoresAnteriores(LocalDate fecha)throws SQLException{

        List<Jugador> lista = new ArrayList<>();
        Date algo = java.sql.Date.valueOf(fecha);

        String obtener = "select * from jugador where posicion <"+algo;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {

            lista.add(createJugador(ps));
        }

        return lista;
    }

    public List<AgruparDTO> agrupar () throws SQLException{

        List<AgruparDTO> lista = new ArrayList<>();

        String obtener = "select posicion, "
                + "AVG(canasto),"
                + "AVG(asisto),"
                + "AVG(reboto),"
                + "MAX(canasto)"
                + "MAX(asisto)"
                + "MAX(reboto)"
                + "MIN(canasto)"
                + "MIN(asisto)"
                + "MIN(reboto)"
                + " from jugador group by posicion";
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {
            AgruparDTO a = new AgruparDTO();

            a.setPosicion(ps.getString("posicion"));
            a.setMediac(ps.getDouble(2));
            a.setMediaa(ps.getDouble(3));
            a.setMediar(ps.getDouble(4));
            a.setMaximoc(ps.getInt(5));
            a.setMaximoa(ps.getInt(6));
            a.setMaximor(ps.getInt(7));
            a.setMinimoc(ps.getInt(8));
            a.setMinimoa(ps.getInt(9));
            a.setMinimor(ps.getInt(10));

            lista.add(a);
        }

        return lista;
    }

    public List<RankingDTO> rankin() throws SQLException{

        List<RankingDTO> ranking = new ArrayList<>();

        String obtener = "select nombre,canasto from jugador";
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while(ps.next()){
            ranking.add(new RankingDTO(ps.getString("nombre"),ps.getInt("canasto")));
        }

        ranking.sort(Comparator.comparing(RankingDTO::getPuntos));

        return ranking;
    }

    public List<Team> localidadTeams(String localidad) throws SQLException{

        List<Team> teams = new ArrayList<>();

        String obtener = "select * from team WHERE localidad ="+localidad;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while(ps.next()){
            teams.add(new Team(ps.getString("nombre"),ps.getString("localidad"),ps.getDate("fechac").toLocalDate()));
        }

        return teams;
    }

    public List<String> jugadoresEquipo(String equipo) throws SQLException{

        List<String> jugadores = new ArrayList<>();

        String obtener = "select nombre from jugador WHERE equipo ="+equipo;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()){
            jugadores.add(ps.getString("nombre"));
        }

        return jugadores;
    }

    public List<Jugador> jugadoresEquipoPosicion(String nequipo, String posicion) throws SQLException{

        List<Jugador> jugadores = new ArrayList<>();

        String obtener = "select nombre from jugador WHERE equipo ="+nequipo+" and posicion ="+posicion;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()){

            jugadores.add(createJugador(ps));
        }
        return jugadores;
    }

    //TODO Arreglar esto
    public Jugador maxEquipo(String nequipo) throws SQLException{

        String obtener = "select nombre from jugador WHERE equipo ="+nequipo+" and MAX(canasto)";
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        return new Jugador(createJugador(ps));

    }

    public void conectar() throws SQLException {
        String url = "jdbc::mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    private Jugador createJugador(ResultSet ps) throws SQLException{

        Jugador p = new Jugador();
        p.setNombre(ps.getString("nombre"));
        p.setFechan(ps.getDate("fechan").toLocalDate());
        p.setCanasto(ps.getInt("canasto"));
        p.setAsisto(ps.getInt("asisto"));
        p.setReboto(ps.getInt("reboto"));
        p.setPosicion(ps.getString("posicion"));
        String equipo = (ps.getString("equipo"));

        String obtenerT = "select * from team where nombre = " + equipo;
        Statement consulta2 = conexion.createStatement();

        ResultSet pst = consulta2.executeQuery(obtenerT);
        Team t = new Team();

        t.setNombre(pst.getString("nombre"));
        t.setLocalidad(pst.getString("localidad"));
        t.setFechac(pst.getDate("fechac").toLocalDate());

        p.setEquipo(t);

        return p;
    }
}
