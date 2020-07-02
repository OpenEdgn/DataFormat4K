package com.github.open_edgn.data.format.old.prop

import com.github.open_edgn.data.format.old.factory.DataPropertiesFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class DataSerializableFactoryTest {
    @Test
    fun testAll() {
        val properties = DataPropertiesFactory.defaultFactory.createEmptyProperties()
        properties["String"] = "testString"
        properties["int"] = 1212L
        val outputStream = ByteArrayOutputStream()
        val writer = outputStream.bufferedWriter(Charsets.UTF_8)
        assertEquals(properties.exportData(writer),2)
        writer.flush()
        val bytes = outputStream.toByteArray()
        println(bytes.toString(Charsets.UTF_8))
        var reader = ByteArrayInputStream(bytes).bufferedReader(Charsets.UTF_8)
        val properties2 = DataPropertiesFactory.defaultFactory.createEmptyProperties()
        assertEquals(properties2.importData(reader,true),2)
        reader = ByteArrayInputStream(bytes).bufferedReader(Charsets.UTF_8)
        properties.importData(reader,true)
        assertEquals(properties.length,2)
    }


}