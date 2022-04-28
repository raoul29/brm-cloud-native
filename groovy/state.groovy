#!/usr/bin/groovy
import java.lang.management.*
import javax.management.ObjectName
import javax.management.remote.JMXConnectorFactory as JmxFactory
import javax.management.remote.JMXServiceURL as JmxUrl
import javax.naming.Context


String jmxServerUrl =  'service:jmx:rmi:///jndi/rmi://ecs1-0.ece-server.brm12-apps.svc.cluster.local:31022/jmxrmi'

def attributes = new Hashtable();
def username = "controlRole";
def password = "R&D";

if (username != null && !username.trim().equalsIgnoreCase("")) {
   def credentials = [username, String.valueOf(password)]             
   attributes.put("jmx.remote.credentials", (String[]) credentials)         
}

def server = JmxFactory.connect(new JmxUrl(jmxServerUrl), attributes).MBeanServerConnection

String beanName = "ECE State Machine:type=StateManager"
def dataSystem = new GroovyMBean(server, beanName)
println "Current state : $dataSystem.stateName"
