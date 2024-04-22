package modelo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;
import modelo.pojo.PacienteAlimento;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


public class PacientesDAO {

    public static List<Paciente> obtenerPacientesMedico(Integer idMedico){
        List<Paciente> pacientes = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                pacientes = conexionBD.selectList("pacientes.obtenerPacientesIdMedico", idMedico);
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return pacientes;
    }
    
    public static Mensaje registrarPaciente(Paciente paciente){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.insert("pacientes.registrar", paciente);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Información del paciente registrada con éxito.");
                }else{
                    msj.setMensaje("Lo sentimos, por el momento no se puede registrar la información del paciente");
                }
            } catch (Exception e) {
                msj.setMensaje("Error: "+e.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error al registrar la información, por el momento no hay conexión con la base de datos.");
        }
        return msj;
    }
    
    public static Mensaje editarPaciente(Paciente paciente){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.update("pacientes.editar", paciente);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Informacion del paciente editada con exito.");
                }else{
                    msj.setMensaje("Lo sentimos, por el momento no se puede editar la información del paciente");
                }
            } catch (Exception e) {
                msj.setMensaje("Error: "+e.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error al registrar la información, por el momento no hay conexión con la base de datos.");
        }
        return msj;
    }
    
    public static Mensaje registrarFotoPaciente(int idPaciente, byte[] foto){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idPaciente", idPaciente);
        parametros.put("foto", foto);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.update("pacientes.guardarFoto", parametros);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Fotografía del paciente guardada correctamente.");
                }else{
                    msj.setMensaje("Lo sentimos hubo un error al intentar guardar la "
                            + "fotografía, por favor inténtelo más tarde.");
                }
            } catch (Exception e) {
                msj.setMensaje("Error: "+e.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error de conexión, por el momento no se puede registrar la "
                    + "fotografía del paciente.");
        }
        return msj;
    }
    
    public static Paciente obtenerFotografiaPaciente(int idPaciente){
        Paciente paciente = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                paciente = conexionBD.selectOne("pacientes.obtenerFoto", idPaciente);
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return paciente;
    }
    
    public static List<PacienteAlimento> obtenerAlimentos(Integer idPaciente){
        List<PacienteAlimento> alimentos = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                alimentos = conexionBD.selectList("pacientes.obtenerPacienteAlimentos", idPaciente);
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return alimentos;
    }
}
