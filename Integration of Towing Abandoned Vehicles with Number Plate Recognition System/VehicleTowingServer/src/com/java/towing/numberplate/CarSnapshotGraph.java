
package com.java.towing.numberplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;


public class CarSnapshotGraph extends Graph {
    // configuration for searching bands in image !
    private static double peakFootConstant = 
            Intelligence.configurator.getDoubleProperty("carsnapshotgraph_peakfootconstant"); //0.55
    private static double peakDiffMultiplicationConstant = 
            Intelligence.configurator.getDoubleProperty("carsnapshotgraph_peakDiffMultiplicationConstant");//0.1
    
    MyCarSnapshot handle;
    
    public CarSnapshotGraph(MyCarSnapshot handle) {
        this.handle = handle;
    }
    
    public class PeakComparer implements Comparator {
        Vector<Float> yValues = null;
        
        public PeakComparer(Vector<Float> yValues) {
            this.yValues = yValues;
        }
        
        private float getPeakValue(Object peak) {
            return this.yValues.elementAt( ((Peak)peak).getCenter()  ); // podla intenzity
            //return ((Peak)peak).getDiff();
        }
        
        public int compare(Object peak1, Object peak2) { // Peak
            double comparison = this.getPeakValue(peak2) - this.getPeakValue(peak1);
            if (comparison < 0) return -1;
            if (comparison > 0) return 1;
            return 0;
        }
    }
    
    public Vector<Peak> findPeaks(int count) {
        
        Vector<Peak> outPeaks = new Vector<Peak>();
        
        for (int c=0; c<count; c++) { // for count
            float maxValue = 0.0f;
            int maxIndex = 0;
            for (int i=0; i<this.yValues.size(); i++) { 
                if (allowedInterval(outPeaks, i)) { 
                    if (this.yValues.elementAt(i) >= maxValue) {
                        maxValue = this.yValues.elementAt(i);
                        maxIndex = i;
                    }
                }
            } 
            int leftIndex = indexOfLeftPeakRel(maxIndex,peakFootConstant);
            int rightIndex = indexOfRightPeakRel(maxIndex,peakFootConstant);
            int diff = rightIndex - leftIndex;
            leftIndex -= peakDiffMultiplicationConstant * diff;   /*CONSTANT*/
            rightIndex+= peakDiffMultiplicationConstant * diff;   /*CONSTANT*/

                outPeaks.add(new Peak(
                    Math.max(0,leftIndex),
                    maxIndex,
                    Math.min(this.yValues.size()-1,rightIndex)
                    ));
        } // end for count
        
        Collections.sort(outPeaks, (Comparator<? super Graph.Peak>)
                                   new PeakComparer(this.yValues));
        
        super.peaks = outPeaks; 
        return outPeaks;
    }

    
}

