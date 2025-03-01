import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap
import groovy.xml.*

def Message processData(Message message) {
    // Get Properties
    properties = message.getProperties()
    // XMLをパース
    def xml1 = new XmlParser().parseText(properties.get('xml1'))
    def xml2 = new XmlParser().parseText(properties.get('xml2'))

    // input2 のデータをリスト化して検索しやすくする
    def records2 = xml2.record

    // input1 の各 record に対し、input2 の対応する record を検索し、マージ
    xml1.record.each { record1 ->
        def key1 = record1.key1.text()
        def key2 = record1.key2.text()

        // key1 と key2 の両方が一致するレコードを検索
        def record2 = records2.find { it.key1.text() == key1 && it.key2.text() == key2 }

        if (record2) {
            // price と taxrate を追加
            record1.appendNode('price', record2.price.text())
            record1.appendNode('taxrate', record2.taxrate.text())
        }
    }

    // XMLを設定
    message.setBody(XmlUtil.serialize(xml1))
    return message
}
