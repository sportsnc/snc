package proto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class UI extends JFrame implements ActionListener  {
   
   public ArrayList<String> keywords = new ArrayList<String>();
   private JButton jbtOk = new JButton("Search");
   private JButton jbtCancel = new JButton("Exit");
   JTextField keyword = new JTextField(16);
   private ImageIcon logoIcon = new ImageIcon("src/image/logo.png");
   private JCheckBox[] artBox = new JCheckBox[6];
   String[] siteName = {"AS", "BBC", "MailOnline", "SkySports", "TheSun", "NBCsports"};
   private JCheckBox sortBox = new JCheckBox("sort by Date");
   public ArrayList<String> MySites = new ArrayList<String>();
   public int sort = 0;
   
   public UI() {
      // display first UI view //
      setTitle("Sports News Crawler");
      setSize(500,380);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      setLayout(new BorderLayout(2,0));      // set appropriate layout
      

      JPanel p0 = new JPanel();
      JPanel p1 = new JPanel();
      JPanel p2 = new JPanel();
      p0.setLayout(new BorderLayout(2,0));
      p1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
      p2.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 15));
      
      
      p0.add(new JLabel(" Please enter the keyword."), BorderLayout.NORTH);
      p0.add(keyword, BorderLayout.CENTER);
      
      p0.add(new JLabel(logoIcon), BorderLayout.SOUTH);
      
      for(int i=0; i<artBox.length; i++) {
            artBox[i] = new JCheckBox(siteName[i]); // 문자열을 갖는 체크박스 생성
            p1.add(artBox[i]); // 패널에 체크박스 부착하기
            artBox[i].addItemListener(new MyCheckListener()); // 체크박스에 리스너 등록
           }
      
      p2.add(sortBox);
      sortBox.setBorderPainted(true); // 체크박스의 외곽선(테두리) 보여주기
      sortBox.addItemListener(new ItemListener() {
         
         @Override
         public void itemStateChanged(ItemEvent e) {
            // TODO Auto-generated method stub
            if(e.getStateChange()==ItemEvent.SELECTED) {
               sort = 1;               
            }
            else sort = 0;
         }
      });   
      
      p2.add(jbtOk);
      p2.add(jbtCancel);
      add(p0, BorderLayout.NORTH);
      add(p1, BorderLayout.CENTER);
      add(p2, BorderLayout.SOUTH);
      jbtOk.addActionListener(this);
      jbtCancel.addActionListener(this);
      setVisible(true);
   

   }
   class MyCheckListener implements ItemListener{      // 체크박스 선택값만 정확히 ArrayList배열에 저장

      @Override
      public void itemStateChanged(ItemEvent e) {         
         if(e.getStateChange()==ItemEvent.SELECTED) {
            
            if(e.getItem()==artBox[0]) {
               MySites.add("AS");
            }
            else if(e.getItem()==artBox[1]) {
               MySites.add("BBC");
            }
            else if(e.getItem()==artBox[2]) {
               MySites.add("MailOnline");
            }
            else if(e.getItem()==artBox[3]) {
               MySites.add("SkySports");
            }
            else if(e.getItem()==artBox[4]) {
               MySites.add("TheSun");
            }
            else if(e.getItem()==artBox[5]) {
                MySites.add("NBCSports");
             }
         }      
            if(e.getStateChange()==ItemEvent.DESELECTED) {
               
               if(e.getItem()==artBox[0]) {
                  MySites.remove("AS");
               }
               else if(e.getItem()==artBox[1]) {
                  MySites.remove("BBC");
               }
               else if(e.getItem()==artBox[2]) {
                  MySites.remove("MailOnline");
               }
               else if(e.getItem()==artBox[3]) {
                  MySites.remove("SkySports");
               }
               else if(e.getItem()==artBox[4]) {
                  MySites.remove("TheSun");
               }
               else if(e.getItem()==artBox[5]) {
                   MySites.add("NBCSports");
               }
            }
      }   
      
   }
   public void actionPerformed(ActionEvent e) {   // set action when you click "Search" button
      if (e.getSource().equals(jbtOk)) {
         // keyword parsing //
         String input = new String(keyword.getText());
            try {
            new Output(input,MySites,sort);
         } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
            keyword.setText("");   // set textfield after you click the button.
              
      } else if (e.getSource().equals(jbtCancel)) {
         System.exit(0);
      }
   }
}