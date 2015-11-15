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
import ChopnFletch.Enums.BANK;
import ChopnFletch.Enums.LOG;
import ChopnFletch.Enums.TREE;


 
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
	   
	   switch (treeChoice.getSelectedItem().toString()) {
	   	case "Normal":
	   		ChopnFletch.treeToChop 	= TREE.NORMAL.getTreeIds();
			ChopnFletch.logToCut 	= LOG.NORMAL.getLogId();
			ChopnFletch.pathToWalk	= 0;
			break;
	   	case "Oak":
	   		ChopnFletch.treeToChop 	= TREE.OAK.getTreeIds();
			ChopnFletch.logToCut 	= LOG.OAK.getLogId();
			ChopnFletch.pathToWalk	= 1;
			break;
	   	case "Willow":
	   		ChopnFletch.treeToChop 	= TREE.WILLOW.getTreeIds();
			ChopnFletch.logToCut 	= LOG.WILLOW.getLogId();
			ChopnFletch.pathToWalk	= 2;
			break;
	   	case "Maple":
	   		ChopnFletch.treeToChop 	= TREE.MAPLE.getTreeIds();
			ChopnFletch.logToCut 	= LOG.MAPLE.getLogId();
			ChopnFletch.pathToWalk	= 3;
			break;
	   	case "Yew":
	   		ChopnFletch.treeToChop 	= TREE.YEW.getTreeIds();
	   		ChopnFletch.logToCut 	= LOG.YEW.getLogId();
	   		ChopnFletch.pathToWalk	= 4;
	   		break;
	   	case "Magic":
	   		ChopnFletch.treeToChop 	= TREE.MAGIC.getTreeIds();
			ChopnFletch.logToCut 	= LOG.MAGIC.getLogId();
			ChopnFletch.pathToWalk	= 5;
			break;
		default:
			break;
		}
	   
	   switch (fletchChoice.getSelectedItem().toString()) {
		case "Dont Fletch":
			ChopnFletch.fletch = 0;
			break;
		case "Arrows":
			ChopnFletch.fletch = 1;
			break;
		case "Shortbows":
			ChopnFletch.fletch = 2;
			break;
		case "Longbows":
			ChopnFletch.fletch = 3;
			break;
		default:
			break;
		}
	   
	   switch (bankChoice.getSelectedItem().toString()) {
	   
		case "Draynor":
			ChopnFletch.bankToBank 	= BANK.DRAYNOR;
			ChopnFletch.anchor		= BANK.DRAYNOR.getSPOTS().getSpecificAnchor(treeChoice.getSelectedIndex());
			break;
		case "Seers Village":
			ChopnFletch.bankToBank 	= BANK.SEERSVILLAGE;
			ChopnFletch.anchor		= BANK.SEERSVILLAGE.getSPOTS().getSpecificAnchor(treeChoice.getSelectedIndex());
			break;
		case "The Grand Exchange":
			ChopnFletch.bankToBank	= BANK.GRANDEXCHANGE;
			ChopnFletch.anchor		= BANK.GRANDEXCHANGE.getSPOTS().getSpecificAnchor(treeChoice.getSelectedIndex());
			break;
		case "Varrock East":
			ChopnFletch.bankToBank	= BANK.VARROCKEAST;
			ChopnFletch.anchor		= BANK.VARROCKEAST.getSPOTS().getSpecificAnchor(treeChoice.getSelectedIndex());
		case "Catherby":
			ChopnFletch.bankToBank	= BANK.CATHERBY;
			ChopnFletch.anchor		= BANK.CATHERBY.getSPOTS().getSpecificAnchor(treeChoice.getSelectedIndex());
		default:
			break;
		}
	   
	   if(powercuttingChoice.isSelected()) {
		   ChopnFletch.anchor = ctx.players.local().tile();
	   }
	   
	   ChopnFletch.powerCut = powercuttingChoice.isSelected();
	   ChopnFletch.startScript = true;
	   
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
