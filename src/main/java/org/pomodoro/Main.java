package org.pomodoro;

import org.pomodoro.console.render.ConsoleRenderer;
import org.pomodoro.core.PomodoroTimer;
import org.pomodoro.core.PomodoroTimerFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConsoleRenderer renderer = new ConsoleRenderer();
        Scanner scanner = new Scanner(System.in);
        PomodoroTimerFactory factory = PomodoroTimerFactory.getInstance();

        while (true) {
            renderer.clearConsole();
            renderer.printMenu();

            String command = scanner.next();
            if (command.equals("2")) {
                break;
            }
            if (!command.equals("1")) {
                continue;
            }

            renderer.clearConsole();
            PomodoroTimer pomodoroTimer = factory.next();
            pomodoroTimer.start();
            while (!pomodoroTimer.isFinished()) {
                renderer.printProgressBar(pomodoroTimer);
                Thread.sleep(100);
            }
            renderer.printProgressBar(pomodoroTimer);
            renderer.printFinishMessage();
        }

        renderer.printQuit();
    }
}
