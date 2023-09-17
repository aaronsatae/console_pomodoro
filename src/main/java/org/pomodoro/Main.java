package org.pomodoro;

import org.pomodoro.console.render.ConsoleRenderer;
import org.pomodoro.core.PomodoroTimer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConsoleRenderer renderer = new ConsoleRenderer();

        renderer.clearConsole();
        renderer.printMenu();

        renderer.clearConsole();
        PomodoroTimer pomodoroTimer = new PomodoroTimer(3);
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
