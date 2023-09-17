package org.pomodoro;

import org.pomodoro.console.render.ConsoleRenderer;
import org.pomodoro.core.PomodoroTimer;
import org.pomodoro.core.PomodoroTimerFactory;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConsoleRenderer renderer = new ConsoleRenderer();
        PomodoroTimerFactory factory = PomodoroTimerFactory.getInstance();

        renderer.clearConsole();
        renderer.printMenu();

        renderer.clearConsole();
        PomodoroTimer pomodoroTimer = factory.next();
        pomodoroTimer.start();
        while (!pomodoroTimer.isFinished()) {
            renderer.printProgressBar(pomodoroTimer);
            Thread.sleep(100);
        }
        renderer.printProgressBar(pomodoroTimer);
        renderer.printFinishMessage();

        renderer.printQuit();
    }
}
