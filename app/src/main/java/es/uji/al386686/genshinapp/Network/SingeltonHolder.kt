package es.uji.al386686.genshinapp.Network

open class SingletonHolder<out T, in A>(private val constructor: (A) -> T) {
    @Volatile
    private var instance: T? = null
    fun getInstance(arg: A): T =
        instance ?: synchronized(this) {
            instance ?: constructor(arg).also { instance = it}
        }
}