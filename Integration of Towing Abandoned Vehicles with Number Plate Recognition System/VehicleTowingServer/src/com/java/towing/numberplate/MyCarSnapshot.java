
package com.java.towing.numberplate;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class MyCarSnapshot extends Photo {
    private static int distributor_margins = 
            Intelligence.configurator.getIntProperty("carsnapshot_distributormargins");
    private static int carsnapshot_graphrankfilter =
            Intelligence.configurator.getIntProperty("carsnapshot_graphrankfilter");

    static private int numberOfCandidates = Intelligence.configurator.getIntProperty("intelligence_numberOfBands");
    private CarSnapshotGraph graphHandle = null;

    
    public static Graph.ProbabilityDistributor distributor = new Graph.ProbabilityDistributor(0,0,distributor_margins,distributor_margins);
    
    public MyCarSnapshot() {
    }
    
    public MyCarSnapshot(String filepath) throws IOException {
        super(filepath);
    }
    
    public MyCarSnapshot(BufferedImage bi) {
        super(bi);
    }

    public BufferedImage renderGraph() {
        this.computeGraph();
        return graphHandle.renderVertically(100, this.getHeight());
    }    
    
    private Vector<Graph.Peak> computeGraph() {
        if (graphHandle != null) return graphHandle.peaks; // graf uz bol vypocitany

        BufferedImage imageCopy = this.duplicateBufferedImage(this.image);
        
        try {
            File outputfile = new File("D:\\original.png");
            ImageIO.write(imageCopy, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
        }

        verticalEdgeBi(imageCopy);

        try {
            File outputfile = new File("D:\\verticalEdge.png");
            ImageIO.write(imageCopy, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
        }

        thresholding(imageCopy);

        try {
            File outputfile = new File("D:\\thresholding.png");
            ImageIO.write(imageCopy, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        graphHandle = this.histogram(imageCopy);
        graphHandle.rankFilter(carsnapshot_graphrankfilter);
        graphHandle.applyProbabilityDistributor(distributor);
        
        graphHandle.findPeaks(numberOfCandidates); 
        return graphHandle.peaks;
    }
        
    public Vector<Band> getBands() {
        Vector<Band> out = new Vector<Band>();

        Vector<Graph.Peak> peaks = computeGraph();
        
        for (int i=0; i<peaks.size(); i++) {
            Graph.Peak p = peaks.elementAt(i);
            out.add(new Band(
                    image.getSubimage(  0  ,
                    (int) (p.getLeft())  ,
                    image.getWidth()  ,
                    (int) (p.getDiff()  )
                    ))
                    );
        }
        return out;
        
    }
    
    public void verticalEdgeBi(BufferedImage image) {
        BufferedImage imageCopy = duplicateBufferedImage(image);
        
        float data[] = {
            -1,0,1,
            -1,0,1,
            -1,0,1,
            -1,0,1
        };
        
        new ConvolveOp(new Kernel(3, 4, data), ConvolveOp.EDGE_NO_OP, null).filter(imageCopy, image);
    }
    
    public CarSnapshotGraph histogram(BufferedImage bi) {
        CarSnapshotGraph graph = new CarSnapshotGraph(this);
        for (int y=0; y<bi.getHeight(); y++) {
            float counter = 0;
            for (int x=0; x<bi.getWidth();x++)
                counter += getBrightness(bi,x,y);
            graph.addPeak(counter);
        }
        return graph;        
    }
}
