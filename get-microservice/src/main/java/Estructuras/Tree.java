/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

public class Tree <T extends Comparable<T>> {
    nodo_binario root; // todavia no le doy uso, pero pronto.
    
    Tree(){// no usamos el constructor todavia, pero pronto
        
    }
    
    //---------------------------------------------------------------------------------------------------------    
    // ***************************** OJO ESTE ARBOL NO ESTÁ BALANCEADO **************************************
    // para dar uso del balanceo toca usar el método rotación() para equilibrarlo cada vez que sucede un insert.
    // También se puede añadir los datos y luego usar rotación() para convertirlo en uno balanceado.
    //---------------------------------------------------------------------------------------------------------
    
    public nodo_binario insert(nodo_binario root, T data){ 
        if(root == null){            
            return new nodo_binario(data);  // inicializo la raiz (o el nodo_binario nulo) para insertarle el dato en esa posicion            
        }else{
            if(0<= root.dato.compareTo(data)){ // Usamos el compareTo (que es funcion por defecto de los genéricos T) 
                                                  // para poder comparar si es menor o igual a data (retorna un entero dependiendo de si es menor(-), igual(=) o mayor(+))
                root.left = insert(root.left,data); // le digo que revise el nodo_binario de la izquierda como si fuese tambien un árbol hasta encontrar un nodo_binario nulo donde vaya el dato
                
            }else{
                root.right = insert(root.right,data);
            }
        return root; // devuelvo la misma raiz para no afectar el árbol y devolverme.
        }
    }
    
    public boolean search(nodo_binario root, T data){
        if(root == null){
            return false;
        }else{
            nodo_binario buscado = root;
            int dato_b; // guarda el resultado de compareTo();
            while(buscado!=null){   //Se termina si llega al final del árbol
                dato_b = buscado.dato.compareTo(data);
                if(0==dato_b){
                    return true;
                }else if(0<dato_b){
                    buscado = buscado.right;
                }else{
                    buscado = buscado.left;
                }
            }
            return false;
        }
    }
    
    public int equilibrio(nodo_binario root){
        if(root == null){   // por si la raiz está vacia
            return 0;
        }
        return getHeight(root.right) - getHeight(root.left);
    }
    
    public nodo_binario rotacion(nodo_binario root){    // nos ayuda a identificar si es necesario hacer una rotación simple o doble.
        if(root == null){
            return root;
        }
        int balance = equilibrio(root);
        if(balance == 0 || balance == -1 || balance == 1){
            return root; // porque ya está equilibrado
        }else{
            if(balance < -1){    // osea que está desequilibrado a la izquierda
                int balance_siguiente = equilibrio(root.left);
                if(balance_siguiente <= 0){ // significa balance simple, el árbol izquierda tambien se desbalancea la izquierda
                    root = rotacionIzquierda(root);                    
                }else{ //balance doble, balanceamos el nodo izquierdo por el lado de la derecha
                    root.left = rotacionDerecha(root.left);
                    root = rotacionIzquierda(root);
                }              
            }else{  //desequilibrado a la derecha
                int balance_siguiente = equilibrio(root.right);
                if(balance_siguiente >= 0){ // significa balance simple, el árbol derecho tambien se desbalancea la derecha
                    root = rotacionDerecha(root);                    
                }else{ //balance doble, balanceamos el nodo derecho por el lado de la izquierda
                    root.right = rotacionIzquierda(root.right);
                    root = rotacionDerecha(root);
                }
                
            }
        return rotacion(root); 
        }
    }
    
    public nodo_binario rotacionDerecha(nodo_binario root){
        nodo_binario caja = root.right;
        root.right = caja.left;
        caja.left = root;
        return caja;
    }
    
    public nodo_binario rotacionIzquierda(nodo_binario root){
        nodo_binario caja = root.left;
        root.left = caja.right;
        caja.right = root;
        return caja;
    }
    
    public int getHeight(nodo_binario root){
        if(root == null){   //caso de cuando la raiz está vacia o si llegamos iterativamente al final del árbol
            return 0;
        }
        if(root.right == null && root.left == null){
            return 0;
        }else{
            if(root.right == null){
                return 1+this.getHeight(root.left);
            }else{
                if(root.left == null){
                    return 1+this.getHeight(root.right);
                }else{
                    int lft = this.getHeight(root.left);
                    int rgt = this.getHeight(root.right);
                    if(lft > rgt){
                        return 1+lft;
                    }else{
                        return 1+rgt;
                    }
                }
            }
        }
    }
    
    // ******************* EL display() level-order NO TA FUNCIONANDO OJO ************************
    // MOTIVO: parece que nuestra pila noa cepta tipos nodo_binario y no puede "ser casteado" a Comparable
    public void display(nodo_binario root){ // Imprimimos todo por orden de nivel
        Cola queu = new Cola();
        if(root != null){
            queu.enqueue(root);
            nodo_binario tree ;
            while(!queu.isEmpty()){ //Mientras NO esté vacio (por eso el "!")
                tree = queu.dequeue();
                System.out.print(tree.dato);
                
                if(tree.left!=null){
                    queu.enqueue(tree.left);
                }
                if(tree.right!=null){
                    queu.enqueue(tree.right);
                }
            }
        }
    }
    
    public void display2(nodo_binario root){    // imprimir en pre order
        
        if(root != null){   // por si envian un árobl null
            System.out.print(root.dato);
            if(root.left != null){
                display2(root.left);
            }
            if(root.right != null){
                display2(root.right);
            }
        }
    }

    
    public void display3(nodo_binario root){ // PostOrden
        if(root!= null){
            if(root.left!=null){
                display3(root.left);
            }
            if(root.right!=null){
                display3(root.right);
            }            
            System.out.print(root.dato);
        }
    }
    
    public void display4(nodo_binario root){    //Inorder
        if(root!= null){
            if(root.left!=null){
                display4(root.left);
            }
            System.out.print(root.dato);
            if(root.right!=null){
                display4(root.right);
            }
        }
    }
    
}
