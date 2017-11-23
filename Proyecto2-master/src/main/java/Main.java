/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author luzdy
 */
public class Main {
   
    public static void main (String[] args)throws IOException{
        
        CargarArchivo file = new CargarArchivo();
        
        String ruta = "\"C:\\Users\\luzdy\\Desktop\\ejemplo.txt\"";
        String x = null;
        String textoLeido = null;
        
        file.loadFileWithJava7(ruta);
        
        
        System.out.println(file.loadFileWithJava7(ruta));
    }
}
