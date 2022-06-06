package com.mycompany.coneccionmongo;

import com.mongodb.client.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mycompany.linearDataStructures.ALCircular;
import com.mycompany.linearDataStructures.ArrayList;
import com.mycompany.linearDataStructures.Cola;
import com.mycompany.linearDataStructures.Pilas;
import com.mycompany.linearDataStructures.Tree;
import com.mycompany.linearDataStructures.linkedList;
import com.mycompany.linearDataStructures.nodo_binario;
import com.mycompany.linearDataStructures.nodo;



import com.mongodb.client.FindIterable;
import java.util.Iterator;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.model.Filters.*;


public class Main {
    public static void main( String[] args ) {

        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {//busque en la url si hay cliente mongo

            MongoDatabase database = mongoClient.getDatabase("proyecto");//traiga toda la base de datos proyecto
            MongoCollection<Document> collection = database.getCollection("almacenamiento");//toda la coleccion "almacenamiento", estocomo archivo bson
                                                          
            Bson filter = eq("capacidad", 838);//solo los que tengan x, no usado por ahora
            //MongoCursor<Document> cursor = collection.find(filter).iterator();
            MongoCursor<Document> cursor = collection.find().iterator(); //itere sobre los datos de mi coleccion

            nodo_binario cabezaTree =  ArbolImplementar(database);
            System.out.println(cabezaTree.dato);
            //pruebas de velocidad, crear, buscar, eliminar
            //ALCircularImplementar(database);
            //ArrayListImplementar(database);
            //ColaImplementar(database);
            //PilasImplementar(database);
            //LinkedListImplementar(database);

            
/*            
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().get("nombre")); //aca lo meto a las estructuras
                }
            } finally {
                cursor.close();
            }
*/

    }
}
    
    public static nodo_binario ArbolImplementar(MongoDatabase database){
        Tree myTree = new Tree();
        nodo_binario root = null;
        Comparable data;
        MongoCollection<Document> collection = database.getCollection("almacenamiento");
        MongoCursor<Document> cursor = collection.find().iterator();
   
        long beginRead = System.currentTimeMillis();
         try {
                while (cursor.hasNext()) {
        root = myTree.insert(root,(Comparable) cursor.next().get("velLectura"));
            }
        }
         catch(Error E){
         System.out.println(E);
         }
         finally {
            cursor.close();
        }
        long endRead = System.currentTimeMillis();
        System.out.println("tiempo usado en la escritura con ARBOL BINARIO con "+10000+" datos: "+ (endRead-beginRead));
        return root;
        
        
    }
    
    public static void ALCircularImplementar(MongoDatabase database){
        //no identifica bien si no le traigo toda la base de datos, mejor hacerlo
    MongoCollection<Document> collection = database.getCollection("almacenamiento");
    MongoCursor<Document> cursor = collection.find().iterator();
    ALCircular alCircular = new ALCircular();//implenento la estructura

    long cantidadDatos = 100000;//cuantos datos quiero ver, tambien lo puedo hacer con un while(cursor.next!=null), para pruebas es mas facil asi

    long beginRead = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        alCircular.add((Comparable) cursor.next().get("velLectura")); 
    }
    long endRead = System.currentTimeMillis();
    System.out.println("tiempo usado en la escritura de ALCIRCULAR para "+cantidadDatos+" datos: "+ (endRead-beginRead));
            
    long beginSearch = System.currentTimeMillis();
    for(int j=0;j<cantidadDatos;j++){
        alCircular.search(j); 
    }
    long endSearch = System.currentTimeMillis();
    System.out.println("tiempo usado en la busqueda de ALCIRCULAR para "+cantidadDatos+" datos: "+ (endSearch-beginSearch));
   
