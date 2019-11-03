import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class GenerateTable {

    public GenerateTable(int interval, int Q1, ArrayList<ChartPoint> chartPointsFIFO, ArrayList<ChartPoint> chartPointsMRU)
    {
        Object[] columnNames = { "Quantidade de frames", "FIFO", "MRU", "Segunda chance", "NRU", "Ótimo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable j = new JTable(tableModel);
        JFrame f = new JFrame();
        f.setTitle("Tabela com Números de acertos");

        for(int i = 0; i <= interval; i++){
            int fifo = chartPointsFIFO.get(i).getNumHits();
            int mru = chartPointsMRU.get(i).getNumHits();
            Object[] data = { Q1 + i, fifo, mru};
            tableModel.addRow(data);

        }

        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(850, 400);
        f.setVisible(true);
    }

}

