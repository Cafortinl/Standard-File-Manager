/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standard_file_manager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author cafor
 */
public class BTreeNode implements Serializable{
    private BTreeNode parent;
    private ArrayList<String> keys = new ArrayList();
    private ArrayList<BTreeNode> children = new ArrayList();
    private boolean isLeaf;

    public BTreeNode() {
    }

    public BTreeNode(BTreeNode parent, boolean isLeaf) {
        this.parent = parent;
        this.isLeaf = isLeaf;
    }

    public BTreeNode getParent() {
        return parent;
    }

    public void setParent(BTreeNode parent) {
        this.parent = parent;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public ArrayList<BTreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<BTreeNode> children) {
        this.children = children;
    }

    public boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
    
    public void removeKey(int i){
        if(!this.keys.isEmpty() && i <= this.keys.size()-1)
            this.keys.remove(i);
    }
    
    public void removeChild(int i){
        if(!this.children.isEmpty() && i <= this.children.size()-1)
            this.children.remove(i);
    }
    
}
