package controlador;

import modelo.Usuario;
import servicio.ServicioAutenticacion;
import logueo_registro.JFLogueo;
import logueo_registro.JFRegistro;
import graficos.JFPizarra;
import graficos.JFAdmin;
import javax.swing.JFrame;

public class ControladorPrincipal {
    
    private ServicioAutenticacion servicioAuth;
    private Usuario usuarioActual;
    private JFrame ventanaActual;
    
    public ControladorPrincipal() {
    }

    public void inicializar() {
        this.servicioAuth = new ServicioAutenticacion();
        this.servicioAuth.inicializar();
    }

    public void iniciarAplicacion() {
        if (servicioAuth == null) {
            inicializar();
        }
        mostrarLogin();
    }

    public void mostrarLogin() {
        cerrarVentanaActual();
        JFLogueo login = new JFLogueo();
        login.setControlador(this);
        login.setVisible(true);
        this.ventanaActual = login;
    }

    public void mostrarRegistro() {
        cerrarVentanaActual();
        JFRegistro registro = new JFRegistro();
        registro.setControlador(this);
        registro.setVisible(true);
        this.ventanaActual = registro;
    }
    
    /**
     * Procesa el intento de login
     * @param nombreUsuario nombre del usuario
     * @param contrasena contraseña del usuario
     * @return mensaje de error o null si el login fue exitoso
     */
    public String procesarLogin(String nombreUsuario, String contrasena) {

        String errorValidacion = servicioAuth.validarDatosEntrada(nombreUsuario, contrasena);
        if (errorValidacion != null) {
            return errorValidacion;
        }

        Usuario usuario = servicioAuth.autenticar(nombreUsuario, contrasena);
        if (usuario == null) {
            return "Usuario o contraseña incorrectos.";
        }
        
        this.usuarioActual = usuario;
        abrirVentanaPrincipal();
        return null;
    }
    
    /**
     * Procesa el intento de registro
     * @param nombreUsuario nombre del usuario
     * @param contrasena contraseña del usuario
     * @param confirmarContrasena confirmación de contraseña
     * @return mensaje de error o null si el registro fue exitoso
     */
    public String procesarRegistro(String nombreUsuario, String contrasena, String confirmarContrasena) {

        String errorValidacion = servicioAuth.validarDatosRegistro(nombreUsuario, contrasena, confirmarContrasena);
        if (errorValidacion != null) {
            return errorValidacion;
        }

        boolean registroExitoso = servicioAuth.registrarUsuario(nombreUsuario, contrasena);
        if (!registroExitoso) {
            return "El nombre de usuario ya existe. Elija otro.";
        }

        mostrarLogin();
        return null;
    }

    private void abrirVentanaPrincipal() {
        cerrarVentanaActual();
        
        if (usuarioActual.isEsAdmin()) {
    
            JFAdmin admin = new JFAdmin();
            admin.setControlador(this);
            admin.inicializarComponentes();
            admin.setVisible(true);
            this.ventanaActual = admin;
        } else {
    
            JFPizarra pizarra = new JFPizarra();
            pizarra.setControlador(this);
            pizarra.inicializarComponentes();
            pizarra.setVisible(true);
            this.ventanaActual = pizarra;
        }
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
        mostrarLogin();
    }

    public void volverAlLogin() {
        mostrarLogin();
    }

    private void cerrarVentanaActual() {
        if (ventanaActual != null) {
            ventanaActual.dispose();
            ventanaActual = null;
        }
    }
    
    /**
     * Obtiene el usuario actualmente autenticado
     * @return usuario actual o null si no hay sesión activa
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    /**
     * Permite acceso a cualquier ventana si hay un usuario autenticado
     * @return true si hay un usuario autenticado, false en caso contrario
     */
    public boolean tieneAcceso() {
        return usuarioActual != null;
    }
    
    /**
     * Verifica si el usuario actual es administrador
     * @return true si es administrador, false en caso contrario
     */
    public boolean esAdministrador() {
        return usuarioActual != null && usuarioActual.isEsAdmin();
    }
}