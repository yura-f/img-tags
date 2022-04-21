package ru.z13.imgtags.mvp.utils

import io.reactivex.Scheduler

/**
 * @author Yura F (yura-f.github.io)
 */
interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}