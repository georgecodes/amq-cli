# AMQ-CLI

A command-line utility for interacting with AtiveMQ brokers

## Usage

Installing the project will put a jar-with-dependencies in your M2 repo.

You can call the jar directly using:

    java -cp <path.to.jar> <systemProperties> com.elevenware.engineering.AmqCli

Or you can install it in ~/.m2 and use the provided bash scripts

### Using the jar directly

After the classpath, but before the classname, include all relevant system properties:

    -DbrokerUri=  the broker URI of your ActiveMQ broker
    -DqName=      the queue to be sent to/consumed from
    -Dmessage=    a text message to send to the queue
    -Dfile=       path to a file whose text will be sent to the queue
    -Dtimeout=    how long to wait for a message (receive only)

If you set both message and file, file takes precedence

### Using the scripts

The jmssend script takes the following arguments

    -b brokerUri
    -t textMessage
    -q queue name
    -f filename

and jmsreceive takes the following

    -b brokerUri
    -q queue name
    -t timeout

### Limitations

This is pretty basic stuff at the moment, knocked together in half an hour for a quick smoke test. I'll probably get around to making it more robust and usable at some point.
