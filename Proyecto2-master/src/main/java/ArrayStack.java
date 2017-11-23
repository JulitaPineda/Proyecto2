
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
public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY=100;
	private E[] data;
	private int t=-1;
	
	public ArrayStack() {
		this(CAPACITY);
	}

	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public int size(){
		return t+1;
	}

	public boolean isEmpty() {
		return (t == -1);
	}

	public void push(E e) {
		data[++t] = e;
	}

	public E top() {
		if (isEmpty()) return null;
		return data[t];
	}

	public E pop() {
		if (isEmpty()) return null;
		E response = data[t];
		data[t] = null;
		t--;
		return response;
	}
        
        public void dotFile() throws IOException{
            String label = "";
            String cadena = "dirgraph g\n{\ngraph [\nrankdir = \"LR\"\n]; \nnode [\n" +
                    "fontsize = \"16\"\n" +
                    "shape = \"ellipse\"\n" +
                    "];\n"
                    + "\"node0\"[\nlabel=\"ArrayStack\"";
            for (int i = data.length - 1; i > 0; i--){
                label += "|" + data[i].toString();
            }
            cadena += label;
            cadena += "\n];\n}";
            String direccion = "C:\\ArrayStack.txt";
            FileWriter escritor = new FileWriter(direccion);
            escritor.write(cadena);
            escritor.close();
            generar(direccion, "ArrayStack.jpg");
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