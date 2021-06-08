import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.IllegalStateException
import java.time.Duration
import java.util.*

fun main(args: Array<String>) {
//    Flux.fromIterable(listOf("foo", "bar"))
//        .doOnNext{println(it)}
//        .flatMap { it.plus(it) }
//        .subscribe{println(it)}
//    println("이거 먼저?")

    Flux.error<IllegalStateException>(IllegalStateException())
        .doOnError{println(it)}
        .subscribe()

    Flux.interval(Duration.ofMillis(100))
        .take(10)
        .subscribe{println(it)}

    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")

    println(colors.zip(animals) { color, animal -> "The ${animal.replaceFirstChar { it.uppercase() }} is $color"})

    Thread.sleep(2000)

    Mono.just(1) 
        .map { "foo${it}" }
        .or(Mono.just("bar"))
        .subscribe{println(it)}
}