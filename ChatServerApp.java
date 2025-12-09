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
        bsub.setBounds(100,120,100,40);

        fmsg = new JLabel("Type here:");
        fmsg.setBounds(50,180,200,30);

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
            fmsg.setText("Bye...");
        }
    }
}


class ChatServerApp
{
    public static void main(String A[]) throws Exception
    {
        SwingUtilities.invokeLater
        (() -> 
        {
            new ChatApp("Server Chat Window", 400, 400);
        });

        {
        ServerSocket ssobj = new ServerSocket(5100);
        System.out.println("Server is waiting at  port 5100");

        Socket sobj = ssobj.accept();
        System.out.println("Client request gets accepted succefully");

        PrintStream pobj = new PrintStream(sobj.getOutputStream());
        BufferedReader bobj1 = new BufferedReader (new InputStreamReader(sobj.getInputStream()));
        BufferedReader bobj2 = new BufferedReader (new InputStreamReader(System.in));

        String str1 = null , str2 = null;

        while((str1 = bobj1.readLine())!= null)
        {
            System.out.println("Client says : "+str1);
            System.out.println("Enter message for client : ");
            str2 = bobj2.readLine();
            pobj.println(str2); 
        }
    }

    }    
}