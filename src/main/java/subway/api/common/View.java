package subway.api.common;

public abstract class View {
    protected boolean showing;

    protected View() {
        this.showing = true;
    }

    public void quit() {
        this.showing = false;
    }

    protected void startShowing() {
        this.showing = true;
    }

    public void run() {
        startShowing();

        while (showing) {
            show();
        }
    }

    protected String inputSelect() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return InputView.string();
    }

    protected abstract void show();
}
