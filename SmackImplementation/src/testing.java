//import java.util.Scanner;
//
//import org.jivesoftware.smack.Chat;
//import org.jivesoftware.smack.ConnectionConfiguration;
//import org.jivesoftware.smack.MessageListener;
//import org.jivesoftware.smack.XMPPConnection;
//import org.jivesoftware.smack.packet.Message;
//
//public class App {
//    public static void main(String[] args) throws Exception {
//        new Thread(){
//        public void run(){
//            try{
//                ConnectionConfiguration config = new ConnectionConfiguration("alumchat.fun", 5222);
//                config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//                config.setDebuggerEnabled(false);
//
//                XMPPConnection con = new XMPPConnection(config);
//                con.connect();
//                
//                //Login 
//                System.out.println("> Hello, welcome to the xmpp chat implementation...");
//                System.out.println("> Please sign-in to start....");
//
//                System.out.println("> Username: ");
//
//                Scanner userScanner = new Scanner(System.in);  // Create a Scanner object
//
//                String userName = userScanner.nextLine();  // Read user input
//
//                System.out.println("> Password: ");
//
//                Scanner passScanner = new Scanner(System.in);  // Create a Scanner object
//
//                String password = passScanner.nextLine();  // Read user input
//                
//                con.login(userName, password);
//
//
//                //Entering the user to start a chat
//                System.out.println("Enter the user that you want to chat with: ");
//
//                Scanner chatScanner = new Scanner(System.in);  // Create a Scanner object
//
//                String chatNameString = passScanner.nextLine();  // Read user input
//                
//                Chat chat = con.getChatManager().createChat(chatNameString, new MessageListener() {
//                    
//                    @Override
//                    public void processMessage(Chat chat, Message msg){
//                            System.out.println(chat.getParticipant() + " said: " + msg.getBody());
//                    }
//                });
//                
//                System.out.println("Connected");
//                
//                try (Scanner reader = new Scanner(System.in)) {
//                    while(con.isConnected()){
//                        chat.sendMessage( reader.nextLine());
//                    }
//                }
//                
//            }
//            
//            catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        }.start();
//    }
//}
//










// import java.util.Scanner;

// import org.jivesoftware.smack.Chat;
// import org.jivesoftware.smack.ChatManager;
// import org.jivesoftware.smack.ConnectionConfiguration;
// import org.jivesoftware.smack.MessageListener;
// import org.jivesoftware.smack.PacketListener;
// import org.jivesoftware.smack.XMPPConnection;
// import org.jivesoftware.smack.XMPPException;
// import org.jivesoftware.smack.filter.PacketFilter;
// import org.jivesoftware.smack.filter.PacketTypeFilter;
// import org.jivesoftware.smack.packet.Message;
// import org.jivesoftware.smack.packet.Packet;
// import org.jivesoftware.smack.packet.XMPPError;

// public class App {
//     public static XMPPConnection con;
//     public static Chat newChat;
//     public static ChatManager chatmanager;
//     public static void main(String[] args) throws Exception {
//         new Thread(){
//         public void run(){
//             while (true){


//                 try{
//                     ConnectionConfiguration config = new ConnectionConfiguration("alumchat.fun", 5222);
//                     config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//                     config.setDebuggerEnabled(false);
    
//                     con= new XMPPConnection(config);
//                     con.connect();
                    
//                     //AccountManager manager = con.getAccountManager();
//                     //manager.createAccount("User1", "hola");
    
//                     //Login 
//                     System.out.println("> Hello, welcome to the xmpp chat implementation...");
//                     System.out.println("> Please sign-in to start....");
    
//                     System.out.println("> Username: ");
    
//                     Scanner userScanner = new Scanner(System.in);  // Create a Scanner object
    
//                     String userName = userScanner.nextLine();  // Read user input
    
//                     System.out.println("> Password: ");

//                     Scanner passScanner = new Scanner(System.in);  // Create a Scanner object
    
//                     String password = passScanner.nextLine();  // Read user input
                    
//                     con.login(userName, password);

//                     addListener();

//                     System.out.println("Authenticated = " + con.isAuthenticated());
    
//                     //Entering the user to start a chat
//                     System.out.println("Enter the username that you want to chat with: ");
    
//                     Scanner chatScanner = new Scanner(System.in);  // Create a Scanner object
    
//                     String chatNameString = chatScanner.nextLine();  // Read user input

                    
//                     //Sending message to the entered user
                    
//                     newChat= con.getChatManager().createChat(chatNameString + "@alumchat.fun" , new MessageListener() {
                        
//                         @Override
//                         public void processMessage(Chat chat, Message msg){
//                                 System.out.println(chat.getParticipant() + " said: " + msg.getBody());
//                         }
//                     });
                    
                    
                    
//                     try (Scanner reader = new Scanner(System.in)) {
//                         while(con.isConnected()){
//                             newChat.sendMessage( reader.nextLine());
//                         }
//                     }
                    
//                 }
                
//                 catch(Exception e){
//                     e.printStackTrace();
//                 }
//             }
            
            
//         }
//         }.start();
//     }

//     public static void addListener(){
//         PacketFilter filterMessage = new PacketTypeFilter(Message.class);
//         PacketListener myListener = new PacketListener() {
//             public void processPacket(Packet packet){
//                 System.out.println(packet.toXML());
//                 System.out.println("From: " + packet.getFrom() + "\n");
//                 System.out.println("Body: " + ((Message) packet).getBody());

//                 try{
//                     newChat.sendMessage("hi!");

//                 }
//                 catch (XMPPException e) {
//                     e.printStackTrace();
//                 }
//             }
//         };
//         con.addPacketListener(myListener, filterMessage);
//     }
// }
