package org.pomodoro.console.render;

import org.pomodoro.core.PomodoroTimer;

public class ConsoleRenderer {
    private static final int PROGRESS_BAR_LENGTH = 25;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String BLACK_BACKGROUND = "\u001B[40m";
    private static final String RED_BACKGROUND = "\u001B[41m";
    private static final String GREEN_BACKGROUND = "\u001B[42m";
    private static final String YELLOW_BACKGROUND = "\u001B[43m";
    private static final String BLUE_BACKGROUND = "\u001B[44m";
    private static final String PURPLE_BACKGROUND = "\u001B[45m";
    private static final String CYAN_BACKGROUND = "\u001B[46m";
    private static final String WHITE_BACKGROUND = "\u001B[47m";

    public void printProgressBar(final PomodoroTimer pomodoroTimer) {
        carriageReturn();

        final long remainTimeInSecond = pomodoroTimer.getRemainTimeInSecond();
        final String remainTime = String.format("%02d:%02d", remainTimeInSecond / 60, remainTimeInSecond % 60);

        final long durationInSecond = pomodoroTimer.getDurationInSecond();
        final int progressLength = (int) ((pomodoroTimer.getElapsedTimeInSecond() * PROGRESS_BAR_LENGTH) / durationInSecond);
        final String progressBar = "#".repeat(progressLength) + "-".repeat(PROGRESS_BAR_LENGTH - progressLength);

        System.out.printf("Pomodoro: [%s] %s", remainTime, progressBar);
    }

    public void printFinishMessage() {
        System.out.println();
        String background = "";
        for (int i = 0; i < 10; i++) {
            carriageReturn();
            System.out.print(background + "Finished!" + ANSI_RESET);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            background = background.isEmpty()
                    ? RED_BACKGROUND
                    : "";
        }
        System.out.println();
    }

    private static void carriageReturn() {
        System.out.print("\r");
    }
}
