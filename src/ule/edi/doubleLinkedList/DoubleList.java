package ule.edi.doubleLinkedList;


import java.util.Iterator;

import ule.edi.exceptions.EmptyCollectionException;

	
	public interface DoubleList<T> extends Iterable<T>{
		/**
		 * TAD 'DoubleList'
		 * 
		 * Almacena una colección de objetos de tipo  T  , permitiendo elementos repetidos.  
		 * 
		 * Ejemplo: (A B C A B D )
		 *
		 * 
		 * Excepciones
		 * 
		 * No se permiten elementos  null  . Si a cualquier método que recibe un elemento se le pasa el 
		 * valor  null  , lanzará una excepción  NullPointerException.
		 * 
		 * Los valores de parámetros  position   deben ser mayores que cero y nunca negativos. Si se 
		 * recibe un valor negativo o cero se lanzará  IllegalArgumentException.
		 * 
		 * 
		 * Constructores
		 * 
		 * Se definirá un constructor por defecto que inicialice la instancia
		 * como lista vacía.
		 * 
		 *  
		 * Método {@link Object#toString()}
		 * 
		 * El formato será mostrar el toString de los elementos separados por espacios (A B C D D D B )  
		 *
		 * 
		 * @author profesor
		 *
		 * @param <T> tipo de elementos en la lista
		 */
		
		
		/**
		 * Indica si esta lista está vacía
		 * 
		 * @return  true   si no contiene elementos
		 */
		public boolean isEmpty();
		
		/**
		 * Elimina todo el contenido de esta lista.
		 * Deja la lista vacía
		 * 
		 */
		public void clear();
		
		/**
		 * Añade un elemento como primer elemento de la lista.
		 * Si una lista l contiene (A B C )  y hacemos l.addFirst("C") 
		 * la lista quedará (C A B C )
		 * 
		 * @param elem el elemento a añadir
		 * 
		 * @throws NullPointerException si elem es null
		 */
		public void addFirst(T elem);
		
		/**
		 * Añade un elemento como último elemento de la lista
		 *  
		 * Si una lista l contiene (A B C )  y hacemos l.addLast("C") 
		 * la lista quedará (A B C C )
		 * 
		 * @param elem el elemento a añadir
		 * 
		 * @throws NullPointerException si elem es  null  
		 */
		public void addLast(T elem);
		

		/**
		 * Añade un elemento en la posición pasada como parámetro desplazando los elementos que estén a partir de esa posición.
		 *  
		 * Si una lista l contiene (A B C )  y hacemos l.addPos("C", 2) 
		 * la lista quedará (A C B C ).
		 *  
		 * Si position>size() se insertará como último elemento.
		 * 
		 * @param elem el elemento a añadir
		 * @param position la posición en la que añadirá el elemento
		 * 
		 * @throws NullPointerException si elem es  null  
		 * @throws IllegalArgumentException si position <= 0
		 * 
		 */
		public void addPos(T elem, int position);
		
		/**
		 * Añade un elemento delante de la primera aparición del elemento pasado como 2º parámetro desplazando los elementos que estén a partir de ese elemento.
		 *  
		 * Si una lista l contiene (A B C B )   
		 *  l.insertPos("D", "B") ;   la lista quedará (A D B C B ).
		 * 
		 * 
		 * @param elem el elemento a añadir
		 * @param target el elemento delante del cual insertará elem
		 * 
		 * @throws NullPointerException  si elem o target son  null  
		 * @throws NoSuchElementException  si target no está en la lista
		 * 
		 */
		public void addBefore(T elem, T target);
		
		/**
		 * Elimina y devuelve el segundo elemento de la lista.
		 *  
		 * Si una lista l contiene (A B C )  y hacemos l.removeSecond() 
		 * la lista quedará (A C ) y devolverá B
		 * 
		 * 
	     * @throws EmptyCollectionException si la lista es vacía 
		 * @throws NoSuchElementException  si la lista tiene un solo elemento
		 * 
		 */
		public T removeSecond() throws EmptyCollectionException;
		
		/**
		 * Elimina y devuelve el último elemento de la lista.
		 *
		 * Si una lista l contiene (A B C )  y hacemos l.removeLast() 
		 * la lista quedará (A B ) y devolverá C
		 * 
	     * @throws EmptyCollectionException si la lista es vacía 
		 * 
		 */
		public T removeLast() throws EmptyCollectionException;;

		/**
		 * Elimina y devuelve el elemento que está en la posición position de la lista.
		 *  
		 * Si una lista l contiene (A B C )  y hacemos l.removePos(2) 
		 * la lista quedará (A C ) y devolverá B
		 * 
         * Si una lista l contiene (A B C B D A ): 
		 * l.removePos(1) -> "A", dejando la lista (B C B D A ): 
		 * l.removePos(4) -> "D", dejando la lista (A B B A ): 
		 * l.removePos(10) -> IllegalArgumentException
		 *
	     * @param position posición a comprobar para devolver y eliminar el elemento 
		 * 
		 * @throws IllegalArgumentException si position no está entre 1 y size() 
		 * @throws EmptyCollectionException si la lista es vacía 
		 * 
		 */
		public T removePos(int position) throws EmptyCollectionException;

		/**
		 * Intenta eliminar num apariciones del elemento y devuelve el número de instancias eliminadas.
		 *  
		 * Si una lista l contiene (A B C B D A B ):  
		 * a) l.removeN("A",3) -> 2, dejando la lista (B C B D B ):  
		 * b) l.remove_N("B",2) -> 2, dejando la lista (A C D A B ):  
	 	 * c) l.removeN("Z",1) -> NoSuchElementException 
	 	 * 
	     * @param elem elemento a eliminar.
	     * @param times es el número de elementos a eliminar.
	     * 
		 * @throws NullPointerException si elem es  null  
		 * @throws IllegalArgumentException si times<1.
		 * @throws EmptyCollectionException si la lista es vacía 
		 * @throws NoSuchElementException si elem no está en la lista.	 * 
		 * 
		 */
		public int removeN(T elem, int times)throws EmptyCollectionException;;
	
	
		/**
		 * Devuelve el elemento que está en position.
		 *  
		 * Si una lista l contiene (A B C D E ):  
		 * l.getElemPos(1) -> A  
		 * l.getElemPos(3) -> C  
		 * l.getElemPos(10) -> IllegalArgumentException
		 * 
		 * 
		 * @param position posición a comprobar para devolver el elemento 
		 * 
		 * @throws IllegalArgumentException si position no está entre 1 y size() 
		 * 
		 */
		public T getElemPos(int position);
		
		/**
		 * Devuelve la posición de la primera aparición del elemento.
		 *  
		 * Si una lista l contiene (A B C B D A ):  
		 * l.getPosFirst("A") -> 1  
		 * l.getPosFirst("B") -> 2  
		 * l.getPosFirst("Z") -> NoSuchElementException
		 * 
		 * @param elem elemento a encontrar.
		 *
		 * @throws NullPointerException si elem es  null  
		 * @throws NoSuchElementException si elem no está en la lista.
		 * 
		 */
		public int getPosFirst(T elem);
		
		/**
		 * Devuelve la posición de la última aparición del elemento.
		 *  
		 * Si una lista l contiene (A B C B D A ):  
		 * l.getPosFirst("A") -> 6  
		 * l.getPosFirst("B") -> 4  
		 * l.getPosFirst("Z") -> NoSuchElementException
		 * 
		 * @param elem elemento a encontrar.
		 * 
		 * @throws NullPointerException si elem es  null  
		 * @throws NoSuchElementException si elem no está en la lista.
		 * 
		 */
		public int getPosLast(T elem);
		
			
			
		/**
		 * Indica si el elemento está en esta lista.  
		 * 
		 * Devuelve  true   si al menos existe una
		 * instancia del elemento dado en esta lista (es decir,
		 * un elemento 'x' tal que  x.equals(element)  )
		 * y  false   en caso contrario.
		 * 
		 * @param elem elemento a buscar en esta lista
		 * @return  true  / false   según el resultado
		 * 
		 * @throws NullPointerException el elemento indicado es  null  
		 */
		public boolean contains(T elem);
		
		/**
		 * Devuelve el número total de elementos en esta lista.  
		 * 
		 * Ejemplo: 
	     * Si una lista l contiene (A B C B D A B ):  
		 *  l.size() -> 7
		 *   
		 * @return número total de elementos en esta lista
		 */
		public int size();
		
		/**
		 * Crea y devuelve un String con el contenido de la lista empezando por el final hasta el principio.
		 * 
		 *   Si esta lista es vacía devuelve el toString() de la lista vacía -> ().  
		 * 
		 *   Ejemplo: Si una lista l contiene (A B C ):  
		 *  l.toStringReverse() -> (C B A )
		 *   
		 * @return recorrido inverso de la lista (desde el final al principio)
		 */
		public String toStringReverse();
		
		
		/**
		 * Crea una nueva lista copia de esta lista.  
		 * Si esta lista es vacía devuelve la lista vacía.  
		 * OJO: Lista y copia son dos objetos distintos que apuntan a nodos distintos pero con el mismo contenido
		 * Ejemplo: 
		 * Si una lista l contiene (A B C ):  
		 *  l.copy().toString() -> (A B C ) 
		 * @return lista igual a esta lista 
		 */
		public DoubleList<T> copy();
		
		/**
		 * Devuelve el número de veces que se repite el elemento con máximo número de repeticiones.  
		 * Ejemplo: 
		 * Si una lista l contiene (A B C A A C A):  
		 *  l.maxRepeated() -> 4   // el elemento que más se repite es A, y lo hace 4 veces
		 * @return nº de repeticiones del elemento que más se repite 
		 */
		public int maxRepeated();
		
		/**
		 * Indica si esta lista es igual a la pasada como parámetro (tienen los mismos elementos sin importar el orden).  
		 * 
		 * Ejemplos: 
		 * l1=(A B C ) ; l2=(B C A )  : l1.sameContent(l2) -> true  
		 * l1=(A B C ) ; l2=(A B C )  : l1.sameContent(l2) -> true  
		 * l1=(A B B C ) ; l2=(A B C )  : l1.sameContent(l2) -> false  
		 * 
		 * @param other lista a comprobar si tiene el mismo contenido que esta lista
		 * 
		 * @return true si tienen los mismos elementos, false en caso contrario
		 * @throws NullPointerException si other es null
		 */
		public boolean sameContent(DoubleList<T> other);
		
		/**
		 * Devuelve el toString de la sublista formada por los elementos situados entre las posiciones from hasta until incluidas. 
		 * 
		 *   Si until > size() se muestra hasta el final de la lista.
		 *   Si from > size() se muestra () (toString de la lista vacía)
		 * 
		 *   Ejemplos: 
		 * l1=(A B C D E ) ; l1.toSringFromUntil(1,3)  -> (A B C )  
		 * l1=(A B C D E ) ; l1.toSringFromUntil(3,10)  -> (C D E )  
		 * l1=(A B C D E ) ; l1.toStringFromUntil(10,20) -> ()  
		 * 
		 * @param from posición desde la que se empieza a considerar la lista (incluida)
		 * @param until posición hasta la que se incluyen elementos (incluida)
		 *  
		 * @return String de la sublista formada por los elementos en el rango establecido por los dos parámetros.
		 *
		 *@throws IllegalArgumentException si from o until son <=0 ; o si until < from
		 *
		 */
		public String toStringFromUntil(int from, int until);
	    
		
		/**
		 * Devuelve el toString de la sublista formada por los elementos situados entre las posiciones from hasta until incluidas en el sentido inverso. 
		 * 
		 *  Si from > size() se muestra desde el último de la lista
		 *  Si from <1 se muestra el toString de la lista vacía
		 * 
		 *   Ejemplos: 
		 * l1=(A B C D E ) ; l1.toSringFromUntilReverse(3,1)  -> (C B A )  
		 * l1=(A B C D E ) ; l1.toSringFromUntilReverse(10,3)  -> (E D C )  
		 * l1=(A B C D E ) ;l1.toStringFromUntilReverse(20,5) -> (E )  
		 * 
		 * @param from posición desde la que se empieza a considerar la lista (incluida)
		 * @param until posición hasta la que se incluyen elementos (incluida)
		 *  
		 * @return String de la sublista formada por los elementos en el rango establecido por los dos parámetros.
		 *
		 *@throws IllegalArgumentException si from o until son <=0 ; o si from < until
		 *
		 */
		public String toStringFromUntilReverse(int from, int until);
		
		/**
		* Devuelve un iterador que recorre la lista en orden inverso.  
		*
		* Por ejemplo, para una lista x con elementos (A B C D E)
		*
		* el iterador creado con x.reverseIterator() devuelve en sucesivas llamadas a next(): E, D, C, B y A.
		*
		* @return iterador para orden inverso.
		*/
		public Iterator<T> reverseIterator();
		
		/**
		* Devuelve un iterador que recorre los elementos con posición impar de la lista.  
		*
		* Por ejemplo, para una lista x con elementos (A B C D E )
		*
		* el iterador creado con x.oddPositionIterator() devuelve en sucesivas llamadas a next(): A, C y E.
		*
		* @return iterador para recorrer elementos en posiciones impares.
		*/
	    public Iterator<T> oddPositionsIterator();
		
	    /**
		* Devuelve un iterador que recorre los elementos saltando progresivamente i posiciones de la lista.
		*
		*   Es decir, en cada llamada a next() se salta i posiciones, la i empieza en 1 y va incrementandose en cada llamada a next.
		* 
		* Por ejemplo, para una lista x con elementos (1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 )
		* 
		* el iterador creado con x.progressReverseIterator() devuelve en sucesivas llamadas a next(): 19, 18, 16, 13, 9 y 4.
		*
		* @return iterador para recorrer ciertos elementos de la lista como se indica anteriormente.
		*/
	    public Iterator<T> progressReverseIterator();	
	}
	