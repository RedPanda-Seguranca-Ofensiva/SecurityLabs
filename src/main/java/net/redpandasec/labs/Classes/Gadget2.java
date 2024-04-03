package net.redpandasec.labs.Classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Gadget2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String code;
    private Object obj;

    public Gadget2(int id, String code, Object obj) {
        this.id = id;
        this.code = code;
        this.obj = obj;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        System.out.println(obj.toString());
    }
}
