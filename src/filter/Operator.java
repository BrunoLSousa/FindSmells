/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Operator {
    
    private List<EnumOperator> children;
    
    public Operator(){
        this.children = new ArrayList<>();
    }
    
    public void addChild(EnumOperator child){
        this.children.add(child);
    }
    
    public List<EnumOperator> getChildren(){
        return this.children;
    }
    
    public EnumOperator getChildrenIndex(int index){
        return this.children.get(index);
    }
    
    public boolean validate(EnumOperator child){
        for(EnumOperator op : this.children){
            if(child.equals(op)){
                return true;
            }
        }
        return false;
    }
    
}
