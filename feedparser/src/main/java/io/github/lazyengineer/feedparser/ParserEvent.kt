package io.github.lazyengineer.feedparser

sealed class ParserEvent

object ValueEvent : ParserEvent()
object ContainerEvent : ParserEvent()
object AttributeEvent : ParserEvent()
object UnsupportedEvent : ParserEvent()
