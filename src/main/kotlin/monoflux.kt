import reactor.core.publisher.Mono
import java.util.*

fun main(args: Array<String>){
    var stringList: List<String> = listOf("1", "2", "3")
    var stringListMono = Mono.just(stringList)


//    var userList  = listOf(User("Hello",12), User("B",11))
    var userList  = listOf<User>()

    val testuserList = listOf(User("A",11   ), User("B",11), User("C", 13), User("D",14))
    stringListMono.
    filter{!it.isNullOrEmpty()}
        .map{println(stringList)}
//        .subscribe()

    println("git master는 진행된다 ")

    println("master는 계속 또 진행됨")


    val userNameList = userList.associateBy { it.name }


    val name = "Hello"
    println(name !in userNameList)
    testuserList
        .filter { it.name !in userNameList }
        .map {user -> println(user.name) }

    testuserList.groupBy { it.age }.map { println(it) }

}

data class User(
    val name: String,
    val age: Int
)