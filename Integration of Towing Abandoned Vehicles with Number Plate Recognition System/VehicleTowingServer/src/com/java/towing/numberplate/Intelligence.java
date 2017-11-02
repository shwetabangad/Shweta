package com.java.towing.numberplate;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.java.towing.numberplate.CharacterRecognizer.RecognizedChar;
import com.java.towing.service.WebService;

public class Intelligence {

	private long lastProcessDuration = 0; 
	public static Configurator configurator = new Configurator("C:\\config.xml");
	public static CharacterRecognizer chrRecog;
	public static Parser parser;

	public Intelligence() throws Exception {
		configurator = new Configurator(WebService.path + "config.xml");

		this.chrRecog = new KnnPatternClassificator();

		this.parser = new Parser();
	}

	public long lastProcessDuration() {
		return this.lastProcessDuration;
	}

	public String recognize(MyCarSnapshot carSnapshot) throws Exception {
		
		System.out.println("inside recognize..");
		TimeMeter time = new TimeMeter();
		int syntaxAnalysisMode = Intelligence.configurator.getIntProperty("intelligence_syntaxanalysis");
		int skewDetectionMode = Intelligence.configurator.getIntProperty("intelligence_skewdetection");

		File oututfile = null;
		for (Band b : carSnapshot.getBands()) { 

			try {
				File outputfile = new File("D:\\band.png");
				ImageIO.write(b.image, "png", outputfile);
			} catch (IOException ex) {
				Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
			}

			for (Plate plate : b.getPlates()) {

				try {
					File outputfile = new File("D:\\plate.png");
					ImageIO.write(plate.image, "png", outputfile);
				} catch (IOException ex) {
					Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
				}

				// SKEW-RELATED
				Plate notNormalizedCopy = null;
				BufferedImage renderedHoughTransform = null;
				HoughTransformation hough = null;
				if (skewDetectionMode != 0) { 
					notNormalizedCopy = plate.clone();
					notNormalizedCopy.horizontalEdgeDetector(notNormalizedCopy.getBi());
					try {
						File outputfile = new File("D:\\plate-horizontalEdgeDetector.png");
						ImageIO.write(notNormalizedCopy.image, "png", outputfile);
					} catch (IOException ex) {
						Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
					}

					hough = notNormalizedCopy.getHoughTransformation();
					renderedHoughTransform = hough.render(HoughTransformation.RENDER_ALL, HoughTransformation.COLOR_BW);

					try {
						File outputfile = new File("D:\\plate-HoughTransform.png");
						ImageIO.write(renderedHoughTransform, "png", outputfile);
					} catch (IOException ex) {
						Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				if (skewDetectionMode != 0) { 
					AffineTransform shearTransform = AffineTransform.getShearInstance(0, -(double) hough.dy / hough.dx);
					BufferedImage core = plate.createBlankBi(plate.getBi());
					core.createGraphics().drawRenderedImage(plate.getBi(), shearTransform);
					plate = new Plate(core);

					try {
						File outputfile = new File("D:\\plate-AffineTransform.png");
						ImageIO.write(plate.image, "png", outputfile);
					} catch (IOException ex) {
						Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

				plate.normalize();
				
				try {
					oututfile = new File("D:\\normalize-plate.png");
					ImageIO.write(plate.image, "png", oututfile);
				} catch (IOException ex) {
					Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
				}

				float plateWHratio = (float) plate.getWidth() / (float) plate.getHeight();
				if (plateWHratio < Intelligence.configurator.getDoubleProperty("intelligence_minPlateWidthHeightRatio")
						|| plateWHratio > Intelligence.configurator.getDoubleProperty("intelligence_maxPlateWidthHeightRatio")) {
					continue;
				}

				Vector<Char> chars = plate.getChars();
				RecognizedPlate recognizedPlate = new RecognizedPlate();

				int i = 0;
				for (Char chr : chars) {
					try {
						File outputfile = new File("D:\\char" + i++ + ".png");
						ImageIO.write(chr.image, "png", outputfile);
					} catch (IOException ex) {
						Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

				for (Char chr : chars) {
					chr.normalize();
					
					try {
						File outputfile = new File("D:\\nor-char" + i++ + ".png");
						ImageIO.write(chr.image, "png", outputfile);
					} catch (IOException ex) {
						Logger.getLogger(MyCarSnapshot.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

				/*float averageHeight = plate.getAveragePieceHeight(chars);
				float averageContrast = plate.getAveragePieceContrast(chars);
				float averageBrightness = plate.getAveragePieceBrightness(chars);
				float averageHue = plate.getAveragePieceHue(chars);
				float averageSaturation = plate.getAveragePieceSaturation(chars);
*/
				for (Char chr : chars) {
					boolean ok = true;
					String errorFlags = "";

/*					float widthHeightRatio = (float) (chr.pieceWidth);
					widthHeightRatio /= (float) (chr.pieceHeight);

					if (widthHeightRatio < Intelligence.configurator.getDoubleProperty("intelligence_minCharWidthHeightRatio")
							|| widthHeightRatio > Intelligence.configurator.getDoubleProperty("intelligence_maxCharWidthHeightRatio")) {
						errorFlags += "WHR ";
						ok = false;
					
					}


					if ((chr.positionInPlate.x1 < 2
							|| chr.positionInPlate.x2 > plate.getWidth() - 1)
							&& widthHeightRatio < 0.12) {
						errorFlags += "POS ";
						ok = false;
					}


					float contrastCost = Math.abs(chr.statisticContrast - averageContrast);
					float brightnessCost = Math.abs(chr.statisticAverageBrightness - averageBrightness);
					float hueCost = Math.abs(chr.statisticAverageHue - averageHue);
					float saturationCost = Math.abs(chr.statisticAverageSaturation - averageSaturation);
					float heightCost = (chr.pieceHeight - averageHeight) / averageHeight;

					if (brightnessCost > Intelligence.configurator.getDoubleProperty("intelligence_maxBrightnessCostDispersion")) {
						errorFlags += "BRI ";
						ok = false;
					}
					if (contrastCost > Intelligence.configurator.getDoubleProperty("intelligence_maxContrastCostDispersion")) {
						errorFlags += "CON ";
						ok = false;
					}
					if (hueCost > Intelligence.configurator.getDoubleProperty("intelligence_maxHueCostDispersion")) {
						errorFlags += "HUE ";
						ok = false;
					}
					if (saturationCost > Intelligence.configurator.getDoubleProperty("intelligence_maxSaturationCostDispersion")) {
						errorFlags += "SAT ";
						ok = false;
					}
					if (heightCost < -Intelligence.configurator.getDoubleProperty("intelligence_maxHeightCostDispersion")) {
						errorFlags += "HEI ";
						ok = false;
					}
*/
					float similarityCost = 0;
					RecognizedChar rc = null;
					if (ok == true) {
						rc = this.chrRecog.recognize(chr);
						similarityCost = rc.getPatterns().elementAt(0).getCost();
						System.out.println("similarityCost:" + similarityCost);
						recognizedPlate.addChar(rc);
						/*if (similarityCost > Intelligence.configurator.getDoubleProperty("intelligence_maxSimilarityCostDispersion")) {
							errorFlags += "NEU ";
							ok = false;
						}*/

					}

					/*if (ok == true) {
						recognizedPlate.addChar(rc);
					} else {
					}*/

				} // end for each char


/*				if (recognizedPlate.chars.size() < Intelligence.configurator.getIntProperty("intelligence_minimumChars")) {
					continue;
				}
*/
				this.lastProcessDuration = time.getTime();
				String parsedOutput = parser.parse(recognizedPlate, oututfile, syntaxAnalysisMode);
				return parsedOutput;

			} // end for each  plate

		}

		this.lastProcessDuration = time.getTime();
		//return new String("not available yet ;-)");
		return null;
	}
}
