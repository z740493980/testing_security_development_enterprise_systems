package org.tsdes.spring.amqp.fanout

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by arcuri82 on 07-Aug-17.
 */
class WorkReceiver(val id: String) {

    @Autowired
    private lateinit var counter: Counter

    @RabbitListener(queues = arrayOf("#{queueNameHolder.name}"))
    fun receive(x: java.lang.Long) {
        doWork(x)
    }

    private fun doWork(x: java.lang.Long){

        println("Worker '$id' going to do work with value: $x")

        Thread.sleep(x.toLong())

        counter.doneJob(id, x.toInt())
    }
}