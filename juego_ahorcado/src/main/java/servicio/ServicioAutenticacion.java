package servicio;

import modelo.Usuario;
import persistencia.GestorUsuariosXML;

public class ServicioAutenticacion {
    
    private GestorUsuariosXML gestorUsuarios;
    
    public ServicioAutenticacion() {
    
    }
    
    public void inicializar() {
        this.gestorUsuarios = new GestorUsuariosXML();
    }
    
    /**
     * Valida las credenciales del usuario
     * @param nombreUsuario nombre del usuario
     * @param contrasena contraseña del usuario
     * @return Usuario autenticado o null si las credenciales son inválidas
     */
    public Usuario autenticar(String nombreUsuario, String contrasena) {
        if (gestorUsuarios == null) {
            inicializar();
        }
        return gestorUsuarios.validarCredenciales(nombreUsuario, contrasena);
    }
    
    /**
     * Registra un nuevo usuario
     * @param nombreUsuario nombre del usuario
     * @param contrasena contraseña del usuario
     * @return true si el registro fue exitoso, false si el usuario ya existe
     */
    public boolean registrarUsuario(String nombreUsuario, String contrasena) {
        if (gestorUsuarios == null) {
            inicializar();
        }
        return gestorUsuarios.registrarUsuario(nombreUsuario, contrasena);
    }
    
    /**
     * Valida que los datos de entrada sean correctos
     * @param nombreUsuario nombre del usuario
     * @param contrasena contraseña del usuario
     * @return mensaje de error o null si los datos son válidos
     */
    public String validarDatosEntrada(String nombreUsuario, String contrasena) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            return "Debe ingresar un nombre de usuario.";
        }
        
        if (contrasena == null || contrasena.trim().isEmpty()) {
            return "Debe ingresar una contraseña.";
        }
        
        return null;
    }
    
    /**
     * Valida los datos para registro
     * @param nombreUsuario nombre del usuario
     * @param contrasena contraseña del usuario
     * @param confirmarContrasena confirmación de contraseña
     * @return mensaje de error o null si los datos son válidos
     */
    public String validarDatosRegistro(String nombreUsuario, String contrasena, String confirmarContrasena) {
        String validacionBasica = validarDatosEntrada(nombreUsuario, contrasena);
        if (validacionBasica != null) {
            return validacionBasica;
        }
        
        if (confirmarContrasena == null || confirmarContrasena.trim().isEmpty()) {
            return "Debe confirmar la contraseña.";
        }
        
        if (!contrasena.equals(confirmarContrasena)) {
            return "Las contraseñas no coinciden.";
        }
        
        if (contrasena.length() < 4) {
            return "La contraseña debe tener al menos 4 caracteres.";
        }
        
        return null;
    }
}