package dev.jetlaunch.ottonova.models

data class HealthPrompt(
    val uuid: String,
    val message: String,
    val display_category: String,
    val permanent: Boolean,
    val metadata: Metadata?,
    val style: Style?
)

data class Style(
    val primary_color: String,
    val secondary_color: String,
    val image: String
)
data class Metadata( val link: Link)

data class Link(
    val title: String,
    val url: String
)