# Proyecto1-Redes
El programa tiene funciones como:
- Inicio de sesión
- Creacion de usuarios
- Mensajes privados
- Mensaes a traves de un chat grupal
- Agregar contactos
- Desloguear 
- Enviar/recibir notificaciones

  Se implementaron manejadores de distintos tipos de archivos como: FileManager, ChatManager, MultiUserChatManager, etc. 

  De momento existen dos versiones del mismo programa, el primero siendo en Smack 3.0.1 y el segundo en Smack 4.4.1
  
  Para poder compilar el código se debe tener instalado JDK 17. Además de que se configuraron los archivos de Maven, para que se reconocieran las 
  funciones propias de Smack 4.
  
  Durante el proyecto aprendí de como funciona realmente un chat. Carácteristicas como lo que implica agregar a alguien más como usuario, ya que en XMPP
  que está más orientado a la seguridad pide permisos para agregar a alguien más, en comparación con alternativas como Whatsapp que no permite aceptar 
  o denegar la solicitud
  
Las dependencias utilizadas son: 
  Smack 3.0:
     - AccountManager
     - Chat
     - ChatManager
     - ConnectionConfiguration
     - MessageListener
     - XMPPConnection
     - Message
     
   Smack 4.4:
     - AbstractXMPPConnection
     - ConnectionConfiguration
     - Chat
     - ChatManager
     - IncomingChatMessageListener
     - Message
     - XMPPTCPConnection
     - File
     - EntityBareJid
     - Jid
     - JidCreate
