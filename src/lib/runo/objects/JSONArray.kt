package lib.runo.objects

import lib.runo.objects.error.TypeErrorException
import lib.runo.parser.Node
import lib.runo.parser.Type.*

class JSONArray: JSONBase {

    var name: String
    var list = ArrayList<JSONBase>()

    constructor(n: Node) {
        if(n.type != ARRAY) throw TypeErrorException("non-array node can not be casted to JSONArray")
        else {
            this.name = n.value
            n.children.forEach {
                list.add(when(it.type) {
                    ARRAY -> JSONArray(it)
                    NUMBER -> JSONNumber(it)
                    STRING -> JSONString(it)
                    BOOLEAN -> JSONBoolean(it)
                    NULL -> JSONNull()
                    OBJECT -> JSONObject(it)
                })
            }
        }
    }

    override fun toInt(): Int {
        throw TypeErrorException("array can not cast to int")
    }

    override fun toDouble(): Double {
        throw TypeErrorException("array can not cast to double")
    }

    override fun toString(): String {
        val sb = StringBuffer()
        list.forEach {
            sb.append(it).append(" ")
        }
        sb.deleteCharAt(sb.length - 1)
        sb.appendln()
        return sb.toString()
    }

    override fun toBoolean(): Boolean {
        throw TypeErrorException("array can not cast to Boolean")
    }

    override fun toNull(): String {
        throw TypeErrorException("array can not cast to null")
    }

    override fun toArray(): Array<JSONBase> {
        return list.toTypedArray()
    }

    override fun forEach(action:(JSONBase) -> Unit) {
        list.forEach(action)
    }

}