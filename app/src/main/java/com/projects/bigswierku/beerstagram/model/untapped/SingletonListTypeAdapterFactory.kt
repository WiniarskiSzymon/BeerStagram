package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.Collections



class SingletonListTypeAdapterFactory : TypeAdapterFactory {
    override fun <T> create(gson: Gson, typeToken: TypeToken<T>): TypeAdapter<T>? {

        val type = typeToken.getType()
        if (typeToken.rawType != List::class.java || type !is ParameterizedType) {
            return null
        }
        val elementType = (type as ParameterizedType).getActualTypeArguments()[0]
        val elementAdapter = gson.getAdapter(TypeToken.get(elementType))
        val arrayAdapter = gson.getDelegateAdapter(this, typeToken)
        return newSingtonListAdapter(
            elementAdapter as TypeAdapter<Any>,
            arrayAdapter as TypeAdapter<List<Any>>
        ) as TypeAdapter<T>
    }

    private fun <E> newSingtonListAdapter(
        elementAdapter: TypeAdapter<E>,
        arrayAdapter: TypeAdapter<List<E>>
    ): TypeAdapter<List<E>> {
        return object : TypeAdapter<List<E>>() {

            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: List<E>?) {
                if (value == null || value.isEmpty()) {
                    out.nullValue()
                } else if (value.size == 1) {
                    elementAdapter.write(out, value[0])
                } else {
                    arrayAdapter.write(out, value)
                }
            }

            @Throws(IOException::class)
            override fun read(`in`: JsonReader): List<E> {
                if (`in`.peek() !== JsonToken.BEGIN_ARRAY) {
                    val obj = elementAdapter.read(`in`)
                    return Collections.singletonList(obj)
                }
                return arrayAdapter.read(`in`)
            }
        }
    }
}