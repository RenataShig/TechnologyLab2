package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class FileReaderWriter {
    private static final String FILE_NAME = "Data.xml";

    public static void write(Data data) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(FILE_NAME);
            jaxbMarshaller.marshal(data, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Data read() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Data result = (Data)jaxbUnmarshaller.unmarshal(new File(FILE_NAME));
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
