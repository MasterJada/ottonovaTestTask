package dev.jetlaunch.ottonova.models

data class TimelineModel(
    val uuid: String,
    val timestamp: String,
    val display_category: String,
    val title: String,
    val description: String,
    val category: String
): ITimeLineRecyclerItem{
    override fun getText(): String {
        return title
    }
}


