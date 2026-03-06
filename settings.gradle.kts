pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ToDoReminder"
include(":app")
include(":core_domain")
include(":core_data")
include(":navigation")
include(":core_ui")
include(":feature:create-task")
include(":feature:edit-task")
include(":feature:detail")
include(":feature:list-tasks")
