/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics;

/**
 *
 * @author bruno
 */
public enum MetricMethod {
    MLOC, NBD, PAR, VG;
    
    public static boolean contains(String metric){
        for(MetricMethod m : MetricMethod.values()){
            if(m.toString().toLowerCase().equals(metric)){
                return true;
            }
        }
        return false;
    }
}
