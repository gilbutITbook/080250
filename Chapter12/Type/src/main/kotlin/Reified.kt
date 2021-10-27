inline fun <reified T : Any> Any.cast(): T? = this as? T
inline fun normalInlineFunction(x:String): String = "${x}${x}${x}"