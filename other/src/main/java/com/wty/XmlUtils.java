package com.wty;

import com.wty.xml.ImpactRadiusResponse;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author peter
 * @date 2022/5/7 11:23
 */
public class XmlUtils {

    public static <T> T xmlToObject(Class<T> clazz, String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (T)jaxbUnmarshaller.unmarshal(new StringReader(xml));
    }

}
