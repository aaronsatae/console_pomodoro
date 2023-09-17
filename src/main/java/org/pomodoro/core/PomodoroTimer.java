package org.pomodoro.core;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer {
    private final int durationInSecond;
    private final TimerType timerType;
    private final CountDownLatch latch;
    private final ScheduledExecutorService scheduledExecutorService;
    private final Runnable command;
    private volatile TimerStatus timerStatus;

    public PomodoroTimer(final int durationInSecond, final TimerType timerType) {
        this.durationInSecond = durationInSecond;
        this.timerType = timerType;

        this.timerStatus = TimerStatus.READY;
        this.latch = new CountDownLatch(this.durationInSecond);
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.command = () -> {
            if (this.timerStatus == TimerStatus.PAUSED
                    || this.timerStatus == TimerStatus.FINISHED) {
                this.scheduledExecutorService.shutdownNow();
                return;
            }
            this.latch.countDown();
            if (this.latch.getCount() == 0) {
                this.scheduledExecutorService.shutdownNow();
                this.timerStatus = TimerStatus.FINISHED;
            }
        };
    }

    public boolean isFinished() {
        return this.timerStatus == TimerStatus.FINISHED;
    }

    public long getDurationInSecond() {
        return this.durationInSecond;
    }

    public long getElapsedTimeInSecond() {
        return this.durationInSecond - this.latch.getCount();
    }

    public long getRemainTimeInSecond() {
        return this.latch.getCount();
    }

    public TimerType getTimerType() {
        return this.timerType;
    }

    /**
     * 타이머를 시작한다.
     */
    public void start() {
        this.scheduledExecutorService.scheduleAtFixedRate(
                command, 1, 1, TimeUnit.SECONDS
        );
    }

    /**
     * 타이머를 일시 정지 한다.
     * 다시 타이머를 실행하면, 기존 작업이 실행된다.
     */
    public void pause() {
        this.timerStatus = TimerStatus.PAUSED;
    }

    /**
     * 타이머를 정지한다.
     * 다시 타이머를 실행하면, 다음 작업이 실행된다.
     */
    public long stop() {
        this.timerStatus = TimerStatus.FINISHED;
        return this.getElapsedTimeInSecond();
    }
}
