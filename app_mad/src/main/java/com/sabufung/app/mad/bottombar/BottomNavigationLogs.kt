package com.sabufung.app.mad.bottombar

object BottomNavigationLogs {
    const val BOTTOM_BAR_SECTION = "bottom_bar"

    // TODO spec is go_<laplaceScreenName> but not considered for automation yet
    // It might be possible to automatically log all navigation without having to define constants
    // or at least ensure consistency by using Route constants
    const val GO_HOME = "go_home"
    const val GO_HELP = "go_help"
}
