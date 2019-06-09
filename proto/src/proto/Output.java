package proto;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.StringTokenizer; 
import java.util.ArrayList;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class Output extends JFrame implements ActionListener {
   
   private JScrollBar jscbHort = new JScrollBar(JScrollBar.HORIZONTAL);
   private JScrollBar jscbVert = new JScrollBar(JScrollBar.VERTICAL);
   private Transmission trs = null;
   public JLabel keyList = new JLabel();
   JButton reset = new JButton("Back");
   
   
   public Output(String words, ArrayList<String> Sites, int sort) throws Exception {
      
      // parsing by comma keyword you entered
      ArrayList<String> keywords = new ArrayList<String>();
      StringTokenizer s = new StringTokenizer(words);  
      while(s.hasMoreTokens()) {                
         keywords.add(s.nextToken(", "));               
      }  
      
      trs = new Transmission(keywords,Sites,sort);
        String []index = {"Date", "Site", "Headline", "Url"};   // Set table entities suitable
        String [][]cont = new String[100][100];
       
        for(int i=0 ; i < trs.getArt().getHowManyData() ; i++) {
           for (int j=0 ; j < 4 ; j++) {
              cont[i][0] = Integer.toString(trs.getArt().getDate(i));
              cont[i][1] = trs.getArt().getSite(i);
              cont[i][2] = trs.getArt().getHeadline(i);
              cont[i][3] = trs.getArt().getUrl(i);
           }
        }
        
        
      setTitle("Search result");
      setSize(1200,800);
      

      
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      DefaultTableModel model = new DefaultTableModel(cont, index);
      model.setColumnCount(4);
      
      setLayout(new BorderLayout());
      JTable table = new JTable(model);

      table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
      table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField()));
        
        // set detail attributes of column sizes
        table.getColumnModel().getColumn(0).setMaxWidth(200); 
        table.getColumnModel().getColumn(0).setMinWidth(100); 
        table.getColumnModel().getColumn(0).setWidth(80); 

        table.getColumnModel().getColumn(1).setMaxWidth(200); 
        table.getColumnModel().getColumn(1).setMinWidth(100); 
        table.getColumnModel().getColumn(1).setWidth(80); 
        
        table.getColumnModel().getColumn(2).setMaxWidth(600); 
        table.getColumnModel().getColumn(2).setMinWidth(200); 
        table.getColumnModel().getColumn(2).setWidth(200); 
        
        table.getColumnModel().getColumn(3).setMaxWidth(400); 
        table.getColumnModel().getColumn(3).setMinWidth(100); 
        table.getColumnModel().getColumn(3).setWidth(100); 
         // 행의 높이 정하기 
        table.setRowHeight(50); 
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();   // 날짜와 웹사이트 부분 가운데 정렬
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setCellRenderer(dtcr);
        tcm.getColumn(1).setCellRenderer(dtcr);
        
      JScrollPane sc = new JScrollPane(table);
      
      JPanel p0 = new JPanel();
      p0.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
      JPanel p1 = new JPanel();
      p1.setLayout(new GridLayout(1, 2));
   
      
      p0.add(new JLabel("Keyword ->"));
      
      p0.add(new JTextField(words, 25));

      p0.add(reset);
      reset.addActionListener(this);
      
      add(p0, BorderLayout.NORTH);
      p1.add(sc);

   
      add(p1);
   }
   // when you click the 'Back' button, you can re-search keyword at first view
   public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(reset)) {
         dispose();
      }
   }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
   public ButtonRenderer() {
      setOpaque(true);
   }
   
   @Override      
   public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
         int col) {         
      setText((obj == null) ? "" : obj.toString());         
      return this;         
   }      
}   
class ButtonEditor extends DefaultCellEditor {
   protected JButton btn;
   private String lbl;
   private Boolean clicked;
   public ButtonEditor(JTextField txt) {
      super(txt);
      btn = new JButton();
      btn.setOpaque(true);
      btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
         }
      });
   }
   @Override
   public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
      lbl = (obj == null) ? "" : obj.toString();
      btn.setText(lbl);
      clicked = true;
      return btn;
   }
   @Override
   public Object getCellEditorValue() {
      if (clicked) {
         Desktop d = Desktop.getDesktop();
         try {
            d.browse(new URI(lbl));
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      clicked = false;
      return new String(lbl);
   }
   
   @Override
   public boolean stopCellEditing() {
      clicked = false;
      return super.stopCellEditing();
   }
   @Override
   protected void fireEditingStopped() {
      super.fireEditingStopped();
   }
}