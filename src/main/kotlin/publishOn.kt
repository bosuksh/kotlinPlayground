import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

fun main() {

    Flux.range(1,5)
        .doOnNext{consumer(it)}
        .map{
            println("Inside map the thread is ${Thread.currentThread().name}")
            it * 10
        }
        .publishOn(Schedulers.boundedElastic())
        .doOnNext{consumer(it)}
        .subscribeOn(Schedulers.newParallel("subscribeOn_thread"))
        .doOnNext{consumer(it)}
        .subscribeOn(Schedulers.newParallel("subscribeOn_thread"))
        .subscribe()
}

fun <T> consumer(value : T): Unit = println("${value}: ${Thread.currentThread().name}")