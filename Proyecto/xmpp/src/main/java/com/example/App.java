package com.example;

import java.util.Collection;
import java.util.Scanner;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

//import java.io.File;  // Import the File class

import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
//import org.jivesoftware.smackx.si.packet.StreamInitiation.File;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Resourcepart;


public final class App {

    public static String options = null;

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
    
    //private OutputStream outputStream;

    // private Jid initiator;

    // private Thread transferThread;

    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        new Thread(){    
        public void run(){
            try{
                XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("zombie","test")
                .setXmppDomain("alumchat.fun")
                .setHost("alumchat.fun")
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setSendPresence(true)
                .build();

                AbstractXMPPConnection connection = new XMPPTCPConnection(config);
                connection.connect(); //Establishes a connection to the server
                System.out.println("Connected");
                //Scanner user = new Scanner(System.in);
                connection.login(); //Logs in
                //EntityBareJid jid = JidCreate.entityBareFrom("" + "@" + connection.getHost());

                

                // Roster roster = Roster.getInstanceFor(connection);
                
                // if (!roster.isLoaded()) 
                //     roster.reloadAndWait();

                // Collection<RosterEntry> entries = roster.getEntries();
                // for (RosterEntry entry : entries) {
                //     System.out.println(entry);
                // }

                // while (connection.isConnected()){
                //     roster.addRosterListener(new RosterListener() {
                //         public void entriesAdded(Collection<Jid> addresses) { }
                //         public void entriesDeleted(Collection<Jid> addresses) {  }
                //         public void entriesUpdated(Collection<Jid> addresses) {  }
                //         public void presenceChanged(Presence presence) { 
                //             System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
                //         }
                //     });                                    
                // }

                // //Roster roster = Roster.getInstanceFor(con);
                //File file = new File("hello.txt");
               
                
                // EntityFullJid jid = JidCreate.entityFullFrom("hola2@upload.alumchat.fun/hola"); 
                // //#region FileManager
                // //Create the file transfer manager
                // FileTransferManager filemanager = FileTransferManager.getInstanceFor(connection);
                // File file = new File("/home/zombiewafle/Descargas/hello.java");
                // // Create the outgoing file transfer
                // OutgoingFileTransfer transfer = filemanager.createOutgoingFileTransfer(jid);
                // // Send the file
                // System.out.println(file.getAbsolutePath());

                // while(!transfer.isDone()) {
                //     if (transfer.getStatus().equals(Status.error)) {
                //         System.out.println("ERROR!!! " + transfer.getError());
                //     } else {
                //         System.out.println(transfer.getStatus());
                //         System.out.println(transfer.getProgress());
                //     }
                //     sleep(1000);
                // }

                // transfer.sendFile(file, 10, "You won't believe this!");
                

                //Create the file transfer manager
                //final FileTransferManager manager = FileTransferManager.getInstanceFor(connection);
                // Create the listener
                // manager.addFileTransferListener(new FileTransferListener() {
                // 	public void fileTransferRequest(FileTransferRequest request) {
                // 	// Check to see if the request should be accepted
                // 	if (shouldAccept(request)) {
                // 		// Accept it
                // 		IncomingFileTransfer transfer = request.accept();
                // 		transfer.recieveFile(new File("shakespeare_complete_works.txt"));
                // 	} else {
                // 		// Reject it
                // 		request.reject();
                // 	}
                // }
                // });
                ////#endregion

                //#region Users
                // AccountManager manager = AccountManager.getInstance(connection);
                // Localpart nickname = Localpart.from("Hola3");
                
                // try {
                //     if (manager.supportsAccountCreation()) {
                //         manager.sensitiveOperationOverInsecureConnection(true);
                //         manager.createAccount(nickname, "hola");

                //     }
                // } catch (SmackException.NoResponseException e) {
                //     e.printStackTrace();
                // } catch (XMPPException.XMPPErrorException e) {
                //     e.printStackTrace();
                //     System.out.println("Ya existe cuenta");
                // } catch (SmackException.NotConnectedException e) {
                //     e.printStackTrace();
                // }
                //#endregion


                //#region adding friends
                //Scanner user = new Scanner(System.in);
                // EntityBareJid jid = JidCreate.entityBareFrom("zombie" + "@alumchat.fun" );

                // try {
                //     Roster.setDefaultSubscriptionMode(Roster.SubscriptionMode.manual);
                //     Roster roster = Roster.getInstanceFor(connection);
    
                //     if (!roster.contains(jid)) {
                //         roster.createItemAndRequestSubscription(jid, "mnovella@alumchat.fun", null);
                //     } else {
                //         System.out.println("A friend has been added");
                //     }
    
                // } catch (SmackException.NotLoggedInException e) {
                //     e.printStackTrace();
                // } catch (SmackException.NoResponseException e) {
                //     e.printStackTrace();
                // } catch (SmackException.NotConnectedException e) {
                //     e.printStackTrace();
                // } catch (XMPPException.XMPPErrorException e) {
                //     e.printStackTrace();
                // } catch (Exception e) {
                //     e.printStackTrace();
                // }
                //#endregion


                //#region Chat 1v1
                // Scanner user = new Scanner(System.in);
                EntityBareJid jid = JidCreate.entityBareFrom("testingchat@conference." + connection.getHost());
                // EntityBareJid jid = JidCreate.entityBareFrom(user.nextLine() + "@alumchat.fun" );
                // ChatManager chatManager = ChatManager.getInstanceFor(connection);
                // //Chat chat = chatManager.chatWith(jidGroup);
                // Chat chat = chatManager.chatWith(jid);
                
                // chatManager.addIncomingListener(new IncomingChatMessageListener() {
                //     @Override
                //     public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                //       System.out.println("New message from " + from + ": " + message.getBody());
                //     }
                // });

                // Scanner messege = new Scanner(System.in);
                // while(connection.isConnected()){
                //     chat.send(messege.nextLine());
                // }
                //#endregion
                
                //#region Chat Grupal
                MultiUserChatManager manager = MultiUserChatManager.getInstanceFor(connection);
                MultiUserChat muc = manager.getMultiUserChat(jid);
                Resourcepart room = Resourcepart.from("zombiewafle");
                if (!muc.isJoined())
                    muc.join(room);

                muc.addMessageListener(new MessageListener() {
                    @Override
                    public void processMessage(Message message){
                        System.out.println((message != null ? message.getBody() : "NULL") + "  , Message sender :" + message.getFrom());
                    }
                });
                
                Scanner conteiner = new Scanner(System.in);
                String messegeString = "";
                System.out.println("Para ver la opciones en el menu precione 1: ");
                while(!messegeString.contains("~")){
                    messegeString = conteiner.nextLine();
                        if (messegeString.contains("1"))
                            System.out.println("Presione ~ para salir");
                        else
                            muc.sendMessage(messegeString);
                    }
                    
                    muc.leave();
                // //#endregion

                //#region Mostrar todo los usuarios
                // Roster roster = Roster.getInstanceFor(connection);

                // roster.addRosterListener(new RosterListener() {
                //     public void entriesAdded(Collection<Jid> addresses) { }
                //     public void entriesDeleted(Collection<Jid> addresses) {  }
                //     public void entriesUpdated(Collection<Jid> addresses) {  }
                //     public void presenceChanged(Presence presence) { 
                //         System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
                //     }
                // });

                // if (!roster.isLoaded()) 
                // try {
                //     roster.reloadAndWait();
                // } catch (SmackException.NotLoggedInException | SmackException.NotConnectedException | InterruptedException e) {
                //     e.printStackTrace();
                // }
                // Collection<RosterEntry> entries = roster.getEntries();
                // Presence presence;
                // for (RosterEntry entry : entries) {
                //     presence = roster.getPresence(entry.getJid());
                //     System.out.println(entry.getJid());
                //     System.out.println(presence.getType().name());
                //     System.out.println(presence.getStatus());
                // }                //#endregion
                

                // //#region Informacion de un usuario
                // //Roster roster = Roster.getInstanceFor(connection);
                // if (!roster.isLoaded()) 
                // try {
                //     roster.reloadAndWait();
                // } catch (SmackException.NotLoggedInException | SmackException.NotConnectedException | InterruptedException e) {
                //     e.printStackTrace();
                // }

                // //Scanner conteiner = new Scanner(System.in);
                
                // Scanner scanner = new Scanner(System.in);
                // String scann = scanner.nextLine();

                // System.out.println("\nInfo of the user:" + scann);

                // EntityBareJid jid = JidCreate.entityBareFrom(scann + "@" + connection.getHost());
                // Presence presen = roster.getPresence(jid);
                // System.out.println("Jid: " + presen.getFrom()); 
                // System.out.println("User name: " + presen.getType().name());
                // System.out.println("Status: " + presen.getStatus());
                // System.out.println("Root: " + presen.getElementName());
                // System.out.println("Mode: " + presen.getMode()); 	
                // System.out.println("Priority: " + presen.getPriority()); 	 	
                // System.out.println("Available: " + presen.isAvailable()); 	
                // //#endregion
                    
                System.out.println("Disconnected");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        }.start();
    }
}
