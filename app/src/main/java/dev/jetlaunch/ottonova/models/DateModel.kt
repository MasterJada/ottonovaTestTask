package dev.jetlaunch.ottonova.models

data class DateModel(private val date: String): ITimeLineRecyclerItem {
    override fun getText(): String {
        return date
    }
}