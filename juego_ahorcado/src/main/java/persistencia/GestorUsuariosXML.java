package persistencia;

import modelo.Usuario;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuariosXML {
    private static final String ARCHIVO_USUARIOS = "usuarios.xml";
    private static final String ELEMENTO_RAIZ = "usuarios";
    private static final String ELEMENTO_USUARIO = "usuario";
    
    public GestorUsuariosXML() {
        // Constructor vacío
    }

    public void guardarUsuarios(List<Usuario> usuarios) {
        guardarElementos(usuarios);
    }

    public List<Usuario> cargarUsuarios() {
        return cargarElementos();
    }
    
    private void guardarElementos(List<Usuario> usuarios) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element raiz = doc.createElement(ELEMENTO_RAIZ);
            doc.appendChild(raiz);
            
            for (Usuario usuario : usuarios) {
                Element elemento = crearElementoXML(doc, usuario);
                raiz.appendChild(elemento);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ARCHIVO_USUARIOS));
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private List<Usuario> cargarElementos() {
        File archivo = new File(ARCHIVO_USUARIOS);
        if (!archivo.exists()) {
            crearElementosPorDefecto();
        }
        return cargarDesdeXML();
    }

    private void crearElementosPorDefecto() {
        List<Usuario> usuariosPorDefecto = new ArrayList<>();
        usuariosPorDefecto.add(new Usuario("admin", "admin123", true));
        usuariosPorDefecto.add(new Usuario("usuario1", "pass123", false));
        guardarElementos(usuariosPorDefecto);
    }
    
    private List<Usuario> cargarDesdeXML() {
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            File archivo = new File(ARCHIVO_USUARIOS);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("usuario");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    usuarios.add(crearObjetoDesdeXML(element));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        
        return usuarios;
    }
    
    private Element crearElementoXML(Document doc, Usuario usuario) {
        Element usuarioElement = doc.createElement(ELEMENTO_USUARIO);
        
        Element nombreElement = doc.createElement("nombreUsuario");
        nombreElement.appendChild(doc.createTextNode(usuario.getNombreUsuario()));
        usuarioElement.appendChild(nombreElement);
        
        Element contrasenaElement = doc.createElement("contrasena");
        contrasenaElement.appendChild(doc.createTextNode(usuario.getContrasena()));
        usuarioElement.appendChild(contrasenaElement);
        
        Element adminElement = doc.createElement("esAdmin");
        adminElement.appendChild(doc.createTextNode(String.valueOf(usuario.isEsAdmin())));
        usuarioElement.appendChild(adminElement);
        
        return usuarioElement;
    }
    
    private Usuario crearObjetoDesdeXML(Element element) {
        String nombreUsuario = element.getElementsByTagName("nombreUsuario").item(0).getTextContent();
        String contrasena = element.getElementsByTagName("contrasena").item(0).getTextContent();
        boolean esAdmin = Boolean.parseBoolean(element.getElementsByTagName("esAdmin").item(0).getTextContent());
        
        return new Usuario(nombreUsuario, contrasena, esAdmin);
    }

    public Usuario validarCredenciales(String nombreUsuario, String contrasena) {
        List<Usuario> usuarios = cargarUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && 
                usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        
        return null;
    }

    public boolean registrarUsuario(String nombreUsuario, String contrasena) {
        List<Usuario> usuarios = cargarUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return false;
            }
        }

        usuarios.add(new Usuario(nombreUsuario, contrasena, false));
        guardarUsuarios(usuarios);
        return true;
    }
}