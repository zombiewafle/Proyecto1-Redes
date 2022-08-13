from argparse import ArgumentParser
import asyncio
import logging
from getpass import getpass
from slixmpp import ClientXMPP


class LogOutBot(ClientXMPP):

    def __init__(self, jid, password):
        ClientXMPP.__init__(self, jid, password)
        

        self.add_event_handler("session_start", self.start)
        #self.add_event_handler("message", self.message)


    async def start(self, event):
        self.send_presence()
        await self.get_roster()

        #def message(self, msg):
        #    if msg['type'] in ('chat', 'normal'):
        #        self.send_message(mto=msg['from'], 
        #        mbody='Thanks for sending a message')
        self.disconnect()


if __name__ == '__main__':
    # Ideally use optparse or argparse to get JID,
    # password, and log level.
    
    parser = ArgumentParser()

    # Output verbosity options.
    parser.add_argument("-q", "--quiet", help="set logging to ERROR",
                        action="store_const", dest="loglevel",
                        const=logging.ERROR, default=logging.INFO)
    
    parser.add_argument("-d", "--debug", help="set logging to DEBUG",
                        action="store_const", dest="loglevel",
                        const=logging.DEBUG, default=logging.INFO)

    parser.add_argument("-j", "--jid", dest="jid", 
                        help="JID to use")

    parser.add_argument("-p", "--password", dest="password", 
                        help="password to use")

    parser.add_argument("-t", "--to", dest="to", 
                        help="JID to send the message to")

    parser.add_argument("-r", "--room", dest="room", 
                        help="Room chat to join")
    
    parser.add_argument("-n", "--nick", dest="nick", 
                        help="Nickname")
    
    args = parser.parse_args()

    logging.basicConfig(level=args.loglevel, 
                        format='%(levelname)-8s %(message)s')

    #if args.jid is None:
    #    args.jid = input("Username: ")
    #
    #if args.password is None:
    #    args.password = getpass("Password: ")
#
    #if args.recipient is None:
    #    args.password = getpass("Password: ")

    menuOptions= 0
    subMenuOptions = 0
    print("Welcome to the XMPP implementation.... ")
    ans = True
    while ans:
        print("Please choose one of the options in the menu...")
        print("1. Login")
        print("9. Exit")

        #print("1. Private Messages")
        #print("2. Group Messages")
        #print("3. Send Presence")
        #print("9. Exit")

        menu = input("Type the number of the option in the menu")
        if menu == 1:
            print("1. Private Messages")
            print("2. Group Messages")
            print("3. Send Presence")
            print("4. Log out")
            print("5. Remove account from server")
            print("6. List all the users in the server and their status")
            print("7. Add a new contact")
            print("8. Change the status message (Presence message)")
            print("9. Send notifications")
            print("0. Send files")
            print("20. Exit")

            subMenuOptions = input("Please choose one of the options of the Sub Menu")
            
            if(subMenuOptions == 1):
                pass
                #Will use the MessageBot class

            elif(subMenuOptions == 2):
                pass
                #Will use the GroupMessageBot

            elif(subMenuOptions == 3):
                pass
                #Will send a presence

            elif(subMenuOptions == 4):
                pass
                #Will Log Out

            elif(subMenuOptions == 5):
                pass
                #Remove the account from the server

            elif(subMenuOptions == 6):
                pass
                #Will list all the users in the server

            elif(subMenuOptions == 7):
                pass
                #Will add a new contact for a chat

            elif(subMenuOptions == 8):
                pass
                #Will change the status 

            elif(subMenuOptions == 9):
                pass
                #Will send a notification
            elif(subMenuOptions == 0):
                pass
                #Will send files to another user


        elif menu == 9:
            ans = False
            exit()



    
    #if args.to is None:
    #    args.to = input("Send to: ")
    #
    #if args.message is None:
    #    args.message = input("Message: ")

    xmpp = MessageBot(args.jid, args.password, args.to, args.message)
    xmpp.register_plugin('xep_0030')
    #xmpp.register_plugin('xep_0004')
    #xmpp.register_plugin('xep_0060')
    xmpp.register_plugin('xep_0199')

    #xmpp.connect()
    #xmpp.process()

    #logging.basicConfig(level=logging.DEBUG,
    #                    format='%(levelname)-8s %(message)s')

    #xmpp = EchoBot('test@alumchat.fun', 'test')
    xmpp.connect()
    xmpp.process(forever=False)
