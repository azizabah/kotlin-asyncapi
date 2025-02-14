package com.asyncapi.kotlinasyncapi.context.annotation.processor

import org.junit.jupiter.api.Test
import com.asyncapi.kotlinasyncapi.annotation.Schema
import com.asyncapi.kotlinasyncapi.context.TestUtils.assertJsonEquals
import com.asyncapi.kotlinasyncapi.context.TestUtils.json
import kotlin.reflect.full.findAnnotation

internal class SchemaProcessorTest {

    private val processor = SchemaProcessor()

    @Test
    fun `should process schema annotation`() {
        val payload = TestSchema::class
        val annotation = payload.findAnnotation<Schema>()!!

        val expected = json("annotation/schema_component.json")
        val actual = json(processor.process(annotation, payload))

        assertJsonEquals(expected, actual)
    }

    @Schema
    data class TestSchema(
        val id: Int = 0,
        val name: String,
        val isTest: Boolean,
        val sub: TestSubSchema?
    )

    data class TestSubSchema(
        val exists: Boolean
    )
}
