package domain;

public class FinishState implements State {
    Order order;

    public FinishState(Order order) {
        this.order = order;
    }

    @Override
    public void nextAction() {

    }

    @Override
    public void backAction() {
        order.setState(order.getProcessState());
    }

    @Override
    public void refreshAction() {

    }

    @Override
    public void closeAction() {

    }

    @Override
    public String toString() {
        return "finishing order";
    }
}
