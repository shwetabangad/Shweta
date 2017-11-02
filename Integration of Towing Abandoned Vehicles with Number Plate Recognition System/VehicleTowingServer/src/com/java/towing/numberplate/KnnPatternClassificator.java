
package com.java.towing.numberplate;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import com.java.towing.service.WebService;



public class KnnPatternClassificator extends CharacterRecognizer {
    Vector<Vector<Double>> learnVectors;
    
    public KnnPatternClassificator() throws IOException {
        String path = Intelligence.configurator.getPathProperty("char_learnAlphabetPath");
        String alphaString = "0123456789abcdefghijklmnopqrstuvwxyz";
        path = WebService.path + path;
        this.learnVectors = new Vector<Vector<Double>>();
        for (int i=0; i<alphaString.length(); i++) this.learnVectors.add(null);
        
        File folder = new File(path);
        for (String fileName : folder.list()) {
            int alphaPosition = alphaString.indexOf(fileName.toLowerCase().charAt(0));
            if (alphaPosition==-1)  continue; 

            Char imgChar = new Char(path+File.separator+fileName);
            imgChar.normalize();
            this.learnVectors.set(alphaPosition, imgChar.extractFeatures());
        }
        
        for (int i=0; i<alphaString.length(); i++) 
            if (this.learnVectors.elementAt(i)==null) throw new IOException("Warning : alphabet in "+path+" is not complete");
      
    }
 
    public RecognizedChar recognize(Char chr) throws Exception {
        Vector<Double> tested = chr.extractFeatures();
        int minx = 0;
        float minfx = Float.POSITIVE_INFINITY;
        
        RecognizedChar recognized = new RecognizedChar();
        
        for (int x = 0; x < this.learnVectors.size(); x++) {
            float fx = this.simplifiedEuclideanDistance(tested, this.learnVectors.elementAt(x));

            recognized.addPattern(recognized.new RecognizedPattern(this.alphabet[x], fx));
        }
//        return new RecognizedChar(this.alphabet[minx], minfx);
        recognized.sort(0);
        return recognized;
    }
    
    public float difference(Vector<Double> vectorA, Vector<Double> vectorB) {
        float diff = 0;
        for (int x = 0; x<vectorA.size(); x++) {
            diff += Math.abs(vectorA.elementAt(x) - vectorB.elementAt(x));
        }
        return diff;
    }
    
    public float simplifiedEuclideanDistance(Vector<Double> vectorA, Vector<Double> vectorB) {
        float diff = 0;
        float partialDiff;
        for (int x = 0; x<vectorA.size(); x++) {
            partialDiff = (float)Math.abs(vectorA.elementAt(x) - vectorB.elementAt(x));
            diff += partialDiff * partialDiff;
        }
        return diff;        
    }
    
}