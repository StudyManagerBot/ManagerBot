plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "StudyManager"
include("domain")
include("application")
include("infrastructure")
