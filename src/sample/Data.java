package sample;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    private List<Integer> value;

    public List<Integer> getListValue() {
        return value;
    }

    public void setListValue(List<Integer> listValue) {
        this.value = listValue;
    }

    public Data(List<Integer> listValue) {
        this.value = listValue;
    }

    public Data() { }
}
