package encipher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MailGUI extends JFrame implements ActionListener {
	JFrame f;
	JButton b;
	JTextField tf1;
	MailGUI()
	{
		setTitle("ENCIPHER");
		JLabel l1 = new JLabel("Enter the recievers email!");
	    l1.setFont(new Font("Osward", Font.BOLD, 25));
	    l1.setBounds(100,40,600,40);
	    add(l1);
	    JLabel l2 = new JLabel("Enter email");
	    l2.setFont(new Font("Osward", Font.BOLD, 15));
	    l2.setBounds(100,100,600,40);
	    add(l2);
	    tf1  = new JTextField(15);
	    tf1.setBounds(200,100,200,40);
	    tf1.setFont(new Font("Arial", Font.BOLD, 14));
	    add(tf1);
		b=new JButton("Submit");//create button  
		b.setBounds(350,150,100,40);  
	    b.setBackground(Color.BLACK);
	    b.setForeground(Color.WHITE);
		add(b);//adding button on frame  
		
	    b.addActionListener(this);
	    getContentPane().setBackground(Color.WHITE);
		setSize(800,600);  
		setLayout(null);  
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
        try{        
        	if(ae.getSource()==b)
        	{
        		String id  = tf1.getText();
        		setVisible(false);
        		new SendingMail(id);
        		
        	}
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	public static void main (String[] args)
	{
		new MailGUI();
	}
}