    return;
    }
    
    public static void ArrayListImplementar(MongoDatabase database){
        
    MongoCollection<Document> collection = database.getCollection("almacenamiento");
    MongoCursor<Document> cursor = collection.find().iterator();
    MongoCursor<Document> respaldo = collection.find().iterator();
    ArrayList arraylist = new ArrayList();

    long cantidadDatos = 100000;

    long beginRead = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        arraylist.push((Comparable) cursor.next().get("velLectura")); 
    }
    System.out.println(respaldo.next().get("velLectura"));
    long endRead = System.currentTimeMillis();
    System.out.println("tiempo usado en la escritura de ARRAYLIST para "+cantidadDatos+" datos: "+ (endRead-beginRead));
            
    long beginSearch = System.currentTimeMillis();
    for(int j=0;j<cantidadDatos;j++){
        arraylist.Search(j); 
    }
    long endSearch = System.currentTimeMillis();
    System.out.println("tiempo usado en la busqueda de ARRAYLIST para "+cantidadDatos+" datos: "+ (endSearch-beginSearch));
    
    long beginDelete = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        arraylist.pop(); 
    }
    long endDelete= System.currentTimeMillis();
    System.out.println("tiempo usado en la elmininacion de ARRAYLIST para "+cantidadDatos+" datos: "+ (endDelete-beginDelete));
    
    return;
    }
    
    public static void ColaImplementar(MongoDatabase database){
        
    MongoCollection<Document> collection = database.getCollection("almacenamiento");
    MongoCursor<Document> cursor = collection.find().iterator();
    Cola cola = new Cola();

    long cantidadDatos = 100000;

    long beginRead = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        cola.enqueue((Comparable) cursor.next().get("velLectura")); 
    }
    long endRead = System.currentTimeMillis();
    System.out.println("tiempo usado en la escritura de COLA para "+cantidadDatos+" datos: "+ (endRead-beginRead));

    long beginSearch = System.currentTimeMillis();
    for(int j=0;j<cantidadDatos;j++){
        cola.search(j); 
    }
    long endSearch = System.currentTimeMillis();
    System.out.println("tiempo usado en la busqueda de COLA para "+cantidadDatos+" datos: "+ (endSearch-beginSearch));
    
    long beginDelete = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        cola.dequeue(); 
    }
    long endDelete= System.currentTimeMillis();
    System.out.println("tiempo usado en la elmininacion de COLA para "+cantidadDatos+" datos: "+ (endDelete-beginDelete));
    
    return;
    }
    
    
    public static void PilasImplementar(MongoDatabase database){
        
    MongoCollection<Document> collection = database.getCollection("almacenamiento");
    MongoCursor<Document> cursor = collection.find().iterator();
    Pilas pila = new Pilas();

    long cantidadDatos = 100000;

    long beginRead = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        pila.insert((Comparable) cursor.next().get("velLectura")); 
    }
    long endRead = System.currentTimeMillis();
    System.out.println("tiempo usado en la escritura de PILA para "+cantidadDatos+" datos: "+ (endRead-beginRead));
            
    long beginSearch = System.currentTimeMillis();
    for(int j=0;j<cantidadDatos;j++){
        pila.Search(j); 
    }
    long endSearch = System.currentTimeMillis();
    System.out.println("tiempo usado en la busqueda de PILA para "+cantidadDatos+" datos: "+ (endSearch-beginSearch));
    
    long beginDelete = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        pila.remove(); 
    }
    long endDelete= System.currentTimeMillis();
    System.out.println("tiempo usado en la elmininacion de PILA para "+cantidadDatos+" datos: "+ (endDelete-beginDelete));
    
    return;
    }
 
    public static void LinkedListImplementar(MongoDatabase database){
        
    MongoCollection<Document> collection = database.getCollection("almacenamiento");
    MongoCursor<Document> cursor = collection.find().iterator();
    linkedList listaEnlazada = new linkedList();

    long cantidadDatos = 100000;

    long beginRead = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        listaEnlazada.add((Comparable) cursor.next().get("velLectura")); 
    }
    long endRead = System.currentTimeMillis();
    System.out.println("tiempo usado en la escritura de LINKEDLIST para "+cantidadDatos+" datos: "+ (endRead-beginRead));
            
    long beginDelete = System.currentTimeMillis();
    for(int i=0;i<cantidadDatos;i++){
        listaEnlazada.popFront(); 
    }
    long endDelete= System.currentTimeMillis();
    System.out.println("tiempo usado en la elmininacion de LINKEDLIST para "+cantidadDatos+" datos: "+ (endDelete-beginDelete));
    
    return;
    }
    
}

/*    
    FindIterable<Document> iterDoc = collection.find();
    Iterator it = iterDoc.iterator();//para que itere por cada archivo
    //while (it.hasNext()) {
    for (int i=0;i<25;i++) {
            System.out.println(it.next().getClass().getSimpleName());
            System.out.println(it.next());
        }
*/