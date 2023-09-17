package org.pomodoro.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PomodoroTimerTest {
    @Test
    void startAndFinished() throws InterruptedException {
        PomodoroTimer pomodoroTimer = new PomodoroTimer(5, TimerType.WORK);
        pomodoroTimer.start();
        Assertions.assertFalse(pomodoroTimer.isFinished());

        Thread.sleep(5010);

        Assertions.assertTrue(pomodoroTimer.isFinished());
        Assertions.assertEquals(pomodoroTimer.getElapsedTimeInSecond(), 5);

        Thread.sleep(2000);
        Assertions.assertEquals(pomodoroTimer.getElapsedTimeInSecond(), 5);
    }

    @Test
    void startAndPause() throws InterruptedException {
        PomodoroTimer pomodoroTimer = new PomodoroTimer(5, TimerType.WORK);
        pomodoroTimer.start();

        Thread.sleep(3100);
        pomodoroTimer.pause();

        Assertions.assertFalse(pomodoroTimer.isFinished());
        Assertions.assertEquals(pomodoroTimer.getElapsedTimeInSecond(), 3);

        Thread.sleep(2000);
        Assertions.assertEquals(pomodoroTimer.getElapsedTimeInSecond(), 3);
    }

    @Test
    void startAndStop() throws InterruptedException {
        PomodoroTimer pomodoroTimer = new PomodoroTimer(5, TimerType.WORK);
        pomodoroTimer.start();

        Thread.sleep(3100);
        long stop = pomodoroTimer.stop();

        Assertions.assertTrue(pomodoroTimer.isFinished());
        Assertions.assertEquals(stop, 3);

        Thread.sleep(2000);
        Assertions.assertEquals(pomodoroTimer.getElapsedTimeInSecond(), 3);
    }
}
