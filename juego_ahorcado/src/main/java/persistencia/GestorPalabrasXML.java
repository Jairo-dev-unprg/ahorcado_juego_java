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

    public void guardarPalabras(List<Palabra> palabras) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
    
            Element rootElement = doc.createElement("palabras");
            doc.appendChild(rootElement);
            
    
            for (Palabra palabra : palabras) {
                Element palabraElement = doc.createElement("palabra");
                rootElement.appendChild(palabraElement);
                
                Element textoElement = doc.createElement("texto");
                textoElement.appendChild(doc.createTextNode(palabra.getPalabra()));
                palabraElement.appendChild(textoElement);
                
                Element pistaElement = doc.createElement("pista");
                pistaElement.appendChild(doc.createTextNode(palabra.getPista()));
                palabraElement.appendChild(pistaElement);
            }
    
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ARCHIVO_PALABRAS));
            transformer.transform(source, result);
            
        } catch (Exception e) {
            System.err.println("Error al guardar palabras: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Palabra> cargarPalabras() {
        List<Palabra> palabras = new ArrayList<>();
        
        try {
            File archivo = new File(ARCHIVO_PALABRAS);
            if (!archivo.exists()) {
        
                crearPalabrasPorDefecto();
                return cargarPalabras();
            }
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(archivo);
            
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("palabra");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    
                    String texto = element.getElementsByTagName("texto").item(0).getTextContent();
                    String pista = element.getElementsByTagName("pista").item(0).getTextContent();
                    
                    palabras.add(new Palabra(texto, pista));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error al cargar palabras: " + e.getMessage());
            e.printStackTrace();
        }
        
        return palabras;
    }
    
    private void crearPalabrasPorDefecto() {
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
        
        guardarPalabras(palabrasPorDefecto);
    }
    
    public void agregarPalabra(String texto, String pista) {
        List<Palabra> palabras = cargarPalabras();
        palabras.add(new Palabra(texto.toUpperCase(), pista));
        guardarPalabras(palabras);
    }

    public boolean actualizarPalabra(String palabraOriginal, String nuevaPalabra, String nuevaPista) {
        List<Palabra> palabras = cargarPalabras();
        
        for (Palabra palabra : palabras) {
            if (palabra.getPalabra().equals(palabraOriginal)) {
                palabra.setPalabra(nuevaPalabra.toUpperCase());
                palabra.setPista(nuevaPista);
                guardarPalabras(palabras);
                return true;
            }
        }
        
        return false;
    }

    public boolean eliminarPalabra(String texto) {
        List<Palabra> palabras = cargarPalabras();
        
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).getPalabra().equals(texto)) {
                palabras.remove(i);
                guardarPalabras(palabras);
                return true;
            }
        }
        
        return false;
    }
}