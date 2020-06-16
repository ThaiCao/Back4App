package com.app.back4app.model.product

import com.parse.ParseObject
import com.parse.ParseQuery


class ProductRepository {
    fun getProducts(){
        val query = ParseQuery.getQuery<ParseObject>("Product")

        // The query will search for a ParseObject, given its objectId.
        // When the query finishes running, it will invoke the GetCallback
        // with either the object, or the exception thrown
        // The query will search for a ParseObject, given its objectId.
// When the query finishes running, it will invoke the GetCallback
// with either the object, or the exception thrown
        query.getInBackground(
            "<PARSE_OBJECT_ID>"
        ) { result, e ->
            if (e == null) {
                println(result)
            } else { // something went wrong
            }
        }
    }
}