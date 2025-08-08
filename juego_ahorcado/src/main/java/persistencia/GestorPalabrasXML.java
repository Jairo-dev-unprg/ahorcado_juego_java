package persistencia;

import modelo.Palabra;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorPalabrasXML {
    private static final String ARCHIVO_PALABRAS = "palabras.xml";
    private static final String ELEMENTO_RAIZ = "palabras";
    private static final String ELEMENTO_PALABRA = "palabra";
    
    public GestorPalabrasXML() {
        // Constructor vacío
    }

    public void guardarPalabras(List<Palabra> palabras) {
        guardarElementos(palabras);
    }

    public List<Palabra> cargarPalabras() {
        return cargarElementos();
    }
    
    private void guardarElementos(List<Palabra> palabras) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element raiz = doc.createElement(ELEMENTO_RAIZ);
            doc.appendChild(raiz);
            
            for (Palabra palabra : palabras) {
                Element elemento = crearElementoXML(doc, palabra);
                raiz.appendChild(elemento);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ARCHIVO_PALABRAS));
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private List<Palabra> cargarElementos() {
        File archivo = new File(ARCHIVO_PALABRAS);
        if (!archivo.exists()) {
            crearElementosPorDefecto();
        }
        return cargarDesdeXML();
    }
    
    private void crearElementosPorDefecto() {
        List<Palabra> palabrasPorDefecto = new ArrayList<>();
        palabrasPorDefecto.add(new Palabra("PROGRAMACION", "Disciplina para escribir instrucciones a computadoras."));
        palabrasPorDefecto.add(new Palabra("JAVA", "Lenguaje de programación orientado a objetos."));
        palabrasPorDefecto.add(new Palabra("AHORCADO", "Juego clásico de adivinar palabras."));
        palabrasPorDefecto.add(new Palabra("COMPUTADORA", "Dispositivo que procesa información."));
        palabrasPorDefecto.add(new Palabra("UNIVERSIDAD", "Centro de estudios superiores."));
        palabrasPorDefecto.add(new Palabra("SOFTWARE", "Conjunto de programas que ejecuta una computadora."));
        palabrasPorDefecto.add(new Palabra("CLASE", "Unidad de código en programación orientada a objetos."));
        palabrasPorDefecto.add(new Palabra("VARIABLE", "Espacio en memoria para almacenar datos."));
        palabrasPorDefecto.add(new Palabra("ALGORITMO", "Secuencia de pasos para resolver un problema."));
        palabrasPorDefecto.add(new Palabra("BASE DE DATOS", "Sistema para almacenar y organizar información."));
        
        guardarElementos(palabrasPorDefecto);
    }
    
    public void agregarPalabra(String texto, String pista) {
        List<Palabra> palabras = cargarElementos();
        palabras.add(new Palabra(texto.toUpperCase(), pista));
        guardarElementos(palabras);
    }

    public boolean actualizarPalabra(String palabraOriginal, String nuevaPalabra, String nuevaPista) {
        List<Palabra> palabras = cargarElementos();
        
        for (Palabra palabra : palabras) {
            if (palabra.getPalabra().equals(palabraOriginal)) {
                palabra.setPalabra(nuevaPalabra.toUpperCase());
                palabra.setPista(nuevaPista);
                guardarElementos(palabras);
                return true;
            }
        }
        
        return false;
    }

    public boolean eliminarPalabra(String texto) {
        List<Palabra> palabras = cargarElementos();
        
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).getPalabra().equals(texto)) {
                palabras.remove(i);
                guardarElementos(palabras);
                return true;
            }
        }
        
        return false;
    }
    
    private List<Palabra> cargarDesdeXML() {
        List<Palabra> palabras = new ArrayList<>();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(ARCHIVO_PALABRAS));
            
            NodeList nodos = doc.getElementsByTagName(ELEMENTO_PALABRA);
            
            for (int i = 0; i < nodos.getLength(); i++) {
                Element elemento = (Element) nodos.item(i);
                palabras.add(crearObjetoDesdeXML(elemento));
            }
            
        } catch (Exception e) {
            System.err.println("Error al cargar palabras: " + e.getMessage());
        }
        
        return palabras;
    }
    
    private Element crearElementoXML(Document doc, Palabra palabra) {
        Element elementoPalabra = doc.createElement(ELEMENTO_PALABRA);
        
        Element texto = doc.createElement("texto");
        texto.setTextContent(palabra.getPalabra());
        elementoPalabra.appendChild(texto);
        
        Element pista = doc.createElement("pista");
        pista.setTextContent(palabra.getPista());
        elementoPalabra.appendChild(pista);
        
        return elementoPalabra;
    }
    
    private Palabra crearObjetoDesdeXML(Element elemento) {
        String texto = elemento.getElementsByTagName("texto").item(0).getTextContent();
        String pista = elemento.getElementsByTagName("pista").item(0).getTextContent();
        return new Palabra(texto, pista);
    }
}