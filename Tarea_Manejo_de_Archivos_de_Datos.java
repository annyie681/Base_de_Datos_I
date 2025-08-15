import java.io.BufferedReader;

public class leerArchivo {
  public static void main(String[] args) {
    String documento = "https://github.com/annyie681/Base_de_Datos_I/blob/main/codigos_postales_hmo.csv";
    try {
      BufferedReader leerDocumento = new BufferedReader(documento);    
    } catch (Exception e) {
      System.out.println("Error al leer el archivo.");
        }
  }
}
