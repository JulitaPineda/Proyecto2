
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luzdy
 */
public class LinkedList<E> implements Stack<E>, Queue<E>{

	/**
	 * Inner class
	 * @author tuxtor
	 *
	 * @param <E>
	 */
	private static class Node<E>{
		private E element; //Valor
		private Node<E> next; //Puntero en la lista
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

	private Node<E> head = null;
	private Node<E> tail = null;
	
	private int size = 0;
	
	public int size() {return size;}
	
	public boolean isEmpty() { return size == 0;}
	
	public E first() {
		if (isEmpty()) return null;
		return head.getElement();
	}
	
	public E last() {
		if (isEmpty()) return null;
		return tail.getElement();
	}
	
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if (size == 0) tail = head;
		size++;
	}
	
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);
		if(isEmpty())
			head = newest;
		else
			tail.setNext(newest);
		tail = newest;
		size++;
	}
	
	public E removeFirst() {
		if (isEmpty()) return null;
		E response = head.getElement();
		head = head.getNext( );
		size--;
		if(size == 0) tail = null;
		return response;
	}

	@Override
	public void push(E e) {
		this.addFirst(e);
		
	}

	@Override
	public E top() {
		
		return this.first();
	}

	@Override
	public E pop() {
		
		return this.removeFirst();
	}

	@Override
	public void enqueue(E e) {
		this.addLast(e);
		
	}

	@Override
	public E dequeue() {
		return removeFirst();
	}
	
        public void dotFile() throws IOException{
            String nodo = "";
            String cadena = "dirgraph g{graph [rankdir = \"LR\"]; node [\n" +
                    "fontsize = \"16\"\n" +
                    "shape = \"ellipse\"\n" +
                    "];";
            Node<E> actual = head;
            int cont = 0;
            String uniones = "";
            while (actual.getNext() != null){
                nodo = "\"node" + String.valueOf(cont) + "\" [\n"
                        + "label = " + actual.getElement().toString() + "\n"
                        + "shape = \"record\"\n"
                        + "];\n";
                cadena += nodo;
                cont++; 
                actual = actual.getNext();
            }
            int i = 0;
            while (i < cont){
                uniones += "\"node" + String.valueOf(i) + "\" -> \"node" + String.valueOf(i + 1) + "\":f0 [\n"
                        + "id = " + i
                        + "\n];\n";
                i++;
            }
            cadena += uniones;
            cadena += "\n];\n}";
            String direccion = "C:\\LinkedList.txt";
            FileWriter escritor = new FileWriter(direccion);
            escritor.write(cadena);
            escritor.close();
            generar(direccion, "LinkedList.jpg");
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
