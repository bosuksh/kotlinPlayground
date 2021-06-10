import reactor.core.publisher.Flux
import kotlin.system.measureNanoTime
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {

    var map = mutableMapOf<Int,String>()
    for(i in 1..100000)
        map[i] = "${i}등"
    var stringList = mutableListOf<String>()
    val elapsed1 = measureTimedValue {
        map.asSequence()
            .filter { it.key % 100 == 0 }
            .map {stringList.add(it.value)}

    }
    println("map time: ${elapsed1.duration.inWholeMilliseconds}")


    println(stringList.size)




}