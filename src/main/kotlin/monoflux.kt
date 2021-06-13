import reactor.core.publisher.Mono

fun main(args: Array<String>){
    var stringList: List<String> = listOf("1", "2", "3")
    var stringListMono = Mono.just(stringList)



    var userList  = listOf<User>()

    val testuserList = listOf(User("A",11   ), User("B",11), User("C", 13), User("D",14))
    stringListMono.
    filter{!it.isNullOrEmpty()}
        .map{println(stringList)}

    println("git master는 진행된다 ")
    println("master는 계속 또 진행됨")
    println("123123 from master")
    println("456456 from test")
    println("789789")
    println("1231231231231")
    println("newone")



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