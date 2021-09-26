package io.milkcan.weatherapp.data.cache

import java.time.LocalDate

class CacheObject<T> {
    var cacheObject: T? = null
    var timestamp: LocalDate? = LocalDate.now()
}