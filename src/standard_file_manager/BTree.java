/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard_file_manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author cafor
 */
public class BTree implements Serializable{
    private BTreeNode root;
    private ArrayList<BTreeNode> nodes = new ArrayList();
    private int nodeSize;

    public BTree() {
    }

    public BTree(BTreeNode root, int nodeSize) {
        this.root = root;
        this.nodeSize = nodeSize;
    }

    public BTreeNode getRoot() {
        return root;
    }

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    public ArrayList<BTreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<BTreeNode> nodes) {
        this.nodes = nodes;
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public void setNodeSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }
    
    public int getKey(String info){
        String[] index = info.split(";");
        return Integer.parseInt(index[0]);
    }
    
    public BTreeNode search(BTreeNode node, String key){
        BTreeNode temp = node;
        int i = 0;
        while((i < temp.getKeys().size()) && (getKey(key) > getKey(temp.getKeys().get(i)))){
            i++;
        }
        if((i < temp.getKeys().size()) && (getKey(key) == getKey(temp.getKeys().get(i))))
            return temp;
        if(temp.isIsLeaf()){
            return null;
        }else
            return search(temp.getChildren().get(i),key);
    }
    
    public void insert(String key){
        BTreeNode temp = root;
        if(temp.getKeys().size() == nodeSize - 1){
            BTreeNode nNode = new BTreeNode(null, false);
            root = nNode;
            root.getChildren().add(temp);
            temp.setParent(root);
            splitChild(root, temp, 0);
            temp = root;
            insertNonFull(temp, key);
        }else
            insertNonFull(temp, key);
    }
    
    public void insertNonFull(BTreeNode node, String key){
        int i = node.getKeys().size()-1;
        if(node.isIsLeaf()){
            while((i >= 0) && (getKey(key) < getKey(node.getKeys().get(i)))){
//                if(i+1 != node.getKeys().size())
//                    node.getKeys().add(i+1, node.getKeys().get(i));
                i--;
            }
            //System.out.println(key +" "+ node.getKeys());
            node.getKeys().add(i+1, key);
        }else{
            while((i >= 0) && (getKey(key) < getKey(node.getKeys().get(i)))){
                //System.out.println(key + " " + node.getKeys().get(i));
                i--;
            }
            i++;
            if(node.getChildren().get(i).getKeys().size() == nodeSize-1){
                splitChild(node, node.getChildren().get(i), i);
                if(getKey(key) > getKey(node.getKeys().get(i)))
                    i++;
            }
            //System.out.println(key + " " + node.getChildren().get(i).getKeys());
            insertNonFull(node.getChildren().get(i), key);       
        }
    }
    
    public void splitChild(BTreeNode nonFullNode, BTreeNode fullNode, int i){
        BTreeNode nNode = new BTreeNode(nonFullNode, fullNode.isIsLeaf());
        //Creando right
        //System.out.println(fullNode.getKeys());
        for(int j = fullNode.getKeys().size()-1;j >= ((nodeSize-1)/2);j--){
            //System.out.println(fullNode.getKeys().get(j));
            nNode.getKeys().add(0, fullNode.getKeys().get(j));
            fullNode.removeKey(j);
        }
        //Si right es nodo intermedio pasando los hijos de las llaves correspondientes
        if(!fullNode.isIsLeaf()){
            for(int j = 0;j <= (nodeSize-1)/2;j++){
                System.out.println(fullNode.getChildren().get(((nodeSize-1)/2)).getKeys());
                nNode.getChildren().add(fullNode.getChildren().get(((nodeSize-1)/2)+1));
                fullNode.getChildren().remove(((nodeSize-1)/2)+1);
            }
        }
        nonFullNode.getChildren().add(i+1, nNode);
        nonFullNode.getKeys().add(i, nNode.getKeys().get(0));
        nNode.removeKey(0);
        //System.out.println(nonFullNode.getKeys() + " " + fullNode.getKeys() + " " + nNode.getKeys());
    }
    
    public void moveKey(String key, BTreeNode oNode, BTreeNode nNode){
        if(nNode.getKeys().isEmpty())
            nNode.getKeys().add(key);
        else
            nNode.getKeys().add(0, key);
        if(nNode.getChildren().isEmpty())
            nNode.getChildren().add(oNode.getChildren().get(oNode.getKeys().indexOf(key)));
        else
            nNode.getChildren().add(0, oNode.getChildren().get(oNode.getKeys().indexOf(key)));
        oNode.getKeys().remove(oNode.getKeys().indexOf(key));
        oNode.getChildren().remove(oNode.getKeys().indexOf(key));
    }
    
    public void mergeNodes(BTreeNode y, BTreeNode z){
        BTreeNode nNode, oNode;
        if(y.getParent().getChildren().indexOf(y) < y.getParent().getChildren().indexOf(z)){
            nNode = y;
            oNode = z;
        }else{
            nNode = z;
            oNode = y;
        }
        nNode.getKeys().add(nNode.getParent().getKeys().get(nNode.getParent().getChildren().indexOf(nNode)));
        nNode.getParent().getKeys().remove(nNode.getParent().getChildren().indexOf(nNode));
        for(int i = 0;i < oNode.getKeys().size();i++){
            nNode.getKeys().add(oNode.getKeys().get(i));
        }
        for(int i = 0;i < oNode.getChildren().size();i++){
            nNode.getChildren().add(oNode.getChildren().get(i));
        }
        nNode.getParent().getChildren().remove(oNode);
    }
    
    public BTreeNode precedingChild(BTreeNode node){
        if(node.getParent().getChildren().indexOf(node) != 0){
            return node.getParent().getChildren().get(node.getParent().getChildren().indexOf(node)-1);
        }else
            return null;
    }
    
    public BTreeNode succesorChild(BTreeNode node){
        if(node.getParent().getChildren().indexOf(node) != node.getParent().getChildren().size()-1)
            return node.getParent().getChildren().get(node.getParent().getChildren().indexOf(node)+1);
        else
            return null;
    }
    
    public String findPredecesorKey(BTreeNode node, String key){
        if(node.getParent().getChildren().indexOf(node) != 0)
            return node.getParent().getChildren().get(node.getParent().getChildren().indexOf(node)-1).getKeys().get(node.getParent().getChildren().get(node.getParent().getChildren().indexOf(node)-1).getKeys().size()-1);
        else
            return null;
    }
    
    public String findSuccesorKey(BTreeNode node, String key){
        if(node.getParent().getChildren().indexOf(node) != node.getParent().getChildren().size()-1)
            return node.getParent().getChildren().get(node.getParent().getChildren().indexOf(node)+1).getKeys().get(0);
        else
            return null;
    }
    
    public void removeKey(BTreeNode node, String key){
        node.getKeys().remove(key);
    }
    
    public BTreeNode findSibling(BTreeNode node){
        BTreeNode x, y;
        x = precedingChild(node);
        y = succesorChild(node);
        if(x != null && y != null){
            if(x.getKeys().size() > y.getKeys().size())
                return x;
            else if(x.getKeys().size() < y.getKeys().size())
                return y;
            else
                return x;
        }else
            return null;
    }
    
    public void delete(BTreeNode node, String key){
        String k;
        if(!node.isIsLeaf()){
            BTreeNode y, z;
            y = precedingChild(node);
            z = succesorChild(node);
            if(y != null && y.getKeys().size() > (nodeSize-1)/2){
                k = findPredecesorKey(node, key);
                moveKey(k, y, node);
                moveKey(key, node, z);
                delete(z, key);
            }else if(z != null && z.getKeys().size() > (nodeSize-1)/2){
                k = findSuccesorKey(node, key);
                moveKey(k, z, node);
                moveKey(key, node, y);
                delete(y, key);
            }else{
                moveKey(key, node, y);
                mergeNodes(y, z);
                delete(y, key);
            }
        }else{
            String v;
            BTreeNode y, z, w;
            y = precedingChild(node);
            z = succesorChild(node);
            w = node.getParent();
            v = node.getParent().getKeys().get(node.getParent().getChildren().indexOf(node));
            if(node.getChildren().size() > (nodeSize-1)/2){
                removeKey(node, key);
            }else if( y != null && y.getChildren().size() > (nodeSize-1)/2){
                k = findPredecesorKey(w, v);
                moveKey(k, y, w);
                k = findSuccesorKey(w, v);
                moveKey(k, w, node);
                delete(node, key);
            }else if(z != null && w.getChildren().size() > (nodeSize-1)/2){
                System.out.println(w.getKeys());
                k = findSuccesorKey(w, v);
                moveKey(k, z, w);
                k = findPredecesorKey(w, v);
                moveKey(k, w, node);
                delete(node, key);
            }else{
                BTreeNode s = findSibling(w);
                BTreeNode w2 = w.getParent();
                if(w.getChildren().size() == (nodeSize-1)/2){
                    mergeNodes(w2, w);
                    mergeNodes(w, s);
                    delete(node, key);
                }else{
                    moveKey(v, w, node);
                    delete(node, key);
                }
            }
        }
    }
    
    public void BFS(){
        Queue<BTreeNode> printQueue = new LinkedList<>();
        BTreeNode temp = root;
        //System.out.println(root.getKeys());
        printQueue.add(temp);
        while(!printQueue.isEmpty()){
            temp = printQueue.peek();
            printQueue.remove();
            System.out.print(temp.getKeys() + ": ");
            for(BTreeNode tr : temp.getChildren()){
                System.out.print(tr.getKeys());
                printQueue.add(tr);
            }
            System.out.println("");
            
        }
    }
    
}
