import java.util.Scanner;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

public class App {
    public static String options = null;
    public static ConnectionConfiguration config = new ConnectionConfiguration("alumchat.fun", 5222);
    public static XMPPConnection con = new XMPPConnection(config);
    public static AccountManager manager = con.getAccountManager();
    public static  MessageListener listener;

    public static void Menu(){
        System.out.println("""
            --------Enter one of the options in the menu--------

            1. Login
            2. Create account
            3. DM's
            4. Create roomChat
            5. List of roomChats
            6. Send Files
            7. List all the contacts
            8. Add a new contact
            9. Delete user's account
           10. Log Out
            0. Exit
            ----------------------------------------------------

            """);
            
            Scanner scan = new Scanner(System.in); // Capturing the input
            options = scan.nextLine();
    }
  
    
    public static void main(String[] args) throws Exception {
        new Thread(){
        public void run(){
            try{
                
                config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                config.setDebuggerEnabled(false);

                con.connect();
                

                //Scanner scan = new Scanner(System.in); // Capturing the input
                System.out.println("> Hello, welcome to the xmpp chat implementation...");
                //System.out.println("1. Login / Create Account \n9. Exit");
                do {
                    
                    //System.out.println("Enter one of the options in the menu");
                    Menu();
                    switch (options) {
                        
                        case "1":// Log-in / Sign-In

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
 
                            break;

                        
                        case "2": //CREATE ACCOUNT
                            System.out.println("Please enter the username: ");
                                    
                            Scanner newUserScanner = new Scanner(System.in);  // Create a Scanner object
                            String newUserName = newUserScanner.nextLine();  // Read user input

                            System.out.println("Please enter the password: ");

                            Scanner newPassScanner = new Scanner(System.in);  // Create a Scanner object
                            String newPass = newPassScanner.nextLine();  // Read user input

                            manager.createAccount(newUserName,newPass);
                            System.out.println("User has been created\n");

                            break;

                        case "3": //DMS
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
                            Scanner reader = new Scanner(System.in);
                            System.out.println(reader.nextLine().contains("~"));
                            do {
                                chat.sendMessage(reader.nextLine());
                            } while (con.isConnected() && reader.nextLine().contains("~") == false);
                            
                            break;
                        
                        case "9":
                            System.out.println("Enter the account that you want to remove from the server: ");
                            
                            con.getAccountManager().deleteAccount();
                            Menu();
                            break;
                        
                        case "10":
                            con.disconnect();
                            Menu();
                            break;

                    }
                } while (!options.equals("0") ); // quitting the program
                
            }
            
            catch(Exception e){
                
                e.printStackTrace();
                Menu();
            }
        }
        }.start();
    }
}