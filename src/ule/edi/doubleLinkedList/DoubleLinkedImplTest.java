package ule.edi.doubleLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedImplTest {
	DoubleLinkedListImpl<String> lv;
	DoubleLinkedListImpl<String> listaConElems;

	@Before
	public void antesDe() {
		lv=new DoubleLinkedListImpl<String>();
		listaConElems=new DoubleLinkedListImpl<String>();
		listaConElems.addFirst("D");
		listaConElems.addFirst("B");
		listaConElems.addFirst("A");
		listaConElems.addFirst("C");
		listaConElems.addFirst("B");
		listaConElems.addFirst("A");

	}

	@Test
	public void isEmptyTest() {
		Assert.assertTrue(lv.isEmpty());
		Assert.assertEquals(0,lv.size());
		Assert.assertFalse(listaConElems.isEmpty());
		Assert.assertEquals(6,listaConElems.size());

	}

	@Test
	public void clearTest() {
		lv.clear();
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);

		listaConElems.clear();
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertEquals(0,listaConElems.size());
	
	}

	@Test
	public void containsTest() {
		Assert.assertFalse(lv.contains("A"));
		Assert.assertTrue(listaConElems.contains("A"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("Z"));

	}

	

	
	@Test
	public void addFirstTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		Assert.assertTrue(lista.isEmpty());
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	
	@Test(expected=NullPointerException.class)
	public void addElementoNuloFirstTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst(null);
	}
	
	@Test
	public void addLastTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		Assert.assertTrue(lista.isEmpty());
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
	}
	
	
	
	@Test
	public void testClear() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.clear();
		Assert.assertTrue(lista.isEmpty());
		Assert.assertEquals("()", lista.toString());
	}
	
	
	@Test(expected=EmptyCollectionException.class)
	public void testRemoveLast() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		Assert.assertEquals("7", lista.removeLast());
		Assert.assertEquals("3", lista.removeLast());
		Assert.assertEquals("2", lista.removeLast());
		Assert.assertEquals("2", lista.removeLast());
	}
	
	@Test
	public void testaddPos() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addPos("2", 3);
		Assert.assertEquals("(2 3 2 7 )", lista.toString());
		lista.addPos("1", 1);
		Assert.assertEquals("(1 2 3 2 7 )", lista.toString());
		lista.addPos("5", 6);
		Assert.assertEquals("(1 2 3 2 7 5 )", lista.toString());
		lista.addPos("6", 10);
		Assert.assertEquals("(1 2 3 2 7 5 6 )", lista.toString());
	}
	

	@Test
	public void testaddBefore() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addBefore("1", "2");
		Assert.assertEquals("(1 2 3 7 )", lista.toString());
		lista.addBefore("2", "2");
		Assert.assertEquals("(1 2 2 3 7 )", lista.toString());
		lista.addBefore("2", "7");
		Assert.assertEquals("(1 2 2 3 2 7 )", lista.toString());
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testaddBeforeTargetNulo() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		lista.addBefore("3", null);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testaddBeforeInexistente() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		lista.addBefore("3", "4");
	}
	
	@Test
	public void testGetElemPos() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		lista.addBefore("1", "2");
		lista.addBefore("2", "2");
		lista.addBefore("2", "7");
		Assert.assertEquals("(1 2 2 3 2 7 )", lista.toString());
		Assert.assertEquals("2", lista.getElemPos(2));
		Assert.assertEquals("7", lista.getElemPos(6));
		Assert.assertEquals("1", lista.getElemPos(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetElemPosExceso() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("2", lista.getElemPos(20));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetElemPosDefecto() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("7");
		Assert.assertEquals("2", lista.getElemPos(0));
	}
	
	@Test
	public void testGetPosFirst() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		Assert.assertEquals("(2 )", lista.toString());	
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("1");
		Assert.assertEquals("(2 3 1 )", lista.toString());
		lista.addBefore("1", "2");
		Assert.assertEquals("(1 2 3 1 )", lista.toString());
		lista.addBefore("2", "2");
		Assert.assertEquals("(1 2 2 3 1 )", lista.toString());
		lista.addBefore("2", "1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals(2, lista.getPosFirst("1"));
		Assert.assertEquals(1, lista.getPosFirst("2"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetPosFirstNulo() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosFirst(null));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetPosFirstInexistente() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosFirst("R"));
	}
	
	@Test
	public void testGetPosLast() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		lista.addBefore("1", "2");
		lista.addBefore("2", "2");
		lista.addBefore("2", "1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals(6, lista.getPosLast("1"));
		Assert.assertEquals(4, lista.getPosLast("2"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetPosLastNulo() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosLast(null));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetPosLastInexistente() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.getPosLast("R"));
	}
	
	@Test
	public void testRemovePos() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		lista.addFirst("4");
		Assert.assertEquals("4", lista.removePos(1));
		Assert.assertEquals("(2 3 1 )", lista.toString());
		Assert.assertEquals("3", lista.removePos(2));
		Assert.assertEquals("(2 1 )", lista.toString());
		Assert.assertEquals("1", lista.removePos(2));
		Assert.assertEquals("(2 )", lista.toString());
		Assert.assertEquals("2", lista.removePos(1));
		Assert.assertEquals("()", lista.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemovePosDefecto() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.removePos(5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemovePosExceso() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(1, lista.removePos(-5));
	}
	
	@Test
	public void testRemoveN() throws EmptyCollectionException {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		lista.addBefore("1", "2");
		lista.addBefore("2", "2");
		lista.addBefore("2", "1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals(3, lista.removeN("2",5));
		Assert.assertEquals("(1 3 1 )", lista.toString());
		Assert.assertEquals(2, lista.removeN("1",3));
		Assert.assertEquals("(3 )", lista.toString());
		Assert.assertEquals(1, lista.removeN("3",1));
		Assert.assertEquals("()", lista.toString());
	}
	
	@Test
	public void testContains() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertTrue(lista.contains("1"));
		Assert.assertTrue(lista.contains("2"));
		Assert.assertFalse(lista.contains("4"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testContainsNulo() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		lista.contains(null);
	}
	
	@Test
	public void testSize() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		Assert.assertEquals(0, lista.size());
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals(3, lista.size());
	}

	

	@Test
	public void maxRepeatedTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		Assert.assertEquals("(2 3 1 )", lista.toString());
		
		lista.addBefore("1", "2");
		Assert.assertEquals("(1 2 3 1 )", lista.toString());
		
		lista.addBefore("2", "2");
		Assert.assertEquals("(1 2 2 3 1 )", lista.toString());
		lista.addBefore("2", "1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals("(1 3 2 2 1 2 )", lista.toStringReverse());
			
		Assert.assertEquals(3, lista.maxRepeated());
		lista.clear();
		Assert.assertEquals(0, lista.maxRepeated());
	}


	

	@Test
	public void toStringFromUntilTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		lista.addLast("1");
		lista.addBefore("1", "2");
		lista.addBefore("2", "2");
		lista.addBefore("2", "1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		Assert.assertEquals("(2 1 2 )", lista.toStringFromUntil(1, 3));
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toStringFromUntil(1, 6));
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toStringFromUntil(1, 10));
		Assert.assertEquals("(2 3 1 )", lista.toStringFromUntil(4, 7));
		Assert.assertEquals("()", lista.toStringFromUntil(8, 10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void toStringFromUntilFromNegativoTest() {
		listaConElems.toStringFromUntil(-3, 4);
	}
	
	
	@Test
	public void toStringTest() {
		Assert.assertEquals("()",  lv.toString());
	}
	
	
	@Test(expected=NoSuchElementException.class)
	public void oddPositionsIteratorFalloTest() {

		Iterator<String> iterator = lv.oddPositionsIterator();
		Assert.assertFalse(iterator.hasNext());
		iterator.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void progressIteratorTest() {
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista.addFirst("2");
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("1");
		Assert.assertEquals("(2 3 1 )", lista.toString());
			
		lista.addBefore("1", "2");
		Assert.assertEquals("(1 2 3 1 )", lista.toString());
		
		lista.addBefore("2", "2");
		Assert.assertEquals("(1 2 2 3 1 )", lista.toString());
		
		lista.addBefore("2", "1");
		Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
		
		lista.addLast("4");
		
		lista.addLast("6");
		Assert.assertEquals("(2 1 2 2 3 1 4 6 )", lista.toString());
		Iterator<String> iterator = lista.progressReverseIterator();
		StringBuffer nuevo = new StringBuffer("(");
		while(iterator.hasNext()) {
			nuevo.append(iterator.next()+ " ");
		}
		nuevo.append(")");
		Assert.assertEquals("(6 4 3 1 )", nuevo.toString());
		Assert.assertFalse(iterator.hasNext());
		iterator.next();
	}
	
	

}