package ChoppyFletchy;



import java.awt.*;      
import java.awt.event.*; 

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
 
public class SettingGUI extends JPanel implements ActionListener {
   private JComboBox bankChoice;
   private JComboBox treeChoice;
   private JComboBox fletchChoice;
   
   private JRadioButton powercuttingChoice; 
   
   private Button isDoneButton;
   
   public SettingGUI () {
	   super();
	   this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      String[] bankChoices = {"Draynor", "Seers Village"};
      bankChoice = new JComboBox(bankChoices);
      bankChoice.setSelectedIndex(0);
      bankChoice.addActionListener(this);
      
      String[] treeChoices = {"Normal", "Oak", "Willow"};
      treeChoice = new JComboBox(treeChoices);
      treeChoice.setSelectedIndex(0);
      treeChoice.addActionListener(this);
      
      String[] fletchChoices = {"Dont Fletch", "Arrows", "Shortbows", "Longbows"};
      fletchChoice = new JComboBox(fletchChoices);
      fletchChoice.setSelectedIndex(0);
      fletchChoice.addActionListener(this);
      
      powercuttingChoice = new JRadioButton("Power cutting");
      
      isDoneButton = new Button("Start bot");
      isDoneButton.addActionListener(this);
 
      add(bankChoice);
      add(treeChoice);
      add(fletchChoice);
      add(powercuttingChoice);
      add(isDoneButton);

   } 

   @Override
   public void actionPerformed(ActionEvent evt) {
	   
	   if(evt.getSource().equals(isDoneButton)) {
		   
		   ChopnFletcher.isDone = true;
		   
		   
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
		  // this.setVisible(false);
		   
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
