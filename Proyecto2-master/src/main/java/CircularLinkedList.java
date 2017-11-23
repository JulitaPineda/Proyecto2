
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
public class CircularLinkedList<E> {
	private static class Node<E>{
		private E element;
		private Node<E> next;
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

	private Node<E> tail = null;
	
	private int size = 0;
	
	public int size() {return size;}
	
	public boolean isEmpty() { return size == 0;}
	
	public E first() {
		if (isEmpty()) return null;
		return tail.getNext().getElement();//Primer cambio
	}
	
	public E last() {
		if (isEmpty()) return null;
		return tail.getElement();
	}
	
	public void rotate() {
		if (tail != null)
			tail = tail.getNext();
	}
	
	public void addFirst(E e) {
		
		if(size == 0) {
			tail = new Node<>(e, null);
			tail.setNext(tail);
		}else {
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}
	
	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}
	
	public E removeFirst() {
		if(isEmpty()) return null;
		Node<E> head = tail.getNext();
		if(head == tail) tail = null;
		else tail.setNext(head.getNext());
		size--;
		return head.getElement();
	}
        
        public void dotFile() throws IOException{
            String nodo = "";
            String cadena = "dirgraph g{graph [rankdir = \"LR\"]; node [\n" +
                    "fontsize = \"16\"\n" +
                    "shape = \"ellipse\"\n" +
                    "];";
            Node<E> actual = tail;
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
            for (int i = 0; i < cont; i++){
                uniones += "\"node" + String.valueOf(i) + "\" -> \"node" + String.valueOf(i + 1) + "\":f0 [\n"
                        + "id = " + i
                        + "\n];\n";
            }
            uniones += "\"node" + String.valueOf(cont) + "\" -> \"node0\":f0 [\n"
                        + "id = " + cont
                        + "\n];\n";
            cadena += uniones;
            cadena += "\n];\n}";
            String direccion = "C:\\CircularLinkedList.txt";
            FileWriter escritor = new FileWriter(direccion);
            escritor.write(cadena);
            escritor.close();
            generar(direccion, "CircularLinkedList.jpg");
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