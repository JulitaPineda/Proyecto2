
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luzdy
 */
public class CargarArchivo {
    
    	public String loadFileWithJava7(String ruta/*, String save*/) {
            
            String x1 = " ";
            String x2 = " ";
            int content = 0;
            
		File file = new File(ruta);    
		try (FileInputStream fis = new FileInputStream(file)) {
                    InputStreamReader stream = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(stream);
                    
                    String linea;
			while ((linea = br.readLine()) != null) {
                            
                            if (content == 0){
                                x1 = linea;
                                content++;
                            }
                            else if(content == 1){
                                content = 0;
                                x2 = linea;
                                //Se debe agregar datos
                            }
				// convert to char and display it
				//System.out.print((char) content);
			}
                        
                        fis.close();

		} 
                
                catch (IOException e) {
			e.printStackTrace();
		}
               
                return ruta;
	}
    
    	public void loadFileWithJava8(String ruta) {
		String fileName = "\"C:\\Users\\luzdy\\Desktop\\ejemplo.txt\"";

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
