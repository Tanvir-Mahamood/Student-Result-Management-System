package project3;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//Form Design+ActionListener
//Output in JFrame

public class LogIn extends JFrame {
    private JLabel userL,passL;
    private JTextField tf;
    private JPasswordField pf;
    private JButton sub,clr;
    private Container c;
    private Font f;
    
    LogIn()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200,100,600,400);
        this.setTitle("Frame");
        
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.GRAY);
        
        f=new Font("Arial",Font.BOLD,18);
        
        userL=new JLabel("Username:");
        userL.setBounds(50,50,150,50);
        userL.setFont(f);
        c.add(userL);
        tf=new JTextField();
        tf.setBounds(170,50,200,50);
        c.add(tf);
        
        passL=new JLabel("Password:");
        passL.setBounds(50,120,150,50);
        passL.setFont(f);
        c.add(passL);
        pf=new JPasswordField();
        pf.setBounds(170,120,200,50);
        pf.setEchoChar('*');
        c.add(pf);
        
        sub=new JButton("Login");
        sub.setBounds(170,190,90,50);
        c.add(sub);
        clr=new JButton("Clear");
        clr.setBounds(280,190,90,50);
        c.add(clr);
        
        clr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("");
                pf.setText("");
            }
        });
        
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName=tf.getText();
                String password=pf.getText();
                
                if(userName.equals("Tanvir") && password.equals("2003062"))
                {
                    JOptionPane.showMessageDialog(null, "Successfully logined");
                    //dispose(); //LoginFrame will be vanished
                    Student frame= new Student();
                    frame.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalide Username or Password");
                }
            }
        });
    }
    
    
    public static void main(String[] args) {
        LogIn fr=new LogIn(); 
        fr.setVisible(true);
         
    }
}
