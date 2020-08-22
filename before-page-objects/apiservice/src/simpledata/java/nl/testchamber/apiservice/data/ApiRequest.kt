package nl.testchamber.apiservice.data

import java.net.URI

/**
* Created by rutger on 08/02/2018.
 *
 * todo: Implement builder pattern
*/

/**
 * @param params The parameters of HTTP request
 * @param headers The headers of HTTP request
 * @param body The body of HTTP request
 */
class ApiRequest(val method: Method = Method.GET, val uri: URI, body: Any? = null, headers: Map<String, String>? = null, params: Map<String, String>? = null) {

    private var body: String? = null
    private var params: MutableMap<String, String> = HashMap()
    private var headers: MutableMap<String, String> = HashMap()

    init {
        // Defaults
        setHeader("Content-Type", "application/json")

        // Custom
        if (params != null) setParams(params)
        if (headers != null) setHeaders(headers)
    }

    fun getBody(): String? {
        return body
    }

    fun setParams(params: Map<String, String>): ApiRequest {
        this.params = Helpers.addToExistingMap(params, this.params)
        return this
    }

    fun setHeaders(headers: Map<String, String>): ApiRequest {
        this.headers = Helpers.addToExistingMap(headers, this.headers)
        return this
    }

    fun setHeader(key: String, value: String): ApiRequest {
        this.headers[key] = value
        return this
    }

    fun setParam(key: String, value: String): ApiRequest {
        this.params[key] = value
        return this
    }

    fun setStringAsBody(body: String): ApiRequest {
        this.body = body
        return this
    }

    fun getParams(): Map<String, String> {
        return params
    }

    fun getHeaders(): Map<String, String> {
        return headers
    }
}

enum class Method {
    GET,
    PUT,
    POST,
    DELETE,
    HEAD,
    TRACE,
    OPTIONS,
    PATCH
}
