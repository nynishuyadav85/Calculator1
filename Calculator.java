import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Calculator extends JFrame
{
  JTextField tb=new JTextField("0");
  JPanel pa=new JPanel();
  JButton [] bt=new JButton[20];
  double num;
  boolean ao=false;
  String op="";
  public Calculator()
  {
	super("Calculator");
	setSize(300,300);
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	addTextbox();
	addButtons();
	setVisible(true); 
  }
  private void addTextbox()
  {
	tb.setBounds(10,10,270,40);
	tb.setHorizontalAlignment(JTextField.RIGHT);
	tb.setFont(new Font("arial",Font.BOLD,20));
	tb.setEditable(false);
	tb.setBackground(Color.white);
	tb.setBorder(BorderFactory.createLineBorder(Color.black,1));
	add(tb);
  }
  private void addButtons()
  {
	pa.setBounds(10,70,270,190);
	add(pa);
	pa.setLayout(new GridLayout(5,4,5,5));
	String []str={"Back","CE","C","%","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	Font fo=new Font("arial",Font.PLAIN,18);
	CalListener listener=new CalListener();
	for(int i=0;i<20;i++)
	{
	  bt[i]=new JButton(str[i]);
	  bt[i].addActionListener(listener);
	  bt[i].setFont(fo);
	  if(i==3 || i==7 || i==11 || i==15 || i==18 || i==19)
	    bt[i].setForeground(Color.red);
	  else
	    bt[i].setForeground(Color.blue);
	  pa.add(bt[i]);
	}
	bt[0].setMargin(new Insets(0,0,0,0));
  }
  class CalListener implements ActionListener
  {
    public void actionPerformed(ActionEvent evt)
    {
	JButton bb=(JButton)evt.getSource();
	String btext=bb.getText();
	String ttext=tb.getText();
	if(bb==bt[3] || bb==bt[7] || bb==bt[11] || bb==bt[15] || bb==bt[19])
	{
	  num=Float.parseFloat(ttext);
	  ao=true;
	  op=btext;
	}
	if(bb==bt[4] || bb==bt[5] || bb==bt[6] || bb==bt[8] || bb==bt[9] || bb==bt[10] || bb==bt[12] || bb==bt[13] || bb==bt[14] || bb==bt[16] || bb==bt[17])
	{
	  if(ttext.equals("0") && btext.equals("."))  
	  {
	    tb.setText(ttext+btext);
	    return;
	  } 
	  if(ttext.equals("0") || ao)
	  {
	    tb.setText(btext);
	    ao=false;
	  }
	  else
	    tb.setText(ttext+btext);
	}
	if(bb==bt[18])//= button
	{
	  cal();
	}
	if(bb==bt[2])//C button
	{
	  num=0;
	  op="";
	  ao=false;
	  tb.setText("0");
	}
	if(bb==bt[1])//CE button
	{
	  tb.setText("0");
	}
	if(bb==bt[0])//back button
	{
	  if(ttext.length()==1)
	  {
	    tb.setText("0");
	    return;
	  }
	  ttext=ttext.substring(0,ttext.length()-1);
	  tb.setText(ttext);
	}
    }
    private void cal()
    {
	double x=Float.parseFloat(tb.getText());
	if(op.equals("+"))
	{
	  double res=num+x;
	  setResult(res);
	}
	if(op.equals("-"))
	{
	  double res=num-x;
	  setResult(res);
	}
	if(op.equals("*"))
	{
	  double res=num*x;
	  setResult(res);
	}
	if(op.equals("/"))
	{
	  double res=num/x;
	  setResult(res);
	}
	if(op.equals("%"))
	{
	  double res=num%x;
	  setResult(res);
	}
    }
    private void setResult(double res)
    {
	int n=(int)res; 
	if(n==res)
	   tb.setText(String.valueOf(n));
	else
	   tb.setText(String.valueOf(res));
    }
  }
  public static void main(String args[])
  {
	JFrame.setDefaultLookAndFeelDecorated(true);
	new Calculator();
  }
}





