package project3_1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class LogIn extends JFrame {

    private JLabel userL, passL;
    private JTextField tf;
    private JPasswordField pf;
    private JButton sub, clr;
    private Container c;
    private Font f;

    public LogIn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 600, 400);
        setTitle("Login");

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.GRAY);

        f = new Font("Arial", Font.BOLD, 18);

        userL = new JLabel("Username:");
        userL.setBounds(50, 50, 150, 50);
        userL.setFont(f);
        c.add(userL);

        tf = new JTextField();
        tf.setBounds(170, 50, 200, 50);
        c.add(tf);

        passL = new JLabel("Password:");
        passL.setBounds(50, 120, 150, 50);
        passL.setFont(f);
        c.add(passL);

        pf = new JPasswordField();
        pf.setBounds(170, 120, 200, 50);
        pf.setEchoChar('*');
        c.add(pf);

        sub = new JButton("Login");
        sub.setBounds(170, 190, 90, 50);
        c.add(sub);

        clr = new JButton("Clear");
        clr.setBounds(280, 190, 90, 50);
        c.add(clr);

        clr.addActionListener(e -> {
            tf.setText("");
            pf.setText("");
        });

        sub.addActionListener(e -> {
            String userName = tf.getText();
            String password = new String(pf.getPassword());

            if (validateLogin(userName, password)) {
                JOptionPane.showMessageDialog(null, "Successfully logged in");
                dispose();
                new Student().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        });
    }

    private boolean validateLogin(String user, String pass) {
        File file = new File("users.txt");
        if (!file.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(user) && data[1].equals(pass)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Login file error");
        }
        return false;
    }

    public static void main(String[] args) {
        new LogIn().setVisible(true);
    }
}
