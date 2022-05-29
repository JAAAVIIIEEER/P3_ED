package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {
	//	referencia al primer aux de la lista
	private DoubleNode<T> front;
	//	referencia al Último aux de la lista
	private DoubleNode<T> last;

	private class DoubleNode<T> {
		T elem;
		DoubleNode<T> next;
		DoubleNode<T> prev;
		
		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}
	}

	///// ITERADOR normal //////////
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		
		public DoubleLinkedListIterator(DoubleNode<T> aux) {
			this.node = aux;
		}

		@Override
		public boolean hasNext() {
			return (node != null);
		}

		@Override
		public T next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			T aux = this.node.elem;
			this.node = this.node.next;
			return aux;
		}
	}
	////// FIN ITERATOR

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			this.node = aux;
		}

		@Override
		public boolean hasNext() {
			return (node != null);
		}

		@Override
		public T next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			T aux = this.node.elem;
			this.node = this.node.prev;
			return aux;
		}
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorOddPositions<T> implements Iterator<T> {
		DoubleNode<T> node;
		
		public DoubleLinkedListIteratorOddPositions(DoubleNode<T> aux) {
			this.node = aux;
		}

		@Override
		public boolean hasNext() {
			return (node != null);
		}

		@Override
		public T next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			T aux = this.node.elem;
			if(this.node.next != null) {
				this.node = this.node.next.next;
			} else {
				this.node = null;
			}
			return aux;
		}		
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgressReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		int cont = 1;
		
		public DoubleLinkedListIteratorProgressReverse(DoubleNode<T> aux) {
			this.node = aux;
		}

		@Override
		public boolean hasNext() {
			return (node != null);
		}

		@Override
		public T next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			T aux = this.node.elem;
			for (int i = 0; i < cont; i++) {
				if(this.node != null) {
					if(this.node.prev != null) {
						this.node = this.node.prev;
					} else {
						this.node = null;
					}
				} else {
					this.node = null;
				}
			}
			cont++;
			return aux;
		}
	}
	/////

	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		for (T elem:v) {
			this.addLast(elem);
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.front == null && this.last == null);
	}


	@Override
	public void clear() {
		this.front = null;
		this.last = null;
	}

	@Override
	public void addFirst(T elem) {
		if (elem == null) 
			throw new NullPointerException();

		DoubleNode<T> nuevo = new DoubleNode<T>(elem);
		
		if (isEmpty()) {
			this.front = nuevo;
			this.last = nuevo;
		} else {
			nuevo.next = this.front;
			this.front.prev = nuevo;
			this.front = nuevo;
		}
	}

	@Override
	public void addLast(T elem) {
		if (elem == null) 
			throw new NullPointerException();
		
		DoubleNode<T> nuevo = new DoubleNode<T>(elem);
		
		if (isEmpty()) {
			this.front = nuevo;
			this.last = nuevo;
		} else {
			nuevo.prev = this.last;
			this.last.next = nuevo;
			this.last = nuevo;
		}
	}

	@Override
	public void addPos(T elem, int position) {
		if (elem == null) 
			throw new NullPointerException();
		if (position <= 0) 
			throw new IllegalArgumentException();
		
		DoubleNode<T> auxiliar = this.front;
		DoubleNode<T> nuevo = new DoubleNode<T>(elem);
		
		if (position == 1) { 
			this.addFirst(elem);
		} else if (position > this.size()) { 
			this.addLast(elem);
		} else {
			for (int i = 1 ; i < position; i++)
				auxiliar = auxiliar.next;
			nuevo.prev = auxiliar.prev;
			nuevo.next = auxiliar;
			auxiliar.prev.next = nuevo;
			auxiliar.prev = nuevo;
		}
	}

	@Override
	public void addBefore(T elem, T target) {
		if (elem == null || target == null) 
			throw new NullPointerException();
		if (!contains(target)) 
			throw new NoSuchElementException();
		
		int posicion = this.getPosFirst(target);
		this.addPos(elem, posicion);
	}

	@Override
	public T getElemPos(int position) {
		if (position < 1 || position > this.size()) 
			throw new IllegalArgumentException();

		int i = 1;
		T result = null;
		DoubleNode<T> auxiliar = front;
		
		while (auxiliar != null && result == null) {
			if (i == position) {
				result = auxiliar.elem;
			} else {
				auxiliar = auxiliar.next;
			}
			i++;
		}
		return result;
	}

	@Override
	public int getPosFirst(T elem) {
		if (elem == null) 
			throw new NullPointerException();
		if (!contains(elem)) 
			throw new NoSuchElementException();
		
		boolean encontrado = false;
		DoubleNode<T> auxiliar = front;
		int cont = 1;
		
		while (auxiliar != null && encontrado == false) {
			if (auxiliar.elem.equals(elem)) {
				encontrado = true;
			} else {
				cont++;
				auxiliar = auxiliar.next;
			}
		}
		return cont;
	}


	@Override
	public int getPosLast(T elem) {
		if (elem == null) 
			throw new NullPointerException();
		if (!contains(elem)) 
			throw new NoSuchElementException();
		
		DoubleNode<T> auxiliar = front;
		int contador = 0;
		int posicion = 0;

		while (auxiliar != null) {
			contador++;
			if (auxiliar.elem.equals(elem)) {
				posicion = contador;
			}
			auxiliar = auxiliar.next;
		}
		return posicion;
	}

	
	@Override
	public T removeLast()  throws EmptyCollectionException{
		if (isEmpty()) {
			throw new EmptyCollectionException("DoubleLinkedList");
		}
		
		T elementoDevuelto = null;
		
		if (this.size() == 1) { 
			elementoDevuelto = this.front.elem;
			clear();
		} else {
			elementoDevuelto = this.last.elem;
			this.last = this.last.prev;
			this.last.next = null;
		}
		return elementoDevuelto;
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		if (pos < 1 || pos > this.size()) 
			throw new IllegalArgumentException();
		if (isEmpty()) {
			throw new EmptyCollectionException("DoubleLinkedList");
		}
		
		T elementoDevuelto = this.getElemPos(pos);
		DoubleNode<T> auxiliar = front;

        if (this.size() == 1){
        	elementoDevuelto = front.elem;
        	this.clear();
        } else if (pos == 1) {
        	elementoDevuelto = auxiliar.elem;
			this.front = this.front.next;
		} else if (pos == this.size()) {
			elementoDevuelto = this.last.elem;
			this.last = this.last.prev;
			this.last.next = null;
		} else {
			for (int i = 2; i <= pos; i++) {
				auxiliar = auxiliar.next;
			}
			elementoDevuelto = auxiliar.elem;
			auxiliar.prev.next = auxiliar.next;
			auxiliar.next.prev = auxiliar.prev;
		}
		return elementoDevuelto;
	}


	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		if (elem == null) 
			throw new NullPointerException();
		if (times < 1) 
			throw new IllegalArgumentException();
		if (this.isEmpty())
			throw new EmptyCollectionException("DoubleLinkedList");
		if (!this.contains(elem))
			throw new NoSuchElementException();
		
		int instanciasEliminadas = 0, pos = 1;
		DoubleNode<T> auxiliar = front;
		
		while (auxiliar != null) {
			if (auxiliar.elem.equals(elem)) {
				if (this.size() == 1){
		        	this.clear();
		        } else if (pos == 1) {
					this.front = this.front.next;
					pos--;
				} else {
					if (pos == this.size()) {
						this.last = this.last.prev;
						this.last.next = null;
					} else {
						auxiliar.prev.next = auxiliar.next;
						auxiliar.next.prev = auxiliar.prev;
						pos--;
					}
				}
				instanciasEliminadas++;
			}
			pos++;
			auxiliar = auxiliar.next;
		}
		return instanciasEliminadas;
	}

	@Override
	public T removeSecond() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("DoubleLinkedList");
		if (this.size() == 1)
			throw new NoSuchElementException();
		
		DoubleNode<T> auxiliar = front.next;
		
		T elementoDevuelto = auxiliar.elem;
		this.front.next = auxiliar.next;
		auxiliar.next.prev = this.front;
		
		return elementoDevuelto;
	}

	@Override
	public DoubleList<T> copy() {
		DoubleList<T> list = new DoubleLinkedListImpl<T>();
		list.clear();
		if(!this.isEmpty()) {
			for (int i = 1; i <= this.size(); i++) {
				list.addLast(this.getElemPos(i));
			}
		}
		return list;
	}

	@Override
	public boolean contains(T elem) {
		if (elem == null) 
			throw new NullPointerException();
		
		DoubleNode<T> auxiliar = front;

		while (auxiliar != null) {
			if (auxiliar.elem.equals(elem)) 
				return true;
			auxiliar = auxiliar.next;
		}
		return false;
	}


	@Override
	public int size() {
		DoubleNode<T> auxiliar = front;
		int tamanyo = 0;
		while (auxiliar != null) {
			tamanyo++;
			auxiliar = auxiliar.next;
		}
		return tamanyo;
	}


	@Override
	public String toStringReverse() {
		StringBuffer buffer = new StringBuffer();
		DoubleNode<T> auxiliar = last;

		if (isEmpty()) {
			buffer.append("()");
		} else {

			buffer.append("(");
			while (auxiliar != null) {

				buffer.append(auxiliar.elem + " ");
				auxiliar = auxiliar.prev;

			}
			buffer.append(")");
		}

		return buffer.toString();
	}


	@Override
	public int maxRepeated() {
		int contador = 0; 
		int maximo = 0;
		DoubleNode<T> auxiliar = front;
		while (auxiliar != null) {
			contador = this.numberElements(auxiliar.elem); 
			if (maximo <= contador) {
				maximo = contador;
			}
			auxiliar = auxiliar.next;
		}
		return maximo;
	}


	@Override
	public boolean sameContent(DoubleList<T> other) {
		if (other == null) 
			throw new NullPointerException();
		
		boolean result = true;
		int cont = 0;
		T aux = null;
		
		if(other.size() != this.size() || other.isEmpty()) {
			result = false;
		} else {
			for (int j = 1; j <= other.size(); j++) {
				aux = this.getElemPos(j);
				cont = this.numberElements(aux);
				for (int i = 1; i <= other.size(); i++) {
					if(other.getElemPos(i).equals(aux)) {
						cont--;
					}
				}
				if(cont != 0){
					result = false;
				}
			}
		}
		return result;
	}



	@Override
	public String toStringFromUntil(int from, int until) {
		StringBuffer buffer = new StringBuffer();
		
		if (from <= 0 || until <= 0 || until < from) {
			throw new IllegalArgumentException();
		}
		buffer.append("(");
		for (int i = from; i <= until; i++) {
			if(i > this.size()) {
				break;
			}
			buffer.append(this.getElemPos(i) + " ");
		}
		buffer.append(")");

		return buffer.toString();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		DoubleNode<T> auxiliar = front;

		if (isEmpty()) {
			buffer.append("()");
		} else {
			buffer.append("(");
			while (auxiliar != null) {
				buffer.append(auxiliar.elem + " ");
				auxiliar = auxiliar.next;
			}
			buffer.append(")");
		}
		return buffer.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new DoubleLinkedListIterator<>(this.front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DoubleLinkedListIteratorReverse<>(this.last);
	}
	

	@Override
	public Iterator<T> oddPositionsIterator() {
		return new DoubleLinkedListIteratorOddPositions<>(this.front);
	}


	@Override
	public Iterator<T> progressReverseIterator() {
		return new DoubleLinkedListIteratorProgressReverse<>(this.last);
	}

	@Override
	public String toStringFromUntilReverse(int from, int until) {
		StringBuffer buffer = new StringBuffer();
		
		if (from <= 0 || until <= 0 || from < until) {
			throw new IllegalArgumentException();
		}
		buffer.append("(");
		for (int i = until; i >= from; i++) {
			if(i > this.size()) {
				break;
			}
			buffer.append(this.getElemPos(i) + " ");
		}
		buffer.append(")");

		return buffer.toString();
	}
	
	private int numberElements(T elem) {
		int contador = 0;
		DoubleNode<T> auxiliar = front;
		while (auxiliar != null) {
			if (auxiliar.elem.equals(elem)) {
				contador++;
			}
			auxiliar = auxiliar.next;

		}
		return contador;
	}
}