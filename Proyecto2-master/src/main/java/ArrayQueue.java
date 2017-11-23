
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julita
 */
public class ArrayQueue<E> implements Queue<E> {
	public static final int CAPACITY=1000;
	private E[] data;
	private int f = 0; //Index for front element
	private int sz = 0; //Curent qty
	
	public ArrayQueue( ) {
		this(CAPACITY);
	}

	public ArrayQueue(int capacity) {
		data = (E[ ]) new Object[capacity];
	}
	
	public int size(){
		return sz;
	}
	
	public boolean isEmpty() {
		return (sz == 0);
	}
	
	public void enqueue(E e) {
		int avail = (f + sz) % data.length;
		data[avail] = e;
		sz++;
	}
	
	public E first( ) {
		if (isEmpty()) return null;
		return data[f];
	}
	
	public E dequeue( ) {
		if (isEmpty()) return null;
		E answer = data[f];
		data[f] = null;
		f = (f + 1) % data.length;
		sz--;
		return answer;
	}
        
        public void dotFile() throws IOException{
            String label = "";
            String cadena = "dirgraph g\n{\ngraph [\nrankdir = \"LR\"\n]; \nnode [\n" +
                    "fontsize = \"16\"\n" +
                    "shape = \"ellipse\"\n" +
                    "];\n"
                    + "\"node0\"[\nlabel=\"ArrayQueue\"";
            for (int i = 0; i < data.length; i++){
                label += "|" + data[i].toString();
            }
            cadena += label;
            cadena += "\n];\n}";
            
            String direccion = "C:\\ArrayQueue.txt";
            FileWriter escritor = new FileWriter(direccion);
            escritor.write(cadena);
            escritor.close();
            generar(direccion, "ArrayQueue.jpg");
        }
        
        
        public void generar(String direccion, String salida) throws IOException
        {
            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String tparam  = "-Tjpg";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0]=dotPath;
            cmd[1]=tparam;
            cmd[2]=direccion;
            cmd[3]=tOParam;
            cmd[4]=salida;
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        }
}