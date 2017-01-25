import DTO.AgruparDTO;
import DTO.RankingDTO;
import model.Jugador;
import model.Team;
import persistance.Basketbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class main {


    public static void main(String[] args) {

        try{

            Basketbbdd gestor = new Basketbbdd();

            gestor.conectar();
            System.out.println("Estableciendo conexion");

            /************************************************AÑADIENDO JUGADORES Y EQUIPOS DE PRUEBA A LA BBDD************************************************/

            Team pruebae = new Team("equipo","barcelona",LocalDate.of(2000,10,10));
            gestor.insertarEquipo(pruebae);
            Jugador pruebaj = new Jugador("jugador",LocalDate.of(1992,3,17),70,256,31,"alero",pruebae);
            gestor.insertarJugador(pruebaj);

            /*************************************************************************************************************************************************/

            Team e = new Team("stucom","barcelona",LocalDate.of(1966,03,27));
            gestor.insertarEquipo(e);
            System.out.println("Equipo insertado");

            Jugador j = new Jugador("alan",LocalDate.of(1900,11,07),10,20,30,"alero",e);
            gestor.insertarJugador(j);
            System.out.println("Jugador insertado");

            System.out.println("error?");

            gestor.modificarCRA("alan",60,50,70);
            System.out.println("modificado CRA a " + j.getNombre());

            System.out.println("error2?");

            Team e2 = new Team("barça","barcelona",LocalDate.of(1900,12,7));
            gestor.modificarJugadorT("alan",e2);
            System.out.println("Modificando equipo de "+j.getNombre());

            gestor.deletePlayer("jugador");
            System.out.println("eliminando el jguador");

            System.out.println(gestor.devolverJugador("alan"));
            System.out.println("jugador devuelto");

            List<Jugador> listJugadores = gestor.listJugadores("al");
            System.out.println("obteniendo concidencias por nombre");

            for(Jugador jugador : listJugadores){
                System.out.println(jugador);
            }

            System.out.println("error3?");

            List<Jugador> listJugadoresC = gestor.listJugadoresC(10);
            System.out.println("obteniendo concidencias por canastas");

            for(Jugador jugador : listJugadoresC){
                System.out.println(jugador);
            }

            List<Jugador> listJugadoresA = gestor.listJugadoresA(10);
            System.out.println("obteniendo concidencias por asistencias");

            for(Jugador jugador : listJugadoresA){
                System.out.println(jugador);
            }

            List<Jugador> listJugadoresP = gestor.listJugadoresP("alero");
            System.out.println("obteniendo concidencias por posicion");

            for(Jugador jugador : listJugadoresP){
                System.out.println(jugador);
            }

            List<Jugador> jugadoresAnteriores = gestor.jugadoresAnteriores(LocalDate.of(1900,11,07));
            System.out.println("obteniendo concidencias por nacimiento");

            for(Jugador jugador : jugadoresAnteriores){
                System.out.println(jugador);
            }

            List<AgruparDTO> agruparDTOS = gestor.agrupar();
            System.out.println("Agrupando jugadores por posicion");

            for(AgruparDTO g : agruparDTOS){
                System.out.println(g);
            }

            List<RankingDTO> ranking = gestor.rankin();
            System.out.println("Ranking de jguadores");

            int i = 1;
            System.out.println("Posicion - Nombre - Puntos");
            for(RankingDTO r : ranking){
                System.out.println(i + " " + r);
            }

            System.out.println("buscando jugador por nombre y su posicion en el ranking");
            i = 1;
            System.out.println("Posicion - Nombre - Puntos");
            for(RankingDTO r : ranking){
                if(r.getNombre().equalsIgnoreCase("alan")){
                    System.out.println(i + " " + r);
                    break;
                }
            }

            List<Team> localidadTeams = gestor.localidadTeams("");
            System.out.println("Obteniendo equipos por localidad");

            for(Team t : localidadTeams){
                System.out.println(t);
            }

            List<String> jugadoresEquipo = gestor.jugadoresEquipo("stucom");
            System.out.println("Obteniendo jugadores por equipo");

            for(String s : jugadoresEquipo){
                System.out.println(s);
            }

            List<Jugador> jugadoresEquipoPosicion = gestor.jugadoresEquipoPosicion("stucom","alero");
            System.out.println("Obteniendo jugadores por equipo y posicion");

            for(Jugador jugador : jugadoresEquipoPosicion){
                System.out.println(jugador);
            }

            System.out.println(gestor.maxEquipo("stucom"));
            System.out.println("jugador top del equipo stucom");

            gestor.desconectar();
            System.out.println("Cerrando conexion");

        }catch(SQLException ex){
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }

    }

}
