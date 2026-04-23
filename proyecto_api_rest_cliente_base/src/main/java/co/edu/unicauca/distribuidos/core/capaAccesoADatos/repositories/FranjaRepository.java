package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Repository;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.FranjaEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.conexion.ConexionBD;

@Repository
public class FranjaRepository {

    private final ConexionBD conexionABaseDeDatos;

    public FranjaRepository() {
        conexionABaseDeDatos = new ConexionBD();
    }

    public FranjaEntity save(FranjaEntity franja) {
        FranjaEntity franjaAlmacenada = null;
        String consulta = "INSERT INTO franjas_horarias(horaInicio, horaFin, fecha, estado, idMedico) VALUES(?,?,?,?,?)";

        try {
            conexionABaseDeDatos.conectar();
            PreparedStatement sentencia = conexionABaseDeDatos.getConnection()
                    .prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            sentencia.setTime(1, Time.valueOf(franja.getHoraInicio()));
            sentencia.setTime(2, Time.valueOf(franja.getHoraFin()));
            sentencia.setDate(3, Date.valueOf(franja.getFecha()));
            sentencia.setString(4, franja.getEstado());
            sentencia.setInt(5, franja.getIdMedico());

            int resultado = sentencia.executeUpdate();
            ResultSet generatedKeys = sentencia.getGeneratedKeys();
            if (generatedKeys.next() && resultado == 1) {
                int idGenerado = generatedKeys.getInt(1);
                franja.setId(idGenerado);
                franjaAlmacenada = this.findById(idGenerado).orElse(null);
            }
            generatedKeys.close();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("Error al insertar franja: " + e.getMessage());
        }
        return franjaAlmacenada;
    }

    public Optional<FranjaEntity> findById(Integer id) {
        FranjaEntity franja = null;
        try {
            conexionABaseDeDatos.conectar();
            PreparedStatement sentencia = conexionABaseDeDatos.getConnection()
                    .prepareStatement("SELECT * FROM franjas_horarias WHERE id = ?");
            sentencia.setInt(1, id);
            ResultSet res = sentencia.executeQuery();
            if (res.next()) {
                franja = mapearFranja(res);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al consultar franja: " + e.getMessage());
        }
        return franja == null ? Optional.empty() : Optional.of(franja);
    }
    
    public Optional<Collection<FranjaEntity>> findByMedicoAndFecha(Integer idMedico, LocalDate fecha) {
        Collection<FranjaEntity> franjas = new LinkedList<>();
        try {
            conexionABaseDeDatos.conectar();
            PreparedStatement sentencia = conexionABaseDeDatos.getConnection()
                .prepareStatement("SELECT * FROM franjas_horarias WHERE idMedico = ? AND fecha = ?");
            sentencia.setInt(1, idMedico);
            sentencia.setDate(2, Date.valueOf(fecha));
            ResultSet res = sentencia.executeQuery();
            while (res.next()) {
                franjas.add(mapearFranja(res));
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al consultar franjas: " + e.getMessage());
        }
        return franjas.isEmpty() ? Optional.empty() : Optional.of(franjas);
    }

    // Método auxiliar para no repetir el mapeo de ResultSet
    private FranjaEntity mapearFranja(ResultSet res) throws SQLException {
        FranjaEntity f = new FranjaEntity();
        f.setId(res.getInt("id"));
        f.setHoraInicio(res.getTime("horaInicio").toLocalTime());
        f.setHoraFin(res.getTime("horaFin").toLocalTime());
        f.setFecha(res.getDate("fecha").toLocalDate());
        f.setEstado(res.getString("estado"));
        f.setIdMedico(res.getInt("idMedico"));
        return f;
    }
}