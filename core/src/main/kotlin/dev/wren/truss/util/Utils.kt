package dev.wren.truss.util

import com.electronwill.nightconfig.core.Config
import com.electronwill.nightconfig.yaml.YamlFormat
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.InputStream
import java.util.concurrent.ThreadFactory

object Utils {
    fun newThreadFactory(name: String): ThreadFactory {
        val modClassLoader = Utils.javaClass.getClassLoader()

        return ThreadFactory { r: Runnable? ->
            val t = Thread(r, name)
            t.setContextClassLoader(modClassLoader)
            t
        }
    }

    fun fileStream(path: String): InputStream? = Utils.javaClass.getResourceAsStream(path)

    fun getConfig(path: String): Config {
        return fileStream(path).use { stream ->
            if (stream == null) {
                throw RuntimeException("File '$path' not found!")
            }

            YamlFormat.defaultInstance().createParser().parse(stream)
        }
    }
}

fun logger(name: String): Logger = LogManager.getLogger(name)

