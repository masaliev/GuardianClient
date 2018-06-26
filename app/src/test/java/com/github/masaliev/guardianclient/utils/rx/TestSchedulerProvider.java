package com.github.masaliev.guardianclient.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

public class TestSchedulerProvider implements SchedulerProvider {

    private TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler) {
        mTestScheduler = testScheduler;
    }

    @Override
    public Scheduler ui() {
        return mTestScheduler;
    }

    @Override
    public Scheduler computation() {
        return mTestScheduler;
    }

    @Override
    public Scheduler io() {
        return mTestScheduler;
    }
}
