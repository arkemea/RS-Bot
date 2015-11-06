package ChoppyFletchy;



import java.awt.*;      
import java.awt.event.*; 
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.powerbot.script.rt4.ClientContext;
 
public class Gui extends JFrame implements ActionListener {
   private JPanel contentPane;
   private JComboBox<String> bankChoice, treeChoice, fletchChoice;
   
   private JRadioButton powercuttingChoice; 
   
   private Button startButton;
   
   private ClientContext ctx;
   private ArrayList<Task> taskList;
   
   private String[] bankChoices = {"Draynor", "Seers Village"},
		   			treeChoices = {"Normal", "Oak", "Willow"}, 
		   			fletchChoices = {"Dont Fletch", "Arrows", "Shortbows", "Longbows"};
   
   public void onStart() {
			     
	   if(treeChoice.getSelectedItem().toString().equals("Normal")) {
		   ChopnFletcher.treeToChop = TREE.NORMAL.getTreeIds();
	   } else if(treeChoice.getSelectedItem().toString().equals("Oak")) {
		   ChopnFletcher.treeToChop = TREE.OAK.getTreeIds();
	   }else if(treeChoice.getSelectedItem().toString().equals("Willow")) {
		   ChopnFletcher.treeToChop = TREE.WILLOW.getTreeIds();
	   }else if(treeChoice.getSelectedItem().toString().equals("Maple")) {
		   ChopnFletcher.treeToChop = TREE.MAPLE.getTreeIds();
	   }else if(treeChoice.getSelectedItem().toString().equals("Magic")) {
		   ChopnFletcher.treeToChop = TREE.MAGIC.getTreeIds();
	   }
   
	   if(fletchChoice.getSelectedItem().toString().equals("Dont Fletch")) {
		   ChopnFletcher.fletch = 0;
	   } else if(fletchChoice.getSelectedItem().toString().equals("Arrows")) {
		   ChopnFletcher.fletch = 1;
	   } else if(fletchChoice.getSelectedItem().toString().equals("Shortbows")) {
		   ChopnFletcher.fletch = 2;
	   } else if(fletchChoice.getSelectedItem().toString().equals("Longbows")) {
		   ChopnFletcher.fletch = 3;
	   }
	   
	   ChopnFletcher.powerCut = powercuttingChoice.isSelected();
	   ChopnFletcher.startScript = true;
	   
	   dispose();
   }
   
   
   public Gui (ClientContext ctx, ArrayList<Task> taskList) {
	   
	  this.ctx = ctx;
	  this.taskList = taskList;
	  
	  setResizable(false);
	  setTitle("ChopnFletcher");
	  setBounds(100,100,350,275);
	  
	  contentPane = new JPanel();
	  contentPane.setBorder(new EmptyBorder(5,5,5,5));
	  setContentPane(contentPane);
	  contentPane.setLayout(null);
	  
	  JLabel bankLabel = new JLabel("Bank");
	  bankLabel.setBounds(12,5,50,30);
	  contentPane.add(bankLabel);
	  
	  bankChoice = new JComboBox(bankChoices);
	  bankChoice.setBounds(10, 30, 100, 30);
	  bankChoice.setSelectedIndex(0);
	  bankChoice.addActionListener(this);
      contentPane.add(bankChoice);
      
      powercuttingChoice = new JRadioButton("Powercutting");
      powercuttingChoice.setBounds(110, 35, 100, 20);
      powercuttingChoice.addActionListener(this);
      contentPane.add(powercuttingChoice);
      
      JLabel treeLabel = new JLabel("Tree");
	  treeLabel.setBounds(12,60,50,30);
	  contentPane.add(treeLabel);
	  
	  treeChoice = new JComboBox(treeChoices);
	  treeChoice.setBounds(10, 85, 100, 30);
	  treeChoice.setSelectedIndex(0);
	  treeChoice.addActionListener(this);
      contentPane.add(treeChoice);
  
	  JLabel fletchLabel = new JLabel("Fletch");
	  fletchLabel.setBounds(12, 115, 50, 30);
	  contentPane.add(fletchLabel);
	  
	  fletchChoice = new JComboBox(fletchChoices);
	  fletchChoice.setBounds(10, 140, 100, 30);
	  fletchChoice.addActionListener(this);
	  contentPane.add(fletchChoice);
	  
	  startButton = new Button("Start bot");
	  startButton.setBounds(10, 180, 70, 30);
	  startButton.addActionListener(this);
	  contentPane.add(startButton);
	 

   } 

   @Override
   public void actionPerformed(ActionEvent evt) {
	   
	   if(evt.getSource().equals(startButton)) {
		   
		   onStart();
		   
			   
	   } else if(evt.getSource().equals(bankChoice)) {
		   
		   String[] choices;
		   switch(bankChoice.getSelectedIndex()) {
		   
			   case 0:
				   choices = new String[] {"Normal", "Oak", "Willow"};
				   treeChoice.setModel(new JComboBox<>(choices).getModel());
				   break;
			   case 1:
				   choices = new String[] {"Normal", "Oak", "Maple"};
				   treeChoice.setModel(new JComboBox<>(choices).getModel());
				   break;
		   }
		   
	   } else if(evt.getSource().equals(treeChoice)) {
		   String[] choices;
		   switch(treeChoice.getSelectedIndex()) {
		   
			   case 0:
				   choices = new String[] {"Dont fletch", "Arrows", "Shortbow", "Longbow"};
				   fletchChoice.setModel(new JComboBox<>(choices).getModel());
				   break;
			   default:
				   choices = new String[] {"Dont fletch", "Shortbow", "Longbow"};
				   fletchChoice.setModel(new JComboBox<>(choices).getModel());
				   break;
		   }
	   }
	   
   }  
}
