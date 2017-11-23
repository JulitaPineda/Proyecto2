
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
public class DoubleLinkedList<E> {

	private static class Node<E> {
		private E element;
		private Node<E> prev;//Anterior
		private Node<E> next;//Siguiente

		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

	}

	private Node<E> header = null;//Referencia
	private Node<E> trailer = null;
	private int size = 0;

	public DoubleLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		return header.getNext().getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return trailer.getPrev().getElement();
	}

	public void addFirst(E e) {
		addBetween(e, header, header.getNext());
	}

	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer);
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		return remove(header.getNext());
	}
	public E removeLast() {
		if (isEmpty())
			return null;
		return remove(trailer.getPrev());
	}
	
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev( );
		Node<E> successor = node.getNext( );
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement( );
	}
        public void dotFile() throws IOException{
            String nodo = "";
            String cadena = "dirgraph g{graph [rankdir = \"LR\"]; node [\n" +
                    "fontsize = \"16\"\n" +
                    "shape = \"ellipse\"\n" +
                    "];";
            Node<E> actual = header;
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
            int id = 1000000000;
            int i = 0;
            while (i < cont){
                uniones += "\"node" + String.valueOf(i) + "\" -> \"node" + String.valueOf(i + 1) + "\":f0 [\n"
                        + "id = " + i
                        + "\n];\n";
                uniones += "\"node" + String.valueOf(i + 1) + "\" -> \"node" + String.valueOf(i) + "\":f0 [\n"
                        + "id = " + id
                        + "\n];\n";
                i++;
                id++;
            }
            cadena += uniones;
            cadena += "\n];\n}";
            
            String direccion = "C:\\DoubleLinkedList.txt";
            FileWriter escritor = new FileWriter(direccion);
            escritor.write(cadena);
            escritor.close();
            generar(direccion, "DoubleLinkedList.jpg");
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