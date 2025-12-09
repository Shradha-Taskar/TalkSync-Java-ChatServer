import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

class ChatApp implements ActionListener
{
    JFrame fobj;
    JTextField tmsg1;
    JLabel lmsg1, fmsg;
    JButton bsub;

    public ChatApp(String title, int width, int height)
    {
        fobj = new JFrame(title);

        lmsg1 = new JLabel("Message:");
        lmsg1.setBounds(50,50,100,30);

        tmsg1 = new JTextField();
        tmsg1.setBounds(150,50,150,30);

        bsub = new JButton("SEND");
        bsub.setBounds(50,120,100,40);

        fmsg = new JLabel("Type here: ");
        fmsg.setBounds(150,180,200,30);
        
        fobj.add(lmsg1);
        fobj.add(tmsg1);
        fobj.add(bsub);
        fobj.add(fmsg);

        bsub.addActionListener(this);

        fobj.setSize(width, height);
        fobj.setLayout(null);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fobj.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            String msg1 = tmsg1.getText();
            fmsg.setText("Message sent: " + msg1);

            String msg2 = lmsg1.getText();
            fmsg.setText("Message recived: " + msg2);

        }
        catch(Exception ex)
        {
            fmsg.setText("Error sending message!");
        }
    }
}


class ChatClientApp
{
    public static void main(String A[]) throws Exception
    {
        SwingUtilities.invokeLater
        (() -> 
        {
            new ChatApp("Client Chat Window", 400, 400);
        });
        {
        


            Socket sobj = new Socket("localhost",5100);
            System.out.println("Client gets connected with the server successfully");

            PrintStream pobj = new PrintStream(sobj.getOutputStream());
            BufferedReader bobj1 = new BufferedReader (new InputStreamReader(sobj.getInputStream()));
            BufferedReader bobj2 = new BufferedReader (new InputStreamReader(System.in));

            String str1 = null , str2 = null;
            System.out.println("Enter message for server : ");
            while(!(str1 = bobj2.readLine()).equals("End"))
                {
                    pobj.println(str1);
                    str2 = bobj1.readLine();
                    System.out.println("Servers says : "+ str2);
                    System.out.println("Enter message for server : ");
                }
        }
    }
}