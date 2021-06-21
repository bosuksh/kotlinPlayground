import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toMono


fun main(args: Array<String>) {
    val stringList = listOf("1", "2", "3")

    val publishScheduler = Schedulers.newParallel("publishScheduler", )
    val subscribeScheduler = Schedulers.newParallel("subscribeScheduler")
//    Flux.range(1,3)
//        .map{it + 10}
//        .publishOn(scheduler)
//        .map { "value $it" }
//        .log()
//        .subscribe{println(it)}

    // publishOn으로 설정한 Thread이후 연산은 publishOn에서 설정한 쓰레드에서 처리한다.
    Flux.range(1,2)
        .map {
            println("First map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .publishOn(publishScheduler)
        .map {
            println("Second map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .doOnComplete{println("------------------------------------------------------------------------------------")}
        .subscribe()


    // subscribeOn으로 설정한 쓰레드로 모두 처리한다.
    Flux.range(1,2)
        .map {
            println("First map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(subscribeScheduler)
        .map {
            println("Second map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .doOnComplete{println("------------------------------------------------------------------------------------")}
        .subscribe()


    //publishOn이 여러 개일 경우 publishOn이 발생한 이후 쓰레드로 변환된다. (계속 변환)
    Flux.range(1,2)
        .map {
            println("First map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .publishOn(publishScheduler)
        .map {
            println("Second map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .publishOn(publishScheduler)
        .map {
            println("Third map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .doOnComplete{println("------------------------------------------------------------------------------------")}
        .subscribe()


    // subscribeOn이 여러 개일 경우에는 첫번째 설정한 subscribeOn Thread로 실행 된다.
    Flux.range(1,2)
        .map {
            println("First map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(subscribeScheduler)
        .map {
            println("Second map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(subscribeScheduler)
        .map {
            println("Third map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .doOnComplete{println("------------------------------------------------------------------------------------")}
        .subscribe()


    // subscribeOn과 publishOn이 같이 있을 경우 subscribeOn으로 설정된 Thread로 실행이 되다가 publishOn 이후 설정된 Thread로 변환된다.

    Flux.range(1,2)
        .map {
            println("First map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .publishOn(publishScheduler)
        .map {
            println("Second map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(subscribeScheduler)
        .map {
            println("Third map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .doOnComplete{println("------------------------------------------------------------------------------------")}
        .subscribe()


    Flux.range(1,2)
        .map {
            println("First map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(subscribeScheduler)
        .map {
            println("Second map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .publishOn(publishScheduler)
        .map {
            println("Third map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .doOnComplete{println("------------------------------------------------------------------------------------")}
        .subscribe()


    // Nested subscribeOn은 내부에서 설정한 subscribeOn Thread는 내부에서만 돌고, 외부에서 subscribeOn으로 실행한 thread는 외부에서만 돈다.
    Flux.range(1,2)
        .map {
            println("First map $it  Current Thead = ${Thread.currentThread().name}")
            it
        }
        .subscribeOn(subscribeScheduler)
        .map {
            println("Second map $it  Current Thead = ${Thread.currentThread().name}")

            Flux.range(1,2)
                .map {num ->
                    println("First map ($it.$num)  Current Thead = ${Thread.currentThread().name}")
                    num
                }
                .subscribeOn(publishScheduler)
                .map { num ->
                    println("Second map ($it.$num)  Current Thead = ${Thread.currentThread().name}")
                    num
                }
                .subscribe()
        }
        .subscribe()


}