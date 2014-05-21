package domain;

public class Order {
    State beginState;
    State processState;
    State finishState;

    State state = beginState;

    public Order() {
        beginState = new BeginState(this);
        processState = new ProcessState(this);
        finishState = new FinishState(this);

    }

    public void next() {
        state.nextAction();
    }

    public void back() {
        state.backAction();
    }

    public void refresh() {
        state.refreshAction();
    }

    public void close() {
        state.closeAction();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getBeginState() {
        return beginState;
    }

    public State getProcessState() {
        return processState;
    }

    public State getFinishState() {
        return finishState;
    }

    public String toString() {
        return("Order is " + state + "\n");
    }
}
