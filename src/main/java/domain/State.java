package domain;

public interface State {
    public void nextAction();
    public void backAction();
    public void refreshAction();
    public void closeAction();

}
