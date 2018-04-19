import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class LanderDash extends JFrame implements Runnable {
    public static final long serialVersionUID = 2L;
    public static void main ( String[] args ) throws UnknownHostException {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                new LanderDash(args[0], Integer.parseInt(args[1]));
            }
        });
    }

    /* Information from Lander to Display */
    /* TODO  Declare some variables to hold Information
        from the game controller to display
    */
    private InetSocketAddress address;

    /* TODO Declare pannels to display Information
    */

    /* Panel to display IP and port numnber */
    DatagramPanel connection = new DatagramPanel();


    public LanderDash(String ip, int port){
        super("Lunar Lander Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(
            new BoxLayout(getContentPane(),BoxLayout.Y_AXIS) );

        /* TODO add pannels to window */
        address = new InetSocketAddress(ip, port);
        connection.setAddress(address);
        add( connection ) ;

        pack();
        setVisible(true);
         (new Thread(this)).start();
    }

    public void run(){
        try {
            DatagramSocket socket = new DatagramSocket(address);
            while(true){
                /* set up socket for reception */
                if(socket!=null){
                /* start with fresh datagram packet */
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive( packet );
                    /* extract message and pick appart into
                       lines and key:value pairs
                    */
                    String message = new String(packet.getData());

                    /*TODO adjust message parsing, if you're not
                        using key:value pairs in a line oriented
                        protocol
                    */
                    String[] lines = message.trim().split("\n");
                    for(String l : lines) {
                        String[] pair = l.split(":");
                        /* TODO act on key value pairs,
                            setting properties to display */
                    }
                }
                try{Thread.sleep(100);}catch(InterruptedException e){}
            }
        }
        catch(Exception e) {
            System.err.println(e);
            System.err.println("in LanderDash.run()");
            System.exit(-1);
        }
    }
}
