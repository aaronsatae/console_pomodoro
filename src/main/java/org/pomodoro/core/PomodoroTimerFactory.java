package org.pomodoro.core;

public class PomodoroTimerFactory {
    private static PomodoroTimerFactory instance;

    private final int workDurationInSeconds = 5;
    private final int shortBreakDurationInSeconds = 2;
    private final int longBreakDurationInSeconds = 3;
    private final int longBreakCycle = 4;

    private int currentCycle = 0;
    private TimerType currentTimerType = TimerType.BREAK;

    public static PomodoroTimerFactory getInstance() {
        if (instance == null) {
            instance = new PomodoroTimerFactory();
        }
        return instance;
    }

    private PomodoroTimerFactory() {
    }

    public PomodoroTimer next() {
        return switch (this.currentTimerType) {
            case WORK -> {
                this.currentTimerType = TimerType.BREAK;
                if (this.currentCycle == this.longBreakCycle) {
                    this.currentCycle = 0;
                    yield longBreak();
                }
                yield shortBreak();
            }
            case BREAK -> {
                this.currentTimerType = TimerType.WORK;
                this.currentCycle += 1;
                yield work();
            }
        };
    }

    private PomodoroTimer work() {
        return new PomodoroTimer(workDurationInSeconds, TimerType.WORK);
    }

    private PomodoroTimer shortBreak() {
        return new PomodoroTimer(shortBreakDurationInSeconds, TimerType.BREAK);
    }

    private PomodoroTimer longBreak() {
        return new PomodoroTimer(longBreakDurationInSeconds, TimerType.BREAK);
    }
}
