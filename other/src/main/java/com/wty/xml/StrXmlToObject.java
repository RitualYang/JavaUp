package com.wty.xml;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import com.wty.XmlUtils;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.lang3.StringUtils;


/**
 * @author peter
 * @date 2022/5/6 12:54
 */
public class StrXmlToObject {

    public static void main(String[] args) throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ImpactRadiusResponse>\n"
                + "  <Status>QUEUED</Status>\n"
                + "  <QueuedUri>/Advertisers/IRFsKLfFzDcP3147054iA3rUo8U4ri4Rk1/APISubmissions/A-50304594-6119-441d-af49-7af0d655027b</QueuedUri>\n"
                + "</ImpactRadiusResponse>";
//        JSONObject jsonObject = XML.toJSONObject(xml);
        ImpactRadiusResponse impactRadiusResponse = XmlUtils.xmlToObject(ImpactRadiusResponse.class, xml);
        System.out.println(impactRadiusResponse);
        String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ImpactRadiusResponse>\n"
                + "    <APISubmission>\n"
                + "        <BatchId>A-50304594-6119-441d-af49-7af0d655027b</BatchId>\n"
                + "        <AccountId>3147054</AccountId>\n"
                + "        <SubmissionDate>2022-05-05T11:00:04-07:00</SubmissionDate>\n"
                + "        <CampaignId>15090</CampaignId>\n"
                + "        <ActionTrackerId>28303</ActionTrackerId>\n"
                + "        <MediaPartnerId>49764</MediaPartnerId>\n"
                + "        <EventCode/>\n"
                + "        <OrderId>21</OrderId>\n"
                + "        <Payload>CampaignId=15090&ActionTrackerId=28303&EventDate=2022-05-04T13%3A37%3A42Z&UserAgent=&IpAddress=10.80.10.214&OrderId=21&CustomerId=4222918&CustomerEmail=&CurrencyCode=USD&ItemSubTotal=0&ItemCategory=FirstTrade&ItemSku=sBTCUSDT&ItemQuantity=1</Payload>\n"
                + "        <Type>Conversion</Type>\n"
                + "        <Status>Complete</Status>\n"
                + "        <CompletionDate>2022-05-05T11:04:15-07:00</CompletionDate>\n"
                + "        <ErrorType/>\n"
                + "        <ErrorReason/>\n"
                + "        <Uri>/Advertisers/IRFsKLfFzDcP3147054iA3rUo8U4ri4Rk1/APISubmissions/A-50304594-6119-441d-af49-7af0d655027b</Uri>\n"
                + "    </APISubmission>\n"
                + "</ImpactRadiusResponse>";

        ImpactRadiusResponse impactRadiusResponse2 = XmlUtils.xmlToObject(ImpactRadiusResponse.class, StringUtils.replace(xml2, "&", ";"));
        System.out.println(impactRadiusResponse2);
    }



}
