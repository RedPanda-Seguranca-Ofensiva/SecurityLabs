package net.redpandasec.labs.Classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Evil1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cmd;

    public Evil1(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        Runtime process = Runtime.getRuntime();
        process.exec(cmd);
    }
}
