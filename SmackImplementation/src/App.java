import java.util.Scanner;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

public class App {
    public static void main(String[] args) throws Exception {
        new Thread(){
        public void run(){
            try{
                ConnectionConfiguration config = new ConnectionConfiguration("alumchat.fun", 5222);
                config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                config.setDebuggerEnabled(false);

                XMPPConnection con = new XMPPConnection(config);
                
                con.connect();
                
                String options = null;
                String options2 = null;

                Scanner scan = new Scanner(System.in); // Capturing the input
                System.out.println("> Hello, welcome to the xmpp chat implementation...");
                System.out.println("Enter one of the options in the menu");
                System.out.println("1. Login / Create Account \n9. Exit");
                do {
                    options = scan.nextLine();
                    System.out.println("Enter one of the options in the menu");
                    
                    switch (options) {
                        
                        case "1":
                            // Log-in / Sign-In

                            //Login 
                            
                            System.out.println("> Please sign-in to start....");

                            System.out.println("> Username: ");

                            Scanner userScanner = new Scanner(System.in);  // Create a Scanner object

                            String userName = userScanner.nextLine();  // Read user input

                            System.out.println("> Password: ");

                            Scanner passScanner = new Scanner(System.in);  // Create a Scanner object

                            String password = passScanner.nextLine();  // Read user input

                            con.login(userName, password);

                            System.out.println("\n\nHello " + userName + "...\n");

                            System.out.println("1. DM's \n2. RoomChats");

                            options2 = scan.nextLine();

                            switch (options2) {

                                case "1": //DMS
                                
                                    //Entering the user to start a chat
                                    System.out.println("Enter the user that you want to chat with: ");

                                    Scanner chatScanner = new Scanner(System.in);  // Create a Scanner object

                                    String chatNameString = chatScanner.nextLine();  // Read user input

                                    Chat chat = con.getChatManager().createChat(chatNameString + "@alumchat.fun", new MessageListener() {
                                        @Override
                                        public void processMessage(Chat chat, Message msg){
                                                System.out.println(chat.getParticipant() + " said: " + msg.getBody());
                                        }
                                    });

                                    System.out.println("Connected");
                                
                                    try (Scanner reader = new Scanner(System.in)) {
                                        while(con.isConnected()){
                                            chat.sendMessage( reader.nextLine());
                                        }
                                    }while (!options2.equals("9") ); // quitting the program

                                    break;


                                case "2": //Chat rooms
                                    break;

                                case "3": //Log out
                                    break;

                                
                                case "4": //Add new contacts
                                    break; 


                                case "5": //List all the contacts
                                    break;

                                case "6": //Send files
                                    break;
                                    
                                case "7": //Change status (Presence)
                                    break;

                                case "9": //Previous menu
                                    System.out.println("Going back ....");
                                    break;
                            }
                                
                            break;


                            case "9": //Finisshing the program
                                con.disconnect();
                                break;
                    }
                } while (!options.equals("9") ); // quitting the program
                
            }
            
            catch(Exception e){
                e.printStackTrace();
            }
        }
        }.start();
    }
}
