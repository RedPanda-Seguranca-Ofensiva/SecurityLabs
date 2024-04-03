package net.redpandasec.labs.Classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Evil3 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String clName;
    private String mtName;
    private String parameters;

    public Evil3(String clName, String mtName, String parameters) {
        this.clName = clName;
        this.mtName = mtName;
        this.parameters = parameters;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        try {
            Class<?> cl = Class.forName(clName);
            Method mt = cl.getMethod(mtName, String.class);
            mt.invoke(cl, parameters);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
