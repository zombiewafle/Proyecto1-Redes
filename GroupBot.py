from argparse import ArgumentParser
import asyncio
from email import message
import logging
from getpass import getpass
from slixmpp import ClientXMPP


class GroupBot(ClientXMPP):

    def __init__(self, jid, password, room, nick):
        ClientXMPP.__init__(self, jid, password)

        
        #self.add_event_handler("message", self.message)
        #self.add_event_handler("register", self.registration)

        self.room = room
        self.nick = nick

        self.add_event_handler("session_start", self.session_start)
        self.add_event_handler("group_message", self.group_message)
        self.add_event_handler("muc::%::got_online" % self.room, self.muc_online)


        xmpp.register_plugin('xep_0045')

    async def session_start(self, event):
        self.send_presence()
        await self.get_roster()

        self.plugin['xep_0045'].join_muc(self.room, self.nick)


    def group_message(self, message):
        if message['mucnick'] != self.nick and self.nick in message['body']:
            self.send_message(mto=message['from'].bare, mbody="I heard that, %s." % message['mucnick'], mtype='groupchat')
        #def message(self, msg):
        #    if msg['type'] in ('chat', 'normal'):
        #        self.send_message(mto=msg['from'], 
        #        mbody='Thanks for sending a message')
        
    def muc_online(self, presence):
        if presence['muc']['nick'] !=self.nick:
            self.send_message(mto=presence['from'].bare,
                            mbody="Hello, %s %s" % (presence['muc']['role'], 
                                                    presence['muc']['nick']), 
                                                    mtype='groupchat')
        self.disconnect()



if __name__ == '__main__':
    # Ideally use optparse or argparse to get JID,
    # password, and log level.
    
    parser = ArgumentParser(description=GroupBot.__doc__)

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

    parser.add_argument("-r", "--room", dest="room",
                        help="Room chat to join")

    parser.add_argument("-n", "--nick", dest="nick",
                        help="Nickname")

    
    args = parser.parse_args()

    logging.basicConfig(level=args.loglevel, 
                        format='%(levelname)-8s %(message)s')

    if args.jid is None:
        args.jid = input("Username: ")
    
    if args.password is None:
        args.password = getpass("Password: ")

    if args.room is None:
        args.room = input("Groupchat room to join: ")

    if args.nick is None:
        args.nick = input("Group nickname: ")

    #if args.to is None:
    #    args.to = input("Send to: ")
    
    #if args.message is None:
    #    args.message = input("Message: ")

    xmpp = GroupBot(args.jid, args.password, args.room, args.nick)
    xmpp.register_plugin('xep_0030')
    #xmpp.register_plugin('xep_0004')
    #xmpp.register_plugin('xep_0060')
    xmpp.register_plugin('xep_0199')
    xmpp.register_plugin('xep_0045')


    #xmpp.connect()
    #xmpp.process()

    #logging.basicConfig(level=logging.DEBUG,
    #                    format='%(levelname)-8s %(message)s')

    #xmpp = EchoBot('test@alumchat.fun', 'test')
    xmpp.connect()
    xmpp.process()