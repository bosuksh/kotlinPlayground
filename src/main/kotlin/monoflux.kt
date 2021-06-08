import reactor.core.publisher.Mono
import java.util.*

fun main(args: Array<String>){
    var stringList: List<String> = Arrays.asList("1", "2", "3")
    var stringListMono = Mono.just(stringList)

    stringListMono.flatMap { it.isEmpty().let{
        it
        println(it)
    } }
}