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

    protected abstract void show();
}
