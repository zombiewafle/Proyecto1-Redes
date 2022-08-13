from argparse import ArgumentParser
import asyncio
import logging
from getpass import getpass
from slixmpp import ClientXMPP
from slixmpp.exceptions import IqError, IqTimeout

class RegisterBot(ClientXMPP):

    def __init__(self, jid, password):
        ClientXMPP.__init__(self, jid, password)

        
        #self.add_event_handler("register", self.registration)

        self.add_event_handler("user_register", self.register)

        self.add_event_handler("session_start", self.start)
        #self.add_event_handler("message", self.message)


    async def start(self, event):
        self.send_presence()
        self.get_roster()

        #def message(self, msg):
        #    if msg['type'] in ('chat', 'normal'):
        #        self.send_message(mto=msg['from'], 
        #        mbody='Thanks for sending a message')
        self.disconnect()

    def register(self, iq):
        resp = self.Iq()
        resp['type'] = 'set'
        resp['register']['username'] = self.boundjid.user
        resp['register']['password'] = self.password

        try:
            yield from resp.send()
            logging.info("Account created for %s!" % self.boundjid)

        except IqError as e:
            logging.error("Couldn't register the new account" % e.iq['error']['text'])

        except IqTimeout:
            logging.error("No response from the server...")
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

    # JID and password options.
    parser.add_argument("-j", "--jid", dest="jid",
                        help="JID to use")
    parser.add_argument("-p", "--password", dest="password",
                        help="password to use")
    

    args = parser.parse_args()

    logging.basicConfig(level=args.loglevel, 
                        format='%(levelname)-8s %(message)s')

    if args.jid is None:
        args.jid = input("Username: ")
    
    if args.password is None:
        args.password = getpass("Password: ")


    #MenuOptions= 0
    #print("Welcome to the XMPP implementation.... ")
    #while True:
    #    print("Please choose one of the options in the menu...")
    #    print("1. Private Messages")
    #    print("2. Group Messages")
    #    print("3. Send Presence")
    #    print("9. Exit")


    
    #if args.to is None:
    #    args.to = input("Send to: ")
    #
    #if args.message is None:
    #    args.message = input("Message: ")

    xmpp = RegisterBot(args.jid, args.password)
    xmpp.register_plugin('xep_0030')
    xmpp.register_plugin('xep_0004')
    xmpp.register_plugin('xep_0066')
    xmpp.register_plugin('xep_0077')

    xmpp['xep_0077'].force_registration = True

    #xmpp.connect()
    #xmpp.process()

    #logging.basicConfig(level=logging.DEBUG,
    #                    format='%(levelname)-8s %(message)s')

    #xmpp = EchoBot('test@alumchat.fun', 'test')
    xmpp.connect()
    xmpp.process()