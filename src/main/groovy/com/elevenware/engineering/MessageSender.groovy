package com.elevenware.engineering

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.DeliveryMode
import javax.jms.Session

class MessageSender {

    def sendFile(filePath, brokerUri, qName) {
        def file = new File(filePath)
        if ( !file.exists() ) {
            println "'$filePath' does not exist. Sorry"
            System.exit(-1)
        }
        def message = file.text
        send(message, brokerUri, qName)
    }

    def send(message, brokerUri, qName) {


        println "Sending ${message} to ${qName} @ ${brokerUri}"

        def connectionFactory = new ActiveMQConnectionFactory(brokerUri)
        def connection = connectionFactory.createConnection()
        connection.start()

        println "Connection started"

        def session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)

        if (! session ) {
            println "No can haz session :("
            System.exit(-1)
        }

        def destination = session.createQueue(qName)
        def producer = session.createProducer(destination)
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT)
        def textMessage = session.createTextMessage(message)

        producer.send(textMessage)

        println "Message sent"

        session.close()
        connection.close()

        println "Cleaned up - exiting"
    }
}
