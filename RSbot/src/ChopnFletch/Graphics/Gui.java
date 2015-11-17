package ChopnFletch.Graphics;



import java.awt.*;      
import java.awt.event.*; 

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.powerbot.script.rt4.ClientContext;

import ChopnFletch.ChopnFletch;
import ChopnFletch.Enums.*;
import ChopnFletch.Tasks.*;


 
public class Gui extends JFrame {
	
   private static final long serialVersionUID = 494368510764413341L;
	
   private JPanel contentPane;
   private JComboBox bankChoice, treeChoice, fletchChoice;
   private JRadioButton powercuttingChoice; 
   private Button startButton;
   
   private ClientContext ctx;
   
   private String[] bankChoices = {"Draynor", "Seers Village", "The Grand Exchange", "Varrock East", "Catherby"},
		   			treeChoices = {"Normal", "Oak", "Willow", "Maple", "Yew", "Magic"}, 
		   			fletchChoices = {"Dont Fletch", "Arrows", "Shortbows", "Longbows"};
		   
   public void onStart() {
	   
	   if(fletchChoice.getSelectedIndex() != 0) {
		   for(Log l: Log.values()) {
			   if(l.getName().equals(treeChoice.getSelectedItem())) {
				   ChopnFletch.taskList.add(new Fletch(ctx,fletchChoice.getSelectedIndex(), l.getLogId()));
			   }
		   }
	   }
	   if(powercuttingChoice.isSelected()) {
		   ChopnFletch.taskList.add(new Drop(ctx));
		   
		   for(Tree t: Tree.values()) {
			   if(t.getName().equals(treeChoice.getSelectedItem())) {
				   ChopnFletch.taskList.add(new Chop(ctx, t.getTreeIds(), ctx.players.local().tile(), 30));
			   }
		   }
	   } else {
		   
		   for(Banks b: Banks.values()) {
			   if(b.getName().equals(bankChoice.getSelectedItem())) {
				   ChopnFletch.taskList.add(new PathFinder(ctx, true, b, treeChoice.getSelectedIndex()));
				   ChopnFletch.taskList.add(new PathFinder(ctx, false, b, treeChoice.getSelectedIndex()));
				   ChopnFletch.taskList.add(new Banking(ctx,b));
				   for(Tree t: Tree.values()) {
					   if(t.getName().equals(treeChoice.getSelectedItem())) {
						   ChopnFletch.taskList.add(new Chop(ctx, t.getTreeIds(), b.getSpots().getSpecificAnchor(treeChoice.getSelectedIndex()),
								   					b.getSpots().getSpecificPath(treeChoice.getSelectedIndex()).getDistanceToAnchor()));
					   }
				   }
			   }
		   }
	   }
	   
	   ChopnFletch.taskList.add(new LootSearcher(ctx));
	   
	   
	   
	   
	   

	   
	   dispose();
   }
   
   
   public Gui (ClientContext ctx) {
	   
	  this.ctx = ctx;
	  
	  setResizable(false);
	  setTitle("ChopnFletcher");
	  setBounds(200,200,310,120);
	  
	  contentPane = new JPanel();
	  contentPane.setBorder(new EmptyBorder(5,5,5,5));
	  setContentPane(contentPane);
	  contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	  
	  contentPane.setLayout(new GridBagLayout());
	  
	  GridBagConstraints c = new GridBagConstraints();
	  c.fill = GridBagConstraints.HORIZONTAL;
	  
	  JLabel bankLabel = new JLabel("Bank");
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 0;
	  c.gridy = 0;
	  contentPane.add(bankLabel, c);
	  
	  bankChoice = new JComboBox(bankChoices);
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 1;
	  c.gridy = 0;
	  bankChoice.setSelectedIndex(0);
      contentPane.add(bankChoice, c);
      
      powercuttingChoice = new JRadioButton("Powercutting");
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 2;
	  c.gridy = 0;
      contentPane.add(powercuttingChoice, c);
      
      JLabel treeLabel = new JLabel("Tree");
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 0;
	  c.gridy = 1;
	  treeLabel.setBounds(12,60,50,30);
	  contentPane.add(treeLabel, c);
	  
	  treeChoice = new JComboBox(treeChoices);
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 1;
	  c.gridy = 1;
	  treeChoice.setSelectedIndex(0);
      contentPane.add(treeChoice, c);
  
	  JLabel fletchLabel = new JLabel("Fletch");
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 0;
	  c.gridy = 2;
	  contentPane.add(fletchLabel, c);
	  
	  fletchChoice = new JComboBox(fletchChoices);
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 1;
	  c.gridy = 2;
	  contentPane.add(fletchChoice, c);
	  
	  startButton = new Button("Start bot");
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.weightx = 0.5;
	  c.gridx = 2;
	  c.gridy = 3;
	  startButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			onStart();
		}
	  });
	  
	  contentPane.add(startButton, c);
	   
   }  
}
