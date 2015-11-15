package ChopnFletch;



import java.awt.*;      
import java.awt.event.*; 

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.powerbot.script.rt4.ClientContext;

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
      contentPane.add(bankChoice);
      
      powercuttingChoice = new JRadioButton("Powercutting");
      powercuttingChoice.setBounds(110, 35, 100, 20);
      contentPane.add(powercuttingChoice);
      
      JLabel treeLabel = new JLabel("Tree");
	  treeLabel.setBounds(12,60,50,30);
	  contentPane.add(treeLabel);
	  
	  treeChoice = new JComboBox(treeChoices);
	  treeChoice.setBounds(10, 85, 100, 30);
	  treeChoice.setSelectedIndex(0);
      contentPane.add(treeChoice);
  
	  JLabel fletchLabel = new JLabel("Fletch");
	  fletchLabel.setBounds(12, 115, 50, 30);
	  contentPane.add(fletchLabel);
	  
	  fletchChoice = new JComboBox(fletchChoices);
	  fletchChoice.setBounds(10, 140, 100, 30);
	  contentPane.add(fletchChoice);
	  
	  startButton = new Button("Start bot");
	  startButton.setBounds(10, 180, 70, 30);
	  
	  startButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			onStart();
		}
	  });
	  
	  contentPane.add(startButton);
	   
   }  
}
