package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.MedicoEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.conexion.ConexionBD;

@Repository
public class MedicoRepository {
    
    private final ConexionBD conexionABaseDeDatos;

    public MedicoRepository() {
        conexionABaseDeDatos = new ConexionBD();
    }
    
    public MedicoEntity save(MedicoEntity objMedico) {
        System.out.println("registrando medico en base de datos");
        MedicoEntity objMedicoAlmacenado = null;
        int resultado = -1;

        try {

            conexionABaseDeDatos.conectar();

            PreparedStatement sentencia = null;
            String consulta = "insert into medicos(nombre, apellido, email, createAt) values(?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, objMedico.getNombre());
            sentencia.setString(2, objMedico.getApellido());
            sentencia.setString(3, objMedico.getEmail());
            sentencia.setDate(4, new java.sql.Date(objMedico.getCreateAt().getTime()));
            resultado = sentencia.executeUpdate();
            
            ResultSet generatedKeys = sentencia.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1); 
                objMedico.setId(idGenerado); 
                System.out.println("ID generado: " + idGenerado);
                if (resultado == 1) {
                    objMedicoAlmacenado = this.findById(idGenerado).get();
                }
            }
            else {
                System.out.println("No se pudo obtener el ID generado.");
            }

            generatedKeys.close();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la inserción: " + e.getMessage());
        }

       
        return objMedicoAlmacenado;
    }

    public Optional<MedicoEntity> findById(Integer idMedico) {
        System.out.println("consultar medico de base de datos");
        MedicoEntity objMedico = null;

        conexionABaseDeDatos.conectar();
        try {
            PreparedStatement sentencia = null;
            String consulta = "select * from medicos WHERE id = ?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, idMedico);
            ResultSet res = sentencia.executeQuery();
            while (res.next()) {
                System.out.println("cliente encontrado");
                objMedico = new MedicoEntity();
                objMedico.setId(res.getInt("id"));
                objMedico.setNombre(res.getString("nombre"));
                objMedico.setApellido(res.getString("apellido"));
                objMedico.setEmail(res.getString("email"));
                objMedico.setCreateAt(res.getDate("createAt"));
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la consulta: " + e.getMessage());
        }

        return objMedico==null ? Optional.empty() : Optional.of(objMedico); 
    }

     public Optional<Collection<MedicoEntity>> findAll() {
        System.out.println("listando medicos de base de datos");        
        Collection<MedicoEntity> medicos = new LinkedList<MedicoEntity>();

        conexionABaseDeDatos.conectar();
        try {
            PreparedStatement sentencia = null;
            String consulta = "select * from medicos";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            ResultSet res = sentencia.executeQuery();
            while (res.next()) {
                MedicoEntity objMedico = new MedicoEntity();
                objMedico.setId(res.getInt("id"));
                objMedico.setNombre(res.getString("nombre"));
                objMedico.setApellido(res.getString("apellido"));
                objMedico.setEmail(res.getString("email"));
                objMedico.setCreateAt(res.getDate("createAt"));
                medicos.add(objMedico);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("error en la consulta: " + e.getMessage());
        }

        return medicos.isEmpty() ? Optional.empty() : Optional.of(medicos);        
    }
}
