import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toMono

fun main(args: Array<String>){
    val ids = listOf("101", "102", "105")

    val customerId = "101"


    Flux.just("Donald", "Duck", "Mickey", "Goofy",
        "Uncle")
        .doOnNext{println("${Thread.currentThread()}   Here ou will get the strings above:$it ")}
        .subscribeOn(Schedulers.newSingle("hello", true))
        .publishOn(Schedulers.newSingle("hi", true))
        .subscribe { println("${Thread.currentThread()}     Here you will get the numbers of how every string is long: $it") }
    var i = 1
    while ( i < 5) {
        println("currentThread : ${Thread.currentThread()}")
        Thread.sleep(2000)
        i++
    }
}