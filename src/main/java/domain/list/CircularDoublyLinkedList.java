package domain.list;

import domain.Node;

public class CircularDoublyLinkedList implements List{
    private Node first; //apuntador al inicio de la lista
    private Node last; //apuntador al final de la lista

    public CircularDoublyLinkedList() {
        this.first = null; //la lista no existe
        this.last = null;
    }

    @Override
    public int size() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is empty");
        }
        Node aux = first;
        int count=0;
        while(aux!=last){
            count++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        return count+1; //+1 para que cuente el ultimo nodo
    }

    @Override
    public void clear() {
        this.first = this.last = null; //anulamos la lista
    }

    @Override
    public boolean isEmpty() {
        return this.first == null; //si es nulo está vacía
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is empty");
        }
        Node aux = first;
        while(aux!=last){
            if(util.Utility.compare(aux.data, element)==0){
                return true;
            }
            aux = aux.next; //lo movemos al sgte nodo
        }
        //se sale del while cuando aux == last
        //entonces solo nos queda verificar si el elemento a buscar esta en el
        //ultimo nodo
        return util.Utility.compare(aux.data, element)==0;
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first = last = newNode;
        }else{
            last.next = newNode;
            //ponemos last a apuntar al ult nodo
            last = newNode;
        }
        //hago el enlace Circular Doubly
        last.next = first;
        //hago el enlace doble
        first.prev = last;
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first = last = newNode;
        }else{
            newNode.next = first;
            //hago el enlace doble
            first.prev = newNode;
            first = newNode;
        }
        //garantizo siempre el enlace Circular Doubly
        last.next = first;
        first.prev = last;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);
        if(isEmpty() || util.Utility.compare(first.data, newNode.data)>0){
            newNode.next = first;
            first = newNode;
        }else{
            Node prev = first;
            Node actual = first.next;
            while (actual != null && util.Utility.compare(actual.data, newNode.data) > 0){
                prev = actual;
                actual = actual.next;
            }
            prev.next = newNode;
            newNode.next = actual;
        }
    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        //Caso 1. El elemento a suprimir esta al inicio
        if(util.Utility.compare(first.data, element)==0){
            first = first.next; //saltamos el primer nodo
        }else{  //Caso 2. El elemento a suprimir puede estar al medio o final
            Node prev = first; //dejo un apuntador al nodo anterior
            Node aux = first.next;
            while(aux!=last && !(util.Utility.compare(aux.data, element)==0)){
                prev = aux;
                aux = aux.next;
            }
            //se sale cuando aux == last o cuando encuentra el elemento
            if(util.Utility.compare(aux.data, element)==0){
                //ya lo encontro, procedo a desenlazar el nodo
                prev.next = aux.next;
                //mantengo el doble enlace
                aux.next.prev = prev;
            }
            //que pasa si el elemetno a suprimir esta en el ultimo nodo
            //es decir donde esta last
            if(aux == last && util.Utility.compare(aux.data, element)==0){
                last = prev; //desenlaza el ultimo nodo
            }
        }
        //mantengo el enlace Circular Doubly
        last.next = first;
        first.prev = last;

        //Otro caso:
        //Que pasa si solo queda un nodo y es el que quiero eliminar
        if(first == last && util.Utility.compare(first.data, element)==0){
            clear(); //anulo la lista
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        Object removeData = first.data;
        first = first.next;
        return removeData;
    }

    @Override
    public Object removeLast() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        Node aux = first;
        Node prev = null;
        while (aux.next != null){
            prev = aux;
            aux = aux.next;
        }
        Object removeData = aux.data;
        if(prev != null){
            prev.next = null;
        }else{
            first = null;
        }
        return removeData;
    }

    @Override
    public void sort() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        for (int i = 1; i <= size() ; i++) {
            for (int j = i+1; j <= size() ; j++) {
                Object obj = getNode(i).data;
                Object obj2 =  getNode(j).data;
                if(util.Utility.compare(obj2, obj) < 0){
                    Object aux = getNode(i).data;
                    getNode(i).data = getNode(j).data;
                    getNode(j).data = aux;
                }
            }
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        Node aux = first;
        int index=1; //la lista inicia en 1
        while(aux!=null){
            if(util.Utility.compare(aux.data, element)==0){
                return index;
            }
            index++; //incremento el indice
            aux=aux.next; //muevo aux al sgte nodo
        }
        //se sale cuando alcanza last
        if(util.Utility.compare(aux.data, element)==0){
            return index;
        }
        return -1; //indica q el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        return last.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        if(util.Utility.compare(first.data, element)==0){
            return "It's the first, it has no previous";
        }
        Node aux = first;
        //mientras no llegue al ult nodo
        while(aux.next!=last){
            if(util.Utility.compare(aux.next.data, element)==0){
                return aux.data; //retornamos la data del nodo actual
            }
            aux=aux.next;
        }
        //se sale cuando aux.next == last
        if(util.Utility.compare(aux.next.data, element)==0){
            return aux.data;
        }
        return "Does not exist in Single Linked List";
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        Node aux = first;
        while (aux != null && util.Utility.compare(aux.data, element) != 0) {
            aux = aux.next;
        }
        if (aux != null && aux.next != null) {
            return aux.next.data;
        } else if (aux != null && aux.next == null) {
            return "No next element found";
        } else {
            return "Element not found in the list";
        }
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is Empty");
        }
        Node aux = first;
        int i = 1; // pos del primer nodo
        while(aux!=last){
            if(util.Utility.compare(i, index)==0) {  //ya encontro el indice
                return aux;
            }
            i++; //incremento la var local
            aux = aux.next; //muevo aux al sgte nodo
        }
        //se sale cuando aux == last
        if(util.Utility.compare(i, index)==0) {  //ya encontro el indice
            return aux;
        }
        return null; //si llega aqui es xq no encontro el index
    }

    @Override
    public String toString() {
        String result = "Circular Doubly Linked List Content\n\n";
        Node aux = first;
        while(aux!=last){
            result+= aux.data + " ";
            aux = aux.next;
        }
        return result + "\n" + aux.data; //agrego la data del ult nodo
    }
}
