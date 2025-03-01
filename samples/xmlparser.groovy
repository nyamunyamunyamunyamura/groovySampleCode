import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlParser

Message processData(Message message) {
	def body = message.getBody(String)
	def parsedXml = new XmlParser().parseText(body)

	parsedXml.record.each{ data ->
		
		
	}
}
