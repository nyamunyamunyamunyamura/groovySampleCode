import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap;
import groovy.xml.*

def Message processData(Message message) {
	def body = message.getBody(String)
	def parsedXml = new XmlParser().parseText(body)

	parsedXml.book.each{ data ->
		if (data.auther.text() == 'Ralls, Kim'){
            data.appendNode('DataFlag', 1)
        } else {
            data.appendNode('DataFlag', 0)
        }
	}
    message.setBody(XmlUtil.serialize(parsedXml))
    return message
}
