package domain;

public class BeginState implements State {
    Order order;

    public BeginState(Order order) {
        this.order = order;
    }

    @Override
    public void nextAction() {
        order.setState(order.getProcessState());
    }

    @Override
    public void backAction() {

    }

    @Override
    public void refreshAction() {

    }

    @Override
    public void closeAction() {

    }

    @Override
    public String toString() {
        return "beginning order";
    }
}
