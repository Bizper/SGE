package lib.runo.objects

import lib.runo.objects.error.TypeErrorException
import lib.runo.parser.Node

class JSONString: JSONBase {

    private var str: String

    constructor(str: String) {
        this.str = str
    }

    constructor(n: Node): this(n.value)

    override fun toArray(): Array<JSONBase> {
        throw TypeErrorException("string can not cast to Array")
    }

    override fun toInt(): Int {
        throw TypeErrorException("string can not cast to Int")
    }

    override fun toDouble(): Double {
        throw TypeErrorException("string can not cast to Double")
    }

    override fun toString(): String {
        return str
    }

    override fun toBoolean(): Boolean {
        throw TypeErrorException("string can not cast to Boolean")
    }

    override fun toNull(): String {
        throw TypeErrorException("string can not cast to Null")
    }

    override fun forEach(action: (JSONBase) -> Unit) {
        action(JSONString(str))
    }

}