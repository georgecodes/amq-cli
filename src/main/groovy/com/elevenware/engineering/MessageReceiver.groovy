package com.elevenware.engineering

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.DeliveryMode
import javax.jms.ExceptionListener
import javax.jms.Session


class MessageReceiver {

    def getMessage(brokerUri, qName, timeout = 30000L) {


        println "Waiting for message on ${qName} @ ${brokerUri}"

        def connectionFactory = new ActiveMQConnectionFactory(brokerUri)
        def connection = connectionFactory.createConnection()
        connection.setExceptionListener({

            println "OOPS $it"

        } as ExceptionListener)

        connection.start()

        println "Connection started"

        def session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)

        if (! session ) {
            println "No can haz session :("
            System.exit(-1)
        }

        def destination = session.createQueue(qName)
        def consumer = session.createConsumer(destination)

        println "Waiting for ${timeout} milliseconds for a message"

        def textMessage = consumer.receive(timeout)

        println "Recieved message"

        consumer.close();
        session.close();
        connection.close();

        println "Cleaned up - exiting"

        if ( textMessage.hasProperty('text')) {
            println "message\n\n${textMessage.text}"
        }  else {
            println "message\n\n$textMessage"
        }


    }

}
