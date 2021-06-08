import reactor.core.publisher.Flux

fun main(args: Array<String>) {
    println("Hello World!")
    var flux = Flux.just("A")
    flux.map { s -> "Foo${s}"}.subscribe {s-> println(s) }
    flux.subscribe {s-> println(s) }
}