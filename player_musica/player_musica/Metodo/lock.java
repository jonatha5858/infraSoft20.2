package Metodo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lock {
    private final Lock acessLock = new ReentrantLock();
    private final Condition condition = acessLock.newCondition();
    private boolean ocupied = false;

    public boolean isOcupied() {
        return ocupied;
    }

    public void setOcupied(boolean ocupied) {
        this.ocupied = ocupied;
    }

    public Condition getCondition() {
        return condition;
    }
    public Lock getAcessLock() {
        return acessLock;
    }
}