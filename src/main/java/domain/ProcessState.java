package domain;

public class ProcessState implements State {
    Order order;

    public ProcessState(Order order) {
        this.order = order;
    }

    @Override
    public void nextAction() {
        order.setState(order.getFinishState());

    }

    @Override
    public void backAction() {
        order.setState(order.getBeginState());
    }

    @Override
    public void refreshAction() {

    }

    @Override
    public void closeAction() {

    }

    @Override
    public String toString() {
        return "processing order";
    }
}
