import org.jfree.ui.RefineryUtilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

public class Main {
	private static List<Inputs> listPages;
	public static void main(String[] args){

		int Q1 = 0, Q2 = 0, numReferences = 0;
		listPages = new ArrayList<>();
		ArrayList listMode = new ArrayList<>();
		File file = new File("src/input_test.txt");
		List<ArrayList<ChartPoint>> chartPoints = new ArrayList<>();
		ArrayList<ChartPoint> chartPointsFIFO = new ArrayList<>();
		ArrayList<ChartPoint> chartPointsMRU = new ArrayList<>();
		ArrayList<ChartPoint> chartPointsSC = new ArrayList<>();
		ArrayList<ChartPoint> chartPointsNRU = new ArrayList<>();
		ArrayList<ChartPoint> chartPointsOPT = new ArrayList<>();

		try {
			Scanner inputPrompt = new Scanner(new InputStreamReader(System.in));
			String buffer = inputPrompt.next();
			Q1 = Integer.parseInt(buffer.substring(0, buffer.indexOf(',')));
			Q2 = Integer.parseInt(buffer.substring(buffer.indexOf(',') + 1, buffer.indexOf('-')));
			numReferences = Integer.parseInt(buffer.substring(buffer.indexOf('-') + 1, buffer.length()));
			Scanner inputFile = new Scanner(new FileReader(file));
			inputFile.useDelimiter("-");
		    String page, mode;
		    while(true) {
				if (inputFile.hasNext()){
					page = inputFile.next();
					mode = page.substring(page.length() - 1);
					page = page.substring(0, page.length() - 1);
					listPages.add(new Inputs(Integer.parseInt(page)));
					listMode.add(mode.charAt(0));
				}
				else {
					break;
				}
			}

		}
		catch (IOException e) {
		    e.getMessage();
		}

		//System.out.println(listMode);
		for(int i = Q1; i <= Q2 ; i++) {
			List<Inputs> list = new ArrayList<>(listPages);
			Algorithms fifo = new Algorithms(i);
			Algorithms mru = new Algorithms(i);
			Algorithms sc = new Algorithms(i);
			Algorithms nru = new Algorithms(i);
			Algorithms opt = new Algorithms(i);
			chartPointsFIFO.add(new ChartPoint(i, fifo.FIFO(list)));
			chartPointsMRU.add(new ChartPoint(i, mru.MRU(list)));
			chartPointsSC.add(new ChartPoint(i, sc.secondChance(list, numReferences)));
			chartPointsNRU.add(new ChartPoint(i, nru.nru(list, listMode, numReferences)));
			chartPointsOPT.add(new ChartPoint(i, opt.optimum(list)));
		}

		GenerateTable table = new GenerateTable(Q2 - Q1, Q1, chartPointsFIFO, chartPointsMRU, chartPointsSC, chartPointsNRU, chartPointsOPT);
		chartPoints.add(chartPointsFIFO);
		chartPoints.add(chartPointsMRU);
		chartPoints.add(chartPointsSC);
		chartPoints.add(chartPointsNRU);
		chartPoints.add(chartPointsOPT);
		GenerateChart chart = new GenerateChart("Algoritmos de substituição de páginas", chartPoints);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}
}


