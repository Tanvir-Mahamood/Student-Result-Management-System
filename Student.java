package project3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Student extends JFrame implements ActionListener{
    private Container c;
    private JLabel titleL,rollL,lnL,phoneL,gpaL;
    private JTextField roll_Tf,lnTf,phoneTf,gpaTf;
    private JButton addBtn,updateBtn,deleteBtn,clearBtn,backBtn;
    private Font f;
    private JTable tbl;
    private DefaultTableModel model;
    private JScrollPane scroll;
    
    private String[] cols={"Roll No","Name","Phone Number","GPA"};
    private String[] rows=new String[4];  //Row means elements
    
    Student()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setBounds(100,100,780,690);
        this.setTitle("Student");
        
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.LIGHT_GRAY);
        
        f=new Font("Arial",Font.BOLD,16);
        
        titleL=new JLabel("Student Registration");
        titleL.setFont(f);
        titleL.setBounds(140, 10, 250, 50);
        c.add(titleL);
        
        rollL=new JLabel("Enter Roll:");
        rollL.setBounds(10, 80, 140, 30);
        c.add(rollL);
        roll_Tf=new JTextField();
        roll_Tf.setBounds(110, 80, 200, 30);
        c.add(roll_Tf);
        addBtn=new JButton("Add");
        addBtn.setBounds(400, 80, 100, 30);
        c.add(addBtn);
        
        lnL=new JLabel("Enter Name:");
        lnL.setBounds(10, 130, 150, 30);
        c.add(lnL);
        lnTf=new JTextField();
        lnTf.setBounds(110, 130, 200, 30);
        c.add(lnTf);
        updateBtn=new JButton("Update");
        updateBtn.setBounds(400, 130, 100, 30);
        c.add(updateBtn);
        
        phoneL=new JLabel("Phone Number:");
        phoneL.setBounds(10, 180, 150, 30);
        c.add(phoneL);
        phoneTf=new JTextField();
        phoneTf.setBounds(110, 180, 200, 30);
        c.add(phoneTf);
        deleteBtn=new JButton("Delete");
        deleteBtn.setBounds(400, 180, 100, 30);
        c.add(deleteBtn);
        
        gpaL=new JLabel("GPA:");
        gpaL.setBounds(10, 230, 150, 30);
        c.add(gpaL);
        gpaTf=new JTextField();
        gpaTf.setBounds(110, 230, 200, 30);
        c.add(gpaTf);
        clearBtn=new JButton("Clear");
        clearBtn.setBounds(400, 230, 100, 30);
        c.add(clearBtn);
        
        backBtn=new JButton("Back");
        backBtn.setBounds(400, 280, 100, 30);
        c.add(backBtn);
        
        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                LogIn frame= new LogIn();
                frame.setVisible(true);
            }
        });
        
        tbl=new JTable();
        model=new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        tbl.setModel(model);
        tbl.setFont(f);
        tbl.setSelectionBackground(Color.GREEN);
        tbl.setBackground(Color.WHITE);
        tbl.setRowHeight(30);
        
        tbl.setAutoCreateRowSorter(true);
        
        scroll=new JScrollPane(tbl);
        scroll.setBounds(10, 360, 740, 265);
        c.add(scroll);
        
        addBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        
        tbl.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me)
                {
                    int numOfRow=tbl.getSelectedRow();
                    
                    String roll_no=model.getValueAt(numOfRow, 0).toString();
                    String l_name=model.getValueAt(numOfRow, 1).toString();
                    String phone=model.getValueAt(numOfRow, 2).toString();
                    String gpa=model.getValueAt(numOfRow, 3).toString();
                    
                   roll_Tf.setText(roll_no);
                   lnTf.setText(l_name);
                   phoneTf.setText(phone);
                   gpaTf.setText(gpa);
                }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addBtn)
        {
            rows[0]=roll_Tf.getText();
            rows[1]=lnTf.getText();
            rows[2]=phoneTf.getText();
            rows[3]=gpaTf.getText();
            
            model.addRow(rows);
            //System.out.println(""+model.getRowCount());
        }
        else if(e.getSource()==clearBtn)
        {
            roll_Tf.setText("");
            lnTf.setText("");
            phoneTf.setText("");
            gpaTf.setText("");
        }
        
        else if(e.getSource()==deleteBtn)
        {
            int  numRow=tbl.getSelectedRow();
            if(numRow>=0)
            {
                model.removeRow(numRow);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No Row has been selected or s.");
            }
        }
        
        else if(e.getSource()==updateBtn)
        {
            int numberOfRow=tbl.getSelectedRow();
            
            String roll_no=roll_Tf.getText();
            String l_name=lnTf.getText();
            String phone=phoneTf.getText();
            String gpa=gpaTf.getText();
            
            model.setValueAt(roll_no, numberOfRow, 0);
            model.setValueAt(l_name, numberOfRow, 1);
            model.setValueAt(phone, numberOfRow, 2);
            model.setValueAt(gpa, numberOfRow, 3);
        }
        
        /*else if(e.getSource()==backBtn)
        {
            LogIn frame=new LogIn();
            frame.setVisible(true);
        }*/
    }
    
    public static void main(String[] args) {
        Student fr=new Student();
        fr.setVisible(true);  
    }
}

