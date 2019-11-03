import org.jfree.ui.RefineryUtilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args){

		int Q1 = 0, Q2 = 0;
		List<Inputs> listPages = new ArrayList<>();
		ArrayList listMode = new ArrayList<>();
		File file = new File("src/input_test.txt");
		List<ArrayList<ChartPoint>> chartPoints = new ArrayList<>();
		ArrayList<ChartPoint> chartPointsFIFO = new ArrayList<>();
		ArrayList<ChartPoint> chartPointsMRU = new ArrayList<>();

		try {
			Scanner inputPrompt = new Scanner(new InputStreamReader(System.in));
			String buffer = inputPrompt.next();
			Q1 = Integer.parseInt(buffer.substring(0, buffer.indexOf(',')));
			Q2 = Integer.parseInt(buffer.substring(buffer.indexOf(',') + 1, buffer.length()));
			Scanner inputFile = new Scanner(new FileReader(file));
			inputFile.useDelimiter("-");
		    String page, mode;
		    while(true) {
				if (inputFile.hasNext()){
					page = inputFile.next();
					mode = page.substring(page.length() - 1);
					page = page.substring(0, page.length() - 1);
					listPages.add(new Inputs(Integer.parseInt(page)));
					listMode.add(mode);
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
			Algorithms fifo = new Algorithms(i);
			Algorithms mru = new Algorithms(i);
			chartPointsFIFO.add(new ChartPoint(i, fifo.FIFO(listPages)));
			chartPointsMRU.add(new ChartPoint(i, mru.MRU(listPages)));
		}

		GenerateTable table = new GenerateTable(Q2 - Q1, Q1, chartPointsFIFO, chartPointsMRU);
		chartPoints.add(chartPointsFIFO);
		chartPoints.add(chartPointsMRU);
		GenerateChart chart = new GenerateChart("Algoritmos de substituição de páginas", chartPoints);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}
}


