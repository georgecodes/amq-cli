package com.elevenware.engineering

class AmqCli {

    static def main(args) {

        def action = System.getProperty('action')
        def brokerUri = System.getProperty('brokerUri')
        def qName = System.getProperty('qName')
        def message = System.getProperty('message')
        def timeout = Long.getLong('timeout')
        def filePath = System.getProperty('file')

        if ( action == 'send' ) {
            def sender = new MessageSender()
            if ( filePath ) {
                sender.sendFile(filePath, brokerUri, qName)
            } else {
                sender.send(message, brokerUri, qName)
            }
        }

        if ( action == 'receive' ) {
            new MessageReceiver().getMessage(brokerUri, qName, timeout)
        }

    }

}
